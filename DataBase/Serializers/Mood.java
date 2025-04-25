package DataBase.Serializers;

import java.io.Serializable;

public record Mood(int mood, long timeStamp) implements Serializable {
}
