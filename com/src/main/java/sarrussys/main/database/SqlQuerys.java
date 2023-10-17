package sarrussys.main.database;

import java.util.ArrayList;
import java.util.List;

/**
 * @description Solução super porca para guardar as querys de inicialização
 */
public class SqlQuerys {

    private List<String> dropEverything = new ArrayList<>();

    private List<String> createTables = new ArrayList<>();

    private List<String> alterTables = new ArrayList<>();

    private List<String> insertData = new ArrayList<>();

    public SqlQuerys () {
        this.dropEverything.add("GRANT UNLIMITED TABLESPACE TO ANONYMOUS");
        this.dropEverything.add("ALTER TABLE FUNCIONARIO DROP CONSTRAINT DEPARTAMENTO_FK");
        this.dropEverything.add("ALTER TABLE DEPARTAMENTO DROP CONSTRAINT FUNCIONARIO_CHEFE_FK");
        this.dropEverything.add("DROP TABLE FUNCIONARIO");
        this.dropEverything.add("DROP TABLE DEPARTAMENTO");

        this.createTables.add("CREATE TABLE FUNCIONARIO (" +
                " ID_FUNCIONARIO NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1)," +
                " NOME VARCHAR2(255) NOT NULL," +
                " CPF VARCHAR2(11) NOT NULL," +
                " EMAIL VARCHAR2(255)," +
                " SALARIO_BRUTO NUMBER(9,2) NOT NULL," +
                " SALARIO_LIQUIDO NUMBER(9,2)," +
                " CONSTRAINT FUNCIONARIO_PK PRIMARY KEY(ID_FUNCIONARIO)" +
                ")");

        this.createTables.add("CREATE TABLE DEPARTAMENTO (" +
                " ID_DEPARTAMENTO NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1)," +
                " NOME VARCHAR2(255) NOT NULL," +
                " SIGLA VARCHAR2(10) NOT NULL," +
                " CONSTRAINT DEPARTAMENTO_PK PRIMARY KEY(ID_DEPARTAMENTO)" +
                ")");

        this.alterTables.add("ALTER TABLE FUNCIONARIO" +
                " ADD ID_DEPARTAMENTO NUMBER");
        this.alterTables.add("ALTER TABLE DEPARTAMENTO" +
                " ADD ID_CHEFE NUMBER");
        this.alterTables.add("ALTER TABLE FUNCIONARIO" +
                " ADD CONSTRAINT DEPARTAMENTO_FK" +
                " FOREIGN KEY (ID_DEPARTAMENTO)" +
                " REFERENCES DEPARTAMENTO(ID_DEPARTAMENTO)" +
                " NOT DEFERRABLE");
        this.alterTables.add("ALTER TABLE DEPARTAMENTO" +
                " ADD CONSTRAINT FUNCIONARIO_CHEFE_FK" +
                " FOREIGN KEY (ID_CHEFE)" +
                " REFERENCES FUNCIONARIO(ID_FUNCIONARIO)" +
                " NOT DEFERRABLE");

        this.insertData.add("INSERT INTO FUNCIONARIO (NOME, CPF, EMAIL, SALARIO_BRUTO, SALARIO_LIQUIDO)" +
                " VALUES ('João Silva', '12345678901', 'joao@email.com', 5000.00, 4000.00)");
        this.insertData.add("INSERT INTO FUNCIONARIO (NOME, CPF, EMAIL, SALARIO_BRUTO, SALARIO_LIQUIDO)" +
                " VALUES ('Maria Santos', '98765432101', 'maria@email.com', 6000.00, 4800.00)");
        this.insertData.add("INSERT INTO DEPARTAMENTO (NOME, SIGLA)" +
                " VALUES ('Departamento de Vendas', 'VD')");
        this.insertData.add("INSERT INTO DEPARTAMENTO (NOME, SIGLA)" +
                " VALUES ('Departamento de TI', 'TI')");
        this.insertData.add("UPDATE FUNCIONARIO" +
                " SET ID_DEPARTAMENTO = 1" +
                " WHERE ID_FUNCIONARIO = 1");
        this.insertData.add("UPDATE DEPARTAMENTO" +
                " SET ID_CHEFE = 1" +
                " WHERE ID_DEPARTAMENTO = 1");
    }

    public List<String> getDropEverything() {
        return dropEverything;
    }

    public List<String> getCreateTables() {
        return createTables;
    }

    public List<String> getAlterTables() {
        return alterTables;
    }

    public List<String> getInsertData() {
        return insertData;
    }
}
