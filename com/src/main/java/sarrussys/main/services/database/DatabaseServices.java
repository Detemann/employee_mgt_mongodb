package sarrussys.main.services.database;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseServices {
    private OracleDataSource dataSource;

    public DatabaseServices(OracleDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public ResultSet fazerConsulta(String sql) {
        try {
            if (sql.isEmpty()) throw new RuntimeException("Sem comando SQL");
            Connection connection = dataSource.getConnection();
            connection.setSchema("ANONYMOUS");
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("[DatabaseServices] Ocorreu um erro durante a consulta" + e.getMessage());
            return null;
        }
    }
    /**
     * @return Retorna a quatidade de linhas modificadas ou 0 caso falhe ou nada seja modificado
     * */
    public int fazerUpdate(String sql) {
        try {
            if (sql.isEmpty()) throw new RuntimeException("Sem comando SQL");
            Connection connection = dataSource.getConnection();
            connection.setSchema("ANONYMOUS");
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("[DatabaseServices] Ocorreu um erro durante a manipulação \n"
                    + e.getMessage());
            return 0;
        }
    }
}
