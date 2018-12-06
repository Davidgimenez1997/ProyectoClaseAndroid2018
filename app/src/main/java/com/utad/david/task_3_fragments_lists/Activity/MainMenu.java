package com.utad.david.task_3_fragments_lists.Activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.utad.david.task_3_fragments_lists.Data.BD.BDUtad;
import com.utad.david.task_3_fragments_lists.Data.DAO.UserDAO;
import com.utad.david.task_3_fragments_lists.Data.Repository.UserRepository;
import com.utad.david.task_3_fragments_lists.Fragment.CommunitiesFragment;
import com.utad.david.task_3_fragments_lists.Fragment.LessonsFragment;
import com.utad.david.task_3_fragments_lists.Fragment.NotesFragment;
import com.utad.david.task_3_fragments_lists.Fragment.NotificationFragment;
import com.utad.david.task_3_fragments_lists.Fragment.TeachersFragment;
import com.utad.david.task_3_fragments_lists.Model.User;
import com.utad.david.task_3_fragments_lists.R;
import com.utad.david.task_3_fragments_lists.SessionUser;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imagemenu;
    private TextView name;
    private TextView surname;
    private TextView email;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private UserRepository userRepository;

    @Override

    //Definimos el Toolbar, el DrawerLayout y el NavigationView
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        findById();
        setSupportActionBar(toolbar);

        userRepository= new UserRepository(getApplication());

        //Muestra en la pantalla un boton que hace visible que es un menu lateral
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Inflamos el layout del header del menu para poder modificar el contenido del header
        LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_main_menu_navheader, navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        findByIdNavigetionView();


        putInfoUserInHeaderMenu();

        //Si la foto es null cogemos una por defecto
        checkPhotoUserNull();

        //Nuestro título sera Lessons
        setTitle(R.string.title_first_fragment);
        displaySelectedScreen(R.id.nav_lessons);

    }

    public void findById() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    //Se tiene que buscar el id con el navigetionView delante ya que en este elemento se incluye el header del menu
    public void findByIdNavigetionView() {
        imagemenu = navigationView.findViewById(R.id.imagemenu);
        name = navigationView.findViewById(R.id.textviewname_menu);
        surname = navigationView.findViewById(R.id.textviewsurname_menu);
        email = navigationView.findViewById(R.id.textview_email);
    }


    /*
    Tanto en el metodo putInfoUserInHeaderMenu() y checkPhotoUserNull() observamos el LiveData del repositorio de user
    y en el caso que haya un objeto mostramos su informacion en la cabecera del menu
     */


    public void putInfoUserInHeaderMenu() {
        userRepository.getAllUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if(user!=null){
                    name.setText(user.getStr_name());
                    surname.setText(user.getStr_surname());
                    email.setText(user.getStr_email());
                }
            }
        });
      }

    public void checkPhotoUserNull() {
        userRepository.getAllUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if(user!=null){
                    if (user.getStr_img_user() != null) {
                        putPhotoUser(user.getStr_img_user());
                    } else {
                        imagemenu.setImageResource(R.drawable.ic_person_outline_black_24dp);
                    }
                }
            }
        });

    }

    //Sirve para poner la foto que hemos recogido en el PersonalData en la cabecera del menu
    public void putPhotoUser(String stringUri) {
        Uri uri = Uri.parse(stringUri);
        final InputStream imageStream;
        try {
            imageStream = getContentResolver().openInputStream(uri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            RoundedBitmapDrawable roundedDrawable1 =
                    RoundedBitmapDrawableFactory.create(getResources(), selectedImage);

            //asignamos el CornerRadius
            roundedDrawable1.setCornerRadius(selectedImage.getHeight());
            imagemenu.setImageDrawable(roundedDrawable1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Cuando le damos hacia atrás con el menu abierto se cierra el menu
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Inflamos en el menu el layout del main_menu(botón derecha logout)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //Funcionalidad de Logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {

            //LLamamos a una asyncTask creada abajo para eliminar el usuario.
            (new MainMenu.DeleteUserAsyncTask(BDUtad.INSTANCE)).execute();
            //return true;
        }

        return true;
    }

    //Vacía user
    public void setEmptyItems() {
        SessionUser.getInstance().user.setStr_email(null);
        SessionUser.getInstance().user.setInt_age(0);
        SessionUser.getInstance().user.setStr_address(null);
        SessionUser.getInstance().user.setStr_city(null);
        SessionUser.getInstance().user.setStr_description(null);
        SessionUser.getInstance().user.setStr_first_hobbie(null);
        SessionUser.getInstance().user.setStr_gender(null);
        SessionUser.getInstance().user.setStr_img_user(null);
        SessionUser.getInstance().user.setStr_name(null);
        SessionUser.getInstance().user.setStr_phone(null);
        SessionUser.getInstance().user.setStr_postal_code(null);
        SessionUser.getInstance().user.setStr_second_hobbie(null);
        SessionUser.getInstance().user.setStr_second_surname(null);
        SessionUser.getInstance().user.setStr_surname(null);
        SessionUser.getInstance().user.setStr_third_hobbie(null);
    }

    //Llamamos a un método propio y le pasamos el id del item pinchado
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    //Este método sirve para cargar los diferentes fragments
    private void displaySelectedScreen(int itemId) {

        //Creamos el objeto fragment
        Fragment fragment = null;

        //iniciamos los fragments dependiendo del item selecionado
        switch (itemId) {
            case R.id.nav_lessons:
                setTitle(R.string.title_first_fragment);
                fragment = new LessonsFragment();
                break;
            case R.id.nav_notifications:
                setTitle(R.string.title_second_fragment);
                fragment = new NotificationFragment();
                break;
            case R.id.nav_notes:
                setTitle(R.string.title_three_fragment);
                fragment = new NotesFragment();
                break;
            case R.id.nav_teachers:
                setTitle(R.string.title_four_fragment);
                fragment = new TeachersFragment();
                break;
            case R.id.nav_communities:
                setTitle(R.string.title_five_fragment);
                fragment = new CommunitiesFragment();
                break;

        }

        //Remplazamos el fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        //Una vez cambiado el fragment cerramos el menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    /*
    Esta asyncTask lo que hace es que en segundo plano borra todos los usuarios. En el metodo onPostExecute
    que se ejecuta cuando se termine de borrar todos los usuarios,invocamos al hilo principal para modificar la
    vista, mostramos un Toast informativo y navegamos al Login.
     */

    private class DeleteUserAsyncTask extends AsyncTask<Void,Void,Void> {
        public UserDAO userDao;

        public DeleteUserAsyncTask(BDUtad bdUtad) {
            userDao = bdUtad.userDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast info = Toast.makeText(getApplicationContext(), getString(R.string.info_delete_acount), Toast.LENGTH_LONG);
                    info.show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    setEmptyItems();
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

}