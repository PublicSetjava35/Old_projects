package diary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PostExample {
    private String content1;
    public String saveContent(String content) throws IOException {
        URL url = new URL("http://localhost:8080/api/create_content");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        String data = "content=" + URLEncoder.encode(content, StandardCharsets.UTF_8)
                + "&token=" + URLEncoder.encode(getTokenJson(), StandardCharsets.UTF_8);
        try(OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(data.getBytes(StandardCharsets.UTF_8));
        }
        int responseCodes = connection.getResponseCode();
        System.out.println("http status: " + responseCodes);

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        String responseBody = sb.toString();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> response = mapper.readValue(responseBody, Map.class);
        return (String) response.get("content");
    }

    // Сохранить ключ в json
    public String getTokenJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/tokens.json");
        if(is == null) new RuntimeException("Ошибка, путь к файлу не найден");
        ObjectNode jsonNodes = (ObjectNode) objectMapper.readTree(is);
        return jsonNodes.get("token").asText();
    }
}