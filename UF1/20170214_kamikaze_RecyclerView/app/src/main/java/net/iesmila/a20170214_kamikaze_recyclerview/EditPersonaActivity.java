package net.iesmila.a20170214_kamikaze_recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import net.iesmila.a20170214_kamikaze_recyclerview.model.Persona;

public class EditPersonaActivity extends AppCompatActivity {

    public static final String PARAM_IDX_PERSONA = "PARAM_IDX_PERSONA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_persona);

        Intent intent = getIntent();
        int idx = intent.getIntExtra(PARAM_IDX_PERSONA, 0);
        Persona p = Persona.getLlistaPersones(this).get(idx);
        EditText edtNom = (EditText) findViewById(R.id.edtNom);
        edtNom.setText(p.getNom());
    }
}
