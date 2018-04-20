import java.util.List;
import java.util.ArrayList;


public class HistoryOption {
	private String tag;
	private String text;
	private Integer destinationNodeID;
	
	public HistoryOption() {
		
	}
	
	public HistoryOption(String text, Integer destinationNode) {
		this.text = text;
		this.destinationNodeID = destinationNode;
	}
	
	

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getDestinationNodeID() {
		return destinationNodeID;
	}

	public void setDestinationNodeID(Integer destinationNodeID) {
		this.destinationNodeID = destinationNodeID;
	}
	
	
}
