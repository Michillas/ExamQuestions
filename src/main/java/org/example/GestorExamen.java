package org.example;

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
    public GestorExamen() {

    }

    public void empezarExamen() {
        crearNuevoExamen();
        mostrarPreguntas();
    }

    public void crearNuevoExamen() {
        examenActual = new Examen(getCantidadExamenes(), preguntas.length());
        examenes.add(examenActual);
    }

    public void mostrarPreguntas() {
        for (int i = 0; i < preguntas.length(); i++) {
            int numero = (i + 1);
            JSONObject preguntaActual = preguntas.getJSONObject("" + numero);
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
        System.out.println("Has tenido " + examenActual.getPreguntasCorrectas() + " preguntas correctas");
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
        String examenFormato = "│ %-9s │ %-11s │ %-8s │";
        System.out.println("├───────────┤─────────────┤──────────┤");
        System.out.printf(examenFormato, examen.getIntento(), examen.getPreguntasCorrectas() + "/" + examen.getPreguntasTotales(), examen.getNota());
        System.out.println();
    }

    public int getCantidadExamenes() {
        return (examenes.isEmpty()) ? 1 : (examenes.size() + 1);
    }
}
