package http.exam01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class HttpGetClient {

    public static void main(String[] args) throws IOException {
        // 코드상으로 닫을 수 있는 HttpClient
        // httpClient - 통신 주체
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // get 방식으로 uri 를 제공할 때
            URIBuilder uriBuilder = new URIBuilder("http://192.168.3.113:8080/IoTWebProgramming/http/exam01");
            // param 값 추가
            uriBuilder.setParameter("thermistor", String.valueOf(25));
            uriBuilder.setParameter("photoresistor", String.valueOf(200));
            // get 방식으로 통신 할 때
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // execute() 를 할 때 실행을 하게 됨
            // 요청을 하면 동기 방식으로 응답을 받음(응답이 올 때 까지 기다림)
            CloseableHttpResponse response = httpClient.execute(httpGet);

            System.out.println("Executing request " + httpGet.getRequestLine());
            try {
                // HttpEntity 는 body 에 있는 내용을 가지고 있는 객체 (시작, 헤더, 바디 행 중에서)
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    // getContent() 로 바디의 내용을 읽어와서 InputStream을 얻음
                    InputStream is = resEntity.getContent();
                    try {
                        // InputStream 통해서 읽은 데이터를 json에 저장할 것
                        String json = "";
                        // json 으로 받는 데이터는 모두 문자열이기 때문에 reader 를 사용함
                        InputStreamReader isr = new InputStreamReader(is);
                        // BufferedReader 을 사용하면 readLine() 을 사용해서 1줄씩 읽을 수 있는 장점이 있음
                        BufferedReader br = new BufferedReader(isr);
                        while (true) {
                            String data = br.readLine();
                            if (data == null) {
                                break;
                            }
                            json += data;
                        }
                        // json 문자열을 파싱하기 위한 코드
                        JSONObject jsonObject = new JSONObject(json);
                        double thermistor = jsonObject.getDouble("thermistor");
                        double photoresistor = jsonObject.getDouble("photoresistor");
                        System.out.println("thermistor: " + thermistor);
                        System.out.println("photoresistor: " + photoresistor);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        is.close();
                    }
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException ex) {
            Logger.getLogger(HttpGetClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            httpClient.close();
        }
    }
}
