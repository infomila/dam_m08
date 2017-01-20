package net.iesmila.supercalcuv2;

import android.graphics.Color;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int btnsIds[] = new int[] {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9  };

        for(int btnId:btnsIds) {
            final Button b = (Button)findViewById(btnId);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int numero = Integer.parseInt(b.getText().toString());
                    numeroApretat(numero);
                }
            });
        }

       Button b = (Button)findViewById(R.id.btnMes);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operacioMes();
            }
        });

        int colors[] = new int[]{
                getResources().getColor(R.color.background),
                getResources().getColor(R.color.highlightBackground)};


        TableLayout tblLayout = (TableLayout) findViewById(R.id.tblLayout);

        for(int i=0;i<tblLayout.getChildCount();i++) {
            ViewGroup vg = (ViewGroup) tblLayout.getChildAt(i);

            for(int n=0;n< vg.getChildCount();n++) {

                View v = (View) vg.getChildAt(n);
                vg.setBackgroundColor(colors[i%2]);
                Log.d(">>>", v.toString());
                if (v instanceof Button) {
                    try {
                        Integer.parseInt(((Button) v).getText().toString());
                        v.setBackgroundColor(Color.argb(255, 128, 0, 0));
                    } catch (Exception ex) {

                    }
                }
            }
        }

    }

    private void operacioMes() {


    }

    private void numeroApretat(int numero) {

    }


}
