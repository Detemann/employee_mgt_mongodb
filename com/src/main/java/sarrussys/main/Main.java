package sarrussys.main;

import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.database.ConexaoOracle;
import sarrussys.main.views.Menu;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();
        ConexaoOracle conection = new ConexaoOracle();


        try {
            conection.initDB();
            conexaoMongoDB.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Menu menu = new Menu(conection.getDataSource());
        menu.inicializacao();
    }


}