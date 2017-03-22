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


    private enum Mode {
        DIBUIXAR,
        ESBORRAR
    };

    private Mode mModeActual = Mode.DIBUIXAR;

    private int mBackgroundColor = Color.BLACK;
    private int mForegroundColor = Color.RED;

    public static final int RADIUS = 60;
    private static final long MAX_DURACIO = 500;
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
        p.setColor(mForegroundColor);
        p.setStyle(Paint.Style.FILL);

        pL = new Paint();
        pL.setColor(p.getColor());
        pL.setStyle(Paint.Style.STROKE);
        pL.setStrokeWidth(RADIUS*2);
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


    private float x=0, y=0; // Isidre approved !
    private float xAnt=0, yAnt=0;
    private Paint p, pL;

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        canvas.drawBitmap(mBuffer,0,0,null);

    }

    private int comptadorClicsSeguits = 0;
    private long primerClickTime = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        x = event.getX();
        y = event.getY();
 
        //p.setFontFeatureSettings("font-size:20px;color:#00ff00;font-weight:bold;");
        //mCanvasOffscreen.drawText("A",x,y,p);
        mCanvasOffscreen.drawCircle(x,y, RADIUS, p);

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                if(comptadorClicsSeguits==0) {
                    primerClickTime = System.currentTimeMillis();
                }
                comptadorClicsSeguits++;
                break;
            }
            case MotionEvent.ACTION_UP: {
                if(comptadorClicsSeguits==2) {
                    if(  System.currentTimeMillis() - primerClickTime < MAX_DURACIO ){
                        canviaMode();
                    }
                    comptadorClicsSeguits = 0;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                mCanvasOffscreen.drawLine(xAnt,yAnt,x,y,pL);
                break;
            }
        }
        xAnt = x;
        yAnt = y;
        return true;
    }

    private void canviaMode() {
        switch (mModeActual) {
            case DIBUIXAR: mModeActual = Mode.ESBORRAR;break;
            case ESBORRAR: mModeActual = Mode.DIBUIXAR;break;
        }
        int color = mModeActual==Mode.ESBORRAR ? mBackgroundColor:mForegroundColor;
        p.setColor(color);
        pL.setColor(color);
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
