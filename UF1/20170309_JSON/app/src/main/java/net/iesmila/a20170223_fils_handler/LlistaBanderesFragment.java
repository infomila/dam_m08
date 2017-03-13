package net.iesmila.a20170223_fils_handler;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.iesmila.a20170223_fils_handler.model.Player;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LlistaBanderesFragment extends Fragment implements DownloadAsyncTask.IDownloadCompleteListener {

    private Button mBtnDownload;
    private ProgressBar mPrgProgres;
    private RecyclerView mRcyLlistaJugadors;

    private List<Player> mPlayers;

    private DownloadAsyncTask mDownloadAsyncTask;


    public LlistaBanderesFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("XXXXXXXXXXX", "ON CREATE VIEW");
        Log.d("XXXXXXXXXXX", ">>"+mPlayers);


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_llista_banderes, container, false);


        //------------------------------------
        // Configuració del ImageLoader      //
        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(getActivity()).
                        //diskCacheFileCount(3).
                        //memoryCacheSize(10).
                                build();
        ImageLoader.getInstance().init(config);
        //------------------------------------

        mBtnDownload = (Button)v.findViewById(R.id.btnDownload);
        mPrgProgres = (ProgressBar) v.findViewById(R.id.prgProgres);
        mRcyLlistaJugadors = (RecyclerView) v.findViewById(R.id.rcyLlistaJugadors);
        configuraRecyclerView(mRcyLlistaJugadors);
        //-------------------------------------
        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnDownload.setEnabled(false);
                mPrgProgres.setVisibility(View.VISIBLE);

                mDownloadAsyncTask = new DownloadAsyncTask(LlistaBanderesFragment.this);
                String urlXML =  "http://footballpool.dataaccess.eu/data/info.wso/AllPlayerNames?bSelected=";
                String urlJSON = "http://footballpool.dataaccess.eu/data/info.wso/AllPlayerNames/JSON/debug?bSelected=";
                mDownloadAsyncTask.executeOnExecutor(
                        AsyncTask.THREAD_POOL_EXECUTOR,
                        urlJSON);
            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Si ja tenim descarregada la llista de jugadors, la carreguem directament
        if(mPlayers!=null) {
            onDownloadComplete(mPlayers);
        }
    }

    private void configuraRecyclerView(RecyclerView mRcyLlistaJugadors) {

        mRcyLlistaJugadors.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRcyLlistaJugadors.setLayoutManager(llm);

    }

    @Override
    public void onDownloadComplete(List<Player> players) {
        mPlayers = players;
        if(players!=null) {
            PlayerAdapter adapter = new PlayerAdapter(players, (MainActivity) getActivity());
            mRcyLlistaJugadors.setAdapter(adapter);
        } else {
            Toast.makeText(getActivity(), "Error de descàrrega", Toast.LENGTH_SHORT).show();
        }
        mBtnDownload.setEnabled(true);
        mPrgProgres.setVisibility(View.INVISIBLE);
    }
}
