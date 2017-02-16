package net.iesmila.a20170214_kamikaze_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import net.iesmila.a20170214_kamikaze_recyclerview.model.Persona;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRcyPersones;
    private AdapterPersones mAdapterPersones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tlbMenu);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Kamikaze");

        mRcyPersones = (RecyclerView) findViewById(R.id.rcyPersones);

        ArrayList<Persona> persones = Persona.getLlistaPersones();

        //----------- Posem el layout que volem fer servir ---------------
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        //GridLayoutManager glm = new GridLayoutManager(this,2);
        //glm.setOrientation(GridLayoutManager.HORIZONTAL);
        mRcyPersones.setLayoutManager(llm);

        mAdapterPersones = new AdapterPersones(persones);
        mRcyPersones.setAdapter(mAdapterPersones);

        //---------- Per augmentar el rendiment -----------
        mRcyPersones.setHasFixedSize(true);

        //-------------- posem un separador de files ---------


    }

}
