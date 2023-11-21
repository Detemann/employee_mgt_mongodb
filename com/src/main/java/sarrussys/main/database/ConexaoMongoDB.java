package sarrussys.main.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class ConexaoMongoDB {
    private final static String DB_url = "mongodb+srv://detemann147:Lucas123456@cluster0.4p9dpuh.mongodb.net/?retryWrites=true&w=majority";

    private MongoDatabase mongoDatabase;

    public void init() {
        try(MongoClient mongoClient = MongoClients.create(DB_url)) {
            MongoDatabase database = mongoClient.getDatabase("employees");
            MongoCollection<Document> collection = database.getCollection("employee");

            Document doc = collection.find(eq("nome", "Lucas Detemann")).first();
            if(doc != null) {
                setDatabse(database);
                System.out.println("[ConexaoMongoDB] Databse Setup: OK");
            } else {
                System.out.println("[ConexaoOracle] Databse Setup: FAIL");
            }
        }
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
    public void setDatabse(MongoDatabase database) {
        mongoDatabase = database;
    }
}
