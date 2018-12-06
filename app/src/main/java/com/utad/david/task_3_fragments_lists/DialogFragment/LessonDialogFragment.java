package com.utad.david.task_3_fragments_lists.DialogFragment;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.utad.david.task_3_fragments_lists.Adapter.LessonDialogAdapter;
import com.utad.david.task_3_fragments_lists.Model.Lesson;
import com.utad.david.task_3_fragments_lists.Model.Teacher;
import com.utad.david.task_3_fragments_lists.R;

public class LessonDialogFragment extends DialogFragment {

    public Lesson lesson;
    public TextView textViewnombre;
    public TextView  textViewCourseYear;
    public TextView  textViewDescripcion;
    public ImageView imageViewv;
    public RecyclerView recyclerView;
    public StaggeredGridLayoutManager staggeredGridLayoutManager;
    public android.support.v7.widget.RecyclerView.Adapter mAdapter;
    public static String KEY = "ItemLesson";

    //Creamos una nueva instancia de esta clase y le pasamos por parámetro un objeto de
    // lessons para más adelante recogerlo con los argumentos
    public static LessonDialogFragment newInstance(Lesson lesson) {
        LessonDialogFragment fragment = new LessonDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY,lesson);
        fragment.setArguments(args);
        return fragment;
    }
    //Nuestra variable lessons coge el valor que se le está pasando
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lesson = getArguments().getParcelable(KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_lesson, container, false);
        v.setBackgroundResource(R.drawable.corner_dialog);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        findById(v);
        configRecycleView();
        configAdapter();
        putData();
        return v;
    }

    public void findById(View v){
        textViewnombre = v.findViewById(R.id.tvNameLesson);
        textViewCourseYear = v.findViewById(R.id.tvCourseYearLesson);
        textViewDescripcion = v.findViewById(R.id.tvDescriptionLesson);
        imageViewv = v.findViewById(R.id.imageLesson);
        recyclerView = v.findViewById(R.id.recycleview_dialog_lesson);
    }

    public void configRecycleView(){
        recyclerView.setHasFixedSize(true);
        //Gracias al staggeredGridLayoutManager somos capaces de poner en horizontal el RecyclerView
        staggeredGridLayoutManager= new StaggeredGridLayoutManager(1,LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }
    public void configAdapter(){
        mAdapter = new LessonDialogAdapter(lesson);
        recyclerView.setAdapter(mAdapter);
    }
    //Ponemos la info de la clase
    public void putData(){
        textViewnombre.setText(lesson.getNameclass());
        textViewCourseYear.setText(lesson.getCourseYear());
        textViewDescripcion.setText(lesson.getDescripcion());
        imageViewv.setImageResource(lesson.getPhotoclass());
    }

}