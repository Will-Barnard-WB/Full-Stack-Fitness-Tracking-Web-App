package com.stravacopy.backend.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "UserDataCollection")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableName {
    @Id
    private String id;

    private String positionLong;

    private String altitude;

    private String distance;

    private String positionLat;

    private String speed;

    private String cadence;

    private String timestamp;

}
