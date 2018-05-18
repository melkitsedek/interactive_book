package br.com.fatec.control;

import java.util.HashMap;
import br.com.fatec.model.HistoryNode;
import br.com.fatec.model.HistoryOption;;

public class AnswerHandler {
	private HashMap<Integer, HistoryNode> historyTree;
	private String previousMessage;
	private int currentID;
	
	public AnswerHandler(HashMap<Integer, HistoryNode> historyTree) {
		this.historyTree = historyTree;
	}
	
	public String handleAnswerAsString(String text) {
		if(text.charAt(0) != '/') {
			previousMessage = text;
			return "Not recognized for now.\n" + this.handleAnswerAsString("/start");
		}
		else {
			if(text == null) {
				currentID = 0;
				return "/start";
			}
			if(text == "/start" || currentID >= 0) {
				
			}
			for (HistoryOption ho : historyTree.get(currentID).getOptions()) {
				if(text == ho.getTag()) {
					currentID = ho.getDestinationNodeID();
					return nodeFormat(historyTree.get(currentID));
				}
			}
			
			return text;
		}
	}
	
	public String nodeFormat(HistoryNode hn) {
		String formatted = hn.getText();
		for (HistoryOption ho : hn.getOptions()) {
			formatted += ho.getTag() + " : " + ho.getText();
			formatted += "\n";
		}
		
		return formatted;
	}
}
