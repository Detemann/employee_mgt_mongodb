package sarrussys.main;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.json.JsonObject;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.repository.DepartamentoRepository;
import java.io.IOException;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();


        try {
            conexaoMongoDB.init();
            DepartamentoRepository departamentoRepository = new DepartamentoRepository(conexaoMongoDB.getMongoDatabase());
            List<MongoCollection<Document>> docs = departamentoRepository.NumFuncionariosDepartamento();
            MongoCursor<Document> cursor = docs.stream().filter(document -> document.getNamespace().toString().equals("employees.employee"))
                    .findFirst().get().find(eq("nome", "Tecnologia")).iterator();

            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Menu menu = new Menu(conexaoMongoDB);
        menu.inicializacao();*/
    }


}