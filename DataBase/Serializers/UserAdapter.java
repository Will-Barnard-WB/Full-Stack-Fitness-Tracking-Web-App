package Serializers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class UserAdapter implements JsonDeserializer<User>, JsonSerializer<User> {

    private final String ID = "id";
    private final String NAME = "name";
    private final String WORKOUTS = "workouts";
    private final String MOODS = "moods";

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null) {
            return null;
        }

        if (!json.isJsonObject()) {
            return null;
        }

        JsonObject object = (JsonObject) json;

        int id = object.get(ID).getAsInt();
        String name = object.get(NAME).getAsString();
        List<Workout> workouts = context.deserialize(object.getAsJsonArray(WORKOUTS), new TypeToken<List<Workout>>() {}.getType());
        List<Mood> moods = context.deserialize(object.getAsJsonArray(MOODS), new TypeToken<List<Workout>>() {}.getType());

        return new User(id, name, workouts, moods);
    }

    @Override
    public JsonElement serialize(User user, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty(ID, user.getId());
        object.addProperty(NAME, user.getName());

        JsonArray workouts = new JsonArray();
        for (Workout workout : user.getWorkouts()) {
            workouts.add(context.serialize(workout, Workout.class));
        }
        object.add(WORKOUTS, workouts);

        JsonArray moods = new JsonArray();
        for (Mood moods : user.getMoods()) {
            workouts.add(context.serialize(mood, Mood.class));
        }
        object.add(MOODS, moods);

        return object;
    }
}
