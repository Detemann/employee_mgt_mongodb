package sarrussys.main;

import sarrussys.main.database.ConexaoMongoDB;
import sarrussys.main.views.Menu;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ConexaoMongoDB conexaoMongoDB = new ConexaoMongoDB();
        try {
            conexaoMongoDB.init();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Menu menu = new Menu(conexaoMongoDB);
        menu.inicializacao();
    }


}