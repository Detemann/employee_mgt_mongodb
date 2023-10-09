package sarrussys.main;

import sarrussys.main.database.ConexaoDB;
import sarrussys.main.views.Menu;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ConexaoDB conection = new ConexaoDB();
        try {
            conection.initDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Menu menu = new Menu(conection.getDataSource());
        menu.inicializacao();
    }


}