package net.iesmila.e2_animacions;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imvKong = (ImageView) findViewById(R.id.imvKong);
        ((AnimationDrawable)imvKong.getDrawable()).start();

        final ImageView imvOil = (ImageView) findViewById(R.id.imvOil);

        final RelativeLayout relActivity_main = (RelativeLayout)  findViewById(R.id.relActivity_main);

        relActivity_main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imvKong.setX((relActivity_main.getWidth()-imvKong.getWidth())/2);
                imvKong.setY(relActivity_main.getHeight()-imvKong.getHeight());

                imvOil.setX((relActivity_main.getWidth()-imvOil.getWidth())/2);

                //imvKong.setScaleX(4);
                //imvKong.setScaleY(4);
                //imvKong.setRotation(90);
                //imvKong.setAlpha();
                //--------------------------------------------------
                // Definició de les fases de l'animació
                //--------------------------------------------------
                ObjectAnimator f01 = ObjectAnimator.ofFloat(imvKong,"alpha", 0.0f, 1.0f);
                f01.setDuration(5000);

                ObjectAnimator f02 = ObjectAnimator.ofFloat(imvKong,"x",
                        0.75f*relActivity_main.getWidth() - imvKong.getWidth()/2f);
                f02.setDuration(1000);
                f02.setStartDelay(2000);


                AnimatorSet as = new AnimatorSet();
                //as.play(f01).before(f02);
                as.playSequentially(f01,f02);

                as.start();



            }
        });


    }
}
