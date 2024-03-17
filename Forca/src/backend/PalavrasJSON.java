package backend;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PalavrasJSON {
	
	private JSONObject jsonObject;
	
	private int sortear(int min, int max) {
		return (int) (min + Math.round(Math.random() * max - min));
	}
	
	public PalavrasJSON(String fileName) {
		
		File file = new File(fileName); 
		JSONParser json = new JSONParser();
		try {
			
			FileReader fr = new FileReader(file);
			jsonObject = (JSONObject) json.parse(fr);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getPalavra(String tema) {
		JSONArray jsonArray = (JSONArray) jsonObject.get(tema);
		String palavra = (String) jsonArray.get(sortear(0, jsonArray.size() - 1));
		return palavra;
	}
	
	public String getTema(int index) {
		return getTemas()[index];
	}
	
	public String[] getTemas() {
		Object[] objArray = jsonObject.keySet().toArray();
		String[] strArray = new String[objArray.length];
		for (int i = 0; i < strArray.length; i++) {
			strArray[i] = (String) objArray[i];
		}
		return strArray;
	}
}
