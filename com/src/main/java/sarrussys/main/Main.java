package sarrussys.main;

import sarrussys.main.database.ConexaoDB;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ConexaoDB conection = new ConexaoDB();
        try {
            conection.initDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n\n FIM DO PROGRAMA");
    }
}