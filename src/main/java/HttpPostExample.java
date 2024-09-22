import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostExample {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://httpbin.org/post");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = "{ \"id\": 1, \"nome\": \"João\", \"nota\": 70 }";

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Código de resposta: " + responseCode);

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Resposta: " + response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
