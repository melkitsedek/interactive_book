package br.com.fatec.view;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.fatec.control.AnswerHandler;
import br.com.fatec.model.HistoryNode;
import br.com.fatec.model.HistoryOption;
import br.com.fatec.model.HistoryTree;



public class TelegramBotController {
	public static void main(String[] args) throws InterruptedException {
		String historyTreeLocation = "src/main/config/psychology.json",
				botToken = "615073347:AAE9xHJABfoE6T8cxlXP9cSLWqUh3G79CX8"; //to insert
		
		HistoryTree historyTree = new HistoryTree(historyTreeLocation);
		TelegramBot bot = TelegramBotAdapter.build(botToken);
		HistoryNode currentNode = historyTree.getHistoryTree().get(0);

		GetUpdatesResponse updatesResponse;
		SendResponse sendResponse;
		BaseResponse baseResponse;

		
		AnswerHandler answerHandler = new AnswerHandler();
		Long chatID = null;
		int offsetControl = 0;
		
		while(true) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(offsetControl));
			List<Update> updates = updatesResponse.updates();
			
			for(Update update : updates) {
				offsetControl = update.updateId() + 1;
				
				if (chatID == null) 
					chatID = update.message().chat().id();
				
				if(update.callbackQuery() != null)
					sendResponse = bot.execute(new SendMessage(update.callbackQuery().message().chat().id(), update.callbackQuery().data()));
				else {
					
					
					if(update.message().text() != null) {
						baseResponse = bot.execute(new SendChatAction(chatID, ChatAction.typing.name()));
						currentNode = 
								historyTree.getHistoryTree().get(
									answerHandler.handleAnswer(update.message().text(), currentNode));
						sendResponse = bot.execute(new SendMessage(chatID, currentNode.getText()));
						for(HistoryOption ho : currentNode.getOptions()){
							sendResponse = bot.execute(new SendMessage(chatID, (ho.getTag() + ": " + ho.getText())));
						}
					}
				}
			}
		}
	}
}
