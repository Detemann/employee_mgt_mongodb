package sarrussys.main.services.database;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseServices {
    private OracleDataSource dataSource;

    public DatabaseServices(OracleDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public ResultSet fazerConsulta(String sql) {
        try {
            if (sql.isEmpty()) throw new RuntimeException("Sem comando SQL");
            Statement statement = dataSource.getConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("[DatabaseServices] Ocorreu um erro durante a consulta" + e.getMessage());
            return null;
        }
    }

    public boolean cadastrarFuncionario(String sql) {
        try {
            if (sql.isEmpty()) throw new RuntimeException("Sem comando SQL");

            Statement statement = dataSource.getConnection().createStatement();
            int resultado = statement.executeUpdate(sql);

            // Se pelo menos uma linha foi inserida com sucesso, retornar true
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("[DatabaseServices] Ocorreu um erro durante a consulta \n" + e.getMessage());
            return false;
        }
    }

    public boolean cadastrarDepartamento(String sql) {
        try {
            if (sql.isEmpty()) throw new RuntimeException("Sem comando SQL");

            Statement statement = dataSource.getConnection().createStatement();
            int resultado = statement.executeUpdate(sql);

            // Se pelo menos uma linha foi inserida com sucesso, retornar true
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("[DatabaseServices] Ocorreu um erro durante a consulta \n" + e.getMessage());
            return false;
        }
    }


    /**
     * @return Retorna a quatidade de linhas modificadas ou 0 caso falhe ou nada seja modificado
     * */
    public int fazerUpdate(String sql) {
        try {
            if (sql.isEmpty()) throw new RuntimeException("Sem comando SQL");
            Statement statement = dataSource.getConnection().createStatement();
             return statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("[DatabaseServices] Ocorreu um erro durante a manipulação \n"
                    + e.getMessage());
            return 0;
        }
    }
}
