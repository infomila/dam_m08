package net.iesmila.a20170307_fragments;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
implements PersonaFragment.OnPersonaClicada {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //R.id.activity_main


        if(savedInstanceState==null ) {
            // Si entrem aquí vol dir que és el primer cop que
            // engega l'activity, i que no venim d'una rotació.
            PersonaFragment pf = PersonaFragment.newInstance("Paco", "Lobaton");
            pf.setRetainInstance(true);
            getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.activity_main, pf).
                    addToBackStack("pas1").
                    //add(pf, "filBackground").
                    commit();
        }

    }

    private void exemplePerAccedirAlFragment(){

        PersonaFragment pf = (PersonaFragment)getSupportFragmentManager().
                findFragmentById(R.id.activity_main);
    }

    @Override
    public void onPersonaClicada(int codiPersona) {

    }
}
