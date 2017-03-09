package net.iesmila.a20170223_fils_handler;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import net.iesmila.a20170223_fils_handler.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BERNAT on 24/02/2017.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder>{


    private List<Player> mPlayers;
    private MainActivity mActivity;

    public PlayerAdapter(List<Player> players, MainActivity activity) {
        mPlayers = players;
        mActivity = activity;
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View fila = LayoutInflater.from(mActivity).inflate(
                R.layout.fila_jugador,parent, false );
        ViewHolder vh = new ViewHolder(fila);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Player p = mPlayers.get(position);

        holder.mTxvJugador.setText(p.getName());
        holder.mTxvPais.setText(p.getCountry());

        //----------- Encarreguem al ImageLoader la feina de descarregar la imatge   ---------------------------
        // Get singleton instance
        ImageLoader imageLoader = ImageLoader.getInstance();
        // Load image, decode it to Bitmap and return Bitmap to callback
        if(p.getFlagBitmap()!=null) {
            holder.mImvBandera.setImageBitmap(p.getFlagBitmap());
        } else {
            holder.mImvBandera.setImageBitmap(null);
            int h = holder.mImvBandera.getDrawable().getIntrinsicHeight();
            int w = holder.mImvBandera.getDrawable().getIntrinsicWidth();

            imageLoader.displayImage(p.getFlag(),
                    new NonViewAware(new ImageSize(w, h), ViewScaleType.FIT_INSIDE),
                    new SimpleImageLoadingListener() {
            //imageLoader.loadImage(p.getFlag(), new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view,
                                              Bitmap loadedImage) {
                    p.setFlagBitmap(loadedImage);
                    notifyItemChanged(position);
                }
            });
        }


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImvBandera;
        private TextView  mTxvPais;
        private TextView  mTxvJugador;

        public ViewHolder(View itemView) {

            super(itemView);

            mImvBandera = (ImageView) itemView.findViewById(R.id.imvBandera);
            mTxvJugador = (TextView)  itemView.findViewById(R.id.txvJugador);
            mTxvPais =    (TextView)  itemView.findViewById(R.id.txvPais);
        }
    }
}
