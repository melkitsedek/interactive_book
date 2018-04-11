package history_content;

import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=wnhvu99A1tg
public class HistoryNode {
	private Integer nodeID;
	private String text;
	private List<String> options;
	
	public HistoryNode() {
		options = new ArrayList<String>();
	}
	
	public HistoryNode(String text) {
		this.text = text;
		options = new ArrayList<String>();
	}
	
}
