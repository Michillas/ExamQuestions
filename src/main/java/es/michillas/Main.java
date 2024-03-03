package es.michillas;

import java.util.Scanner;

public class Main {
    // Slim shady you're based
    Scanner scanner = new Scanner(System.in);
    private final String menuString = """
                #--- ¿Que quieres hacer? ---#
                1) Hacer el examen
                2) Comprobar intentos
                3) Cambiar tipo
                4) Salir
                """;
    private GestorExamen gestorExamen = new GestorExamen();
    private static final Main INSTANCE = new Main();
    private Main() {

    }

    public static Main getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args){
        Main.getInstance().llamarMenuPrincipal();
    }

    public void llamarMenuPrincipal() {
        System.out.println(menuString);
        switch(scanner.nextInt()) {
            case 1:
                gestorExamen.crearNuevoExamen();
                gestorExamen.mostrarPreguntas();
                this.llamarMenuPrincipal();
            case 2:
                gestorExamen.listarExamenes();
                this.llamarMenuPrincipal();
            case 3:
                this.llamarMenuTipos();
            case 4:
                break;
        }
    }

    public void llamarMenuTipos() {
        System.out.println("""
                #--- ¿Que tipo de examen quieres? ---#
                1) Test
                2) Tarjetas
                """);
        switch(scanner.nextInt()) {
            case 1:
                gestorExamen.cambiarTipo(1);
                llamarMenuPrincipal();
            case 2:
                gestorExamen.cambiarTipo(2);
                llamarMenuPrincipal();
        }
    }
}