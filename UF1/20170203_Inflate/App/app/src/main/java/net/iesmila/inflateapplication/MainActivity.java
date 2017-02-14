package net.iesmila.inflateapplication;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import net.iesmila.inflateapplication.model.Persona;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity   {

    private static final String ARXIU_PERSONES = "persones.bin";
    private ArrayList<Persona> mLlistaPersones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("XXXXXX", "M'ESTIC CREANT");

        //ArrayList<Persona> persones =Persona.getLlistaPersones();
        mLlistaPersones = getLlistaPersonesFromFile();

        LinearLayout llyPrincipal = (LinearLayout) findViewById(R.id.llyPrincipal);

        for(Persona p:mLlistaPersones) {
            View v = LayoutInflater.from(this).inflate(R.layout.edicio_persona, null);
            llyPrincipal.addView(v);
            //----------------------------------------------------------
            EditText edtNom = (EditText) v.findViewById(R.id.edtNom);
            edtNom.setText(p.getNom());
            Log.d("XXXXXX", "N>"+p.getNom());
            /*edtNom.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) { }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
                @Override
                public void afterTextChanged(Editable editable) {
                    p.setNom(edtNom.getText().toString());//editable.toString());
                }
            });*/
            PersonaListener listener = new PersonaListener(p);
            edtNom.addTextChangedListener(listener);
            //----------------------------------------------------------
            ImageView imvAvatar = (ImageView) v.findViewById(R.id.imvAvatar);
            imvAvatar.setImageResource(p.getFaceResource());

            //----------------------------------------------------------
            RatingBar rtbRating = (RatingBar) v.findViewById(R.id.rtbRating);
            rtbRating.setRating(p.getRating());
            Log.d("XXXXXX", "R>"+p.getRating());
            Log.d("XXXXXX", "===================");
            rtbRating.setOnRatingBarChangeListener(listener);

        }


        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    @Override
    protected void onPause() {
        super.onPause();
        desarLlista(mLlistaPersones);
    }

    private void desarLlista(ArrayList<Persona>  persones) {
        FileOutputStream fos = null;
        try {
            File carpeta = getFilesDir();
            File subCarpeta = new File(carpeta, "subcarpeta");
            fos = openFileOutput(
                    ARXIU_PERSONES, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Persona p:persones) {
                oos.writeObject(p);
            }
        } catch (FileNotFoundException e) {
            Log.e("XXXX","Error obrint arxiu en mode escriptura", e);
        }
        catch (IOException e) {
            Log.e("XXXX","Error escrivint a l'arxiu", e);
        }
        finally{
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.e("XXXX", "Error tancant a l'arxiu", e);
                }
            }
        }
    }

    private ArrayList<Persona> getLlistaPersonesFromFile() {
        FileInputStream fis=null;
        ArrayList<Persona>  persones = new ArrayList<Persona>();

        try {
            fis = openFileInput(ARXIU_PERSONES);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(true) {
                Persona p = (Persona)ois.readObject();
                persones.add(p);
            }
        } catch (FileNotFoundException e) {
            return Persona.getLlistaPersones();
        } catch(Exception ex){ }
        finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    Log.e("XXXX","Error tancant arxiu", e);
                }
            }
        }
        return persones;
    }

    @Override
    protected void onSaveInstanceState(Bundle b) {

    }


}
