package com.utad.david.task_3_fragments_lists.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.utad.david.task_3_fragments_lists.Data.BD.BDUtad;
import com.utad.david.task_3_fragments_lists.Data.DAO.UserDAO;
import com.utad.david.task_3_fragments_lists.Fragment.FormDataFragment;
import com.utad.david.task_3_fragments_lists.Fragment.PersonalDataFragment;
import com.utad.david.task_3_fragments_lists.Model.User;
import com.utad.david.task_3_fragments_lists.R;
import com.utad.david.task_3_fragments_lists.SessionUser;

public class UserDataActivity extends AppCompatActivity implements FormDataFragment.OnFragmentInteractionListener,PersonalDataFragment.OnFragmentInteractionListener{

    private FormDataFragment formDataFragment;
    private PersonalDataFragment personalDataFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

       //Añadimos el formDataFragment al frameContainer
        if (findViewById(R.id.frame_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            formDataFragment = new FormDataFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_container, formDataFragment).commit();
        }
    }


    //Reemplaza en el frameContainer el fragment anterior por el personalDataFragment
    @Override
    public void comunicationWithButtonClickNext(View view) {
        personalDataFragment = new PersonalDataFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, personalDataFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void comunicationWithButtonClickSave(View view) {

        //Cuando se termine de rellenar todoo el formulario, nuestro (SessionUser) tendra todos los campos completos
        //y llamaremos una AsyncTask para insertar los datos de nuestro sessionUser
        (new UserDataActivity.InsertUserAsyncTask(BDUtad.INSTANCE)).execute();
    }

    /*
    Esta asyncTask lo que hace es que en segundo plano crea un usuario y le settea todos sus atributos sacando el valor
    con el singelton(Seteado anteriormente), despues borra todos los usuarios e inserta el usuario que al que se le acaban
    de settear todos los atributos. En el metodo onPostExecute que se ejecuta cuando se termine de isertar al usuario,
    invocamos al hilo principal para modificar la vista, mostramos un Toast informativo y navegamos al Menu.
     */

    private class InsertUserAsyncTask extends AsyncTask<Void,Void,Void> {
        public UserDAO userDao;

        public InsertUserAsyncTask(BDUtad bdUtad) {
            userDao = bdUtad.userDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            User user = new User();
            //user.setIdUser(SessionUser.getInstance().user.getIdUser());
            user.setStr_img_user(SessionUser.getInstance().user.getStr_img_user());
            user.setStr_gender(SessionUser.getInstance().user.getStr_gender());
            user.setStr_email(SessionUser.getInstance().user.getStr_email());
            user.setStr_name(SessionUser.getInstance().user.getStr_name());
            user.setStr_surname(SessionUser.getInstance().user.getStr_surname());
            user.setStr_second_surname(SessionUser.getInstance().user.getStr_second_surname());
            user.setStr_city(SessionUser.getInstance().user.getStr_city());
            user.setStr_address(SessionUser.getInstance().user.getStr_address());
            user.setStr_phone(SessionUser.getInstance().user.getStr_phone());
            user.setStr_postal_code(SessionUser.getInstance().user.getStr_postal_code());
            user.setStr_description(SessionUser.getInstance().user.getStr_description());
            user.setStr_first_hobbie(SessionUser.getInstance().user.getStr_first_hobbie());
            user.setStr_second_hobbie(SessionUser.getInstance().user.getStr_second_hobbie());
            user.setStr_third_hobbie(SessionUser.getInstance().user.getStr_third_hobbie());
            user.setInt_age(SessionUser.getInstance().user.getInt_age());
            userDao.deleteAllUsers();
            userDao.insertUser(user);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast info = Toast.makeText(getApplicationContext(), getString(R.string.user_insert), Toast.LENGTH_LONG);
                    info.show();
                    Intent intent = new Intent(getApplicationContext(),MainMenu.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });
        }
    }
}
