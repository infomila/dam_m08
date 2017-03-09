package net.iesmila.a20170308_mestredetall_frag.dummy;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ViatgesManager {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Viatge> ITEMS = new ArrayList<Viatge>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, Viatge> ITEM_MAP = new HashMap<Integer, Viatge>();


    static {

        addItem(  new Viatge(1, "Londres", "WTF!?",4.5f, 3.5f, null));
        addItem(  new Viatge(2, "Corea del Nord", "Missil va !",5f, 3.5f, null));
        addItem(  new Viatge(3, "Indonesia", "WTF!?",4.5f, 3f, null));
        addItem(  new Viatge(4, "Budapest", "WTF!?",2, 2, null));
        addItem(  new Viatge(5, "Honk Kong", "ChinoMandarino",2.5f, 2.5f, null));
     }

    private static void addItem(Viatge item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Viatge {
        private int mId;
        private String mName;
        private String mDesc;
        private float mRatingQuality;
        private float mRatingPrice;
        private Bitmap mImage;

        public Viatge(int id, String mName, String mDesc, float mRatingQuality, float mRatingPrice, Bitmap mImage) {
            this.mId = id;
            this.mName = mName;
            this.mDesc = mDesc;
            this.mRatingQuality = mRatingQuality;
            this.mRatingPrice = mRatingPrice;
            this.mImage = mImage;
        }

        public int getId() {
            return mId;
        }

        public String getName() {
            return mName;
        }

        public String getDesc() {
            return mDesc;
        }

        public float getRatingQuality() {
            return mRatingQuality;
        }

        public float getRatingPrice() {
            return mRatingPrice;
        }

        public Bitmap getImage() {
            return mImage;
        }

        @Override
        public String toString() {
            return "Viatge{" +
                    "mId=" + mId +
                    ", mName='" + mName + '\'' +
                    ", mDesc='" + mDesc + '\'' +
                    ", mRatingQuality=" + mRatingQuality +
                    ", mRatingPrice=" + mRatingPrice +
                    '}';
        }
    }
}
