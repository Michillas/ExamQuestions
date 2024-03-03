package es.michillas;

import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GestorJson {

    // preguntas.json es tipo test, preguntas2.json tipo tarjeta.
    private File file = new File("preguntas.json");
    private String filePath = file.getAbsolutePath();

    private JSONObject jsonObject;

    public GestorJson() {
        try {
            cargarContenidoJson();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarContenidoJson() throws IOException {
        String jsonString = "{}";
        FileReader reader = new FileReader(filePath);
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        reader.close();
        jsonString = sb.toString();
        jsonObject = new JSONObject(jsonString);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
