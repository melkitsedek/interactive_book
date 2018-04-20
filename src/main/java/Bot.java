import java.util.List;
import java.util.HashMap;

import javax.swing.text.AbstractDocument.Content;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;


public class Bot {

	public static void main(String[] args) throws InterruptedException {
		
		
		History history = new History();
		HashMap<Integer, HistoryNode> historyMap = new HashMap<Integer, HistoryNode>();
		
		
		historyMap = history.arrayToMap();
		
		Integer nodeID = 0;

		// Criacao do objeto bot com as informacoes de acesso
		TelegramBot bot = TelegramBotAdapter.build("507531907:AAHUyqxiTQObHFM5Q4V0jhFPknwuvJ6KlHs");

		// objeto responsavel por receber as mensagens
		GetUpdatesResponse updatesResponse;
		// objeto responsavel por gerenciar o envio de respostas
		SendResponse sendResponse;
		// objeto responsavel por gerenciar o envio de a��es do chat
		BaseResponse baseResponse;

		// controle de off-set. A partir deste ID serao lidas as mensagens
		// pendentes na fila
		int m = 0;

		// loop infinito pode ser alterado por algum timer de intervalo curto
		while (true) {

			// executa comando no Telegram para obter as mensagens pendentes a partir de um
			// off-set (limite inicial)
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			// lista de mensagens
			List<Update> updates = updatesResponse.updates();
			
			

			// analise de cada acao da mensagem
			for (Update update : updates) {

				// atualizacao do off-set
				m = update.updateId() + 1;

				if (update.callbackQuery() != null) {
					
					sendResponse = bot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
							update.callbackQuery().data()));
				} else {
					System.out.println("Recebendo mensagem:" + update.message().text());

					// envio de "Escrevendo" antes de enviar a resposta
					baseResponse = bot
							.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
					// verificacao de acao de chat foi enviada com sucesso
					System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());
					
					System.out.println("Node ID at this point: " + nodeID);
					HistoryNode responseNode = historyMap.get(nodeID);

					if (update.message().text() != null) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
								responseNode.getText()));

						for(HistoryOption ho : responseNode.getOptions()) {
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
									ho.getText()));
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
									ho.getTag()));
						}
						
						optionTags: for(HistoryOption ho : responseNode.getOptions()) {
							if(update.message().text().equals(ho.getTag())) {
								nodeID = ho.getDestinationNodeID();
								System.out.println(nodeID + " " + ho.getDestinationNodeID() + " " + ho.getTag());
								break optionTags;
							}
						}
						
						
						// verificacao de mensagem enviada com sucesso
						System.out.println("Mensagem Enviada?" + sendResponse.isOk());

					}

				}

			}

		}

	}

}
