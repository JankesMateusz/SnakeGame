package sample;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


public class Controller{

    @FXML
    Snake snakesHead;

    @FXML
    StackPane mainPane;

    @FXML
    Label score;

    public void initialize(){
        Model model = new Model(this, mainPane, snakesHead);
        snakesHead.setFocusTraversable(true);
        snakesHead.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case RIGHT: model.turnSnakeRight(); break;
                case LEFT: model.turnSnakeLeft(); break;
                case DOWN: model.turnSnakeDown(); break;
                case UP: model.turnSnakeUp(); break;
            }
        });
        snakesHead.setMoveRight(true);
        model.generateFruit();
        AnimationTimer at = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if(now - lastUpdate >= 70000000) {
                    model.moveSnake();
                    lastUpdate = now;
                    if (mainPane.lookup("#fruit") != null) {
                        if ((snakesHead.getTranslateX() == mainPane.lookup("#fruit").getTranslateX()) &&
                                (snakesHead.getTranslateY() == mainPane.lookup("#fruit").getTranslateY()))
                            model.eatFruit();
                    }
                    model.renderSnakesBody();
                    if(model.checkIfCollides(snakesHead)) {
                        this.stop();
                    }
                }
            }
        };
        at.start();
    }

    public void updateScore(int value){
        score.setText("SCORE: " + Integer.toString(value));
    }

}
