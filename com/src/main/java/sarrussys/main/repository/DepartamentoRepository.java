package sarrussys.main.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class DepartamentoRepository {
    MongoClient database;

    public DepartamentoRepository(MongoClient database) {
        this.database = database;
    }

    public List<MongoCollection<Document>> NumFuncionariosDepartamento() {
        try {

            MongoCollection<Document> departamentos = database.getDatabase("employees").getCollection("departaments");
            MongoCollection<Document> funcionarios = database.getDatabase("employees").getCollection("employees");

            return Arrays.asList(departamentos, funcionarios);
        } catch (Exception e) {

        }
        return null;
    }

}
