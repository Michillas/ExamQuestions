package org.example;

import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GestorJson {

    private File file = new File("preguntas.json");
    private String filePath = file.getAbsolutePath();

    private JSONObject jsonObject;

    public GestorJson() {
        cargarContenidoJson();
    }

    public void cargarContenidoJson() {
        String jsonString = "{}";
        try {
            FileReader reader = new FileReader(filePath);
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            jsonString = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            jsonObject = new JSONObject(jsonString);
        }
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
