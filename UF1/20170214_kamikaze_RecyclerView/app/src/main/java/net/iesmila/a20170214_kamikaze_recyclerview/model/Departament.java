package net.iesmila.a20170214_kamikaze_recyclerview.model;

import java.util.ArrayList;

public class Departament {

    private static ArrayList<Departament> _departaments;
    public static ArrayList<Departament> getDepartaments() {
        if(_departaments==null) {
            _departaments = new ArrayList<Departament>();
            _departaments.add( new Departament(0, "Informàtica"));
            _departaments.add( new Departament(1, "Compres"));
            _departaments.add( new Departament(2, "Vendes"));
            _departaments.add( new Departament(3, "Producció"));
        }
        return _departaments;
    }

    private int mId;
    private String mNom;

    public Departament(int pId, String pNom) {
        this.mId = pId;
        this.mNom = pNom;
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        this.mId = pId;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String pNom) {
        this.mNom = pNom;
    }
}
