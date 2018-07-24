package sample;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;


public class Model {

    private StackPane pane;
    private Snake snake;
    private ArrayList<Snake> snakesBody;
    private Controller controller;
    private int counter = 0;

    public Model(Controller controller, StackPane pane, Snake snake) {
        this.pane = pane;
        this.snake = snake;
        snakesBody = new ArrayList<>();
        snakesBody.add(snake);
        this.controller = controller;
    }

    /*===== MOVING SNAKE AROUND =====*/

    public void moveSnake() {
        if (snake.getMoveRight()) {
            snake.setTranslateX(snake.getTranslateX() + 40);
            if (snake.getTranslateX() == 400) {
                snake.setTranslateX(-360);
            }
        } else if (snake.getMoveLeft()) {
            snake.setTranslateX(snake.getTranslateX() - 40);
            if (snake.getTranslateX() == -400) {
                snake.setTranslateX(360);
            }
        } else if (snake.getMoveUp()) {
            snake.setTranslateY(snake.getTranslateY() - 40);
            if (snake.getTranslateY() == -400) {
                snake.setTranslateY(360);
            }
        } else if (snake.getMoveDown()) {
            snake.setTranslateY(snake.getTranslateY() + 40);
            if (snake.getTranslateY() == 400) {
                snake.setTranslateY(-360);
            }
        }
    }

    /*===== CONTROLLING SNAKE =====*/

    public void turnSnakeRight() {
        if (!snake.getMoveLeft()) {
            snake.setMoveUp(false);
            snake.setMoveDown(false);
            snake.setMoveLeft(false);
            snake.setMoveRight(true);
        }
    }

    public void turnSnakeLeft() {
        if (!snake.getMoveRight()) {
            snake.setMoveUp(false);
            snake.setMoveRight(false);
            snake.setMoveDown(false);
            snake.setMoveLeft(true);
        }
    }

    public void turnSnakeUp() {
        if (!snake.getMoveDown()) {
            snake.setMoveRight(false);
            snake.setMoveLeft(false);
            snake.setMoveDown(false);
            snake.setMoveUp(true);
        }
    }

    public void turnSnakeDown() {
        if (!snake.getMoveUp()) {
            snake.setMoveRight(false);
            snake.setMoveLeft(false);
            snake.setMoveDown(false);
            snake.setMoveDown(true);
        }
    }

    /*===== HANDLING THE FRUIT =====*/

    public void generateFruit() {
        Random random = new Random();
        Rectangle fruit = new Rectangle(40, 40);
        double x = (random.nextInt(18) - 9) * 40;
        double y = (random.nextInt(18) - 9) * 40;
        fruit.setTranslateX(x);
        fruit.setTranslateY(y);
        fruit.setFill(Color.RED);
        fruit.setId("fruit");

        if(!checkIfCollides(fruit))
            pane.getChildren().add(fruit);
        else
            generateFruit();
    }

    public void eatFruit() {
        pane.getChildren().remove(pane.lookup("#fruit"));
        growSnake();
        generateFruit();
        counter++;
        controller.updateScore(counter);
    }

    private void growSnake() {
        Snake bodyPart = new Snake();
        bodyPart.setTranslateX(snakesBody.get(counter).getTranslateX());
        bodyPart.setTranslateY(snakesBody.get(counter).getTranslateY());
        bodyPart.setWidth(40);
        bodyPart.setHeight(40);
        bodyPart.setFill(Color.GREEN);
        pane.getChildren().add(bodyPart);
        snakesBody.add(bodyPart);
    }

    public void renderSnakesBody() {
        for (int i = 1; i < snakesBody.size(); i++) {
            if(snakesBody.get(i) != null){
                snakesBody.get(i).setTranslateX(snakesBody.get(i - 1).getOldX());
                snakesBody.get(i).setTranslateY(snakesBody.get(i - 1).getOldY());
                snakesBody.get(i - 1).updatePosition();
            }
        }
    }

    public boolean checkIfCollides(Node node){
        for(int s = 1; s < snakesBody.size(); s++){
            if(snakesBody.get(s) != null) {
                if (node.getTranslateX() == snakesBody.get(s).getTranslateX() &&
                        node.getTranslateY() == snakesBody.get(s).getTranslateY())
                    return true;
            }
        }
        return false;
    }
}