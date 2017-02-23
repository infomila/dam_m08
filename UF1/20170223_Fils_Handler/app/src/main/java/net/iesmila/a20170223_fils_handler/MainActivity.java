package net.iesmila.a20170223_fils_handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mBtnDownload;
    private TextView mTxvText;
    private String mHtml;
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
                    for(Element e:list) {
                        Player p = new Player(
                            Integer.valueOf(e.getChildText("iId")),
                            e.getChildText("sName"),
                            e.getChildText("sCountryName"),
                            e.getChildText("sCountryFlag")
                        );
                        resum += p.toString() +"\n\n";


                    }
                    mTxvText.setText(resum);

                } catch (JDOMException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }




            } else {
                mTxvText.setText("Error de descarrega");
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------------
        mBtnDownload = (Button)findViewById(R.id.btnDownload);
        mTxvText = (TextView) findViewById(R.id.txvText);
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
                t.start();

            }
        });


    }
}