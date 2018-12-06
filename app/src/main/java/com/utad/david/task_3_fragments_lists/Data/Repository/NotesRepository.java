package com.utad.david.task_3_fragments_lists.Data.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.utad.david.task_3_fragments_lists.Data.BD.BDUtad;
import com.utad.david.task_3_fragments_lists.Data.DAO.NotesDAO;
import com.utad.david.task_3_fragments_lists.Model.Notes;

import java.util.List;

public class NotesRepository {

    /*
    Creamos una instancia de nuestro DAO y un LiveData de tipo lista (Ya que vamos a recoger un select, varias columnas)
    y los iniciamos en el constructor. Primero esta iniciada la base de datos y luego igualamos a nuestro LiveData el valor del
    metodo de la select.
     */

    private NotesDAO notesDAO;
    private LiveData<List<Notes>> allNotes;

    public NotesRepository(Application application){
        BDUtad bdUtad = BDUtad.getDatabase(application);
        notesDAO = bdUtad.notesDAO();
        allNotes = notesDAO.getAllNotes();
    }

    public LiveData<List<Notes>> getAllNotes() {
        return allNotes;
    }

}
