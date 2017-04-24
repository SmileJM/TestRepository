package ch17.homework0.ex02;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class RootController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox<?> cmbPublic;
    @FXML
    private DatePicker dateExit;
    @FXML
    private TextArea txtContent;
    @FXML
    private Button btnReg;
    @FXML
    private Button btnCancel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String title = txtTitle.getText();
        String password = txtPassword.getText();
//        String strPublic = cmbPublic.
//        LocatDate title = dateExit.get();
        String content = txtContent.getText();
    }    
    
}
