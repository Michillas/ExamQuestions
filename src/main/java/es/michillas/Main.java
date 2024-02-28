package es.michillas;

import java.util.Scanner;

public class Main {
    // Slim shady you're based
    private final String menuString = """
                #--- ¿Que quieres hacer? ---#
                1) Hacer el examen
                2) Comprobar intentos
                3) Salir
                """;
    private GestorExamen gestorExamen = new GestorExamen();
    private static Main INSTANCE;
    private Main() {

    }

    public static Main getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Main();
        }
        return INSTANCE;
    }

    public static void main(String[] args){
        Main.getInstance().llamarMenu();
    }

    public void llamarMenu() {
        System.out.println(menuString);
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        switch(opcion) {
            case 1:
                gestorExamen.empezarExamen();
                this.llamarMenu();
                break;
            case 2:
                gestorExamen.listarExamenes();
                this.llamarMenu();
                break;
            case 3:
                break;
        }
    }
}