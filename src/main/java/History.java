import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;



public class History {
	
	ArrayList<HistoryNode> history;
	
	public History () {
		try (Reader reader = new InputStreamReader(History.class.getResourceAsStream("resources/history.json"), "UTF-8")){
			Gson gson = new GsonBuilder().create();
			
			TypeToken<ArrayList<HistoryNode>> token = new TypeToken<ArrayList<HistoryNode>>(){};
			history = gson.fromJson(reader, token.getType());
			
			/*
			for(HistoryNode hn : history) {
				System.out.println(hn.getText());
			}*/
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public ArrayList<HistoryNode> returnHistory() throws IOException {
		return history;
	}
	
	public HashMap<Integer, HistoryNode> arrayToMap() {
		HashMap<Integer, HistoryNode> historyMap = new HashMap<Integer, HistoryNode>();
		for(HistoryNode hs : history) {
			historyMap.put(hs.getNodeID(), hs);
		}
		
		return historyMap;
	}
	
	
}
