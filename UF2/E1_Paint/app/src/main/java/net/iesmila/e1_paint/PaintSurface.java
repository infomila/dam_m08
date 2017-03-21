package net.iesmila.e1_paint;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewTreeObserver;

/**
 * Created by BERNAT on 17/03/2017.
 */

public class PaintSurface extends SurfaceView
        implements SurfaceHolder.Callback {

    private Bitmap mBuffer;
    private Canvas mCanvasOffscreen;

    public PaintSurface(Context context) {
        this(context, null);
    }
    // Constructors ================================================
    public PaintSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Crea un paint
        p = new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.FILL);
        // registra el holder callback
        getHolder().addCallback(this);

        // Programar un event que saltarà quan l'amplada i l'alçada
        // del view estan disponibles.
        getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //getViewTreeObserver().removeOnGlobalLayoutListener(PaintSurface.this);
                // Creació del buffer off-screen
                mBuffer = Bitmap.createBitmap(
                        PaintSurface.this.getWidth(),
                        PaintSurface.this.getHeight(),
                        Bitmap.Config.ARGB_8888
                );
                mCanvasOffscreen = new Canvas(mBuffer);
            }
        });
    }


    private float x = 40, y = 40;
    private Paint p;

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        canvas.drawBitmap(mBuffer,0,0,null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        if(event.getAction()==MotionEvent.ACTION_DOWN ||
                event.getAction()==MotionEvent.ACTION_MOVE) {
            x = event.getX();
            y = event.getY();
            mCanvasOffscreen.drawCircle(x,y,60, p);
        }
        return true;
    }

    private FilPaint mFilPaint;

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mFilPaint = new FilPaint(this);
        mFilPaint.start();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if(mFilPaint!=null) {
            mFilPaint.porDiosParate();
            try {
                mFilPaint.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }


}
