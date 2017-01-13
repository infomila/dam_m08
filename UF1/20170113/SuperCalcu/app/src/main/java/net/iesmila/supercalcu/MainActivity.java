package net.iesmila.supercalcu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


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
                    int numero = Integer.parseInt(b.getText().toString()3);
                    numeroApretat(numero);
                }
            });
        }
    }

    private void numeroApretat(int numero) {
    }


}
