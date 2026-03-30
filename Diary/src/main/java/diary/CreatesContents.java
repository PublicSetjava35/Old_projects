package diary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CreatesContents {

    public List<String> getContent(String token) throws IOException {
        URL url = new URL("http://localhost:8080/api/get_content?token=" + URLEncoder.encode(saveTokenJson(token), StandardCharsets.UTF_8));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
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
        List<TaskResponse> tasks = mapper.readValue(responseBody,
                new com.fasterxml.jackson.core.type.TypeReference<List<TaskResponse>>() {});
        return tasks.stream().map(TaskResponse::getContent).toList();
    }
    // Сохранить ключ в json
    public String saveTokenJson(String token) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/tokens.json");
        ObjectNode jsonNodes = (ObjectNode) objectMapper.readTree(is);
        if(!jsonNodes.hasNonNull("token") || jsonNodes.get("token").asText().isEmpty()) {
            jsonNodes.put("token", token);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue((DataOutput) is, jsonNodes);
        }
        return jsonNodes.get("token").asText();
    }
}