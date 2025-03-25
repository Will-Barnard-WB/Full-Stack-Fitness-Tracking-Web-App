
import com.mongodb.client.*;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

    public class JsonToMongoDB {

        public static void main(String[] args) {
        }

        public void writeToDB(String filePath){
            //hardcoded the data base connection for now
            String uri = "mongodb+srv://Writer:Writer@coursework2cluster.dxh23.mongodb.net/?retryWrites=true&w=majority&appName=Coursework2Cluster";
            String databaseName = "Coursework2Cluster";
            String collectionName = "UserDataCollection";
            String jsonFilePath = "path/to/your/jsonfile.json";

            try (MongoClient mongoClient = MongoClients.create(uri)) {
                MongoDatabase database = mongoClient.getDatabase(databaseName);
                MongoCollection<Document> collection = database.getCollection(collectionName);

                String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

                // Check if the JSON content is an array or a single object
                List<Document> documents = new ArrayList<>();
                if (jsonContent.trim().startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(jsonContent);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        documents.add(Document.parse(jsonArray.getJSONObject(i).toString()));
                    }
                } else {
                    JSONObject jsonObject = new JSONObject(jsonContent);
                    documents.add(Document.parse(jsonObject.toString()));
                }

                // Insert into MongoDB
                if (!documents.isEmpty()) {
                    collection.insertMany(documents, new InsertManyOptions().ordered(false));
                    System.out.println("Data inserted successfully.");
                } else {
                    System.out.println("No valid data found to insert.");
                }
            } catch (IOException e) {
                System.err.println("Error reading JSON file: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error inserting data into MongoDB: " + e.getMessage());
            }
        }
    }
