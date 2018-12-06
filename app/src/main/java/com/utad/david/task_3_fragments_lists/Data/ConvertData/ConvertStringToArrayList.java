package com.utad.david.task_3_fragments_lists.Data.ConvertData;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;


/*
Esta clase se usa para convertir ArrayList de tipo String a String, necesaria para convertir los ArrayList de las entidades
Es importante que tengan la anotacion TypeConverter para que la base de datos entienda los metodos que convierten los datos.
 */

public class ConvertStringToArrayList {

    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayLisr(ArrayList<String> list) {
        //GSON implentacion en el gradle
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
