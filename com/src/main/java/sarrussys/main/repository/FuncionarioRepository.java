package sarrussys.main.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Funcionario;
import sarrussys.main.util.Utils;

import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;

public class FuncionarioRepository {
    private ConexaoMongoDB database;

    MongoCollection<Document> collection;

    private Utils utils = new Utils();

    public FuncionarioRepository(ConexaoMongoDB database) {
        this.database = database;
    }

    @SuppressWarnings("unchecked")
    public List<Funcionario> buscarFuncionarios() {
        collection = database.getMongoDatabase().getDatabase("employees").getCollection("employee");


        List<Funcionario> funcionarioList = (List<Funcionario>) utils.fabricate((int)collection.countDocuments(), Funcionario.class);
        funcionarioList = (List<Funcionario>) utils.populate(collection.find().cursor(), funcionarioList);

        return funcionarioList;
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        try (MongoCursor<Document> funcionarios = database.getMongoDatabase().getDatabase("employees")
                .getCollection("employee")
                .find(eq("_id", id)).cursor()) {

            return (Funcionario) utils.populate(funcionarios, utils.fabricate(1, Funcionario.class));
        } catch (Exception e) {
            System.out.println("[DepartamentoRepository] "+e.getMessage());
            return null;
        }
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

    public void updateOnMass(List<Funcionario> funcionarios) {
        funcionarios.forEach(this::update);
    }

    public void update(Funcionario funcionario) {
        collection = database.getMongoDatabase().getDatabase("employees").getCollection("employee");

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
            collection.updateOne(query, updates);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Funcionario funcionario) {
        try {
            collection = database.getMongoDatabase().getDatabase("employees").getCollection("employee");

            DeleteResult result = collection.deleteOne(eq("id_", funcionario.getIdFuncionario()));
        } catch (Exception e) {
            System.out.println("[DepartamentoRepository] "+e.getMessage());
        }
    }
}
