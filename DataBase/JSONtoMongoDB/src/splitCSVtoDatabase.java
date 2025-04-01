import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
public class splitCSVtoDatabase {
    public void sendSplits(ArrayList<ArrayList<String>> rawSplitsData){
        ArrayList<String> timestamps = rawSplitsData.getFirst();
        ArrayList<String> positionLongs = rawSplitsData.get(1);
        ArrayList<String> positionLats = rawSplitsData.get(2);
        ArrayList<String> distances = rawSplitsData.get(3);
        ArrayList<String> altitudes = rawSplitsData.get(4);
        ArrayList<String> speeds = rawSplitsData.get(5);
        ArrayList<String> cadences = rawSplitsData.get(6);
        String jsonTemplate;
        MongoDBConnector md = new MongoDBConnector("Writer","Writer");
        for (int i = 0 ; i < timestamps.size() ; i ++) {
            JSONObject json = new JSONObject();
            json.put("timestamp", timestamps.get(i));
            json.put("positionLong", positionLongs.get(i));
            json.put("positionLat", positionLats.get(i));
            json.put("distance", distances.get(i));
            json.put("altitude", altitudes.get(i));
            json.put("speed", speeds.get(i));
            json.put("cadence", cadences.get(i));

            md.insertJSON(json);
        }
    }
}
