package net.iesmila.e0_graphview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by BERNAT on 14/03/2017.
 */

public class Pissarra extends View implements View.OnClickListener {

                                          //H     S   B
    private float mColorHSB[] = new float[]{0f  ,1f, 0.1f} ;
    private float alfaInicial = 0;
    public Pissarra(Context context) {
        this(context, null);
    }

    public Pissarra(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("XXXXX", "CLICK");
        mColorHSB[2] += 0.1;
        if(mColorHSB[2]>1) {
            mColorHSB[2] = 0;
        }
        invalidate();
     }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("XXXXX", "onDraw");
//        canvas.drawColor(Color.GREEN);
//        canvas.drawColor(Color.parseColor("#ff0023"));
//        canvas.drawColor(Color.rgb(234,30,55));

        // 1.0 exemple ull
        //dibuixaUll(canvas);

        // 2.0 exemple flor
        //dibuixaFlor(canvas);

        // 3.-Treball amb bitmaps
        // Això hauria de posar-se al constructor.
        Bitmap ventilador =
                BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.ventilador);
        canvas.drawBitmap(ventilador,0,0,null);
        int wv,hv,W,H;
        wv = ventilador.getWidth();
        hv = ventilador.getHeight();
        W = getWidth();
        H = getHeight();
        float x = 0.5f*(W-wv);
        float y = 0.5f*(H-hv);
        canvas.drawBitmap(ventilador,0,0,null);
        canvas.drawBitmap(ventilador,x,y,null);


        float WFinestra = W * 0.5f;
        float HFinestra = H * 0.5f;

        float proporcioW = WFinestra / wv;
        float proporcioH = HFinestra / hv;

        float WFinal, HFinal;
        float proporcioAplicada;
        if(proporcioH>proporcioW) {
            WFinal = WFinestra;
            HFinal = hv * proporcioW;
            proporcioAplicada = proporcioW;
        } else {
            HFinal = HFinestra;
            WFinal = wv * proporcioH;
            proporcioAplicada = proporcioH;
        }

        canvas.drawBitmap(
                ventilador,
                new Rect(0,0,wv,hv),
                new Rect((int)(0.5*W - 0.5*WFinal),
                         (int)(0.5*H - 0.5*HFinal) ,
                         (int)(0.5*W + 0.5*WFinal),
                         (int)(0.5*H + 0.5*HFinal))
                ,null);

        Matrix m = new Matrix();
        m.postTranslate(-wv*0.5f,-hv*0.5f);
        m.postRotate(180);
        m.postScale(proporcioAplicada,proporcioAplicada);
        m.postTranslate(0.5f*W, 0.5f*H);
        canvas.drawBitmap(ventilador, m, null);



        //---------------------------------------
        Bitmap lobul =
                BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.lobul);

        for(int angle=0;angle<360;angle+=30) {
            Matrix m1 = new Matrix();
            m1.postTranslate(0, -lobul.getHeight() * 0.5f);
            m1.postRotate(angle);
            //m1.postScale(proporcioAplicada,proporcioAplicada);
            m1.postTranslate(0.5f * W, 0.5f * H);
            canvas.drawBitmap(lobul, m1, null);
        }


    }

    private void dibuixaUll(Canvas canvas) {
        canvas.drawColor(Color.HSVToColor(mColorHSB));
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.STROKE);

        for(int delta = 20;delta<200;delta += 25) {
            canvas.drawRect(
                    0 + delta,                  //x1
                    0 + delta,                  //y1
                    this.getWidth() - delta,    //x2
                    this.getHeight() - delta,   //y2
                    p);
        }
        p.setStrokeWidth(6);
        canvas.drawLine(0,0, this.getWidth(), this.getHeight(), p);
        Path path = new Path();
        path.moveTo(0,0);
        path.quadTo(this.getWidth(), 0, this.getWidth(), this.getHeight());
        path.quadTo(0, this.getHeight(), 0, 0);
        canvas.drawPath(path, p);
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        canvas.drawOval(0,0,this.getWidth(), this.getHeight(), p);
        p.setColor(Color.parseColor("#6d2605"));
        float r = Math.min(this.getWidth(), this.getHeight())/2.0f;
        canvas.drawCircle(  this.getWidth()/2.0f,
                            this.getHeight()/2.0f,
                            r,
                            p);
        p.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(  this.getWidth()/2.0f,
                this.getHeight()/2.0f,
                r/2.0f ,
                p);
        p.setColor(Color.parseColor("#ffffff"));
        double angle = Math.PI / 4.0;
        float d = 0.75f*r;
        canvas.drawCircle(  this.getWidth()/2.0f  + (float)Math.cos(angle)*d,
                            this.getHeight()/2.0f - (float)Math.sin(angle)*d,
                r/4.0f ,
                p);
    }

    private void dibuixaFlor(Canvas canvas) {
        canvas.drawColor(Color.HSVToColor(mColorHSB));
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.STROKE);

        float xc = getWidth()/2;
        float yc = getHeight()/2;
        int N=8;
        double anglePas = 2*Math.PI / N;
        float R=Math.min(this.getWidth(), this.getHeight())/2.0f;
        float R2 = (float) (R / Math.cos(anglePas/2));

        Path path = new Path();
        int petals = 0;
        for(float alfa = alfaInicial;petals<N;alfa+=anglePas, petals++)
        {
            path.moveTo(xc,yc);
            /*path.lineTo(
                    (float)(xc+R*Math.cos(alfa)),
                    (float)(yc+R*Math.sin(alfa))*/
            // anada
            path.quadTo(
                    (float)(xc+R2*Math.cos(alfa+anglePas/2)),
                    (float)(yc+R2*Math.sin(alfa+anglePas/2)),
                    (float)(xc+R*Math.cos(alfa)),
                    (float)(yc+R*Math.sin(alfa))
            );
            //tornada
            path.quadTo(
                    (float)(xc+R2*Math.cos(alfa-anglePas/2)),
                    (float)(yc+R2*Math.sin(alfa-anglePas/2)),
                    xc,
                    yc
            );
        }
        p.setColor(Color.YELLOW);
        canvas.drawPath(path,p);

        alfaInicial = alfaInicial + (float)(Math.PI/20);
    }


}
