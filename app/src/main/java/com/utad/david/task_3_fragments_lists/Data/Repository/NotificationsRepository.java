package com.utad.david.task_3_fragments_lists.Data.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.utad.david.task_3_fragments_lists.Data.BD.BDUtad;
import com.utad.david.task_3_fragments_lists.Data.DAO.NotificationDAO;
import com.utad.david.task_3_fragments_lists.Model.Notifications;

import java.util.List;

public class NotificationsRepository {

    /*
    Creamos una instancia de nuestro DAO y un LiveData de tipo lista (Ya que vamos a recoger un select, varias columnas)
    y los iniciamos en el constructor. Primero esta iniciada la base de datos y luego igualamos a nuestro LiveData el valor del
    metodo de la select.
     */

    private NotificationDAO notificationsDAO;
    private LiveData<List<Notifications>> allNotifications;

    public NotificationsRepository(Application application) {
        BDUtad bdUtad = BDUtad.getDatabase(application);
        notificationsDAO = bdUtad.notificationDAO();
        allNotifications = notificationsDAO.getAllNotifications();
    }

    public LiveData<List<Notifications>> getAllNotifications() {
        return allNotifications;
    }

}
