package com.utad.david.task_3_fragments_lists.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;

//Implementa parcelable para poder pasarle el objeto al Dialog

/*
Se le pone la anotacion Entity para indicar que es una tabla
 */
@Entity(tableName = "COMMUNITIES_TABLE")
public class Communities implements Parcelable {

    /*
    Se le pone la anotacion PrimaryKey para indicar que es la clave primaria ademas no puede ser nula, en este caso se genera
    automatica.
     */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    //La anotacion ColumnInfo da nombre a la columna de la base de datos
    @ColumnInfo(name = "name")
    private String namecomunities;
    @ColumnInfo(name = "img")
    private int photocomunities;
    @ColumnInfo(name = "coordinador")
    private String coordinador;
    @ColumnInfo(name = "tematica")
    private String tematica;
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "data")
    private ArrayList<String> datos;



    public Communities(String namecomunities, int photocomunities, String coordinador, String tematica, String descripcion, String email, ArrayList<String> datos ) {
        this.namecomunities = namecomunities;
        this.photocomunities = photocomunities;
        this.coordinador = coordinador;
        this.tematica = tematica;
        this.descripcion = descripcion;
        this.email = email;
        this.datos = datos;
    }

    protected Communities(Parcel in) {
        namecomunities = in.readString();
        photocomunities = in.readInt();
        coordinador = in.readString();
        tematica = in.readString();
        descripcion = in.readString();
        email = in.readString();
        datos = in.createStringArrayList();
    }

    public static final Creator<Communities> CREATOR = new Creator<Communities>() {
        @Override
        public Communities createFromParcel(Parcel in) {
            return new Communities(in);
        }

        @Override
        public Communities[] newArray(int size) {
            return new Communities[size];
        }
    };

    public String getNamecomunities() {
        return namecomunities;
    }

    public void setNamecomunities(String namecomunities) {
        this.namecomunities = namecomunities;
    }

    public int getPhotocomunities() {
        return photocomunities;
    }

    public void setPhotocomunities(int photocomunities) {
        this.photocomunities = photocomunities;
    }

    public String getCoordinador(){return coordinador;}

    public void setCoordinador(String coordinador){this.coordinador = coordinador;}

    public String getTematica(){return tematica;}

    public void setTematica(String tematica) { this.tematica = tematica; }

    public ArrayList<String> getDatos() { return datos; }

    public void setDatos(ArrayList<String> datos) { this.datos = datos; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(namecomunities);
        dest.writeInt(photocomunities);
        dest.writeString(coordinador);
        dest.writeString(tematica);
        dest.writeString(descripcion);
        dest.writeString(email);
        dest.writeList(datos);
    }
}
