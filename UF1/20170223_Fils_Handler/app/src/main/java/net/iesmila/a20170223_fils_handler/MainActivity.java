package net.iesmila.a20170223_fils_handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.iesmila.a20170223_fils_handler.model.Player;
import net.iesmila.a20170223_fils_handler.utils.Downloader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mBtnDownload;
    private String mHtml;
    private ProgressBar mPrgProgres;
    private RecyclerView mRcyLlistaJugadors;

    private static final String DOWNLOAD_SUCCESSFUL ="DOWNLOAD_SUCCESSFUL";
    Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            boolean downloadSuccessful = msg.getData().getBoolean(DOWNLOAD_SUCCESSFUL);
            // aquí puc accedir a la UI
            if(downloadSuccessful) {


                SAXBuilder builder = new SAXBuilder();
                 Document document = null;
                try {
                    InputStream stream = new ByteArrayInputStream(mHtml.getBytes("UTF-8"));
                    document = (Document) builder.build(stream);
                    Element rootNode = document.getRootElement();
                    List<Element> list = rootNode.getChildren("tPlayerNames");
                    String resum="";
                    ArrayList<Player> players = new ArrayList<Player>();
                    for(Element e:list) {
                        Player p = new Player(
                            Integer.valueOf(e.getChildText("iId")),
                            e.getChildText("sName"),
                            e.getChildText("sCountryName"),
                            e.getChildText("sCountryFlag")
                        );
                        players.add(p);
                        resum += p.toString() +"\n\n";
                    }
                    PlayerAdapter adapter = new PlayerAdapter(players, MainActivity.this);
                    mRcyLlistaJugadors.setAdapter(adapter);

                } catch (JDOMException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

            }
            mBtnDownload.setEnabled(true);
            mPrgProgres.setVisibility(View.INVISIBLE);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------------
        mBtnDownload = (Button)findViewById(R.id.btnDownload);
        mPrgProgres = (ProgressBar) findViewById(R.id.prgProgres);
        mRcyLlistaJugadors = (RecyclerView) findViewById(R.id.rcyLlistaJugadors);
        configuraRecyclerView(mRcyLlistaJugadors);
        //-------------------------------------
        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        mHtml = Downloader.download("http://footballpool.dataaccess.eu/data/info.wso/AllPlayerNames?bSelected=");
                        boolean ok = mHtml!=null;
                        Message m = mHander.obtainMessage();
                        m.getData().putBoolean(DOWNLOAD_SUCCESSFUL, ok);
                        mHander.sendMessage(m);
                    }
                });

                mBtnDownload.setEnabled(false);
                mPrgProgres.setVisibility(View.VISIBLE);
                t.start();

            }
        });


    }

    private void configuraRecyclerView(RecyclerView mRcyLlistaJugadors) {

        mRcyLlistaJugadors.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRcyLlistaJugadors.setLayoutManager(llm);

    }
}
