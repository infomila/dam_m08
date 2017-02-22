package net.iesmila.a20170214_kamikaze_recyclerview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import net.iesmila.a20170214_kamikaze_recyclerview.model.Persona;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 3;
    private static final int ACTIVITY_EDICIO = 4;
    private RecyclerView mRcyPersones;
    private AdapterPersones mAdapterPersones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //=====================================================
        //  CONFIGURACIÓ DE LA TOOLBAR
        //
        // http://www.hermosaprogramacion.com/2015/06/toolbar-en-android-creacion-de-action-bar-en-material-design/
        //=====================================================
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tlbMenu);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Kamikaze");
        //=====================================================

        mRcyPersones = (RecyclerView) findViewById(R.id.rcyPersones);

        ArrayList<Persona> persones = Persona.getLlistaPersones(this);

        //----------- Posem el layout que volem fer servir ---------------
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        //GridLayoutManager glm = new GridLayoutManager(this,2);
        //glm.setOrientation(GridLayoutManager.HORIZONTAL);
        mRcyPersones.setLayoutManager(llm);

        mAdapterPersones = new AdapterPersones(persones, this);
        mRcyPersones.setAdapter(mAdapterPersones);

        //---------- Per augmentar el rendiment -----------
        mRcyPersones.setHasFixedSize(true);

        //-------------- posem un separador de files ---------


    }

    private Menu mMenu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        //MenuInflater inflater = new MenuInflater(this);
        getMenuInflater().inflate(R.menu.menu_principal,menu);

        notificaItemSeleccionatCanviat(-1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.mnuEsborrar: {
                mAdapterPersones.esborrarElementSeleccionat();
                return true;
            }
            case R.id.mnuUp: {
                mAdapterPersones.moureElementSeleccionat(-1);
            } break;
            case R.id.mnuDown: {
                mAdapterPersones.moureElementSeleccionat(+1);
            } break;
        }
        return false;
    }

    public void notificaItemSeleccionatCanviat(int posicioSeleccionada){
        boolean mostraOpcioMenuDelete = (posicioSeleccionada!=-1);

        MenuItem mnuEsborrar = mMenu.findItem(R.id.mnuEsborrar);
        mnuEsborrar.setVisible(mostraOpcioMenuDelete);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==ACTIVITY_EDICIO) {
            // recollir resultats de l'activity d'edició

        }
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                if (data == null) {
                    //Display an error
                    return;
                }
                try {
                    InputStream inputStream = this.getContentResolver().
                                                openInputStream(data.getData());

                    BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
                    Bitmap bmp = BitmapFactory.decodeStream(inputStream,
                            null, decodeOptions);

                    mAdapterPersones.posarImatgePendent(bmp);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //Now you c
            }
        }
    }


    public void obrirActivityEdicio(Persona p) {
        Intent intent = new Intent(this, EditPersonaActivity.class);


        intent.putExtra(EditPersonaActivity.PARAM_IDX_PERSONA, Persona.getLlistaPersones(this).indexOf(p));
        //startActivity(intent);
        startActivityForResult(intent,ACTIVITY_EDICIO);
    }


}
