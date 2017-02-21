package net.iesmila.a20170214_kamikaze_recyclerview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import net.iesmila.a20170214_kamikaze_recyclerview.model.Persona;

import java.util.ArrayList;

/**
 * Created by BERNAT on 14/02/2017.
 */
public class AdapterPersones extends
            RecyclerView.Adapter<AdapterPersones.ViewHolder>{

    private int mPosicioSeleccionada = -1;
    private View mViewPosicioSeleccionada = null;
    private ArrayList<Persona> mPersones;
    private MainActivity mActivity;
    private Persona mPersonaPendentCanviarImatge;
    private int mPosicioPersonaPendentCanviarImatge;

    public AdapterPersones(ArrayList<Persona> persones, MainActivity activity)
    {
        mPersones = persones;
        mActivity= activity;
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
    public void onBindViewHolder(final ViewHolder holder, final int posicioActual) {
        final Persona p = mPersones.get(posicioActual);

        holder.mEdtNom.setText(p.getNom());
        holder.mImvAvatar.setImageDrawable(p.getFaceDrawable());
        holder.mRtbRating.setRating(p.getRating());
        holder.mPersona = p;

         /*holder.itemView.setBackgroundColor(
                mPosicioSeleccionada==posicioActual?Color.RED:Color.TRANSPARENT
        );*/
        holder.itemView.setSelected(mPosicioSeleccionada==posicioActual);

        //holder.mImvDelete.setVisibility(mModeEsborrat?View.VISIBLE:View.INVISIBLE);

        holder.mImvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int posicioActualDeVeritat = holder.getAdapterPosition();
                Log.d("XXXX", "Pos:"+posicioActualDeVeritat);
                mPersones.remove(posicioActualDeVeritat);
                notifyItemRemoved(posicioActualDeVeritat);
//                notifyItemRangeChanged(posicioActual,getItemCount());
                 if(mPosicioSeleccionada == posicioActualDeVeritat) {
                    mPosicioSeleccionada = -1;
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // cas on SELECCIONEM
                int posicioActual = holder.getAdapterPosition();
                if(mPosicioSeleccionada!=posicioActual){
                    int posicioAnterior = mPosicioSeleccionada;
                    mPosicioSeleccionada = posicioActual;
                    if(posicioAnterior!=-1) notifyItemChanged(posicioAnterior);

                }else {
                    mPosicioSeleccionada = -1;
                }
                notifyItemChanged(posicioActual);
                mActivity.notificaItemSeleccionatCanviat(mPosicioSeleccionada);

            }
        });

        //--------------- Listener de l'Image View per canviar
        //--------------- la foto.
        holder.mImvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mPersonaPendentCanviarImatge = p;
                mPosicioPersonaPendentCanviarImatge = posicioActual;
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                boolean handlerExists =
                        (intent.resolveActivity(mActivity.getPackageManager())!= null);
                if(handlerExists) {
                    mActivity.startActivityForResult(Intent.createChooser(intent,
                            "Select Picture"), MainActivity.PICK_IMAGE);
                }
            }
        });

    }

    public void posarImatgePendent( Bitmap pBitmap) {
        if(mPersonaPendentCanviarImatge!=null) {
            mPersonaPendentCanviarImatge.setFaceDrawable(
                    new BitmapDrawable(pBitmap));
            notifyItemChanged(mPosicioPersonaPendentCanviarImatge);
        }
    }

    public Persona getPersonaSeleccionada() {
        if(mPosicioSeleccionada==-1) return null;
        return mPersones.get(mPosicioSeleccionada);
    }


    @Override
    public int getItemCount() {
        return mPersones.size();
    }

    public void esborrarElementSeleccionat() {

        if(mPosicioSeleccionada!=-1) {
            mPersones.remove(mPosicioSeleccionada);
            notifyItemRemoved(mPosicioSeleccionada);
            mPosicioSeleccionada = -1;
        }

    }

    public void moureElementSeleccionat(int offset) {
        if(mPosicioSeleccionada!=-1) {

            int novaPosicio = mPosicioSeleccionada + offset;
            if(!(novaPosicio<0 || novaPosicio>=mPersones.size())) {

                Persona tmp = mPersones.get(novaPosicio);
                mPersones.set(novaPosicio,mPersones.get(mPosicioSeleccionada) );
                mPersones.set(mPosicioSeleccionada,tmp);
                int posicioSeleccionadaCopia = mPosicioSeleccionada;
                mPosicioSeleccionada = novaPosicio;

                notifyItemMoved(posicioSeleccionadaCopia, novaPosicio);


            }

        }
    }


    //------------------------------------------------
    //                 VIEW HOLDER Begins
    //------------------------------------------------
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImvAvatar;
        private EditText mEdtNom;
        private RatingBar mRtbRating;
        private ImageView mImvDelete;
        private Persona mP;
        private Persona mPersona;

        public ViewHolder(View fila) {
            super(fila);
            mImvAvatar = (ImageView) fila.findViewById(R.id.imvAvatar);
            mEdtNom =    (EditText)  fila.findViewById(R.id.edtNom);
            mRtbRating = (RatingBar) fila.findViewById(R.id.rtbRating);
            mImvDelete = (ImageView) fila.findViewById(R.id.imvDelete);
        }
    }
    //------------------------------------------------
    //                 VIEW HOLDER Ends
    //------------------------------------------------

}
