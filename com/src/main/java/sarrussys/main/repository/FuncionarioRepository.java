package sarrussys.main.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Funcionario;
import sarrussys.main.util.Utils;

import java.util.List;

public class FuncionarioRepository {
    private ConexaoMongoDB database;

    private Utils utils = new Utils();

    public FuncionarioRepository(ConexaoMongoDB database) {
        this.database = database;
    }

    public List<Funcionario> buscarFuncionarios() {
        MongoCollection<Document> funcionarios = database.getMongoDatabase().getDatabase("employees").getCollection("employee");


        List<Funcionario> funcionarioList = (List<Funcionario>) utils.fabricate((int)funcionarios.countDocuments(), Funcionario.class);
        funcionarioList = (List<Funcionario>) utils.populate(funcionarios.find().cursor(), funcionarioList);

        return funcionarioList;
    }
}
