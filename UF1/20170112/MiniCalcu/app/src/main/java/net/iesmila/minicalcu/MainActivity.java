package net.iesmila.minicalcu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button mBtnMes, mBtnMenys;
    private EditText mEdtOp1, mEdtOp2, mEdtRes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnMes = (Button) findViewById(R.id.btnMes);
        mBtnMenys = (Button) findViewById(R.id.btnMenys);
        mEdtOp1 = (EditText) findViewById(R.id.edtOp1);
        mEdtOp2 = (EditText) findViewById(R.id.edtOp2);
        mEdtRes = (EditText) findViewById(R.id.edtRes);

        mBtnMes.setOnClickListener(this);
        mBtnMenys.setOnClickListener(this);

/*
        mBtnMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operacio(true);
            }
        });
        mBtnMenys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operacio(false);
            }
        });
        */
    }

    @Override
    public void onClick(View view) {
        operacio(view.getId()==R.id.btnMes);
        //operacio(view==mBtnMes);
    }


    private void operacio(boolean esSuma) {
        try {
            Log.d("XXXXX", "Aquest és el missatge real");

            Log.d("XXXXX", mEdtOp1.getText().toString() + "+" +
                    mEdtOp2.getText().toString());
            int op1 = Integer.parseInt(mEdtOp1.getText().toString());
            int op2 = Integer.parseInt(mEdtOp2.getText().toString());
            int res;
            if(esSuma) {
                res = op1+op2;
            } else {
                res = op1-op2;
            }
            mEdtRes.setText("" + res);
        }catch (Exception ex){
            Log.e("XXXXX", "He petat :-(", ex);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
