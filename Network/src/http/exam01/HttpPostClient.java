package http.exam01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class HttpPostClient {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost("http://192.168.3.113:8080/IoTWebProgramming/http/exam01");

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("thermistor", String.valueOf(25)));
            params.add(new BasicNameValuePair("photoresistor", String.valueOf(200)));
            HttpEntity reqEntity = new UrlEncodedFormEntity(params, Charset.forName("UTF-8"));
            httpPost.setEntity(reqEntity);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    InputStream is = resEntity.getContent();
                    try {
                        String json = "";
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        while (true) {
                            String data = br.readLine();
                            if (data == null) {
                                break;
                            }
                            json += data;
                        }
                        JSONObject jsonObject = new JSONObject(json);
                        double thermistor = jsonObject.getDouble("thermistor");
                        double photoresistor = jsonObject.getDouble("photoresistor");
                        System.out.println("thermistor: " + thermistor);
                        System.out.println("photoresistor" + photoresistor);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        is.close();
                    }
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
    }
}
