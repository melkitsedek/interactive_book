package br.com.fatec.control;

import java.util.HashMap;
import br.com.fatec.model.HistoryNode;
import br.com.fatec.model.HistoryOption;;

public class AnswerHandler {
	private int previousID, nextID;
	
	public int handleAnswer(String text, HistoryNode hn) {
		System.out.println("entering node: " + hn.getNodeID());
		System.out.println("input tag: " + text);
		previousID = hn.getNodeID();
		
		if(text.charAt(0) != '/') {
			nextID = -2;
			return nextID;
		}
		else {
			for(HistoryOption ho : hn.getOptions()){
				System.out.println("i'm entering on for each loop? (answer handler)");
				System.out.println("what tag? " + ho.getTag());
				if(text.equals(ho.getTag()) ){
					nextID = ho.getDestinationNodeID();
					System.out.println("next id changed.");
					return nextID;
					
				}
			}
		}
		System.out.println("returned previous...");
		return previousID;
	}
}
