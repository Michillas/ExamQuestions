package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorExamen {
    private Examen examenActual;
    private ArrayList<Examen> examenes = new ArrayList();
    private GestorJson jsonLoader = new GestorJson();
    private final char[] abcde = {'A', 'B', 'C', 'D', 'E'};
    private Scanner scanner = new Scanner(System.in);
    private JSONObject preguntas = jsonLoader.getJsonObject().getJSONObject("pregunta");
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
            int eleccion;
            switch(scanner.nextLine().toUpperCase()) {
                case "A":
                    eleccion = 1;
                    break;
                case "B":
                    eleccion = 2;
                    break;
                case "C":
                    eleccion = 3;
                    break;
                case "D":
                    eleccion = 4;
                    break;
                case "E":
                    eleccion = 5;
                    break;
                default:
                    eleccion = 0;
                    break;
            }
            if (eleccion == eleccionCorrecta) {
                examenActual.setPreguntasCorrectas(examenActual.getPreguntasCorrectas() + 1);
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
                │  intento  │  preguntas  │   nota   │""");
            for(Examen examen : examenes) {
                mostrarInformacionExamen(examen);
            }
            System.out.println("└───────────┴─────────────┴──────────┘");
        }
    }

    public void mostrarInformacionExamen(Examen examen) {
        String examenFormato = "│ %-9s │ %-11s │ %-8s │";
        System.out.println("├───────────┤─────────────┤──────────┤");
        System.out.printf(examenFormato, examenActual.getIntento(), examenActual.getPreguntasCorrectas() + "/" + examenActual.getPreguntasTotales(), examenActual.getNota());
        System.out.println();
    }

    public int getCantidadExamenes() {
        return (examenes.isEmpty()) ? 1 : (examenes.size() + 1);
    }
}
