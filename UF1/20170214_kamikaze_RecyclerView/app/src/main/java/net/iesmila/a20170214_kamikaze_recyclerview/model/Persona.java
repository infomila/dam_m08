package net.iesmila.a20170214_kamikaze_recyclerview.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
    //@DrawableRes
    //private int mFaceResource;
    private Drawable mFaceDrawable;
    //-----------------------------------------------.

    private static ArrayList<Persona> _llistaPersones;

    public static ArrayList<Persona> getLlistaPersones(Context c) {
        Resources r = c.getResources();
        if(_llistaPersones==null) {
            _llistaPersones = new ArrayList<Persona>();
            _llistaPersones.add(new Persona("Paco", 4, r.getDrawable(R.drawable.paco)));
            _llistaPersones.add(new Persona("Paula", 2, r.getDrawable(R.drawable.paula)));
            _llistaPersones.add(new Persona("Pere", 1,  r.getDrawable(R.drawable.pere)));
            _llistaPersones.add(new Persona("John", 1,  r.getDrawable(R.drawable.john)));
            _llistaPersones.add(new Persona("Paco", 4,  r.getDrawable(R.drawable.paco)));
            _llistaPersones.add(new Persona("Paula", 2, r.getDrawable(R.drawable.paula)));
            _llistaPersones.add(new Persona("Pere", 1,  r.getDrawable(R.drawable.pere)));
            _llistaPersones.add(new Persona("John", 1,  r.getDrawable(R.drawable.john)));
        }
        return _llistaPersones;
    }


    //-----------------------------------------------.


    public Persona(String mNom, int mRating, Drawable pFaceDrawable) {
        this.mNom = mNom;
        this.mRating = mRating;
        this.mFaceDrawable = pFaceDrawable;
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

    public Drawable getFaceDrawable() {
        return mFaceDrawable;
    }

    public void setFaceDrawable(Drawable pFaceDrawable) {

        this.mFaceDrawable = pFaceDrawable;
    }
}
