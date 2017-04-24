
package ch17.exam20;

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
    private ListView<Phone> listView;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 하나의 행을 만들어 주는 메소드,  CollBack 이 Factory 객체라고 생각
        // ListCell - ListCell 셀 객체를 의미, 신경쓰지 않기
        // 무엇을 만들어내기 때문에 Factory
        listView.setCellFactory(new Callback<ListView<Phone>, ListCell<Phone>>() {
            // call() 메소드는 ListView에 Phone 객체가 대입이 될때 자동 실행됨
            // ListCell(한 행) 객체를 만들어 줌
            @Override            
            public ListCell<Phone> call(ListView<Phone> param) {
                // ListCell 이 class 라서 상속, interface 이면 구현
                ListCell<Phone> listCell = new ListCell<Phone>() {
                    // ListCell 안에 무엇이 들어가는지 정의하는 메소드
                    @Override
                    protected void updateItem(Phone item, boolean empty) {
                        // 부모의 기능을 실행하기 위해서 
                        // 현재는 선택한 항목을 선택할 수 있는 기능이 부여 되어 있을 듯
                        super.updateItem(item, empty);
                        // 최초 실행시 폼 객체가 없으므로 한번 실행하게 됨
                        // 없으면 true를 리턴
                        if(empty) return;
                        try {
                            // 이 안에서 Phone의 정보를 얻어내서 ListCell 안의 내용을 만들어냄
                            // 1 외부에서 fxml로 만드는 방법, 불러서 작성
                            // Cell 에 들어갈 컨테이너 생성
                            HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("item.fxml"));
                            // Node 는 ImageView의 최상위 클래스, 그래서 형변환
                            // hbox.lookup("image") -> Node
                            ImageView phoneImage = (ImageView) hbox.lookup("#image");
                            Label phoneName = (Label) hbox.lookup("#name");
                            Label phoneContent = (Label) hbox.lookup("#content");
                            
                            phoneImage.setImage(new Image(getClass().getResource("images/" + item.getImage()).toString()));
                            phoneName.setText(item.getName());
                            phoneContent.setText(item.getContent());
                            
                            // Cell 의 내용으로 설정
                            setGraphic(hbox);
                            // 2 이 자리에서 코드로 만드는 방법
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }                
                };
                return listCell;
            }
        });
        
        // 선택 속성 감시
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {
            @Override
            public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
                System.out.print(newValue.getImage() + ": ");
                System.out.println(newValue.getName());
                System.out.println(newValue.getContent());
                System.out.println();
            }
            
        });
        
        // 데이터 세팅
        ObservableList<Phone> value = FXCollections.observableArrayList();
        value.add(new Phone("phone01.png", "갤럭시S1", "삼성 스마트폰의 최초 모델입니다."));
        value.add(new Phone("phone02.png", "갤럭시S2", "삼성 스마트폰의 두번째 모델입니다."));
        value.add(new Phone("phone03.png", "갤럭시S3", "삼성 스마트폰의 세번째 모델입니다."));
        
        listView.setItems(value);
    }    
}
