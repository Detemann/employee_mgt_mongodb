package sarrussys.main.database;

import oracle.jdbc.driver.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.Properties;

public class ConexaoDB {
    private final static String DB_url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private final static String DB_user = "enterprise";
    private final static String DB_password = "senha123";

    public void initDB() throws SQLException {
        Properties properties = new Properties();
        properties.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_user);
        properties.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_password);
        properties.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");

        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_url);
        ods.setConnectionProperties(properties);

        try(OracleConnection connection = (OracleConnection) ods.getConnection()) {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            System.out.println("Driver name: "+ dbMetaData.getDriverName());
            System.out.println("Driver version: "+ dbMetaData.getDriverVersion());
            System.out.println(connection.getCurrentSchema());
            System.out.println(connection.getDefaultRowPrefetch());
            consultaTeste(connection);
        }
    }

    private void consultaTeste(Connection connection) throws SQLException{
        try(Statement statement = connection.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery("SELECT * FROM TB_FUNCIONARIO")) {
                System.out.print("Teste sample: ");
                resultSet.next();
                System.out.print(resultSet.getInt("ID_FUNCIONARIO")+" ");
                System.out.print(resultSet.getString("NOME"));
            }
        }
    }
}
