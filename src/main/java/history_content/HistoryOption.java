package history_content;

import java.util.List;
import java.util.ArrayList;


public class HistoryOption {
	private String text;
	private Integer destinationNodeID;
	
	public HistoryOption() {
		
	}
	
	public HistoryOption(String text, Integer destinationNode) {
		this.text = text;
		this.destinationNodeID = destinationNode;
	}
}
