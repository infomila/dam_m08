package net.iesmila.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;


public class MainActivity extends Activity implements TextWatcher {
    ImageView mImvCara;
    ImageView mBtnCamera;
    EditText mEdtNom;
    EditText mEdtCognom;
    Spinner mSpnProvincia;
    RadioGroup mRdgSexe;
    Button mBtnOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //-----------------------------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-----------------------------------------------------
        mImvCara = (ImageView) findViewById(R.id.imvCara);
        mBtnCamera = (ImageView) findViewById(R.id.btnCamera);
        mEdtNom = (EditText) findViewById(R.id.edtNom);
        mEdtCognom = (EditText) findViewById(R.id.edtCognom);
        mSpnProvincia = (Spinner) findViewById(R.id.spnProvincia);
        mRdgSexe = (RadioGroup) findViewById(R.id.rdgSexe);
        mBtnOk = (Button) findViewById(R.id.btnOk);

        carregaSpinner();

        valida();
        //-----------------------------------------------------
        mBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        //--------------------------------------------------
        mEdtNom.addTextChangedListener(this);
        mEdtCognom.addTextChangedListener(this);
        mRdgSexe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                valida();
            }
        });

    }

    private void carregaSpinner() {


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.provincies, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpnProvincia.setAdapter(adapter);
    }

    private void valida() {

        boolean ok =    ( mEdtNom.getText().toString().trim().length()>1 ) &&
                        ( mEdtCognom.getText().toString().trim().length()>1 ) &&
                        ( mRdgSexe.getCheckedRadioButtonId()!=-1);// &&
                        //( mSpnProvincia.getSelectedItemId()!=-1);

        mBtnOk.setEnabled(ok);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //---------------------------------------------------------

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImvCara.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

    @Override
    public void afterTextChanged(Editable editable) {
        valida();
    }
}
