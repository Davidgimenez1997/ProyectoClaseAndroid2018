package com.utad.david.task_3_fragments_lists.DialogFragment;

import android.content.Intent;
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

import com.utad.david.task_3_fragments_lists.Adapter.TeacherDialogAdapter;
import com.utad.david.task_3_fragments_lists.Model.Lesson;
import com.utad.david.task_3_fragments_lists.Model.Teacher;
import com.utad.david.task_3_fragments_lists.R;

public class TeacherDialogFragment extends DialogFragment {

    public Teacher teacher;
    public TextView textViewname;
    public TextView textViewsurname;
    public TextView textViewdescription;
    public ImageView imageViewv;
    public RecyclerView recyclerView;
    public FloatingActionButton floatingActionButton;
    public StaggeredGridLayoutManager staggeredGridLayoutManager;
    public RecyclerView.Adapter mAdapter;
    public static String KEY = "Itemteacher";

    //Creamos una nueva instancia de esta clase y le pasamos por parámetro un objeto de
    // teacher para más adelante recogerlo con los argumentos
    public static TeacherDialogFragment newInstance(Teacher teacher) {
        TeacherDialogFragment fragment = new TeacherDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY,teacher);
        fragment.setArguments(args);
        return fragment;
    }

    //Nuestra variable teacher coge el valor que se le está pasando
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teacher = getArguments().getParcelable(KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_teacher, container, false);
        v.setBackgroundResource(R.drawable.corner_dialog);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        findById(v);
        configRecycleView();
        configAdapter();
        putData();
        clickEmailButton();
        return v;
    }

    public void findById(View v){
        textViewname = v.findViewById(R.id.textViewnameteacher);
        textViewsurname = v.findViewById(R.id.textViewsurnameteacher);
        textViewdescription = v.findViewById(R.id.textViewdescriptionteacher);
        imageViewv = v.findViewById(R.id.imageViewteacher);
        recyclerView = v.findViewById(R.id.recycleview_dialog_teacher);
        floatingActionButton = v.findViewById(R.id.fabteacher);
    }


    public void configRecycleView(){
        recyclerView.setHasFixedSize(true);
        //Gracias al staggeredGridLayoutManager somos capaces de poner en horizontal el RecyclerView
        staggeredGridLayoutManager= new StaggeredGridLayoutManager(1,LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    public void configAdapter(){
        mAdapter = new TeacherDialogAdapter(teacher);
        recyclerView.setAdapter(mAdapter);
    }

    //Ponemos la info del profesor
    public void putData(){
        textViewname.setText(teacher.getNameteacher());
        textViewsurname.setText(teacher.getSurnameteacher());
        textViewdescription.setText(teacher.getDescription());
        imageViewv.setImageResource(teacher.getPhototeacher());
    }

    //Abre el Gmail y nos rellena el asunto del mismo con el nombre del profesor
    public void clickEmailButton(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                String recipientList = teacher.getEmailteacher();
                String[] recipients = recipientList.split(",");
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Teacher: " + teacher.getNameteacher() + " " + teacher.getSurnameteacher());
                startActivity(Intent.createChooser(intent,"Choose an email client"));
            }
        });
    }
}
