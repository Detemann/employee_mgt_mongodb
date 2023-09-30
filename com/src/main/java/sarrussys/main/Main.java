package sarrussys.main;

import sarrussys.main.database.ConexaoDB;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ConexaoDB conection = new ConexaoDB();
        try {
            conection.initDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        menuExemplo();
        //System.out.println("\n\n\n FIM DO PROGRAMA");
    }

    public static void menuExemplo(){
        Integer op = null;

        do {
            System.out.println("+======================= MENU PRINCIPAL =======================+\n");
            System.out.println("ESCOLHA UMA OPÇÃO\n"
                    + "\n[1] Relatórios"
                    + "\n[2] Inserir Registros"
                    + "\n[3] Remover Registros"
                    + "\n[4] Atualizar Registros"
                    + "\n[5] Sair");
            System.out.println("+======================= MENU PRINCIPAL =======================+\n");
            op = sc.nextInt();

            switch (op){
                case 1:
                    //relatórios
                    System.out.println("Relatorios");
                    break;
                case 2:
                    //inserir registros
                    System.out.println("Inserir Registros");
                    break;
                case 3:
                    //remover registros
                    System.out.println("Remover registros");
                    break;
                case 4:
                    //atualizar registros
                    System.out.println("Atualizar Registros");
                    break;
                case 5:
                    System.out.println("\n\n\n FIM DO PROGRAMA!");
                    break;
                default:
                    System.out.println("Opção indisponível!\nTente Novamente");
                    break;
            }
        } while (op != 5);
    }
}