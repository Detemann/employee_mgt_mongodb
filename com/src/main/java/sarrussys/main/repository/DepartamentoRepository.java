package sarrussys.main.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;
import sarrussys.main.util.Utils;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class DepartamentoRepository {
    private ConexaoMongoDB database;

    Utils utils = new Utils();

    public DepartamentoRepository(ConexaoMongoDB database) {
        this.database = database;
    }

    public List<Departamento> buscarDepartamento() {
        try {

            MongoCollection<Document> departamentos = database.getMongoDatabase().getDatabase("employees").getCollection("departaments");

            List<Departamento> departamentoList = (List<Departamento>) utils.fabricate((int)departamentos.countDocuments(), Departamento.class);
            departamentoList = (List<Departamento>) utils.populate(departamentos.find().cursor(), departamentoList);


            return departamentoList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
