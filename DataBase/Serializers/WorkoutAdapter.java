package Serializers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class WorkoutAdapter implements JsonDeserializer<Workout>, JsonSerializer<Workout> {

    private final String ID = "id";
    private final String DATE = "date";
    private final String SPLITS = "splits";


    @Override
    public Workout deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null) {
            return null;
        }

        if (!json.isJsonObject()) {
            return null;
        }

        JsonObject object = (JsonObject) json;

        int id = object.get(ID).getAsInt();
        long date = object.get(DATE).getAsLong();
        List<Split> splits = context.deserialize(object.getAsJsonArray(SPLITS), new TypeToken<List<Split>>() {}.getType());

        return new Workout(id, date, splits);
    }

    @Override
    public JsonElement serialize(Workout workout, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty(ID, workout.getId());
        object.addProperty(DATE, workout.getDate());

        JsonArray splits = new JsonArray();
        for (Split split : workout.getSplits()) {
            splits.add(context.serialize(split, Split.class));
        }
        object.add(SPLITS, splits);

        return object;
    }
}
