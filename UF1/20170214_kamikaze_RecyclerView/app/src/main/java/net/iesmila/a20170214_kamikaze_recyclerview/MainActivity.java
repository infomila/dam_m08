package net.iesmila.a20170214_kamikaze_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.iesmila.a20170214_kamikaze_recyclerview.model.Persona;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRcyPersones;
    private AdapterPersones mAdapterPersones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRcyPersones = (RecyclerView) findViewById(R.id.rcyPersones);

        ArrayList<Persona> persones = Persona.getLlistaPersones();

        //----------- Posem el layout que volem fer servir ---------------
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRcyPersones.setLayoutManager(llm);

        mAdapterPersones = new AdapterPersones(persones);
        mRcyPersones.setAdapter(mAdapterPersones);

        //---------- Per augmentar el rendiment -----------
        mRcyPersones.setHasFixedSize(true);
    }
}
