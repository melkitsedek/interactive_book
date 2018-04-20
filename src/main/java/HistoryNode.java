import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=wnhvu99A1tg
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
