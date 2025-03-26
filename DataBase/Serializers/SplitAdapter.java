import com.google.gson.*;
import java.lang.reflect.Type;


public class SplitAdapter implements JsonDeserializer<Split>, JsonSerializer<Split> {

    private final String TIMESTAMP = "timestamp";
    private final String POSITION_LONG = "positionLong";
    private final String POSITION_LAT = "positionLat";
    private final String DISTANCE = "distance";
    private final String ALTITUDE = "altitude";
    private final String SPEED = "speed";
    private final String CADENCE = "cadence";
    private final String HEART_RATE= "heartRate";


    @Override
    public Split deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null) {
            return null;
        }

        if (!json.isJsonObject()) {
            return null;
        }

        JsonObject object = (JsonObject) json;

        int timeStamp = object.get(TIMESTAMP).getAsInt();
        long positionLong = object.get(POSITION_LONG).getAsLong();
        long positionLat = object.get(POSITION_LAT).getAsLong();
        long distance = object.get(DISTANCE).getAsLong();
        long altitude = object.get(ALTITUDE).getAsLong();
        long speed = object.get(SPEED).getAsLong();
        int cadence = object.get(CADENCE).getAsInt();
        int heartRate = object.get(HEART_RATE).getAsInt();

        return new Split(timeStamp, positionLong, positionLat, distance, altitude, speed, cadence, heartRate);
    }

    @Override
    public JsonElement serialize(Split split, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty(TIMESTAMP, split.getTimeStamp());
        object.addProperty(POSITION_LONG, split.getPositionLong());
        object.addProperty(POSITION_LAT, split.getPositionLat());
        object.addProperty(DISTANCE, split.getDistance());
        object.addProperty(ALTITUDE, split.getAltitude());
        object.addProperty(SPEED, split.getSpeed());
        object.addProperty(CADENCE, split.getCadence());
        object.addProperty(HEART_RATE, split.getHeartRate());

        return object;
    }
}
