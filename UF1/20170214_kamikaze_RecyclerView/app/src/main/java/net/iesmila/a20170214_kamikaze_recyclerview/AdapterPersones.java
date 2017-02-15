package net.iesmila.a20170214_kamikaze_recyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import net.iesmila.a20170214_kamikaze_recyclerview.model.Persona;

import java.util.ArrayList;

import static android.graphics.Color.*;

/**
 * Created by BERNAT on 14/02/2017.
 */
public class AdapterPersones extends
            RecyclerView.Adapter<AdapterPersones.ViewHolder>{


    private ArrayList<Persona> mPersones;
    private int mSelectedItem=-1;

    public AdapterPersones(ArrayList<Persona> persones) {
        mPersones = persones;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View fila = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.edicio_persona,
                parent,
                false);
        ViewHolder vh = new ViewHolder(fila);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        Persona p = mPersones.get(position);
        holder.itemView.setSelected(position==mSelectedItem);

        /*if(mSelectedItem == position){
            // Here I am just highlighting the background
            holder.itemView.setBackgroundColor(GREEN);
        }else{
            holder.itemView.setBackgroundColor(TRANSPARENT);
        }*/

        holder.mEdtNom.setText(p.getNom());
        holder.mImvAvatar.setImageResource(p.getFaceResource());
        holder.mRtbRating.setRating(p.getRating());
        holder.mImvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldPosition = mSelectedItem;
                mSelectedItem = position;
                if(oldPosition!=-1) {
                    notifyItemChanged(oldPosition);
                }
                notifyItemChanged(mSelectedItem);


            }
        });
    }

    public void removeItem(int position) {
        mPersones.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return mPersones.size();
    }



    //------------------------------------------------
    //                 VIEW HOLDER Begins
    //------------------------------------------------
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImvAvatar;
        private EditText mEdtNom;
        private RatingBar mRtbRating;
        public ViewHolder(View fila) {
            super(fila);
            mImvAvatar = (ImageView) fila.findViewById(R.id.imvAvatar);
            mEdtNom =    (EditText)  fila.findViewById(R.id.edtNom);
            mRtbRating = (RatingBar) fila.findViewById(R.id.rtbRating);
        }
    }
    //------------------------------------------------
    //                 VIEW HOLDER Ends
    //------------------------------------------------

}
