package net.iesmila.asynctaskapp;

/**
 * Created by BERNAT on 01/03/2017.
 */
public interface IPublicaResultatsDescarrega {

    void publicaResultatDescarrega(String resultat);

    void publicaProgressDescarrega(Integer progress, Integer max);
}
