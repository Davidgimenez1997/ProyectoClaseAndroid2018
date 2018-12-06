package com.utad.david.task_3_fragments_lists.Data.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.utad.david.task_3_fragments_lists.Data.BD.BDUtad;
import com.utad.david.task_3_fragments_lists.Data.DAO.UserDAO;
import com.utad.david.task_3_fragments_lists.Model.User;

public class UserRepository {

    /*
    Creamos una instancia de nuestro DAO y un LiveData de tipo lista (Ya que vamos a recoger un select, varias columnas)
    y los iniciamos en el constructor. Primero esta iniciada la base de datos y luego igualamos a nuestro LiveData el valor del
    metodo de la select.
     */

    private UserDAO userDao;
    private LiveData<User> allUser;

    public UserRepository(Application application){
        BDUtad bdUtad = BDUtad.getDatabase(application);
        userDao = bdUtad.userDAO();
        allUser = userDao.getAllUser();
    }

    public LiveData<User> getAllUser() {
        return allUser;
    }
}
