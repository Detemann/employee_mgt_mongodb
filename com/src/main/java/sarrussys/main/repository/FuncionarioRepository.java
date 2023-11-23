package sarrussys.main.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Funcionario;
import sarrussys.main.util.Utils;

import java.util.List;
import java.util.Random;

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

    public void save(Funcionario funcionario) {
        Random ran = new Random();
        try {
            MongoCollection<Document> collection = database.getMongoDatabase().getDatabase("employees").getCollection("employee");

            InsertOneResult result = collection.insertOne(new Document()
                    .append("_id", ran.nextInt(9999))
                    .append("nome", funcionario.getNome())
                    .append("cpf", funcionario.getCpf())
                    .append("email", funcionario.getEmail())
                    .append("salario_bruto", funcionario.getSalarioBruto())
                    .append("salario_liquido", funcionario.getSalarioLiquido())
                    .append("nome_departamento", funcionario.getNomeDepartamento()));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Funcionario funcionario) {
        MongoCollection<Document> collection = database.getMongoDatabase().getDatabase("employees").getCollection("employee");

        Document query = new Document().append("_id", funcionario.getIdFuncionario());

        Bson updates = Updates.combine(
                Updates.set("nome", funcionario.getNome()),
                Updates.set("cpf", funcionario.getCpf()),
                Updates.set("email", funcionario.getEmail()),
                Updates.set("salario_bruto", funcionario.getSalarioBruto()),
                Updates.set("salario_liquido", funcionario.getSalarioLiquido()),
                Updates.set("nome_departamento", funcionario.getNomeDepartamento())
        );

        try {
            UpdateResult result = collection.updateOne(query, updates);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
