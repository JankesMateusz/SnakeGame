package sample;

import javafx.scene.shape.Rectangle;

public class Snake extends Rectangle {

    private boolean moveRight, moveLeft, moveUp, moveDown;
    private double oldX, oldY;

    /*===== SETTERS =====*/

    public void setMoveRight(Boolean set){
        moveRight = set;
    }

    public void setMoveLeft(Boolean set){
        moveLeft = set;
    }

    public void setMoveUp(Boolean set){
        moveUp = set;
    }

    public void setMoveDown(Boolean set){
        moveDown = set;
    }

     /*===== GETTERS =====*/

    public boolean getMoveRight(){
        return moveRight;
    }

    public boolean getMoveLeft(){
        return moveLeft;
    }

    public boolean getMoveUp(){
        return moveUp;
    }

    public boolean getMoveDown(){
        return moveDown;
    }

    public double getOldX(){
        return oldX;
    }

    public double getOldY(){
        return oldY;
    }

    public void updatePosition(){
        oldX = this.getTranslateX();
        oldY = this.getTranslateY();
    }
}
