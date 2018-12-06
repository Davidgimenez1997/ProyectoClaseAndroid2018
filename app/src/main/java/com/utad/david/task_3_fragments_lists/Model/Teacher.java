package com.utad.david.task_3_fragments_lists.Model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.utad.david.task_3_fragments_lists.Data.ConvertData.ConvertStringToArrayList;

import java.util.ArrayList;

//Implementa parcelable para poder pasarle el objeto al Dialog

/*
Se le pone la anotacion Entity para indicar que es una tabla
 */
@Entity(tableName = "TEACHERS_TABLE")
public class Teacher implements Parcelable {

     /*
    Se le pone la anotacion PrimaryKey para indicar que es la clave primaria ademas no puede ser nula, en este caso se genera
    automatica.
    */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    //La anotacion ColumnInfo da nombre a la columna de la base de datos
    @ColumnInfo(name = "name")
    private String nameteacher;
    @ColumnInfo(name = "surname")
    private String surnameteacher;
    @ColumnInfo(name = "photo")
    private int phototeacher;
    @ColumnInfo(name = "subject")
    @TypeConverters(ConvertStringToArrayList.class)
    private ArrayList<String> subject;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "email")
    private String emailteacher;

    public Teacher( String nameteacher, String surnameteacher, int phototeacher, ArrayList<String> subject,String description,String emailteacher) {
        this.nameteacher = nameteacher;
        this.surnameteacher = surnameteacher;
        this.phototeacher = phototeacher;
        this.subject = subject;
        this.description = description;
        this.emailteacher = emailteacher;
    }


    protected Teacher(Parcel in) {
        id = in.readInt();
        nameteacher = in.readString();
        surnameteacher = in.readString();
        phototeacher = in.readInt();
        subject = in.createStringArrayList();
        description = in.readString();
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getNameteacher() {
        return nameteacher;
    }

    public void setNameteacher(String nameteacher) {
        this.nameteacher = nameteacher;
    }

    public int getPhototeacher() {
        return phototeacher;
    }

    public void setPhototeacher(int phototeacher) {
        this.phototeacher = phototeacher;
    }

    public String getSurnameteacher() {
        return surnameteacher;
    }

    public void setSurnameteacher(String surnameteacher) {
        this.surnameteacher = surnameteacher;
    }

    public ArrayList<String> getSubject() {
        return subject;
    }

    public void setSubject(ArrayList<String> subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailteacher() {
        return emailteacher;
    }

    public void setEmailteacher(String emailteacher) {
        this.emailteacher = emailteacher;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nameteacher);
        dest.writeString(surnameteacher);
        dest.writeInt(phototeacher);
        dest.writeList(subject);
        dest.writeString(description);
        dest.writeString(emailteacher);
    }
}