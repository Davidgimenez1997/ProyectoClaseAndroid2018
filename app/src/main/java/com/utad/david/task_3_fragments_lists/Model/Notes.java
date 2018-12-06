package com.utad.david.task_3_fragments_lists.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/*
Se le pone la anotacion Entity para indicar que es una tabla
 */
@Entity(tableName = "SCORES_table")
public class Notes{


    /*
    Se le pone la anotacion PrimaryKey para indicar que es la clave primaria ademas no puede ser nula, en este caso se genera
    automatica.
     */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_notes")
    private int id;
    //La anotacion ColumnInfo da nombre a la columna de la base de datos
    @ColumnInfo(name = "date_notes")
    private String date;
    @ColumnInfo(name = "classes_notes")
    private String classes;
    @ColumnInfo(name = "works_notes")
    private String works;
    private String notes;

    public Notes(String date, String classes, String works, String notes) {
        this.date = date;
        this.classes = classes;
        this.works = works;
        this.notes = notes;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
