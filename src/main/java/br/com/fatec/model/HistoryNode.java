package br.com.fatec.model;

import java.util.ArrayList;

public class HistoryNode {
	private Integer nodeID;
	private String text;
	private ArrayList<HistoryOption> options;
	
	
	
	public HistoryNode() {
		options = new ArrayList<HistoryOption>();
	}
	
	public HistoryNode(String text) {
		this.text = text;
		options = new ArrayList<HistoryOption>();
	}

	public Integer getNodeID() {
		return nodeID;
	}

	public void setNodeID(Integer nodeID) {
		this.nodeID = nodeID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<HistoryOption> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<HistoryOption> options) {
		this.options = options;
	}
}
