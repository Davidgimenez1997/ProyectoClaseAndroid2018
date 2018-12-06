package com.utad.david.task_3_fragments_lists.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.utad.david.task_3_fragments_lists.Model.Lesson;
import com.utad.david.task_3_fragments_lists.R;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private List<Lesson> mDataset;
    private final OnItemClickListener listener;

    //Obtenemos información del item
    public interface OnItemClickListener {
        void onItemClick(Lesson item);
    }


    public LessonAdapter(List<Lesson> myDataset, LessonAdapter.OnItemClickListener listener) {
        this.mDataset = myDataset;
        this.listener = listener;

    }

    @Override
    public LessonAdapter.LessonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycleview, viewGroup, false);
        return new LessonViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(LessonViewHolder lessonViewHolder, int i) {
        if(mDataset!=null){
            final Lesson item = mDataset.get(i);
            lessonViewHolder.textView.setText(item.getNameclass());
            lessonViewHolder.imageView.setImageResource(item.getPhotoclass());
            //Hacemos un onClickListener para la celda(itemView), y llamamos a nuestra interfaz
            lessonViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemClick(item);
                    }
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //Esta clase es el ViewHolder del adapter, contiene la información de las celdas que se van a mostrar
    static class LessonViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public LessonViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.tvnameitem);
            imageView = v.findViewById(R.id.imageViewitem);
        }
    }
}

