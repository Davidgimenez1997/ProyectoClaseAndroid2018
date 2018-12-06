package com.utad.david.task_3_fragments_lists.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/*
Se le pone la anotacion Entity para indicar que es una tabla
 */
@Entity(tableName = "NOTIFICATIONS_TABLE")
public class Notifications {

    /*
    Se le pone la anotacion PrimaryKey para indicar que es la clave primaria ademas no puede ser nula, en este caso se genera
    automatica.
    */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int idNotifications;
    //La anotacion ColumnInfo da nombre a la columna de la base de datos
    @ColumnInfo(name = "date_notification")
    private String date;
    @ColumnInfo(name = "user_name_notification")
    private String userName;
    @ColumnInfo(name = "title_notification")
    private String title;

    public Notifications(String date, String userName, String title){
        this.date = date;
        this.userName = userName;
        this.title = title;
    }

    @NonNull
    public int getIdNotifications() {
        return idNotifications;
    }

    public void setIdNotifications(@NonNull int idNotifications) {
        this.idNotifications = idNotifications;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
