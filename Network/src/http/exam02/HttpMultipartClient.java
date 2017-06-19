package http.exam02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;



public class HttpMultipartClient {

    public static void main(String[] args) throws IOException {
        String title = "제목";
        // (문자열, 타입, 인코딩)
        StringBody titleBody = new StringBody(title, ContentType.create("text/plain", Charset.forName("UTF-8")));
        String content = "내용";
        StringBody contentody = new StringBody(content, ContentType.create("text/plain", Charset.forName("UTF-8")));
        File attach = new File("C:/Temp/사막.jpg");
        FileBody attachBody = new FileBody(attach, ContentType.create("image/jpeg"));

        HttpPost httpPost = new HttpPost("http://192.168.3.113:8080/IoTWebProgramming/http/exam02");

        // MultipartEntityBuilder라이브러리가 필요함 (메이븐에서 jar 파일 받음)
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        // "title" 서버의 매개변수 이름과 같아야 함
        // 문자 파트
        multipartEntityBuilder.addPart("title", titleBody);
        multipartEntityBuilder.addPart("content", contentody);
        // 파일 파트
        // 파일 이름이 한글이 포함되어 있을 경우
        multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
        // 브라우저가 파일을 보내는 방식과 동일하게 함
        // 두가지를 다 해야 스프링에서 한글이 깨지지 않음
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        multipartEntityBuilder.addPart("attach", attachBody);
        // 멀티 파트 인코딩된 본문(body) 얻기
        HttpEntity reqEntity = multipartEntityBuilder.build();

        httpPost.setEntity(reqEntity);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        // execute() 를 할 때 실행을 하게 됨
        // 요청을 하면 동기 방식으로 응답을 받음
        CloseableHttpResponse response = httpClient.execute(httpPost);
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
                    String resTitle = jsonObject.getString("title");
                    String resContent = jsonObject.getString("content");
                    String originalfilename = jsonObject.getString("originalfilename");
                    String filecontenttype = jsonObject.getString("filecontenttype");
                    String savedfilename = jsonObject.getString("savedfilename");

                    System.out.println("title: " + resTitle);
                    System.out.println("content: " + resContent);
                    System.out.println("originalfilename: " + originalfilename);
                    System.out.println("filecontenttype: " + filecontenttype);
                    System.out.println("savedfilename: " + savedfilename);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    is.close();
                }
            }
        } finally {
            response.close();
        }
    }
}
