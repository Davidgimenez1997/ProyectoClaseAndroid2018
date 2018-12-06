package com.utad.david.task_3_fragments_lists.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.utad.david.task_3_fragments_lists.Model.Communities;
import com.utad.david.task_3_fragments_lists.Model.Teacher;
import com.utad.david.task_3_fragments_lists.R;

import java.util.List;

public class CommunitiesAdapter extends RecyclerView.Adapter<CommunitiesAdapter.CommunitiesViewHolder>  {

    private final CommunitiesAdapter.OnItemClickListener listener;

    private List<Communities> mDataset;

    public CommunitiesAdapter (List<Communities> myDataset, CommunitiesAdapter.OnItemClickListener listener) {
        this.listener = listener;
        this.mDataset = myDataset;
    }

    //Obtenemos información del item
    public interface OnItemClickListener {
        void onItemClick(Communities item);
    }
    @Override
    public CommunitiesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycleview, viewGroup, false);
        return new CommunitiesViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(CommunitiesViewHolder communitiesViewHolder, final int i) {
        final Communities item = mDataset.get(i);
        communitiesViewHolder.textView.setText(item.getNamecomunities());
        communitiesViewHolder.imageView.setImageResource(item.getPhotocomunities());
        //Hacemos un onClickListener para la celda(itemView), y llamamos a nuestra interfaz
        communitiesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                    listener.onItemClick(item);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //Esta clase es el ViewHolder del adapter, contiene la información de las celdas que se van a mostrar
    public class CommunitiesViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public CommunitiesViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.tvnameitem);
            imageView = v.findViewById(R.id.imageViewitem);
        }
    }
}