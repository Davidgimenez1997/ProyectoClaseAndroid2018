package com.utad.david.task_3_fragments_lists.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.utad.david.task_3_fragments_lists.Model.Lesson;
import com.utad.david.task_3_fragments_lists.Model.Teacher;
import com.utad.david.task_3_fragments_lists.R;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>  {

    private final TeacherAdapter.OnItemClickListener listener;

    private List<Teacher> mDataset;

    //Obtenemos información del item
    public interface OnItemClickListener {
        void onItemClick(Teacher item);
    }

    public TeacherAdapter(List<Teacher> myDataset, TeacherAdapter.OnItemClickListener listener) {
        this.mDataset = myDataset;
        this.listener = listener;
    }

    @Override
    public TeacherViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycleview, viewGroup, false);
        return new TeacherViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(TeacherViewHolder teacherViewHolder, int i) {
        final Teacher item = mDataset.get(i);
        teacherViewHolder.textView.setText(item.getNameteacher());
        teacherViewHolder.imageView.setImageResource(item.getPhototeacher());
        //Hacemos un onClickListener para la celda(itemView), y llamamos a nuestra interfaz
        teacherViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //Esta clase es el ViewHolder del adapter, contiene la información de las celdas que se van a mostrar
    public class TeacherViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public TeacherViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.tvnameitem);
            imageView = v.findViewById(R.id.imageViewitem);
        }
    }
}
