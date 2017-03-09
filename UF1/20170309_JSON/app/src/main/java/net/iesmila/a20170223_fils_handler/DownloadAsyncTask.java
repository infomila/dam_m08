package net.iesmila.a20170223_fils_handler;

import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import net.iesmila.a20170223_fils_handler.model.Player;
import net.iesmila.a20170223_fils_handler.model.PlayerParser;
import net.iesmila.a20170223_fils_handler.utils.Downloader;

import java.util.List;

/**
 * Created by BERNAT on 09/03/2017.
 */

public class DownloadAsyncTask extends AsyncTask<String,Void, List<Player> > {

    private IDownloadCompleteListener mListener;

    public interface IDownloadCompleteListener{
        void onDownloadComplete(List<Player> players);
    }

    public DownloadAsyncTask(IDownloadCompleteListener pListener) {
        mListener = pListener;
    }

    @Override
    protected List<Player> doInBackground(String... urls) {
        String downloadedText = Downloader.download(urls[0]);
        Log.d("XXXXXX","<DOWNLOADED>\n" + downloadedText);
        if(downloadedText==null) return null;

        //List<Player> players = PlayerParser.parseFromJSON(downloadedText);
        List<Player> players = PlayerParser.parseFromXML(downloadedText);

        return players;
    }

    @Override
    protected void onPostExecute(List<Player> players) {
        super.onPostExecute(players);
        mListener.onDownloadComplete(players);
    }
}
