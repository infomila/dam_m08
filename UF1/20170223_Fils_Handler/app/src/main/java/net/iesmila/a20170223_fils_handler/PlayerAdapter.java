package net.iesmila.a20170223_fils_handler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.iesmila.a20170223_fils_handler.model.Player;

import java.util.ArrayList;

/**
 * Created by BERNAT on 24/02/2017.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder>{


    private ArrayList<Player> mPlayers;
    private MainActivity mActivity;

    public PlayerAdapter(ArrayList<Player> players, MainActivity activity) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        Player p = mPlayers.get(position);

        holder.mTxvJugador.setText(p.getName());
        holder.mTxvPais.setText(p.getCountry());
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
