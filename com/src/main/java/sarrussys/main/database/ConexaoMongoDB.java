package sarrussys.main.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class ConexaoMongoDB {
    private final static String DB_url = "mongodb+srv://detemann147:Lucas123456@cluster0.4p9dpuh.mongodb.net/?retryWrites=true&w=majority";

    private MongoClient mongoDatabase;

    public void init() {
        try {
            MongoClient mongoClient = MongoClients.create(DB_url);
            setDatabse(mongoClient);
            System.out.println("[ConexaoMongoDB] Connection status: OK");
        } catch (Exception e) {
            System.out.println("[ConexaoMongoDB] Connection status: FAIL");
        }
    }

    public MongoClient getMongoDatabase() {
        return mongoDatabase;
    }
    public void setDatabse(MongoClient database) {
        mongoDatabase = database;
    }
}
