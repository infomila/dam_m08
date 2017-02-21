package net.iesmila.a20170214_kamikaze_recyclerview.model;

import android.graphics.drawable.Drawable;

/**
 * Created by BERNAT on 21/02/2017.
 */

public class IlCapo extends Persona {

    private Departament mDepartament;

    public IlCapo(String mNom, Drawable pFaceDrawable, Departament pDepartament) {
        super(mNom, 5, pFaceDrawable);
        mDepartament = pDepartament;
    }

    public Departament getDepartament() {
        return mDepartament;
    }

    public void setDepartament(Departament pDepartament) {
        this.mDepartament = pDepartament;
    }
}
