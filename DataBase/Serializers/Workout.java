package Serializers;

import java.util.List;

public class Workout {

    private final int id;
    private final long date;
    private final List<Split> splits;

    public Workout(int id, long date, List<Split> splits) {
        this.id = id;
        this.date = date;
        this.splits = splits;
    }

    public int getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public List<Split> getSplits() {
        return splits;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", date=" + date +
                ", splits=" + splits +
                '}';
    }
}
