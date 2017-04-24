
package ch17.exam21;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class RootController implements Initializable {

    @FXML
    private ListView<Food> listView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        listView.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>() {
            @Override
            public ListCell<Food> call(ListView<Food> param) {                
                ListCell<Food> listCell = new ListCell<Food>(){
                    @Override
                    protected void updateItem(Food item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) return;
                        try {                            
                            HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("item.fxml"));
                            
                            ImageView foodImage = (ImageView) hbox.lookup("#foodImage");
                            Label name = (Label) hbox.lookup("#name");
                            ImageView starImage = (ImageView) hbox.lookup("#starImage");
                            Label description = (Label) hbox.lookup("#description");                            
                            
                            foodImage.setImage(new Image(getClass().getResource("images/" + item.getImage()).toString()));
                            name.setText(item.getName());
                            description.setText(item.getDesciption());
                            starImage.setImage(new Image(getClass().getResource("images/star" + item.getScore() +".png").toString()));;
                            setGraphic(hbox);
                            
                        } catch (IOException ex) {
                            Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }                    
                };
                return listCell;
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Food>() {
            @Override
            public void changed(ObservableValue<? extends Food> observable, Food oldValue, Food newValue) {
                String foodImage = newValue.getImage();
                String name = newValue.getName();
                int socre = newValue.getScore();
                String description = newValue.getDesciption();
                
                System.out.println(name + "-평점(" + socre + "), 평가(" + description + ")");         
                System.out.println();
            }            
        });
        ObservableList<Food> value = FXCollections.observableArrayList();
        value.add(new Food("food01.png", "삼겹살", 10, "삼겹살은 언제나 맛있죠"));
        value.add(new Food("food02.png", "매운장어구이", 5, "매워요"));
        value.add(new Food("food03.png", "장어구이", 8, "정말 맛있어요~"));
        value.add(new Food("food04.png", "비빔밥", 2, "별로에요"));
        value.add(new Food("food05.png", "볶음밥", 7, "무난합니다"));
        
        listView.setItems(value);
        
        
        ///////
        
        listView.setOnMouseClicked( e->{
//            System.out.println("MouseClicked");
            Food food = listView.getSelectionModel().getSelectedItem();
            System.out.println(food.getName());
        });
    }        
}
