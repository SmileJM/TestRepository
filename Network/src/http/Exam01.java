package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class Exam01 {

    // Field
    // Constructor
    // Method
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://192.168.3.113:8080/IoTWebProgramming/http/exam01");

            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    try {
                        InputStreamReader isr = new InputStreamReader(instream);
                        BufferedReader br = new BufferedReader(isr);
                        String json = "";
                        while (true) {
                            String data = br.readLine();
                            if (data == null) {
                                break;
                            }
                            json += data;
                        }
                        JSONObject jsonObject = new JSONObject(json);
                        String temperature = jsonObject.getString("temperature");
                        System.out.println("temperature: " + temperature);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        instream.close();
                    }
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
    }
}
