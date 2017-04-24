package ch17.homework3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private ListView<Game> listView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>() {
            @Override
            public ListCell<Game> call(ListView<Game> param) {
                ListCell<Game> listCell = new ListCell<Game>() {
                    @Override
                    protected void updateItem(Game item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) return ;
                        try {
                            HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("item.fxml"));
                            ImageView imgView = (ImageView) hbox.lookup("#imgView");
                            ImageView imgScore = (ImageView) hbox.lookup("#imgScore");
                            Label txtName = (Label) hbox.lookup("#txtName");
                            Label txtCompany = (Label) hbox.lookup("#txtCompany");
                            Label txtRank = (Label) hbox.lookup("#txtRank");                            
                            
                            imgView.setImage(new Image(getClass().getResource("images/" + item.getView()).toString()));
                            imgScore.setImage(new Image(getClass().getResource("images/star" + item.getScore() +".png").toString()));                           
                            txtName.setText(item.getName());
                            txtCompany.setText(item.getCompany());
                            txtRank.setText(String.valueOf(item.getRank()));
                            
                            setGraphic(hbox);
                            
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }                        
                    }
                };
                return listCell;
            }
        });
        
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Game>() {
            @Override
            public void changed(ObservableValue<? extends Game> observable, Game oldValue, Game newValue) {
                String view = newValue.getView();
                String name = newValue.getName();
                String company = newValue.getCompany();
                int score = newValue.getScore();
                int rank = newValue.getRank();

                System.out.print("[" +rank + "] ");
                System.out.print("[" +name + "] ");
                System.out.print("[제작사: " +company + "] ");
                System.out.print("[평점: " +score + "] ");
                System.out.println("[" +view + "] ");
            }            
        });
        // 데이터 세팅
        ObservableList<Game> value = FXCollections.observableArrayList();             
        value.add(new Game(1, "Yu-Gi-Oh! Duel Links", "KONAMI", "game01.png", 10));
        value.add(new Game(2, "아우라킹덤", "X-LEGEND KOREA", "game02.png", 9));
        value.add(new Game(3, "라그나로크R", "GRAVITY Co., Ltd", "game03.png", 8));
        value.add(new Game(4, "여명 for Kakao", "Kakao Corp", "game04.png", 7));
        value.add(new Game(5, "밀크초고 -온라인 FPS", "GameParadiso", "game05.png", 6));
        value.add(new Game(6, "별이되어라! for Kakao", "GAMEVIL", "game06.png", 5));
        value.add(new Game(7, "표창키우기", "마우스덕", "game07.png", 4));
        listView.setItems(value);        
    }    
}