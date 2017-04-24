package ch17.exam25;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class RootController implements Initializable {

    @FXML
    private MediaView mediaView;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnStop;
    @FXML
    private Slider sliderVolume;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator progressIndicator;    
    @FXML
    private Label labelTime;
    
    private boolean endOfMedia;    
    @FXML
    private Slider progressSlider;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media media = new Media(getClass().getResource("media/video.m4v").toString());
//        Media media = new Media(getClass().getResource("media/audio.wav").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        
        mediaPlayer.setOnReady( ()-> {
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);
            labelTime.setText(0 + "/" + (int)mediaPlayer.getTotalDuration().toSeconds() + " sec");
        });
        mediaPlayer.setOnPlaying( () -> {
            btnPlay.setDisable(true);
            btnPause.setDisable(false);
            btnStop.setDisable(false);            
        });
        mediaPlayer.setOnPaused( ()->{
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(false);  
        });
        mediaPlayer.setOnStopped(()->{
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);              
        });
        mediaPlayer.setOnEndOfMedia(()->{
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);  
            endOfMedia = true;
            progressBar.setProgress(1);
            progressIndicator.setProgress(1);
        });        
        
        btnPlay.setOnAction( e -> {
            if(endOfMedia){
                mediaPlayer.stop();
                // 시작 시간을 찾아라, 돌아가라
                mediaPlayer.seek(mediaPlayer.getStartTime());
            }
            mediaPlayer.play();
            endOfMedia = false;
        });
        btnPause.setOnAction( e -> mediaPlayer.pause());
        btnStop.setOnAction( e -> mediaPlayer.stop());     
        
        sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(newValue.doubleValue()/100.0);
            }           
        });
        sliderVolume.setValue(50);
        
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
//                System.out.println(newValue.toSeconds());
                double progress = newValue.toSeconds()/mediaPlayer.getTotalDuration().toSeconds();
                progressBar.setProgress(progress);
                progressIndicator.setProgress(progress);
                labelTime.setText((int)newValue.toSeconds() + "/" + (int)mediaPlayer.getTotalDuration().toSeconds() + " sec");
                
                // progressBar, progressIndicator 처럼 재생시 바를 이동시키기 위해 값을 부여함
                // slider는 0~100까지의 값을 가지기 때문에 100을 곱함
                progressSlider.setValue(progress*100);
            }            
        });     
        
        progressSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {                
                // 미디어의 전체 시간 (단위: sec)
                double totalSec = mediaPlayer.getTotalDuration().toSeconds();
                // slider의 현재 값
                double newProgress = newValue.doubleValue();
                // slider의 이전 값
                double oldProgress = oldValue.doubleValue();
                // slider의 슬라이더를 움직으려고 할때
                if(progressSlider.isValueChanging()){
                    // 미디어의 전체 길이에 대한 현재 slider의 비율로 미디어의 위치를 찾음
                    mediaPlayer.seek(Duration.seconds(totalSec * newProgress / 100));                    
                } else if(Math.abs(newProgress - oldProgress) > 0.5) {
                    // 영상이 엄청 길어지게 되면 이것만으로는 안됨 / 위에 것이 필요
                    // 영상의 이동 비율이 0.5% 이상일 때만 미디어의 위치를 이동시킴 
                    mediaPlayer.seek(Duration.seconds(totalSec * newProgress / 100));
                }
            }           
        });
    }   
}