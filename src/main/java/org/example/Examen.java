package org.example;

public class Examen {
    private final int intento;
    private final int preguntasTotales;
    private int preguntasCorrectas;
    public Examen(int intento, int preguntasTotales) {
        this.intento = intento;
        this.preguntasTotales = preguntasTotales;
    }

    public int getPreguntasCorrectas() {
        return preguntasCorrectas;
    }

    public void setPreguntasCorrectas(int preguntasCorrectas) {
        this.preguntasCorrectas = preguntasCorrectas;
    }

    public int getIntento() {
        return intento;
    }

    public int getPreguntasTotales() {
        return preguntasTotales;
    }

    public double getNota() {
        return Math.round(((double) preguntasCorrectas/preguntasTotales) * 10);
    }
}
