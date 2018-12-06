package com.utad.david.task_3_fragments_lists.Data.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.utad.david.task_3_fragments_lists.Data.BD.BDUtad;
import com.utad.david.task_3_fragments_lists.Data.DAO.CommunitiesDAO;
import com.utad.david.task_3_fragments_lists.Model.Communities;

import java.util.List;

public class CommunitiesRepository {

    /*
    Creamos una instancia de nuestro DAO y un LiveData de tipo lista (Ya que vamos a recoger un select, varias columnas)
    y los iniciamos en el constructor. Primero esta iniciada la base de datos y luego igualamos a nuestro LiveData el valor del
    metodo de la select.
     */

    private CommunitiesDAO communitiesDAO;
    private LiveData<List<Communities>> allCommunities;


    public CommunitiesRepository(Application application) {
        BDUtad bdUtad = BDUtad.getDatabase(application);
        communitiesDAO = bdUtad.communitiesDAO();
        allCommunities = communitiesDAO.getAllCommunities();
    }

    public LiveData<List<Communities>> getAllCommunities() {
        return allCommunities;
    }

}
