package Serializers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

public class UserAdapterTest {
    public static void main(String[] args) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new UserAdapter());
        Gson gson = gsonBuilder.create();

        // Sample Split objects
        Split split1 = new Split(1627849200000L, 50L, 60L, 100L, 200L, 15L, 80, 120);
        Split split2 = new Split(1627849200000L, 55L, 65L, 110L, 210L, 16L, 85, 125);

        Mood mood1 = new Mood(1, 1627849200000L);
        Mood mood2 = new Mood(3, 1627849200000L);


        // Sample Workout object
        Workout workout1 = new Workout(1, 1627849200000L, Arrays.asList(split1, split2));
        Workout workout2 = new Workout(2, 1627849200000L, Arrays.asList(split1));

        // Sample User object
        User user = new User(1, "John Doe", Arrays.asList(workout1, workout2), Arrays.asList(mood1, mood2));

        // Serialize
        String json = gson.toJson(user);
        System.out.println("Serialized JSON: " + json);

        // Deserialize
        User deserializedUser = gson.fromJson(json, User.class);
        System.out.println("Deserialized User: " + deserializedUser);
    }
}
