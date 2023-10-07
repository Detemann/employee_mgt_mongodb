package sarrussys.main.services.database;

import oracle.jdbc.driver.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.Properties;

public class ConexaoDB {
    private final static String DB_url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";

    private final static String DB_user = "system";

    private final static String DB_password = "senha123";

    private final static String defaultSchema = "ANONYMOUS";

    private OracleDataSource dataSource;

    public OracleDataSource getDataSource() {
        return this.dataSource;
    }

    private void setDataSource(OracleDataSource connection) {
        this.dataSource = connection;
    }

    public void initDB() throws Exception {
        Properties properties = new Properties();
        properties.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_user);
        properties.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_password);

        OracleDataSource oracleDataSource = new OracleDataSource();
        oracleDataSource.setURL(DB_url);
        oracleDataSource.setConnectionProperties(properties);

        setDataSource(oracleDataSource);
        if(initializeTables()) System.out.println("[ConexaoDB] Databse Setup: OK \n");
    }

    private Boolean initializeTables() {
        dropEverything();
        try {
            if(!executeSql("CreateTables.sql")) throw new RuntimeException("Erro ao criar tabelas.");
            if(!executeSql("AlterTable.sql")) throw new RuntimeException("Erro ao configurar as tabelas.");
            if(!executeSql("InsertData.sql")) throw new RuntimeException("Erro ao inserir dados.");
            return true;
        } catch (Exception e) {
            System.out.println("[ConexaoDB - initializeTables] Databse Setup: FAIL" +
                    "\n"+e.getMessage());
            return false;
        }
    }

    private Boolean executeSql(String fileName) {
        try  (Connection connection = dataSource.getConnection()) {
            connection.setSchema(defaultSchema);
            ScriptRunner runSql = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new FileReader("com/src/main/resources/sql/"+fileName));
            runSql.setLogWriter(null);
            runSql.setErrorLogWriter(null);
            runSql.runScript(reader);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void dropEverything() {
        try  (Connection connection = dataSource.getConnection()) {
            connection.setSchema(defaultSchema);
            ScriptRunner runSql = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new FileReader("com/src/main/resources/sql/"+"DropEverything.sql"));
            runSql.setLogWriter(null);
            runSql.setErrorLogWriter(null);
            runSql.runScript(reader);
        } catch (Exception ignored) {
            System.out.println("[ConexaoDB - initializeTables] Schema is clear.");
        }
    }
}
