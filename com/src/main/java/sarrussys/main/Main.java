package sarrussys.main;

import sarrussys.main.database.ConexaoDB;
import sarrussys.main.views.Menu;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        ConexaoDB conection = new ConexaoDB();
        try {
            conection.initDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Menu menu = new Menu(conection.getDataSource());
        menu.inicializacao();
    }


}