package com.utad.david.task_3_fragments_lists.Data.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.utad.david.task_3_fragments_lists.Model.Notifications;

import java.util.List;

/*
La clase DAO es donde se hacen las sentencias sql de las tablas de la base de datos. Es importante la anotacion @Dao encima de la
clase para indicar que es una clase de ese tipo. En esta clase hay metodos para insertar una lista, borrar todos y un select de
toda la tabla en orden ascendente por id. En los metodos de la interfaz no se nos puede olvidar las anotaciones.
 */

@Dao
public interface NotificationDAO {

    @Insert
    void insertAllNotifications(List<Notifications> notificationsList);

    @Query("DELETE FROM NOTIFICATIONS_TABLE ")
    void deleteAllNotifications();

    @Query("SELECT * from NOTIFICATIONS_TABLE ORDER BY id ASC")
    LiveData<List<Notifications>> getAllNotifications();
}
