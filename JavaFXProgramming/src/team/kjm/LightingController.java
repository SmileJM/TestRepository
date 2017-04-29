package team.kjm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class LightingController implements Initializable {

    @FXML
    private BorderPane borderRoomBoiler;
    @FXML
    private ImageView imgRoomBoiler;
    @FXML
    private BorderPane borderRoomUtility;
    @FXML
    private ImageView imgRoomUtility;
    @FXML
    private BorderPane borderRoom1;
    @FXML
    private ImageView imgRoom1;
    @FXML
    private BorderPane borderRoomKitchen;
    @FXML
    private ImageView imgRoomKitchen;
    @FXML
    private BorderPane borderRoomLiving;
    @FXML
    private ImageView imgRoomLiving;
    @FXML
    private BorderPane borderRoomBath;
    @FXML
    private ImageView imgRoomBath;
    @FXML
    private BorderPane borderRoomEntrance;
    @FXML
    private ImageView imgRoomEntrance;
    @FXML
    private BorderPane borderRoom2;
    @FXML
    private ImageView imgRoom2;
    @FXML
    private BorderPane borderRoom3;
    @FXML
    private ImageView imgRoom3;
    @FXML
    private ImageView imgLightOn;
    @FXML
    private ImageView imgLightOff;

    private ObservableList<Light> list;
    private String bulbOn = "images/light-bulb-icon.png";
    private String bulbOff = "images/bulb-icon.png";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        list.add(new Light("boiler", false));
        list.add(new Light("utility", false));
        list.add(new Light("room1", false));
        list.add(new Light("room2", false));
        list.add(new Light("room3", false));
        list.add(new Light("bath", false));
        list.add(new Light("kitchen", false));
        list.add(new Light("living", false));
        list.add(new Light("entrance", false));

        borderRoomBoiler.setOnMouseClicked(e -> handleRoomBoiler(e));
        borderRoomUtility.setOnMouseClicked(e -> handleRoomUtility(e));
        borderRoom1.setOnMouseClicked(e -> handleRoom1(e));
        borderRoom2.setOnMouseClicked(e -> handleRoom2(e));
        borderRoom3.setOnMouseClicked(e -> handleRoom3(e));
        borderRoomBath.setOnMouseClicked(e -> handleRoomBath(e));
        borderRoomKitchen.setOnMouseClicked(e -> handleRoomKitchen(e));
        borderRoomLiving.setOnMouseClicked(e -> handleRoomLiving(e));
        borderRoomEntrance.setOnMouseClicked(e -> handleRoomEntrance(e));
        imgLightOn.setOnMouseClicked(e -> handleAllOn(e));
        imgLightOff.setOnMouseClicked(e -> handleAllOff(e));

    }

    private void handleRoomBoiler(MouseEvent e) {
        if (list.get(0).getOnOff() == false) {
            imgRoomBoiler.setImage(new Image(getClass().getResource(bulbOn).toString()));
            list.get(0).setOnOff(true);
        } else if (list.get(0).getOnOff() == true) {
            imgRoomBoiler.setImage(new Image(getClass().getResource(bulbOff).toString()));
            list.get(0).setOnOff(false);
        }
    }

    private void handleRoomUtility(MouseEvent e) {
        if (list.get(1).getOnOff() == false) {
            imgRoomUtility.setImage(new Image(getClass().getResource(bulbOn).toString()));
            list.get(1).setOnOff(true);
        } else if (list.get(1).getOnOff() == true) {
            imgRoomUtility.setImage(new Image(getClass().getResource(bulbOff).toString()));
            list.get(1).setOnOff(false);
        }
    }

    private void handleRoom1(MouseEvent e) {
        if (list.get(2).getOnOff() == false) {
            imgRoom1.setImage(new Image(getClass().getResource(bulbOn).toString()));
            list.get(2).setOnOff(true);
        } else if (list.get(2).getOnOff() == true) {
            imgRoom1.setImage(new Image(getClass().getResource(bulbOff).toString()));
            list.get(2).setOnOff(false);
        }
    }

    private void handleRoom2(MouseEvent e) {
        if (list.get(3).getOnOff() == false) {
            imgRoom2.setImage(new Image(getClass().getResource(bulbOn).toString()));
            list.get(3).setOnOff(true);
        } else if (list.get(3).getOnOff() == true) {
            imgRoom2.setImage(new Image(getClass().getResource(bulbOff).toString()));
            list.get(3).setOnOff(false);
        }
    }

    private void handleRoom3(MouseEvent e) {
        if (list.get(4).getOnOff() == false) {
            imgRoom3.setImage(new Image(getClass().getResource(bulbOn).toString()));
            list.get(4).setOnOff(true);
        } else if (list.get(4).getOnOff() == true) {
            imgRoom3.setImage(new Image(getClass().getResource(bulbOff).toString()));
            list.get(4).setOnOff(false);
        }
    }

    private void handleRoomBath(MouseEvent e) {
        if (list.get(5).getOnOff() == false) {
            imgRoomBath.setImage(new Image(getClass().getResource(bulbOn).toString()));
            list.get(5).setOnOff(true);
        } else if (list.get(5).getOnOff() == true) {
            imgRoomBath.setImage(new Image(getClass().getResource(bulbOff).toString()));
            list.get(5).setOnOff(false);
        }
    }

    private void handleRoomKitchen(MouseEvent e) {
        if (list.get(6).getOnOff() == false) {
            imgRoomKitchen.setImage(new Image(getClass().getResource(bulbOn).toString()));
            list.get(6).setOnOff(true);
        } else if (list.get(6).getOnOff() == true) {
            imgRoomKitchen.setImage(new Image(getClass().getResource(bulbOff).toString()));
            list.get(6).setOnOff(false);
        }
    }

    private void handleRoomLiving(MouseEvent e) {
        if (list.get(7).getOnOff() == false) {
            imgRoomLiving.setImage(new Image(getClass().getResource(bulbOn).toString()));
            list.get(7).setOnOff(true);
        } else if (list.get(7).getOnOff() == true) {
            imgRoomLiving.setImage(new Image(getClass().getResource(bulbOff).toString()));
            list.get(7).setOnOff(false);
        }
    }

    private void handleRoomEntrance(MouseEvent e) {
        if (list.get(8).getOnOff() == false) {
            imgRoomEntrance.setImage(new Image(getClass().getResource(bulbOn).toString()));
            list.get(8).setOnOff(true);
        } else if (list.get(8).getOnOff() == true) {
            imgRoomEntrance.setImage(new Image(getClass().getResource(bulbOff).toString()));
            list.get(8).setOnOff(false);
        }
    }

    private void handleAllOn(MouseEvent e) {
        imgRoomBoiler.setImage(new Image(getClass().getResource(bulbOn).toString()));
        imgRoomUtility.setImage(new Image(getClass().getResource(bulbOn).toString()));
        imgRoom1.setImage(new Image(getClass().getResource(bulbOn).toString()));
        imgRoom2.setImage(new Image(getClass().getResource(bulbOn).toString()));
        imgRoom3.setImage(new Image(getClass().getResource(bulbOn).toString()));
        imgRoomBath.setImage(new Image(getClass().getResource(bulbOn).toString()));
        imgRoomKitchen.setImage(new Image(getClass().getResource(bulbOn).toString()));
        imgRoomLiving.setImage(new Image(getClass().getResource(bulbOn).toString()));
        imgRoomEntrance.setImage(new Image(getClass().getResource(bulbOn).toString()));
        
        for(int i=0; i<list.size(); i++) {
            list.get(i).setOnOff(true);    
        }
    }

    private void handleAllOff(MouseEvent e) {
         imgRoomBoiler.setImage(new Image(getClass().getResource(bulbOff).toString()));
        imgRoomUtility.setImage(new Image(getClass().getResource(bulbOff).toString()));
        imgRoom1.setImage(new Image(getClass().getResource(bulbOff).toString()));
        imgRoom2.setImage(new Image(getClass().getResource(bulbOff).toString()));
        imgRoom3.setImage(new Image(getClass().getResource(bulbOff).toString()));
        imgRoomBath.setImage(new Image(getClass().getResource(bulbOff).toString()));
        imgRoomKitchen.setImage(new Image(getClass().getResource(bulbOff).toString()));
        imgRoomLiving.setImage(new Image(getClass().getResource(bulbOff).toString()));
        imgRoomEntrance.setImage(new Image(getClass().getResource(bulbOff).toString()));
        
        for(int i=0; i<list.size(); i++) {
            list.get(i).setOnOff(false);    
        }
    }
}
