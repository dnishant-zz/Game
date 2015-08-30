package com.game.firstgame;

/**
 * Created by Nishant on 8/29/2015.
 */
public class Speed {
    static final int RIGHT_DIRECTION = 1;
    static final int LEFT_DIRECTION = -1;
    static final int TOP_DIRECTION = -1;
    static final int BOTTOM_DIRECTION = 1;

    private float xv = 1;
    private float yv = 1;
    private int xDirection = RIGHT_DIRECTION;
    private int yDirection = BOTTOM_DIRECTION;

    public float getXv() {
        return xv;
    }

    public void setXv(float xv) {
        this.xv = xv;
    }

    public float getYv() {
        return yv;
    }

    public void setYv(float yv) {
        this.yv = yv;
    }

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public void toggleXDirection(){
        xDirection *= -1;
    }

    public void toggleYDirection(){
        yDirection *= -1;
    }
}
