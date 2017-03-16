package net.iesmila.e0_graphview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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



        /*for(int delta = 20;delta<200;delta += 25) {
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
                p);*/
    }


}
