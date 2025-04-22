package DataBase.Serializers;

import java.time.LocalDateTime;

public class Mood {
    private final int mood;
    private final LocalDateTime dateTime;

    public Mood(int mood, LocalDateTime dateTime) {
        this.mood = mood;
        this.dateTime = dateTime;
    }

    public int getMood() {
        return mood;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}

