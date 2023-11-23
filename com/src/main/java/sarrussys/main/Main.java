package sarrussys.main;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.json.JsonObject;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.repository.DepartamentoRepository;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();


        try {
            conexaoMongoDB.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Menu menu = new Menu(conexaoMongoDB);
        menu.inicializacao();
    }


}