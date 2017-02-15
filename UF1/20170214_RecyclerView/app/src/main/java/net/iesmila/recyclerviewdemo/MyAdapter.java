package net.iesmila.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BERNAT on 14/02/2017.
 */

interface OnNomClickListener {
    void onNomClick( int pos, String nom);
}

public class MyAdapter extends
        RecyclerView.Adapter<MyAdapter.ViewHolder> implements OnNomClickListener{
    private ArrayList<String> mDataset;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder  {
        // each data item is just a string in this case
        public TextView mTextView;
        public LinearLayout mRow;
        public ViewHolder(LinearLayout v) {
            super(v);
            mRow = v;
            mTextView = (TextView)v.findViewById(R.id.edtNom);
        }

        public void bind(final OnNomClickListener listener, final String nom) {
            mRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onNomClick(getAdapterPosition(),nom);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position));
        holder.bind( this, mDataset.get(position));
    }

    @Override
    public void onNomClick(int pos, String nom) {
        mDataset.remove(pos);
        this.notifyItemRemoved(pos);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}