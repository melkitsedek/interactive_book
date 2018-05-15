package br.com.fatec.control;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.com.fatec.model.HistoryNode;


public class GsonHandler {
	
	public ArrayList<HistoryNode> getJSONResourceAsArrayList(String name) throws FileNotFoundException, IOException{
		ArrayList<HistoryNode> history = new ArrayList<HistoryNode>();
		try (Reader reader = new InputStreamReader(GsonHandler.class.getResourceAsStream(name), "UTF-8")){
			Gson gson = new GsonBuilder().create();
			TypeToken<ArrayList<HistoryNode>> token = new TypeToken<ArrayList<HistoryNode>>(){};
			history = gson.fromJson(reader, token.getType());

		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			System.out.println("Unknown error on GsonHandler.");
		}
		
		return history;
		
	}
}
