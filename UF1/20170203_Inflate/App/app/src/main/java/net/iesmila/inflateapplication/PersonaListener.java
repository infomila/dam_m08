package net.iesmila.inflateapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.RatingBar;

import net.iesmila.inflateapplication.model.Persona;

/**
 * Created by BERNAT on 09/02/2017.
 */
public class PersonaListener implements TextWatcher, RatingBar.OnRatingBarChangeListener {
    private Persona mPersona;
    public PersonaListener(Persona pPersona) {
        mPersona = pPersona;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

    @Override
    public void afterTextChanged(Editable editable) {
        mPersona.setNom(editable.toString());
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        mPersona.setRating(rating);
    }
}
