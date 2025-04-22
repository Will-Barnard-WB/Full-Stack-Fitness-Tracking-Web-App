package DataBase.Serializers;

import java.time.LocalDateTime;

public class Mood {
    private final int mood;
    private final int timeStamp;

    public Mood(int mood, int timeStamp) {
        this.mood = mood;
        this.timeStamp = timeStamp;
    }

    public int getMood() {
        return mood;
    }

    public int getTimeStamp() {
        return timeStamp;
    }
}

