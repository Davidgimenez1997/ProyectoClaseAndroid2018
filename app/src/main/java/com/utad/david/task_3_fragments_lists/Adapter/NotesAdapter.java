package com.utad.david.task_3_fragments_lists.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.utad.david.task_3_fragments_lists.Model.Notes;
import com.utad.david.task_3_fragments_lists.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Notes> mDataset;

    public NotesAdapter(List<Notes> myDataset) {
        this.mDataset = myDataset;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notes, viewGroup, false);
        return new NotesViewHolder(rootView);
    }

    //Método para pintar la información
    @Override
    public void onBindViewHolder(NotesViewHolder notesViewHolder, final int i) {
        if (mDataset != null) {
            final Notes item = mDataset.get(i);
            notesViewHolder.tv_date_notes.setText(item.getDate());
            notesViewHolder.tv_classes_notes.setText(item.getClasses());
            notesViewHolder.tv_works_notes.setText(item.getWorks());
            notesViewHolder.tv_note_notes.setText(item.getNotes());

        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //Esta clase es el ViewHolder del adapter, contiene la información de las celdas que se van a mostrar
    public class NotesViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_date_notes;
        public TextView tv_classes_notes;
        public TextView tv_works_notes;
        public TextView tv_note_notes;

        public NotesViewHolder(View v) {
            super(v);
            tv_date_notes = v.findViewById(R.id.date_notes);
            tv_classes_notes = v.findViewById(R.id.classes_notes);
            tv_works_notes = v.findViewById(R.id.work_notes);
            tv_note_notes = v.findViewById(R.id.note_notes);
        }
    }
}