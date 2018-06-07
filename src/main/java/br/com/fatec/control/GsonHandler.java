package br.com.fatec.control;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.com.fatec.model.HistoryNode;


public class GsonHandler {
	
		//obtem recurso externo (no caso, arquivo .json)
	/*
	public ArrayList<HistoryNode> getJSONResourceAsArrayList(String name) throws FileNotFoundException, IOException{
		ArrayList<HistoryNode> history = new ArrayList<HistoryNode>();
		Reader reader = new InputStreamReader(GsonHandler.class.getResourceAsStream(name), "UTF-8");
		Gson gson = new GsonBuilder().create();
		TypeToken<ArrayList<HistoryNode>> token = new TypeToken<ArrayList<HistoryNode>>(){};
		history = gson.fromJson(reader, token.getType());

		return history;
		
	}
	*/
	
	public ArrayList<HistoryNode> getJSONResourceAsArrayList(String name) throws NullPointerException, IOException {
		ArrayList<HistoryNode> history;
		TypeToken<ArrayList<HistoryNode>> arrayNodeType = new TypeToken<ArrayList<HistoryNode>>(){};
		Gson gson = new GsonBuilder().create();
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(name));
			//Reader reader = new InputStreamReader(History.class.getResourceAsStream("resources/history.json"), "UTF-8");

			history = gson.fromJson(bufferedReader, arrayNodeType.getType());
			System.out.println("Gson Handler success. Node ID proof: ");
			System.out.println(history.get(0).getNodeID());
			return history;
			
		} catch (FileNotFoundException e) {
			System.out.println("JsonHandlerError");
			System.err.println(e);
		}
		System.out.println("Gson handler failed.");
		return null;
	}
}	
