
package ch17.exam23;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class RootController implements Initializable {

    @FXML
    private ListView<String> listView;
    @FXML
    private TableView<Phone> tableView;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCalcel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> data1 = FXCollections.observableArrayList();
        data1.add("갤럭시S1");
        data1.add("갤럭시S2");
        data1.add("갤럭시S3");
        data1.add("갤럭시S4");
        data1.add("갤럭시S5");
        data1.add("갤럭시S6");
        listView.setItems(data1);
        
         listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                tableView.getSelectionModel().select(newValue.intValue());
                tableView.scrollTo(newValue.intValue());
            }            
        });   
        //-----------------------------------------------------------------------------------------------------------------
        TableColumn tc0 = tableView.getColumns().get(0);
        TableColumn tc1 = tableView.getColumns().get(1);
        
        tc0.setCellValueFactory(new PropertyValueFactory<Phone, String>("name"));
        tc1.setCellValueFactory(new PropertyValueFactory<Phone, String>("image"));   
        
        ObservableList<Phone> list = FXCollections.observableArrayList();
        list.add(new Phone("phone01.png", "갤럭시S1", "첫번째 모델"));
        list.add(new Phone("phone02.png", "갤럭시S2", "두번째 모델"));
        list.add(new Phone("phone03.png", "갤럭시S3", "세번째 모델"));
        list.add(new Phone("phone04.png", "갤럭시S4", "네번째 모델"));
        list.add(new Phone("phone05.png", "갤럭시S5", "다섯번째 모델"));
        list.add(new Phone("phone06.png", "갤럭시S6", "여섯번째 모델"));
        tableView.setItems(list);
        
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {
            @Override
            public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
                imageView.setImage(new Image(getClass().getResource("images/" + newValue.getImage()).toString()));
            }            
        });
    }        
}
