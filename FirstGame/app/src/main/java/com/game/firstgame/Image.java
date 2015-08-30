package com.game.firstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Nishant on 8/29/2015.
 */
public class Image {
    private Bitmap bitmap;
    private int x,y;

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    Speed speed;

    private boolean touched;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image(Bitmap map, int x, int y){
        bitmap = map;
        this.x = x;
        this.y = y;
        speed = new Speed();
    }


    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public boolean isTouched() {
        return touched;
    }

    public void update(){
        x += speed.getXv()*speed.getxDirection();
        y += speed.getYv()*speed.getyDirection();
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,x-(bitmap.getWidth()/2),y-(bitmap.getHeight()/2),null);
    }

    public void handleActionDown(int eventX, int eventY){
        if(eventX > x-(bitmap.getWidth()/2) && eventX < x+(bitmap.getWidth()/2)){
            if(eventY > y-(bitmap.getHeight()/2) && eventY < y+(bitmap.getHeight()/2)){
                setTouched(true);
            }
            else {
                setTouched(false);
            }
        }
        else {
            setTouched(false);
        }
    }
}
