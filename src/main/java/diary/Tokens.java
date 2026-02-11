package diary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Tokens {
    public Tokens(String token) throws IOException {
        tokenSaveBase(saveTokenJson(token));
    }
    public void tokenSaveBase(String token) throws IOException {
        URL url = new URL("http://localhost:8080/api/create_user");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        String data = "token=" + URLEncoder.encode(token, StandardCharsets.UTF_8);
        try(OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(data.getBytes(StandardCharsets.UTF_8));
        }
        int responseCodes = connection.getResponseCode();
        System.out.println("http status: " + responseCodes);
    }
    // Сохранить ключ в json
    public String saveTokenJson(String token) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/tokens.json");
        if(is == null) new RuntimeException("Ошибка, не такого файла или каталога");
        ObjectNode jsonNodes = (ObjectNode) objectMapper.readTree(is);
        if(!jsonNodes.hasNonNull("token") || jsonNodes.get("token").asText().isEmpty()) {
            jsonNodes.put("token", token);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue((DataOutput) is, jsonNodes);
        }
        return jsonNodes.get("token").asText();
    }
}