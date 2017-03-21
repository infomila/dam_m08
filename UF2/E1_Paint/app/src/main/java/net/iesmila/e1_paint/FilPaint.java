package net.iesmila.e1_paint;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by BERNAT on 17/03/2017.
 */

public class FilPaint extends Thread {
    private boolean filEnExecucio = false;

    private SurfaceHolder mHolder;
    private PaintSurface  mPaintSurface;
    public FilPaint(PaintSurface pPaintSurface){
        mPaintSurface = pPaintSurface;
        mHolder = pPaintSurface.getHolder();
    }

    @Override
    public void run() {
        filEnExecucio = true;
        Canvas c = null;
        while(filEnExecucio){
            try {
                c = mHolder.lockCanvas();
                synchronized (mHolder) {
                    mPaintSurface.onDraw(c);
                }
            }finally {
                if(c!=null) mHolder.unlockCanvasAndPost(c);
            }
        }
    }
    public void porDiosParate(){
        filEnExecucio = false;
    }
}
