package com.game.firstgame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Nishant on 8/29/2015.
 */
public class GameThread extends Thread {
     private boolean running;
    private GamePanel gamePanel;
    private SurfaceHolder surfaceHolder;
    private static final String TAG = GameThread.class.getSimpleName();

    GameThread(GamePanel panel, SurfaceHolder holder){
        super();
        gamePanel = panel;
        surfaceHolder = holder;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;
        Log.d(TAG, "Game thread started");
        while(running){
            canvas = null;
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.render(canvas);
                }
            }finally {
                if(canvas != null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
        Log.d(TAG, "Game thread ended");
    }
}
