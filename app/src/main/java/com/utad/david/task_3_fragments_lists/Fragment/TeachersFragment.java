package com.utad.david.task_3_fragments_lists.Fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utad.david.task_3_fragments_lists.Adapter.TeacherAdapter;
import com.utad.david.task_3_fragments_lists.Data.Repository.TeacherRepository;
import com.utad.david.task_3_fragments_lists.DialogFragment.TeacherDialogFragment;
import com.utad.david.task_3_fragments_lists.Model.Teacher;
import com.utad.david.task_3_fragments_lists.R;

import java.util.List;

public class TeachersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TeacherRepository teacherRepository;

    public TeachersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_recycleview, container, false);
        configRecyclerViewTeacher(view);
        configAdaparterTeacher();
        return view;
    }

    //Configuramos el RecyclerView
    public void configRecyclerViewTeacher(View view){
        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    //Configuramos el adapter y añadimos el listener del onClick()
    public void configAdaparterTeacher(){

        /*
        Observamos la lista que nos devuelve la consulta select y se la pasamos a nuestro adapter.
         */

        teacherRepository = new TeacherRepository(getActivity().getApplication());
        teacherRepository.getAllTeacher().observe(this, new Observer<List<Teacher>>() {
            @Override
            public void onChanged(@Nullable List<Teacher> teacherEntities) {
                mAdapter = new TeacherAdapter(teacherEntities, new TeacherAdapter.OnItemClickListener() {
                    //Hacemos una transición y mostramos el nuevo fragment
                    @Override
                    public void onItemClick(Teacher item) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                        if (prev != null) {
                            transaction.remove(prev);
                        }
                        transaction.addToBackStack(null);
                        //Pasamos la información del item en el que se está pinchando
                        TeacherDialogFragment newFragment = TeacherDialogFragment.newInstance(item);
                        newFragment.show(transaction, "dialog");
                    }
                });
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}