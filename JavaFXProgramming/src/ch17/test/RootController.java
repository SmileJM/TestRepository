/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch17.test;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class RootController implements Initializable {

    @FXML
    private TableView<Phone> tableView;
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         TableColumn tc0 = tableView.getColumns().get(0);
        TableColumn tc1 = tableView.getColumns().get(1);
        TableColumn tc2 = tableView.getColumns().get(2);       
        
        tc0.setCellValueFactory(new PropertyValueFactory<Phone, String>("name"));
        tc1.setCellValueFactory(new PropertyValueFactory<Phone, String>("image"));
        tc2.setCellValueFactory(new PropertyValueFactory<Phone, String>("content"));
        
        ObservableList<Phone> list = FXCollections.observableArrayList();
        list.add(new Phone("phone01.png", "갤럭시S1", "첫번째 모델"));
        list.add(new Phone("phone02.png", "갤럭시S2", "두번째 모델"));
        list.add(new Phone("phone03.png", "갤럭시S3", "세번째 모델"));
        list.add(new Phone("phone04.png", "갤럭시S4", "네번째 모델"));
        list.add(new Phone("phone05.png", "갤럭시S5", "다섯번째 모델"));
        
        tableView.setItems(list);
        imageView.setOpacity(0.5);
    }    
    
}
