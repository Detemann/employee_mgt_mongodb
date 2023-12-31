package sarrussys.main.repository;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.model.Departamento;
import sarrussys.main.util.Utils;

import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;


public class DepartamentoRepository {
    private ConexaoMongoDB database;

    MongoCollection<Document> collection;

    Utils utils = new Utils();

    public DepartamentoRepository(ConexaoMongoDB database) {
        this.database = database;
    }

    @SuppressWarnings("unchecked")
    public List<Departamento> buscarDepartamentos() {
        try {
            collection = database.getMongoDatabase().getDatabase("employees").getCollection("departaments");

            List<Departamento> departamentoList = (List<Departamento>) utils.fabricate((int) collection.countDocuments(), Departamento.class);
            departamentoList = (List<Departamento>) utils.populate(collection.find().cursor(), departamentoList);

            return departamentoList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Departamento buscarDepartamentoPorId(int id) {
        try (MongoCursor<Document> departamentos = database.getMongoDatabase().getDatabase("employees")
                .getCollection("departaments")
                .find(eq("_id", id)).cursor()) {

            return (Departamento) utils.populate(departamentos, utils.fabricate(1, Departamento.class)).get(0);
        } catch (Exception e) {
            System.out.println("[DepartamentoRepository] " + e.getMessage());
            return null;
        }
    }

    public Departamento buscarDepartamentoPorNome(String nome) {
        try (MongoCursor<Document> departamentos = database.getMongoDatabase().getDatabase("employees")
                .getCollection("departaments")
                .find(eq("nome", nome)).cursor()) {

            return (Departamento) utils.populate(departamentos, utils.fabricate(1, Departamento.class)).get(0);
        } catch (Exception e) {
            System.out.println("[DepartamentoRepository] " + e.getMessage());
            return null;
        }
    }

    public void insert(Departamento novoDepartamento) {
        try {
            collection = database.getMongoDatabase().getDatabase("employees").getCollection("departaments");

            int id = new Random().nextInt(9999);
            collection.insertOne(new Document()
                    .append("_id", id)
                    .append("nome", novoDepartamento.getNomeDepartamento())
                    .append("sigla", novoDepartamento.getSigla())
                    .append("nome_chefe", novoDepartamento.getNomeChefe())
            );

        } catch (MongoException e) {
            System.out.println("[DepartamentoRepository] " + e.getMessage());
        }
    }

    public void delete(Departamento departamento) {
        try {
            collection = database.getMongoDatabase().getDatabase("employees").getCollection("departaments");

            collection.deleteOne(eq("_id", departamento.getIdDepartamento()));
        } catch (Exception e) {
            System.out.println("[DepartamentoRepository] " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateOnMass(List<Departamento> departamentos) {
        departamentos.forEach(this::update);
    }

    public void update(Departamento departamento) {
        try {
            collection = database.getMongoDatabase().getDatabase("employees").getCollection("departaments");

            Document query = new Document().append("_id", departamento.getIdDepartamento());

            Bson updates = Updates.combine(
                    Updates.set("nome", departamento.getNomeDepartamento()),
                    Updates.set("sigla", departamento.getSigla()),
                    Updates.set("nome_chefe", departamento.getNomeChefe())
            );
            collection.updateOne(query, updates);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
