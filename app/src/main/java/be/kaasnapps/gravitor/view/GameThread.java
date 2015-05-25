package be.kaasnapps.gravitor.view;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by jonas on 15/04/2015.
 */
public class GameThread extends Thread {

    private boolean running = false;
    private GameSurfaceView surfaceView;

    public GameThread(GameSurfaceView view) {
        surfaceView = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceView.getHolder().lockCanvas();
            if (canvas != null) {
                synchronized (surfaceView.getHolder()) {
                    surfaceView.drawSomething(canvas);
                }
                surfaceView.getHolder().unlockCanvasAndPost(canvas);
            }
            try {
                sleep(30);
            } catch (InterruptedException e) {
                Log.e("GameThread", e.getLocalizedMessage(), e);
            }

        }
    }


}
