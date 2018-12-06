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

import com.utad.david.task_3_fragments_lists.Adapter.CommunitiesAdapter;
import com.utad.david.task_3_fragments_lists.Data.Repository.CommunitiesRepository;
import com.utad.david.task_3_fragments_lists.DialogFragment.CommunityDialogFragment;
import com.utad.david.task_3_fragments_lists.Model.Communities;
import com.utad.david.task_3_fragments_lists.R;

import java.util.List;

public class CommunitiesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CommunitiesRepository communitiesRepository;

    public CommunitiesFragment() {
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
        configRecyclerViewCommunities(view);
        configAdaparterCommunities();
        return view;
    }
    //Configuramos el RecyclerView
    public void configRecyclerViewCommunities(View view){
        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
    //Configuramos el adapter y añadimos el listener del onClick()
    public void configAdaparterCommunities(){

        /*
        Observamos la lista que nos devuelve la consulta select y se la pasamos a nuestro adapter.
         */

        communitiesRepository = new CommunitiesRepository(getActivity().getApplication());
        communitiesRepository.getAllCommunities().observe(this, new Observer<List<Communities>>() {
            @Override
            public void onChanged(@Nullable List<Communities> communities) {
                mAdapter = new CommunitiesAdapter(communities, new CommunitiesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Communities item) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                        if (prev != null) {
                            transaction.remove(prev);
                        }
                        transaction.addToBackStack(null);
                        //Pasamos la información del item en el que se está pinchando
                        // Creamos el dialogo y lo mostramos
                        CommunityDialogFragment newFragment = CommunityDialogFragment.newInstance(item);
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
