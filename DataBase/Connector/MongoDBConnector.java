
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class MongoDBConnector {
    private static MongoDBConnector instance;
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;
    
    //establish connection
    public MongoDBConnector(String username, String password) {
        String uri = "mongodb+srv://" + username + ":" + password + "@coursework2cluster.dxh23.mongodb.net/?retryWrites=true&w=majority&appName=Coursework2Cluster";
        mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase("Coursework2Cluster");
        collection = database.getCollection("UserDataCollection");

    }
    //ensures only one connection is active at a time with the account
    public static synchronized MongoDBConnector getInstance(String username, String password){
        if (instance == null){
            instance = new MongoDBConnector(username, password);
        }
        return instance;
    }
    
    
    // Create a new document in the DB for a new user. 
    // provide string and password which will be encrypted with SHA-256
    
    public void createUserDocument(String username, String userPassword){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] passwordHash = digest.digest(userPassword.getBytes(StandardCharsets.UTF_8));
            byte[] usernameHash = digest.digest(username.getBytes(StandardCharsets.UTF_8));
            userPassword = new String(passwordHash, StandardCharsets.UTF_8);
            username = new String(usernameHash, StandardCharsets.UTF_8);
        } catch (RuntimeException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            ArrayList<JSONObject> workouts = new ArrayList<JSONObject>();
            ObjectId id = new ObjectId();
            InsertOneResult result = collection.insertOne(new Document().append("_id", id).append("username", username).append("password", userPassword).append("Workouts", workouts));
            System.out.println("Successfuly created user");
            System.out.println(id);
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }
    
    //Append this workout to the user.
    //so far I have left the workout as BSON but you can make overloaded methods to allow for gson/json objects
    
    public void insertWorkout(ObjectId userID, Bson workout){
        try{
            Bson findQuery = new BasicDBObject("_id", userID);
            DBObject listItem = new BasicDBObject("Workouts", workout);
            Bson updateQuery = new BasicDBObject("$push", listItem);
            collection.updateOne(findQuery, updateQuery);
            System.out.println("Succesfully added a new workout to user!!");
        } catch (MongoException me){
            System.err.println("Unable to insert due to an error: " + me);
        }
    }
    public void insertWorkout(String userIDstring , Bson workout){
        try{
            ObjectId userID = new ObjectId(userIDstring);
            Bson findQuery = new BasicDBObject("_id", userID);
            DBObject listItem = new BasicDBObject("Workouts", workout);
            Bson updateQuery = new BasicDBObject("$push", listItem);
            collection.updateOne(findQuery, updateQuery);
            System.out.println("Succesfully added a new workout to user!!");
        } catch (MongoException me){
            System.err.println("Unable to insert due to an error: " + me);
        }
    }


}
