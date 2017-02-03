package net.iesmila.inflateapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> noms = new ArrayList<String>();
        noms.add("Paco");
        noms.add("Joao");
        noms.add("Maria");

        for(String nom:noms) {
            View v = LayoutInflater.from(this).inflate(R.layout.edicio_persona, null);

            LinearLayout llyPrincipal = (LinearLayout) findViewById(R.id.llyPrincipal);
            llyPrincipal.addView(v);

            EditText edtNom = (EditText) v.findViewById(R.id.edtNom);
            edtNom.setText(nom);
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
