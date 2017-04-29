package team.kjm.back.kjm2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class RootController implements Initializable {

    @FXML
    private Button btnList;
    @FXML
    private ListView<String> listView1;
    @FXML
    private Button btnFanOn;
    @FXML
    private Button btnFanAuto;
    @FXML
    private Button btnModeHeat;
    @FXML
    private Button btnModeAuto;
    @FXML
    private Button btnModeCool;
    @FXML
    private Button btnModeOff;
    @FXML
    private ProgressIndicator progressTemp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView1.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                ListCell<String> listCell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) return;
                        
                        try {
                            HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("item.fxml"));
                            Label room = (Label) hbox.lookup("#room");
                            Label temp = (Label) hbox.lookup("#temp");
                            
                            
                            setGraphic(temp);
                        } catch (IOException ex) {
                            
                        }
                        
                    }
                };
                return listCell;
            }
        });
        
        ObservableList<String> value = FXCollections.observableArrayList();
        value.add("Master Bedroom");        
        value.add("Living Room");        
        listView1.setItems(value);
    }
}
