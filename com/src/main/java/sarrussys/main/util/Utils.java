package sarrussys.main.util;

import com.mongodb.client.MongoCursor;
import org.bson.Document;
import sarrussys.main.model.Departamento;
import sarrussys.main.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private List<Object> objArray;

    /**
     * @param quantity
     * @param classe
     * @return Lista de objetos vazios de acordo com quantidade e classe
     */
    public List<?> fabricate(int quantity, Class classe) {
        try {
            objArray = new ArrayList<>();
            create(quantity, classe);
            if(!objArray.isEmpty() && objArray.get(0).getClass().equals(classe)) {
                return objArray;
            } else {
                throw new RuntimeException("[ModelOnDemand] Erro ao retorna lista de objetos");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<?> populate(MongoCursor<Document> cursor, List<?> objList) {
        if(objList.get(0).getClass().equals(Funcionario.class)) {
            for (Object funcionario : objList) {
                Document doc = cursor.next();
                ((Funcionario) funcionario).setIdFuncionario(doc.getInteger("_id"));
                ((Funcionario) funcionario).setNome(doc.getString("nome"));
                ((Funcionario) funcionario).setCpf(doc.getString("cpf"));
                ((Funcionario) funcionario).setEmail(doc.getString("email"));
                ((Funcionario) funcionario).setSalarioBruto(doc.getDouble("salario_bruto"));
                ((Funcionario) funcionario).setSalarioLiquido(doc.getDouble("salario_liquido"));
                ((Funcionario) funcionario).setNomeDepartamento(doc.getString("nome_departamento"));
            }
        } else {
            for (Object departamento: objList) {
                Document doc = cursor.next();
                ((Departamento) departamento).setIdDepartamento(doc.getInteger("_id"));
                ((Departamento) departamento).setNomeDepartamento(doc.getString("nome"));
                ((Departamento) departamento).setSigla(doc.getString("sigla"));
                ((Departamento) departamento).setNomeChefe(doc.getString("nome_chefe"));
            }
        }
        return objList;
    }

    private void create(int quantity, Class classe) throws Exception {
        if(quantity <= 0) {
           return;
        } else {
            objArray.add(classe.getDeclaredConstructor().newInstance());
            create(quantity - 1, classe);
        }
    }
}
