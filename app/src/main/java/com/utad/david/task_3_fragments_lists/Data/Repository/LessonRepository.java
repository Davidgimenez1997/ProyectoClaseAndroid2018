package com.utad.david.task_3_fragments_lists.Data.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.utad.david.task_3_fragments_lists.Data.BD.BDUtad;
import com.utad.david.task_3_fragments_lists.Data.DAO.LessonDAO;
import com.utad.david.task_3_fragments_lists.Model.Lesson;

import java.util.List;

public class LessonRepository {

    /*
    Creamos una instancia de nuestro DAO y un LiveData de tipo lista (Ya que vamos a recoger un select, varias columnas)
    y los iniciamos en el constructor. Primero esta iniciada la base de datos y luego igualamos a nuestro LiveData el valor del
    metodo de la select.
     */

    private LessonDAO lessonDAO;
    private LiveData<List<Lesson>> allLesson;

    public LessonRepository(Application application) {
        BDUtad bdUtad = BDUtad.getDatabase(application);
        lessonDAO = bdUtad.lessonDAO();
        allLesson = lessonDAO.getAllLesson();
    }

    public LiveData<List<Lesson>> getAllLesson() {
        return allLesson;
    }

}
