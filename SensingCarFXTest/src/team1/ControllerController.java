package team1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.Duration;
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
	private Button btnBackTireStop;
	@FXML
	private Button btnBackTireForward;
	@FXML
	private Button btnBackTireBackward;

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
	@FXML
	private Group backTire;
	@FXML
	private TextField txtLcdLine0;
	@FXML
	private TextField txtLcdLine1;
	@FXML
	private Button btnLcdSend;
	@FXML
	private Button btnLcdClear;
	@FXML
	private Label lblLcdLine0;
	@FXML
	private Label lblLcdLine1;
	// 공통으로 사용하는 필드 값 정리
	private String ipAdress = "192.168.0.6";
	private CoapClient coapClient;
	private CoapResponse coapResponse;
	private JSONObject jsonObject;
	private String json;

	private int speed;
	private String direction = "forward";

	private RotateTransition rotate;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		rotate = new RotateTransition();

		// 초기값 읽기		
		frontTireState();
		backTireState();
		lcdState();

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

		// backTiteSpeed 속성감시
		sliderSpeed.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				speed = newValue.intValue();
				backTireRolling(speed);
				lblSpeed.setText(String.valueOf(speed));
				lblDirection.setText(direction);

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

		// backTiteDirection 속성감시
		lblDirection.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				direction = newValue;

				lblSpeed.setText(String.valueOf(speed));
				lblDirection.setText(direction);
				backTireRolling(speed);
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

		// FrontTire
		btnFrontTireClear.setOnAction(e -> handleBtnFrontTireClear());
		btnHandleL.setOnAction(e -> handleBtnHandleL());
		btnHandleR.setOnAction(e -> handleBtnHandleR());
		// BackTire
		btnSpeed1.setOnAction(e -> handleBtnSpeed(1));
		btnSpeed2.setOnAction(e -> handleBtnSpeed(2));
		btnSpeed3.setOnAction(e -> handleBtnSpeed(3));
		btnSpeed4.setOnAction(e -> handleBtnSpeed(4));
		btnSpeed5.setOnAction(e -> handleBtnSpeed(5));
		btnSpeed6.setOnAction(e -> handleBtnSpeed(6));
		btnBackTireForward.setOnAction(e -> handleBtnDirection("run"));
		btnBackTireStop.setOnAction(e -> handleBtnDirection("stop"));
		btnBackTireBackward.setOnAction(e -> handleBtnDirection("back"));
		// LCD
		btnLcdSend.setOnAction(e -> handleBtnLcdSend());
		btnLcdClear.setOnAction(e -> handleBtnLcdClear());
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
		lblDirection.setText(jsonObject.getString("direction"));
		sliderSpeed.setValue(jsonObject.getDouble("speed"));
		speed = jsonObject.getInt("speed");
		direction = jsonObject.getString("direction");
		backTireRolling(speed);
	}

	private void lcdState() {
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAdress + "/lcd");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();

		jsonObject = new JSONObject(json);
		lblLcdLine0.setText(jsonObject.getString("line0"));
		lblLcdLine1.setText(jsonObject.getString("line1"));
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
			lblDirection.setText("forward");
			sliderSpeed.setValue(speed);
		} else if (direction.equals("back")) {
			this.direction = "backward";
			lblDirection.setText("backward");
			sliderSpeed.setValue(speed);
		} else if (direction.equals("stop")) {
			sliderSpeed.setValue(0);
			backTireStop();
		}
	}

	// backTire 회전 애니메이션
	private void backTireRolling(int speed) {
		if (rotate != null) {
			rotate.stop();
			rotate = null;
			rotate = new RotateTransition();
		}
		if (direction.equals("backward")) {
			rotate.setByAngle(360);
		} else {
			rotate.setByAngle(-360);
		}
		rotate.setDuration(Duration.millis(1000));
		rotate.setRate(speed / 1000.0);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setNode(backTire);
		rotate.setCycleCount(Integer.MAX_VALUE);
		rotate.play();
	}

	private void backTireStop() {
		rotate.stop();
	}

	private void handleBtnLcdSend() {
		String line0 = txtLcdLine0.getText();
		String line1 = txtLcdLine1.getText();
		lblLcdLine0.setText(line0);
		lblLcdLine1.setText(line1);

		jsonObject = new JSONObject();
		jsonObject.put("command", "change");
		jsonObject.put("line0", line0);
		jsonObject.put("line1", line1);
		json = jsonObject.toString();

		coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAdress + "/lcd");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		coapClient.shutdown();
	}

	private void handleBtnLcdClear() {
		txtLcdLine0.setText("");
		txtLcdLine1.setText("");
	}
}
