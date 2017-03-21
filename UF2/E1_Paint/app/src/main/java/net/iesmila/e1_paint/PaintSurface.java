package net.iesmila.e1_paint;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by BERNAT on 17/03/2017.
 */

public class PaintSurface extends SurfaceView
        implements SurfaceHolder.Callback {


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
    }

    private int x = 40, y = 40;
    private Paint p;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(x,y, 20, p);
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
