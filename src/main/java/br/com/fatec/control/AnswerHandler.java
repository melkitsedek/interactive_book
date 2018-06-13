package br.com.fatec.control;

import java.util.HashMap;
import br.com.fatec.model.HistoryNode;
import br.com.fatec.model.HistoryOption;;

public class AnswerHandler {
	private HashMap<Integer, HistoryNode> historyTree;
	private int previousID;
	
	public int handleAnswer(String text, HistoryNode hn) {
		previousID = hn.getNodeID();
		
		if(text.charAt(0) != '/') {
			return -2;
		}

		for(HistoryOption ho : hn.getOptions()){
			if(text == ho.getTag()){
				return ho.getDestinationNodeID();
			}
		}

		return -1;
	}
}
