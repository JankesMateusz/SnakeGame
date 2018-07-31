package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    Button exitButton, startButton, topScoresButton;

    public void exit(){
        Platform.exit();
    }

    public void startGame(){
        ViewNavigator.loadView(ViewNavigator.VIEW_1);
    }
}
