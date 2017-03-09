package net.iesmila.a20170223_fils_handler.model;

import android.util.Log;

import net.iesmila.a20170223_fils_handler.MainActivity;
import net.iesmila.a20170223_fils_handler.PlayerAdapter;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BERNAT on 09/03/2017.
 */

public class PlayerParser {

    public static List<Player> parseFromXML(String XML){
        ArrayList<Player> players = new ArrayList<Player>();
        SAXBuilder builder = new SAXBuilder();
        Document document = null;
        try {
            InputStream stream = new ByteArrayInputStream(XML.getBytes("UTF-8"));
            document = (Document) builder.build(stream);
            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren("tPlayerNames");
            String resum="";

            for(Element e:list) {
                Player p = new Player(
                        Integer.valueOf(e.getChildText("iId")),
                        e.getChildText("sName"),
                        e.getChildText("sCountryName"),
                        e.getChildText("sCountryFlagLarge")
                );
                players.add(p);
                resum += p.toString() +"\n\n";
            }

            return players;

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Player> parseFromJSON(String JSON) {
      return null;
    }
}