package com.utad.david.task_3_fragments_lists.Activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.utad.david.task_3_fragments_lists.Data.Repository.UserRepository;
import com.utad.david.task_3_fragments_lists.Model.User;
import com.utad.david.task_3_fragments_lists.SessionUser;
import com.utad.david.task_3_fragments_lists.R;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    EditText et_email;
    @BindView(R.id.editTextPassword)
    EditText et_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.imgLogo)
    ImageView imgUtad;

    private UserRepository userRepository;
    private String str_email;
    private String str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // Asignamos el Listener a los EditText
        et_email.addTextChangedListener(loginTextWatcher);
        et_password.addTextChangedListener(loginTextWatcher);

        cornerPhoto();
        checkLoginAppDatabase();
    }

    /*
    Creamos un observer del LiveData del repositorio de User, comprobamos si nos devuelve algun Objecto, y en el caso que si que
    nos lo devuelva navegamos al menu
     */
    public void checkLoginAppDatabase(){
        userRepository = new UserRepository(getApplication());
        userRepository.getAllUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if(user!=null){
                    Intent intent = new Intent(getApplicationContext(),MainMenu.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void cornerPhoto(){
        //extraemos el drawable en un bitmap
        Drawable originalDrawable = getResources().getDrawable(R.drawable.logoutad);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        imgUtad.setImageDrawable(roundedDrawable);
    }

     //Método que escucha si los editText han sido cambiados
    public TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            str_email = et_email.getText().toString().trim();
            str_password = et_password.getText().toString().trim();
            //Comprobamos que los campos son validos y habilitamos el botón
            btn_login.setEnabled(enableButton());
            if (!enableButton()) {
                if (!emailValidate(str_email)) {
                    et_email.setError(getString(R.string.err_email));
                }
                if (!passValidate(str_password)) {
                    et_password.setError(getString(R.string.err_password));
                }
            }
        }
    };

    public boolean enableButton() {
        if (emailValidate(str_email) && passValidate(str_password)) {
            return true;
        } else {
            return false;
        }
    }

    //Compruebo que el formato del email es correcto
    public boolean emailValidate(String str_email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if (pattern.matcher(str_email).matches()) {
            return true;

        } else {
            return false;

        }
    }
    //Compruebo que la contraseña tenga más de 6 caracteres
    public boolean passValidate(String str_password) {
        if (str_password.length() >= 6) {
            return true;

        } else {
            return false;

        }
    }

    //Método del intent para pasar a la siguiente view guardando el email en el singleton
    public void onLoginAction(View view) {
        SessionUser.getInstance().user.setStr_email(str_email);
        Intent intent = new Intent(this, UserDataActivity.class);
        startActivity(intent);
    }
}
