package br.com.fatec.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.fatec.control.GsonHandler;

public class HistoryTree {
	private HashMap<Integer, HistoryNode> historyTree = new HashMap<Integer, HistoryNode>();

	public HashMap<Integer, HistoryNode> getHistoryTree() {
		return historyTree;
	}

	public void setHistoryTree(HashMap<Integer, HistoryNode> historyTree) {
		this.historyTree = historyTree;
	}

	public HistoryTree(String name) {
		GsonHandler gh = new GsonHandler();
		
		try {
			//criar hashmap de history tree
			ArrayList<HistoryNode> historyArrayList = gh.getJSONResourceAsArrayList(name);
			for(HistoryNode hn : historyArrayList) {
				historyTree.put(hn.getNodeID(), hn);
			}
		} catch (IOException e) {
			System.out.print("Failed to create History Tree. Exception: ");
			System.err.println(e);
		} catch (NullPointerException e){
			System.out.print("Failed to create History Tree. Exception: ");
			System.err.println(e);
			
		} finally {
			System.out.println("Failed to create History Tree. Unknown exception.");
		}
		
		
	}
	
	
}
