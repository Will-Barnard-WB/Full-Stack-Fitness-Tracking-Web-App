package Serializers;

import java.io.Serializable;

public record Split(int timeStamp, long positionLong, long positionLat, long distance, long altitude, long speed,
                    int cadence, int heartRate) implements Serializable {
}
