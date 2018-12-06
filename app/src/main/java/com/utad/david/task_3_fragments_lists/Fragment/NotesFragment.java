package com.utad.david.task_3_fragments_lists.Fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utad.david.task_3_fragments_lists.Adapter.NotesAdapter;
import com.utad.david.task_3_fragments_lists.Data.Repository.NotesRepository;
import com.utad.david.task_3_fragments_lists.Model.Notes;
import com.utad.david.task_3_fragments_lists.R;

import java.util.List;

public class NotesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private NotesRepository notesRepository;

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_view_notes, container, false);
        configRecyclerViewNotes(view);
        configAdaparterNotes();

        return view;
    }

    //Configuramos el RecyclerView
    public void configRecyclerViewNotes(View view){
        mRecyclerView = view.findViewById(R.id.recycle_view_notes);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    //Configuramos el adapter
    public void configAdaparterNotes(){

        /*
        Observamos la lista que nos devuelve la consulta select y se la pasamos a nuestro adapter.
         */

        notesRepository = new NotesRepository(getActivity().getApplication());
        notesRepository.getAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(@Nullable List<Notes> notesEntities) {
                mAdapter = new NotesAdapter(notesEntities);
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
