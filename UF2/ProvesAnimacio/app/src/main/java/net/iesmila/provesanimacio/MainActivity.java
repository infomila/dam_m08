package net.iesmila.provesanimacio;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView plane = (ImageView) findViewById(R.id.imvPlane);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main );


        ImageView imvBot = (ImageView) findViewById(R.id.imvBot);
        ((AnimationDrawable)imvBot.getDrawable()).start();

        //------------------------------------------------

        /*plane.setRotation(90);
        plane.animate().
                translationX(200).
                setDuration(1000).start();*/
        //------------------------------------------------
        plane.setAlpha(0.0f);
        plane.setX(100);
        ObjectAnimator cloudAnimA = ObjectAnimator.ofFloat(plane, "alpha", 1.f);
        ObjectAnimator cloudAnimR = ObjectAnimator.ofFloat(plane, "rotation", 90);
        ObjectAnimator cloudAnimX = ObjectAnimator.ofFloat(plane, "x", plane.getX()+200);
        ObjectAnimator cloudAnimD = ObjectAnimator.ofFloat(plane, "rotation", 180);
        ObjectAnimator cloudAnimY = ObjectAnimator.ofFloat(plane, "y", plane.getY()+600);
        cloudAnimA.setDuration(3000);
        cloudAnimR.setDuration(500);
        cloudAnimX.setDuration(1000);
        cloudAnimD.setDuration(500);
        cloudAnimY.setDuration(1000);
        //cloudAnimY.setStartDelay(500);
        AnimatorSet as = new AnimatorSet();
        as.playSequentially(cloudAnimA, cloudAnimR, cloudAnimX,cloudAnimD, cloudAnimY);
        //as.play(cloudAnimY).after(3000).after(cloudAnimR).after(cloudAnimX).after(3000);


        ObjectAnimator.ofPropertyValuesHolder()

        AnimatorSet as1 = new AnimatorSet();

        as1.play(as);
        as1.start();
    }
}
