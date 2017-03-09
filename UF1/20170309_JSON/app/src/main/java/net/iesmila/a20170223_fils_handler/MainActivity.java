package net.iesmila.a20170223_fils_handler;
//import com.nostra13.universalimageloader.core.*;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.iesmila.a20170223_fils_handler.model.Player;
import net.iesmila.a20170223_fils_handler.model.PlayerParser;
import net.iesmila.a20170223_fils_handler.utils.Downloader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements DownloadAsyncTask.IDownloadCompleteListener {

    private Button mBtnDownload;
    private ProgressBar mPrgProgres;
    private RecyclerView mRcyLlistaJugadors;

    private DownloadAsyncTask mDownloadAsyncTask;

    private static final String DOWNLOAD_SUCCESSFUL ="DOWNLOAD_SUCCESSFUL";



    @Override
    public void onDownloadComplete(List<Player> players) {

            if(players!=null) {
                PlayerAdapter adapter = new PlayerAdapter(players, MainActivity.this);
                mRcyLlistaJugadors.setAdapter(adapter);
            } else {
                Toast.makeText(this, "Error de descàrrega", Toast.LENGTH_SHORT).show();
            }
            mBtnDownload.setEnabled(true);
            mPrgProgres.setVisibility(View.INVISIBLE);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------------
        // Configuració del ImageLoader      //
       ImageLoaderConfiguration config =
               new ImageLoaderConfiguration.Builder(this).
                       //diskCacheFileCount(3).
                       //memoryCacheSize(10).
                       build();
        ImageLoader.getInstance().init(config);
        //------------------------------------
        mBtnDownload = (Button)findViewById(R.id.btnDownload);
        mPrgProgres = (ProgressBar) findViewById(R.id.prgProgres);
        mRcyLlistaJugadors = (RecyclerView) findViewById(R.id.rcyLlistaJugadors);
        configuraRecyclerView(mRcyLlistaJugadors);
        //-------------------------------------
        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnDownload.setEnabled(false);
                mPrgProgres.setVisibility(View.VISIBLE);
                mDownloadAsyncTask = new DownloadAsyncTask(MainActivity.this);
                String urlXML = "http://footballpool.dataaccess.eu/data/info.wso/AllPlayerNames?bSelected=";
                String urlJSON = "http://footballpool.dataaccess.eu/data/info.wso/AllPlayerNames/JSON/debug?bSelected=";
                mDownloadAsyncTask.executeOnExecutor(
                        AsyncTask.THREAD_POOL_EXECUTOR,
                        urlXML);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mDownloadAsyncTask!=null) {
            mDownloadAsyncTask.cancel(true);
            mDownloadAsyncTask = null;
        }
    }

    private void configuraRecyclerView(RecyclerView mRcyLlistaJugadors) {

        mRcyLlistaJugadors.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRcyLlistaJugadors.setLayoutManager(llm);

    }

}
