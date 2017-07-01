package team1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class ControllerController implements Initializable {

    // 컨트롤러 필드 정리
    @FXML
    private Label lblFrontTireAngle;
    @FXML
    private Slider sliderFrontTire;
    @FXML
    private Button btnFrontTireClear;
    @FXML
    private Group circleFrontControl;
    @FXML
    private Button btnHandleL;
    @FXML
    private Button btnHandleR;
    @FXML
    private Slider sliderSpeed;
    @FXML
    private Button btnBackTireRun;
    @FXML
    private Button btnBackTireStop;
    @FXML
    private Button btnBackTireBack;
    @FXML
    private Button btnSpeed1;
    @FXML
    private Button btnSpeed2;
    @FXML
    private Button btnSpeed3;
    @FXML
    private Button btnSpeed4;
    @FXML
    private Button btnSpeed5;
    @FXML
    private Button btnSpeed6;
    @FXML
    private Label lblSpeed;
    @FXML
    private Label lblDirection;

    // 공통으로 사용하는 필드 값 정리
    private String ipAdress = "192.168.0.5";
    private CoapClient coapClient;
    private CoapResponse coapResponse;
    private JSONObject jsonObject;
    private String json;

    private int speed;
    private String direction = "forward";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // 초기값 읽기			
        frontTireState();

        // CoAP 통신을 사용하여, 패널에서 입력한 값을 센싱카로 전달하기
        sliderFrontTire.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int angle = newValue.intValue();
                lblFrontTireAngle.setText(String.valueOf(angle) + " º");
                circleFrontControl.setRotate(angle * 2);

                jsonObject = new JSONObject();
                jsonObject.put("command", "change");
                jsonObject.put("angle", String.valueOf(angle));
                json = jsonObject.toString();

                coapClient = new CoapClient();
                coapClient.setURI("coap://" + ipAdress + "/fronttire");
                coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
                coapClient.shutdown();

            }
        });

        sliderSpeed.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                speed = newValue.intValue();
                if (speed < 0) {
                    direction = "backward";
                    speed = -speed;
                } else {
                    direction = "forward";
                }
                lblSpeed.setText(String.valueOf(speed));
                lblDirection.setText(String.valueOf(direction));

                jsonObject = new JSONObject();
                jsonObject.put("command", "change");
                jsonObject.put("direction", direction);
                jsonObject.put("speed", String.valueOf(speed));
                json = jsonObject.toString();

                coapClient = new CoapClient();
                coapClient.setURI("coap://" + ipAdress + "/backtire");
                coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
                coapClient.shutdown();
            }
        });

        btnFrontTireClear.setOnAction(e -> handleBtnFrontTireClear());
        btnHandleL.setOnAction(e -> handleBtnHandleL());
        btnHandleR.setOnAction(e -> handleBtnHandleR());
        btnSpeed1.setOnAction(e -> handleBtnSpeed(1));
        btnSpeed2.setOnAction(e -> handleBtnSpeed(2));
        btnSpeed3.setOnAction(e -> handleBtnSpeed(3));
        btnSpeed4.setOnAction(e -> handleBtnSpeed(4));
        btnSpeed5.setOnAction(e -> handleBtnSpeed(5));
        btnSpeed6.setOnAction(e -> handleBtnSpeed(6));
        btnBackTireRun.setOnAction(e -> handleBtnDirection("run"));
        btnBackTireStop.setOnAction(e -> handleBtnDirection("stop"));
        btnBackTireBack.setOnAction(e -> handleBtnDirection("back"));
    }

    private void frontTireState() {
        jsonObject = new JSONObject();
        jsonObject.put("command", "status");
        json = jsonObject.toString();

        coapClient = new CoapClient();
        coapClient.setURI("coap://" + ipAdress + "/fronttire");
        coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
        json = coapResponse.getResponseText();

        jsonObject = new JSONObject(json);
        lblFrontTireAngle.setText(jsonObject.getString("angle"));
        sliderFrontTire.setValue(jsonObject.getDouble("angle"));
        circleFrontControl.setRotate(jsonObject.getDouble("angle") * 2);
    }

    private void backTireState() {
        jsonObject = new JSONObject();
        jsonObject.put("command", "status");
        json = jsonObject.toString();

        coapClient = new CoapClient();
        coapClient.setURI("coap://" + ipAdress + "/backtire");
        coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
        json = coapResponse.getResponseText();

        jsonObject = new JSONObject(json);
        lblSpeed.setText(jsonObject.getString("speed"));
        lblDirection.setRotate(jsonObject.getDouble("direction"));
        sliderSpeed.setValue(jsonObject.getDouble("speed"));
    }

    private void handleBtnFrontTireClear() {
        sliderFrontTire.setValue(90);
    }

    private void handleBtnHandleL() {
        sliderFrontTire.setValue(60);
    }

    private void handleBtnHandleR() {
        sliderFrontTire.setValue(120);
    }

    private void handleBtnSpeed(int speed) {
        switch (speed) {
            case 1:
                sliderSpeed.setValue(1000);
                break;
            case 2:
                sliderSpeed.setValue(1600);
                break;
            case 3:
                sliderSpeed.setValue(2200);
                break;
            case 4:
                sliderSpeed.setValue(2800);
                break;
            case 5:
                sliderSpeed.setValue(3400);
                break;
            case 6:
                sliderSpeed.setValue(4000);
                break;
        }
    }

    private void handleBtnDirection(String direction) {
        if (direction.equals("run")) {
            this.direction = "forward";
            sliderSpeed.setValue(speed);
        } else if (direction.equals("back")) {
            this.direction = "backward";
            sliderSpeed.setValue(speed);
        } else if (direction.equals("stop")) {
            sliderSpeed.setValue(0);
        }
    }
}
