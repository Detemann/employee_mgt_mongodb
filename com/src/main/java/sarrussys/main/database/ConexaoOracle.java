package sarrussys.main.database;

import oracle.jdbc.driver.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;
import sarrussys.main.repository.DatabaseServices;

import java.util.List;
import java.util.Properties;

public class ConexaoOracle {
    private final static String DB_url = "jdbc:oracle:thin:@//localhost:1521/xe";

    private final static String DB_user = "labdatabase";

    private final static String DB_password = "labDatabase2022";

    private final static String defaultSchema = "ANONYMOUS";

    private DatabaseServices databaseServices;

    private OracleDataSource dataSource;

    private SqlQuerys sqls;

    private void setDataSource(OracleDataSource connection) {
        this.dataSource = connection;
        this.databaseServices = new DatabaseServices(connection);
        this.sqls = new SqlQuerys();
    }

    public OracleDataSource getDataSource() {
        return this.dataSource;
    }

    public void initDB() throws Exception {
        Properties properties = new Properties();
        properties.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_user);
        properties.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_password);

        OracleDataSource oracleDataSource = new OracleDataSource();
        oracleDataSource.setURL(DB_url);
        oracleDataSource.setConnectionProperties(properties);

        setDataSource(oracleDataSource);
        if(initializeTables()) System.out.println("[ConexaoOracle] Databse Setup: OK \n");
    }

    private Boolean initializeTables() {
        List<String> drop = sqls.getDropEverything();
        List<String> create = sqls.getCreateTables();
        List<String> alter = sqls.getAlterTables();
        List<String> insert = sqls.getInsertData();

        boolean parar = databaseServices.fazerConsulta("SELECT * FROM DEPARTAMENTO") == null &&
                databaseServices.fazerConsulta("SELECT * FROM FUNCIONARIO") == null;

        for (String sql : drop) {
            databaseServices.fazerUpdate(sql);
            if(parar) break;
        }
        try {
            for (String sql : create) {
                databaseServices.fazerUpdate(sql);
            }
            for (String sql : alter) {
                databaseServices.fazerUpdate(sql);
            }
            for (String sql: insert) {
                databaseServices.fazerUpdate(sql);
            }
            return true;
        } catch (Exception e) {
            System.out.println("[ConexaoOracle - initializeTables] Databse Setup: FAIL" +
                    "\n"+e.getMessage());
            return false;
        }
    }
}
