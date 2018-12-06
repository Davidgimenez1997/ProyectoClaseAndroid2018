package com.utad.david.task_3_fragments_lists.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

//Implementa parcelable para poder pasarle el objeto al Dialog

/*
Se le pone la anotacion Entity para indicar que es una tabla
 */
@Entity(tableName = "CLASSES_TABLE")
public class Lesson implements Parcelable {

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
    private String nameclass;
    @ColumnInfo(name = "img")
    private int photoclass;
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @ColumnInfo(name = "curseYear")
    private String courseYear;
    @ColumnInfo(name = "estadistic")
    private ArrayList<String> estadistic;

    public Lesson(String nameclass, int photoclass, String courseYear, String descripcion, ArrayList<String> estadistic) {
        this.nameclass = nameclass;
        this.photoclass = photoclass;
        this.courseYear = courseYear;
        this.descripcion = descripcion;
        this.estadistic = estadistic;
    }

    public Lesson(Parcel in) {
        id = in.readInt();
        nameclass = in.readString();
        photoclass = in.readInt();
        courseYear = in.readString();
        descripcion = in.readString();
        estadistic = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nameclass);
        dest.writeInt(photoclass);
        dest.writeString(courseYear);
        dest.writeList(estadistic);
        dest.writeString(descripcion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Lesson> CREATOR = new Creator<Lesson>() {
        @Override
        public Lesson createFromParcel(Parcel in) {
            return new Lesson(in);
        }

        @Override
        public Lesson[] newArray(int size) {
            return new Lesson[size];
        }
    };

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCourseYear(String courseYear) {
        this.courseYear = courseYear;
    }

    public void setEstadistic(ArrayList<String> estadistic) {
        this.estadistic = estadistic;
    }

    public String getNameclass() {
        return nameclass;
    }

    public void setNameclass(String nameclass) {
        this.nameclass = nameclass;
    }

    public int getPhotoclass() {
        return photoclass;
    }

    public void setPhotoclass(int photoclass) {
        this.photoclass = photoclass;
    }

    public String getCourseYear() { return courseYear; }

    public String getDescripcion() { return  descripcion; }

    public ArrayList<String> getEstadistic() { return estadistic; }


}
