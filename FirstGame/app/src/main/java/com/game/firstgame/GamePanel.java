package com.game.firstgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Nishant on 8/29/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private final static String TAG = GamePanel.class.getSimpleName();
    private GameThread thread;
    private Image img;
    public GamePanel( Context context){
        super(context);
        //to intercept events
        getHolder().addCallback(this);
        img = new Image(BitmapFactory.decodeResource(getResources(),R.drawable.img),50,50);
        thread = new GameThread(this, getHolder());
        //to set view in focus
        setFocusable(true);


    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                thread.join();
                retry = false;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            img.handleActionDown((int) event.getX(), (int) event.getY());
            if (event.getY() > getHeight() - 150) {
                thread.setRunning(false);
                ((Activity) getContext()).finish();
            } else {
                Log.d(TAG, " x co-ordinate " + event.getX() + " y co-ordinate " + event.getY());
            }
        }
            if(event.getAction() ==  MotionEvent.ACTION_MOVE){
                if(img.isTouched()) {
                    img.setX((int) event.getX());
                    img.setY((int) event.getY());
                }
            }
            if(event.getAction() == MotionEvent.ACTION_UP){
                if(img.isTouched()){
                    img.setTouched(false);
                }
            }
        return true;
    }
    public void update() {
        // check collision with right wall if heading right
        if (img.getSpeed().getxDirection() == Speed.RIGHT_DIRECTION
                && img.getX() + img.getBitmap().getWidth() / 2 >= getWidth()) {
            img.getSpeed().toggleXDirection();
        }
        // check collision with left wall if heading left
        if (img.getSpeed().getxDirection() == Speed.LEFT_DIRECTION
                && img.getX() - img.getBitmap().getWidth() / 2 <= 0) {
            img.getSpeed().toggleXDirection();
        }
        // check collision with bottom wall if heading down
        if (img.getSpeed().getyDirection() == Speed.BOTTOM_DIRECTION
                && img.getY() + img.getBitmap().getHeight() / 2 >= getHeight()) {
            img.getSpeed().toggleYDirection();
        }
        // check collision with top wall if heading up
        if (img.getSpeed().getyDirection() == Speed.TOP_DIRECTION
                && img.getY() - img.getBitmap().getHeight() / 2 <= 0) {
            img.getSpeed().toggleYDirection();
        }
        // Update the lone droid
        img.update();
    }

    protected void render(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        img.draw(canvas);

    }
}
