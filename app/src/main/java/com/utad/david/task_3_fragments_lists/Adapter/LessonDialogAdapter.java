package com.utad.david.task_3_fragments_lists.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.utad.david.task_3_fragments_lists.Model.Lesson;
import com.utad.david.task_3_fragments_lists.R;

public class LessonDialogAdapter extends RecyclerView.Adapter<LessonDialogAdapter.LessonDialogHolderDialog> {
    private Lesson lesson;

    public LessonDialogAdapter(Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public LessonDialogAdapter.LessonDialogHolderDialog onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_lesson, viewGroup, false);
        return new LessonDialogHolderDialog(rootView);
    }
    //Método que sirve para añadir los datos a las diferentes clases (La información de las clases está en LessonsFragment)
    @Override
    public void onBindViewHolder(LessonDialogHolderDialog lessonViewHolder, int i) {
        for (int j = 0; j<lesson.getEstadistic().size(); j++){
            switch (i) {
                case 0:
                    lessonViewHolder.item_lesson.setText("Numero de alumnos: " +lesson.getEstadistic().get(i));
                    break;
                case 1:
                    lessonViewHolder.item_lesson.setText("Profesor: " +lesson.getEstadistic().get(i));
                    break;
                case 2:
                    lessonViewHolder.item_lesson.setText("Nota Media: "+lesson.getEstadistic().get(i));
                    break;
                case 3:
                    lessonViewHolder.item_lesson.setText("Mi Nota Media: "+ lesson.getEstadistic().get(i));
                    break;
                case 4:
                    lessonViewHolder.item_lesson.setText("Ranking: "+lesson.getEstadistic().get(i));
            }
        }
    }

    //Devuelve la cantidad de lessons
    @Override
    public int getItemCount() {
        return lesson.getEstadistic().size();
    }

    //Esta clase es el ViewHolder del adapter, contiene la información de las celdas que se van a mostrar
    static class LessonDialogHolderDialog extends RecyclerView.ViewHolder {

        public TextView item_lesson;

        public LessonDialogHolderDialog(View v) {
            super(v);
            item_lesson = v.findViewById(R.id.item_lesson);
        }
    }
}

