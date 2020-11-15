package mas.ssatr.Vancea.Felician;

import java.net.URL;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IncarcareRetea {
	
	private static Gson gson=new Gson();
    private static ModelReteaPetri retea;
    private static Reader reader; 
    
    public ModelReteaPetri incarcareReteaPetri() {
        try {
        	URL url = IncarcareRetea.class.getResource("Input");
        	System.out.println("Value URL = " + url);
        	reader = new FileReader(url.getPath());
        } catch (FileNotFoundException e) {
            System.out.println("File does not found!");
        }
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(reader);
        retea = gson.fromJson(jsonObject, ModelReteaPetri.class);
      return retea;
    }
}
