package net.iesmila.a20170214_kamikaze_recyclerview.model;

import android.support.annotation.DrawableRes;
import net.iesmila.a20170214_kamikaze_recyclerview.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe per desar les dades d'una persona
 */
public class Persona implements Serializable {
    //-----------------------------------------------
    static final long serialVersionUID = 1L;
    //-----------------------------------------------
    private String mNom;
    private float mRating;
    @DrawableRes
    private int mFaceResource;
    //-----------------------------------------------.

    private static ArrayList<Persona> _llistaPersones;

    public static ArrayList<Persona> getLlistaPersones() {
        if(_llistaPersones==null) {
            _llistaPersones = new ArrayList<Persona>();
            _llistaPersones.add(new Persona("Paco", 4, R.drawable.paco));
            _llistaPersones.add(new Persona("Paula", 2, R.drawable.paula));
            _llistaPersones.add(new Persona("Pere", 1, R.drawable.pere));
            _llistaPersones.add(new Persona("John", 1, R.drawable.john));
            _llistaPersones.add(new Persona("Paco", 4, R.drawable.paco));
            _llistaPersones.add(new Persona("Paula", 2, R.drawable.paula));
            _llistaPersones.add(new Persona("Pere", 1, R.drawable.pere));
            _llistaPersones.add(new Persona("John", 1, R.drawable.john));
        }
        return _llistaPersones;
    }


    //-----------------------------------------------.

    public Persona(String mNom, int mRating, int faceResource) {
        this.mNom = mNom;
        this.mRating = mRating;
        this.mFaceResource = faceResource;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String mNom) {
        this.mNom = mNom;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float mRating) {
        this.mRating = mRating;
    }

    public int getFaceResource() {
        return mFaceResource;
    }

    public void setFaceResource(int faceResource) {
        this.mFaceResource = faceResource;
    }
}
