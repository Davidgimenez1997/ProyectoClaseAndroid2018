package com.utad.david.task_3_fragments_lists.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.utad.david.task_3_fragments_lists.Model.Notes;
import com.utad.david.task_3_fragments_lists.Model.Notifications;
import com.utad.david.task_3_fragments_lists.R;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder>  {

    private List<Notifications> mDataset;

    public NotificationsAdapter(List<Notifications> myDataset) {
        this.mDataset = myDataset;
    }

    @Override
    public NotificationsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notifications, viewGroup, false);
        return new NotificationsViewHolder(rootView);
    }

    //Método para pintar la información
    @Override
    public void onBindViewHolder(NotificationsViewHolder notificationsViewHolder, final int i) {
        if(mDataset!=null){
            final Notifications item = mDataset.get(i);
            notificationsViewHolder.tv_date_notifications.setText(item.getDate());
            notificationsViewHolder.tv_userName_notifications.setText(item.getUserName());
            notificationsViewHolder.tv_title_notifications.setText(item.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //Esta clase es el ViewHolder del adapter, contiene la información de las celdas que se van a mostrar
    public class NotificationsViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_date_notifications;
        public TextView tv_userName_notifications;
        public TextView tv_title_notifications;

        public NotificationsViewHolder(View v) {
            super(v);
            tv_date_notifications = v.findViewById(R.id.date_notifications);
            tv_userName_notifications = v.findViewById(R.id.userName_notifications);
            tv_title_notifications = v.findViewById(R.id.title_notifications);
        }
    }
}