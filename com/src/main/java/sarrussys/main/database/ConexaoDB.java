package sarrussys.main.database;

import oracle.jdbc.driver.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.Properties;

public class ConexaoDB {
    private final static String DB_url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private final static String DB_user = "enterprise";
    private final static String DB_password = "senha123";

    private OracleDataSource connection;

    public OracleDataSource getDataSource() {
        return this.connection;
    }

    public void setDataSource(OracleDataSource connection) {
        this.connection = connection;
    }

    public void initDB() throws SQLException {
        Properties properties = new Properties();
        properties.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_user);
        properties.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_password);
        properties.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "50");

        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_url);
        ods.setConnectionProperties(properties);

        try(OracleConnection connection = (OracleConnection) ods.getConnection()) {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            setDataSource(ods);
            System.out.println("[ConexaoDB] Driver name: "+ dbMetaData.getDriverName());
            System.out.println("[ConexaoDB] Driver version: "+ dbMetaData.getDriverVersion());
            System.out.println("[ConexaoDB] Database Connection : OK");
            Thread.sleep(1000); //Isso faz o programa para por X milisegundos, so para um efeito dramatico kkkk
        } catch (Exception e) {
            System.out.println("[ConexaoDB] Databse Connection: FAIL" +
                    "\n"+e.getMessage());
        }
    }

    //metodo criado a principio para realizar consulta sql (em testes)
    public ResultSet executarConsulta(String sql) throws SQLException {
        try (Connection connection = this.connection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)) {

            return resultSet;
        } catch (SQLException e) {
            System.out.println("[ConexaoDB] Erro ao executar consulta SQL: " + e.getMessage());
            throw e;
        }
    }

    // Método para fechar a conexão com o banco de dados
    public void fecharConexao() {
        try {
            if (this.connection != null) {
                this.connection.getConnection().close();
                System.out.println("[ConexaoDB] Conexão fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("[ConexaoDB] Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
        }
    }



}
