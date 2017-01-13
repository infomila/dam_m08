package net.iesmila.programacioevents;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity
        implements View.OnClickListener{

    private Button mBtnGo;
    private TextView mTxvMissatge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBtnGo = (Button) findViewById(R.id.btnGo);
        mTxvMissatge = (TextView) findViewById(R.id.txvMissatge);

        mBtnGo.setOnClickListener(this);




        /*mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTxvMissatge.setText("HOLA!!!");
            }
        });*/

        mBtnGo.setOnClickListener(this);

    }

    @Override
    public void onClick(View sender) {
        mTxvMissatge.setText("HOLA!!!");
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

