package com.utad.david.task_3_fragments_lists.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.utad.david.task_3_fragments_lists.Model.Teacher;
import com.utad.david.task_3_fragments_lists.R;

public class TeacherDialogAdapter extends RecyclerView.Adapter<TeacherDialogAdapter.TeacherDialogHolderDialog> {

    private Teacher teacher;

    public TeacherDialogAdapter(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public TeacherDialogAdapter.TeacherDialogHolderDialog onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_teacher, viewGroup, false);
        return new TeacherDialogHolderDialog(rootView);
    }

    //Método que sirve para añadir las asignaturas a los diferentes teachers (La información de los profesores está en TeachersFreagment)
    @Override
    public void onBindViewHolder(TeacherDialogHolderDialog lessonViewHolder, int i) {
        for (int j=0;j<teacher.getSubject().size();j++){
            switch (i) {
                case 0:
                    lessonViewHolder.item_teacher.setText(teacher.getSubject().get(i));
                    break;
                case 1:
                    lessonViewHolder.item_teacher.setText(teacher.getSubject().get(i));
                    break;
                case 2:
                    lessonViewHolder.item_teacher.setText(teacher.getSubject().get(i));
            }
        }
    }

    //Devuelve la cantidad de asignaturas
    @Override
    public int getItemCount() {
        return teacher.getSubject().size();
    }

    //Esta clase es el ViewHolder del adapter, contiene la información de las celdas que se van a mostrar
    static class TeacherDialogHolderDialog extends RecyclerView.ViewHolder {

        public TextView item_teacher;

        public TeacherDialogHolderDialog(View v) {
            super(v);
            item_teacher = v.findViewById(R.id.item_teacher);
        }
    }
}