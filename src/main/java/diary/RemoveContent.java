package diary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoveContent {

    public RemoveContent() throws IOException {
        // Формируем URL с query-параметрами
        String urlStr = String.format("http://localhost:8080/api/delete_content?token=%s", getTokenJson());
        URL url = new URL(urlStr);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setDoOutput(true);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200 || responseCode == 204) {
            System.out.println("Задача успешно удалена!");
        } else {
            System.out.println("Ошибка при удалении: " + responseCode);
        }

        connection.disconnect();
    }
    // Сохранить ключ в json
    public String getTokenJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Получаем поток из ресурсов JAR
        InputStream is = getClass().getResourceAsStream("/tokens.json");
        if (is == null) throw new RuntimeException("tokens.json не найден в ресурсах!");
        ObjectNode jsonNodes = (ObjectNode) objectMapper.readTree(is);
        return jsonNodes.get("token").asText();
    }
}