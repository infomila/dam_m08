package net.iesmila.e1_paint;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
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


    public void activarPaleta(boolean activar) {
        refreshPending = true;
        if(activar) {
            mModeActual = Mode.PALETA;
        } else {
            mModeActual = Mode.DIBUIXAR;
        }
    }

    private enum Mode {
        DIBUIXAR,
        ESBORRAR,
        PALETA
    };

    private Mode mModeActual = Mode.DIBUIXAR;

    private int mBackgroundColor = Color.BLACK;
    private int mForegroundColor = Color.RED;
    private int mActiveColor = mForegroundColor;

    public int RADIUS = 60;
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

    }


    private float x=0, y=0; // Isidre approved !
    private float xAnt=0, yAnt=0;
    private Paint p, pL;

    private boolean init = false;
    private boolean refreshPending = false;

    //@Override
    protected void onElMeuDraw(Canvas canvas) {
        if(!init) {
            mBuffer = Bitmap.createBitmap(
                    PaintSurface.this.getWidth(),
                    PaintSurface.this.getHeight(),
                    Bitmap.Config.ARGB_8888
            );
            mCanvasOffscreen = new Canvas(mBuffer);

            //Predibuixem la paleta de colors
            dibuixarPaletaOffline();
            init = true;
        }
        if(true) {
            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(mBuffer, 0, 0, null);

            if (mModeActual == Mode.PALETA) {
                dibuixarPaleta(canvas);
                dibuixarColorActual(canvas);
            }
            refreshPending = false;
        }
    }

    private void dibuixarColorActual(Canvas canvas) {
    }

    private Bitmap mPaleta;

    private Point marge;


    private void dibuixarPaleta(Canvas canvas) {
        final int DIM = mPaleta.getWidth();
        marge = new Point(
                (int) ((getWidth()-DIM)*0.5),
                (int) ((getHeight()-DIM)*0.5)
        );
        canvas.drawBitmap(mPaleta,marge.x,marge.y,null);
    }

    private void dibuixarPaletaOffline() {
        final int DIM = (int) (getWidth()*0.6);

        mPaleta = Bitmap.createBitmap(DIM,DIM, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mPaleta);

        Paint pC = new Paint();

        for(int h=0;h<DIM;h++)
        {
            for(int s=0;s<DIM;s++)
            {
                pC.setColor(Color.HSVToColor(new float[]{360.0f*h/DIM,1-((float)s)/DIM,1}));
                canvas.drawPoint( h, s, pC);
            }
        }
    }

    private int comptadorClicsSeguits = 0;
    private long primerClickTime = 0;

    private double quad(double d) {return d*d;}
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        refreshPending = true;

        //return super.onTouchEvent(event);
        x = event.getX();
        y = event.getY();

        if(event.getPointerCount()==2) {
            float d = (float)Math.sqrt(
                    quad(event.getX(0)-event.getX(1))+
                    quad(event.getY(0)-event.getY(1))
            )*0.3f;
            RADIUS = (int)(d*0.5f);
            pL.setStrokeWidth(d);
        }

        if(mModeActual==Mode.PALETA) {

            switch(event.getAction()){
                case MotionEvent.ACTION_UP: {
                    int c =  mPaleta.getPixel((int)x-marge.x,(int)y-marge.y);
                    mForegroundColor = c;
                    setColorActiu(c);
                    activarPaleta(false);
                }
            }
        } else {

            //p.setFontFeatureSettings("font-size:20px;color:#00ff00;font-weight:bold;");
            //mCanvasOffscreen.drawText("A",x,y,p);
            mCanvasOffscreen.drawCircle(x, y, RADIUS, p);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    if (comptadorClicsSeguits == 0) {
                        primerClickTime = System.currentTimeMillis();
                    }
                    comptadorClicsSeguits++;
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    if (comptadorClicsSeguits == 2) {
                        if (System.currentTimeMillis() - primerClickTime < MAX_DURACIO) {
                            canviaMode();
                        }
                        comptadorClicsSeguits = 0;
                    }
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    mCanvasOffscreen.drawLine(xAnt, yAnt, x, y, pL);
                    break;
                }
            }
            xAnt = x;
            yAnt = y;
        }
        return true;
    }

    private void canviaMode() {
        switch (mModeActual) {
            case DIBUIXAR: mModeActual = Mode.ESBORRAR;break;
            case ESBORRAR: mModeActual = Mode.DIBUIXAR;break;
        }
        int color = mModeActual==Mode.ESBORRAR ? mBackgroundColor:mForegroundColor;
        setColorActiu(color);
    }

    private void setColorActiu(int color ) {
        mActiveColor = color;
        p.setColor(mActiveColor);
        pL.setColor(mActiveColor);
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
