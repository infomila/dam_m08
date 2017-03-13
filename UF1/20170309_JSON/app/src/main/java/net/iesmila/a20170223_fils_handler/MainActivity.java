package net.iesmila.a20170223_fils_handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null) {
            LlistaBanderesFragment fragment = new LlistaBanderesFragment();
            fragment.setRetainInstance(true);
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.frg_llista_banderes,
                            fragment).
                    commit();
        } else {

        }




    }
/*
    @Override
    protected void onPause() {
        super.onPause();
        if(mDownloadAsyncTask!=null) {
            mDownloadAsyncTask.cancel(true);
            mDownloadAsyncTask = null;
        }
    }*/



}
