package com.utad.david.task_3_fragments_lists.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.utad.david.task_3_fragments_lists.Model.Communities;
import com.utad.david.task_3_fragments_lists.R;

public class CommunitiesDialogAdapter extends RecyclerView.Adapter<CommunitiesDialogAdapter.CommunitiesDialogHolderDialog> {
    private Communities communities;


    public CommunitiesDialogAdapter(Communities communities) {
        this.communities = communities;
    }

    @Override
    public CommunitiesDialogAdapter.CommunitiesDialogHolderDialog onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dialog_communities, viewGroup, false);
        return new CommunitiesDialogAdapter.CommunitiesDialogHolderDialog(rootView);
    }

    //Método que sirve para añadir los datos a las diferentes communities (La información de las communities está en CommunitiesFreagment)
    @Override
    public void onBindViewHolder(CommunitiesDialogHolderDialog CommunitiesViewHolder, int i) {
        for (int j = 0; j < communities.getDatos().size(); j++) {
            switch (i) {
                case 0:
                    CommunitiesViewHolder.item_communities.setText("Número de alumnos: "+communities.getDatos().get(i));
                    break;
                case 1:
                    CommunitiesViewHolder.item_communities.setText("Número de aulas: "+communities.getDatos().get(i));
                    break;
                case 2:
                    CommunitiesViewHolder.item_communities.setText("Evento: "+communities.getDatos().get(i));
                    break;
                case 3:
                    CommunitiesViewHolder.item_communities.setText("Proyecto: "+communities.getDatos().get(i));
                    break;
                case 4:
                    CommunitiesViewHolder.item_communities.setText("Valoración: "+communities.getDatos().get(i));
            }
        }
    }

    //Devuelve la cantidad de communities
    @Override
    public int getItemCount() {
        return communities.getDatos().size();
    }

    //Esta clase es el ViewHolder del adapter, contiene la información de las celdas que se van a mostrar
    static class CommunitiesDialogHolderDialog extends RecyclerView.ViewHolder {

        public TextView item_communities;

        public CommunitiesDialogHolderDialog(View v) {
            super(v);
            item_communities = v.findViewById(R.id.item_communities);
        }
    }
}
