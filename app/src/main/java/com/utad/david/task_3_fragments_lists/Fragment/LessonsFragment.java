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

import com.utad.david.task_3_fragments_lists.Adapter.LessonAdapter;
import com.utad.david.task_3_fragments_lists.Data.Repository.LessonRepository;
import com.utad.david.task_3_fragments_lists.DialogFragment.LessonDialogFragment;
import com.utad.david.task_3_fragments_lists.Model.Lesson;
import com.utad.david.task_3_fragments_lists.R;

import java.util.List;

public class LessonsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LessonRepository lessonRepository;

    public LessonsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycleview, container, false);
        configRecyclerViewLesson(view);
        configAdaparterLesson();
        return view;
    }

    //Configuramos el RecyclerView
    public void configRecyclerViewLesson(View view) {
        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    //Configuramos el adapter y añadimos el listener del onClick()
    public void configAdaparterLesson() {

        /*
        Observamos la lista que nos devuelve la consulta select y se la pasamos a nuestro adapter.
         */

        lessonRepository = new LessonRepository(getActivity().getApplication());
        lessonRepository.getAllLesson().observe(this, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(@Nullable List<Lesson> lessons) {
                mAdapter = new LessonAdapter(lessons, new LessonAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Lesson item) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                        if (prev != null) {
                            transaction.remove(prev);
                        }
                        transaction.addToBackStack(null);
                        //Pasamos la información del item en el que se está pinchando
                        // Creamos el dialogo y lo mostramos
                        LessonDialogFragment newFragment = LessonDialogFragment.newInstance(item);
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
