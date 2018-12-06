package com.utad.david.task_3_fragments_lists.Data.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.utad.david.task_3_fragments_lists.Data.BD.BDUtad;
import com.utad.david.task_3_fragments_lists.Data.DAO.TeacherDAO;
import com.utad.david.task_3_fragments_lists.Model.Teacher;

import java.util.List;

public class TeacherRepository {

    /*
    Creamos una instancia de nuestro DAO y un LiveData de tipo lista (Ya que vamos a recoger un select, varias columnas)
    y los iniciamos en el constructor. Primero esta iniciada la base de datos y luego igualamos a nuestro LiveData el valor del
    metodo de la select.
     */

    private TeacherDAO teacherDAO;
    private LiveData<List<Teacher>> allTeacher;

    public TeacherRepository(Application application) {
        BDUtad bdUtad = BDUtad.getDatabase(application);
        teacherDAO = bdUtad.teacherDAO();
        allTeacher = teacherDAO.getAllTeachers();
    }

    public LiveData<List<Teacher>> getAllTeacher() {
        return allTeacher;
    }

}
