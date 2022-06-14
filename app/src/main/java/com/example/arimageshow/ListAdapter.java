package com.example.arimageshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<ItemAdapter> mList;
    private Context mContext;
    public ListAdapter(List<ItemAdapter> list, Context context){
        super();
        mList = list;
        mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_row, parent, false);

        final ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.mImg.setOnClickListener(this);
        /*viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Input mode = " + viewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                //Builder.mode = viewHolder.getAdapterPosition();
            }
        });*/

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder, int position) {
        ItemAdapter itemAdapter = mList.get(position);
        ((ViewHolder) viewHolder).mImg.setImageResource(itemAdapter.getImage());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{


        public ImageView mImg;
        public ViewHolder(View itemView) {

            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.grasstree1);

        }
    }
}
