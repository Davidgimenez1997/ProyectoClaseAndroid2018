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

import com.utad.david.task_3_fragments_lists.Adapter.CommunitiesDialogAdapter;
import com.utad.david.task_3_fragments_lists.Adapter.TeacherDialogAdapter;
import com.utad.david.task_3_fragments_lists.Model.Communities;
import com.utad.david.task_3_fragments_lists.R;

public class CommunityDialogFragment extends DialogFragment {
    public Communities communities;
    public TextView textViewTitle;
    public TextView textViewCoordinador;
    public TextView textViewTematica;
    public TextView textViewDescripcion;
    public ImageView imageView;
    public RecyclerView recyclerView;
    public FloatingActionButton floatingActionButton;
    public StaggeredGridLayoutManager staggeredGridLayoutManager;
    public RecyclerView.Adapter mAdapter;
    private static String KEY = "Itemcomunities";

    //Creamos una nueva instancia de esta clase y le pasamos por parámetro un objeto de
    // communities para más adelante recogerlo con los argumentos
    public static CommunityDialogFragment newInstance(Communities communities) {
        CommunityDialogFragment fragment = new CommunityDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY,communities);
        fragment.setArguments(args);
        return fragment;
    }

    //Nuestra variable communities coge el valor que se le está pasando
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communities = getArguments().getParcelable(KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_communities, container, false);
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
        textViewTitle  = v.findViewById(R.id.textViewnameCommunities);
        textViewCoordinador  = v.findViewById(R.id.textViewsurnameCommunities);
        textViewTematica  = v.findViewById(R.id.textViewTematicaCommunities);
        textViewDescripcion  = v.findViewById(R.id.textViewdescriptionCommunities);
        imageView = v.findViewById(R.id.imageViewCommunities);
        recyclerView = v.findViewById(R.id.recycleview_dialog_communities);
        floatingActionButton = v.findViewById(R.id.fabCommunities);
    }
    public void configRecycleView(){
        recyclerView.setHasFixedSize(true);
        //Gracias al staggeredGridLayoutManager somos capaces de poner en horizontal el RecyclerView
        staggeredGridLayoutManager= new StaggeredGridLayoutManager(1, LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    public void configAdapter(){
        mAdapter = new CommunitiesDialogAdapter(communities);
        recyclerView.setAdapter(mAdapter);
    }

    //Ponemos la info del profesor
    public void putData(){
        textViewTitle.setText(communities.getNamecomunities());
        textViewCoordinador.setText(communities.getCoordinador());
        textViewTematica.setText(communities.getTematica());
        textViewDescripcion.setText(communities.getDescripcion());
        imageView.setImageResource(communities.getPhotocomunities());
    }

    //Abre el Gmail y nos rellena el asunto del mismo con el nombre del profesor
    public void clickEmailButton(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                String recipientList = communities.getEmail();
                String[] recipients = recipientList.split(",");
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Communities: " + communities.getNamecomunities() + " " + communities.getCoordinador());
                startActivity(Intent.createChooser(intent,"Choose an email client"));
            }
        });
    }
}
