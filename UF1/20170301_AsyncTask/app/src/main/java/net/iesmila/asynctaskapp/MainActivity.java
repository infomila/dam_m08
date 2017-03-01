package net.iesmila.asynctaskapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements IPublicaResultatsDescarrega {
    private Button      mBtnGo;
    private ProgressBar mPgrBar;
    private ProgressBar mPgrCircle;
    private TextView    mTxvMsg;
    private DescarregaAsyncTask mDAT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mBtnGo = (Button) findViewById(R.id.btnGo);
         mPgrBar = (ProgressBar)findViewById(R.id.pgrBar);
         mPgrCircle = (ProgressBar)findViewById(R.id.pgrCircle);
         mTxvMsg = (TextView)findViewById(R.id.txvMsg);
        //-----------------------------------------------

        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDAT = new DescarregaAsyncTask(MainActivity.this);
                setEstatDescarrega(true);
                //mDAT.execute(1,4,5,2);  // No aconsellat !!!!! Executa en sèrie.
                mDAT.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 1,4,5,2);
            }
        });

        //-----------------------------------------------
        setEstatDescarrega(false);
    }


    private void setEstatDescarrega(boolean estaDescarregant) {
        int esVisible = estaDescarregant? View.VISIBLE:View.INVISIBLE;
        mPgrBar.setProgress(0);// quan rellancem el fil, posem el progress a zero
        mPgrBar.setVisibility(esVisible);
        mPgrCircle.setVisibility(esVisible);
        mBtnGo.setEnabled(!estaDescarregant);

    }

    @Override
    public void publicaResultatDescarrega(String resultat) {
        mTxvMsg.setText(resultat);
        Toast.makeText(this,"Descarrega Completada", Toast.LENGTH_SHORT).show();


        setEstatDescarrega(false);
    }

    @Override
    public void publicaProgressDescarrega(Integer progress, Integer max) {
        mPgrBar.setMax(max);
        mPgrBar.setProgress(progress);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mDAT!=null) {
            mDAT.cancel(false);
        }
    }
}
