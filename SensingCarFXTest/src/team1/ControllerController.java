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
	// 공통으로 사용하는 필드 값 정리

	private String ipAdress = "192.168.0.5";
	private CoapClient coapClient;
	private CoapResponse coapResponse;
	private JSONObject jsonObject;
	private String json;
	

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
		btnFrontTireClear.setOnAction(e -> handleBtnFrontTireClear());
		btnHandleL.setOnAction(e -> handleBtnHandleL());
		btnHandleR.setOnAction(e -> handleBtnHandleR());
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

	private void handleBtnFrontTireClear() {
		sliderFrontTire.setValue(90);
	}

	private void handleBtnHandleL() {
		sliderFrontTire.setValue(60);
	}

	private void handleBtnHandleR() {
		sliderFrontTire.setValue(120);
	}
}
