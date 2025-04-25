
import com.mongodb.client.*;
import org.bson.Document;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.json.JSONObject;

public class MongoDBConnector {
    private static MongoDBConnector instance;
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public MongoDBConnector(String username, String password) {
        String uri = "mongodb+srv://" + username + ":" + password + "@coursework2cluster.dxh23.mongodb.net/?retryWrites=true&w=majority&appName=Coursework2Cluster";
        mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase("Coursework2Cluster");
        collection = database.getCollection("UserDataCollection");

    }

    public static synchronized MongoDBConnector getInstance(String uri, String username, String password){
        if (instance == null){
            instance = new MongoDBConnector(username, password);
        }
        return instance;
    }

    public void insertDocument(Document document){
        collection.insertOne(document);
        System.out.println("Document inserted");
    }

    public void insertJSON(JSONObject json){
        Document doc = Document.parse(json.toString());
        insertDocument(doc);
    }


}
