package es.michillas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorExamen {
    private Examen examenActual;
    private final ArrayList<Examen> examenes = new ArrayList<>();
    private final GestorJson jsonLoader = new GestorJson();
    private final char[] abcde = {'A', 'B', 'C', 'D', 'E'};
    private final Scanner scanner = new Scanner(System.in);
    private final JSONObject preguntas = jsonLoader.getJsonObject().getJSONObject("pregunta");
    private int tipo = 1;

    public void crearNuevoExamen() {
        examenActual = new Examen((examenes.isEmpty()) ? 1 : (examenes.size() + 1), preguntas.length());
        examenes.add(examenActual);
    }

    public void mostrarPreguntas() {
        for (int i = 0; i < preguntas.length(); i++) {
            int numero = i + 1;
            System.out.println("#--- Pregunta Nº" + numero + " ---#");
            switch(tipo) {
                case 1:
                    preguntarTipoTest(numero);
                    break;
                case 2:
                    preguntarTipoTarjeta(numero);
                    break;
            }
        }
        System.out.println("Has tenido " + examenActual.getPreguntasCorrectas() + " preguntas correctas");
    }

    public void preguntarTipoTest(int numero) {
        JSONObject preguntaActual = preguntas.getJSONObject(String.valueOf(numero));
        System.out.println("#--- Pregunta Nº" + numero + " ---#");
        String titulo = preguntaActual.getString("titulo");
        JSONArray respuestas = preguntaActual.getJSONArray("posibles");
        System.out.println(titulo);
        String respuestaCorrecta = preguntaActual.getString("correcta");
        int eleccionCorrecta = 1;
        for(int j = 0; j < respuestas.length(); j++) {
            System.out.println(abcde[j] + ") " + respuestas.get(j));
            if (respuestaCorrecta.equals(respuestas.get(j))) {
                eleccionCorrecta = j + 1;
            }
        }
        int eleccion = switch (scanner.nextLine().toUpperCase()) {
            case "A" -> 1;
            case "B" -> 2;
            case "C" -> 3;
            case "D" -> 4;
            default -> 0;
        };
        if (eleccion == eleccionCorrecta) {
            examenActual.setPreguntasCorrectas(examenActual.getPreguntasCorrectas() + 1);
        } else if (eleccion == 0) {
            System.out.println("[X] - No has elegido una opción correcta, la respuesta es contada como incorrecta");
        }
    }

    public void preguntarTipoTarjeta(int numero) {
        JSONObject preguntaActual = preguntas.getJSONObject(String.valueOf(numero));
        String titulo = preguntaActual.getString("titulo");
        System.out.println(titulo);
        String respuestaCorrecta = preguntaActual.getString("correcta");
        scanner.nextLine();
        System.out.println("""
        La respuesta correcta es:
        #---------------------#
        """ + respuestaCorrecta + """
        #---------------------#
        """);
    }

    public void listarExamenes() {
        if(examenes.isEmpty()) {
            System.out.println("- NO HAY INTENTOS DE EXAMEN");
        } else {
            System.out.println("""
                ┌───────────┬─────────────┬──────────┐
                │  Intento  │  Preguntas  │   Nota   │""");
            for(Examen examen : examenes) {
                mostrarInformacionExamen(examen);
            }
            System.out.println("└───────────┴─────────────┴──────────┘");
        }
    }

    public void mostrarInformacionExamen(Examen examen) {
        String examenFormato = "│  %-8s │  %-10s │  %-7s │";
        System.out.println("├───────────┤─────────────┤──────────┤");
        System.out.printf(examenFormato, examen.getIntento(), examen.getPreguntasCorrectas() + "/" + examen.getPreguntasTotales(), String.format("%.2f", examen.getNota()));
        System.out.println();
    }

    public void cambiarTipo(int tipo) {
        this.tipo = tipo;
    }
}
