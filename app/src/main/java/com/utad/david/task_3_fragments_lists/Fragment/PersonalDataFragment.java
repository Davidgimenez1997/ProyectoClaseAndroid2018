package com.utad.david.task_3_fragments_lists.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.utad.david.task_3_fragments_lists.R;
import com.utad.david.task_3_fragments_lists.SessionUser;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class PersonalDataFragment extends Fragment {

    @BindView(R.id.checkBox_first_hobbie)
    CheckBox checkBox_first_hobbie;
    @BindView(R.id.checkBox_second_hobbie)
    CheckBox checkBox_second_hobbie;
    @BindView(R.id.checkBox_third_hobbie)
    CheckBox checkBox_third_hobbie;
    @BindView(R.id.EditText_description)
    EditText editText_description;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroupGender;
    @BindView(R.id.radioButton_male)
    RadioButton radioGroup_male;
    @BindView(R.id.radioButton_female)
    RadioButton radioGroup_female;
    @BindView(R.id.imageView_selector)
    ImageView imagen_view_galerry;
    @BindView(R.id.textView_name)
    TextView textview_name;
    @BindView(R.id.textView_surname)
    TextView textview_surname;
    @BindView(R.id.btn_save)
    FloatingActionButton btn_save;

    private PersonalDataFragment.OnFragmentInteractionListener mListener;
    private String stringUri;
    private String str_description;
    private String str_gender_male;
    private String str_gender_female;
    private String str_hobbies_first;
    private String str_hobbies_second;
    private String str_hobbies_three;
    private Boolean bool_rgchecked, bool_cbchecked1, bool_cbchecked2, bool_cbchecked3, bool_desc;
    private Uri imageUri;
    private Drawable originalDrawable;

    public PersonalDataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_data, container, false);
        ButterKnife.bind(this,view);
        btn_save.setBackgroundTintList(getResources().getColorStateList(R.color.disable_button));
        btn_save.setEnabled(false);
        setFalse();
        textview_name.setText(SessionUser.getInstance().user.getStr_name());
        textview_surname.setText(SessionUser.getInstance().user.getStr_surname());

        editText_description.addTextChangedListener(enableButtonSave);
        openGallery();
        checkedGender();
        checkHobbies();
        clickButtonSave();
        return view;
    }

    public void setFalse(){
        bool_rgchecked = false;
        bool_cbchecked1 = false;
        bool_cbchecked2 = false;
        bool_cbchecked3 = false;
        bool_desc = false;
    }

    //Sirve para comprobar si los radioButton están activados
    public void checkedGender(){
        radioGroup_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SessionUser.getInstance().user.setStr_gender(radioGroup_male.getText().toString());
                    bool_rgchecked = true;
                }
                btn_save.setEnabled(enableButton());
            }
        });
        radioGroup_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SessionUser.getInstance().user.setStr_gender(radioGroup_female.getText().toString());
                    bool_rgchecked = true;

                }
                btn_save.setEnabled(enableButton());
            }
        });
    }
    //Comprueba si están seleccionados alguno de los hobbies
    public void checkHobbies(){
        checkBox_first_hobbie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SessionUser.getInstance().user.setStr_first_hobbie(checkBox_first_hobbie.getText().toString());
                    bool_cbchecked1 = true;
                }else{
                    SessionUser.getInstance().user.setStr_first_hobbie(null);
                    bool_cbchecked1 = false;
                }
                btn_save.setEnabled(enableButton());
            }
        });
        checkBox_second_hobbie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SessionUser.getInstance().user.setStr_second_hobbie(checkBox_second_hobbie.getText().toString());
                    bool_cbchecked2 = true;
                }else{
                    SessionUser.getInstance().user.setStr_second_hobbie(null);
                    bool_cbchecked2 = false;
                }
                btn_save.setEnabled(enableButton());
            }
        });
        checkBox_third_hobbie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SessionUser.getInstance().user.setStr_third_hobbie(checkBox_third_hobbie.getText().toString());
                    bool_cbchecked3 = true;
                }else{
                    SessionUser.getInstance().user.setStr_third_hobbie(null);
                    bool_cbchecked3 = false;
                }
                btn_save.setEnabled(enableButton());
            }
        });
    }
   //Sirve para abrir la galería
    public void openGallery(){
        imagen_view_galerry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
            }
        });
    }

    //Sirve para comprobar el estado del intent lanzado anteriormente, si no ha habido ningún problema ponemos la imagen
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                imageUri = data.getData();
                final InputStream imageStream = getActivity().getApplicationContext().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imagen_view_galerry.setImageBitmap(selectedImage);
                if(imageUri!=null){
                    stringUri = imageUri.toString();
                    SessionUser.getInstance().user.setStr_img_user(stringUri);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getActivity().getApplicationContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    //Comprueba que la descripción no esté vacía
    public TextWatcher enableButtonSave = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            str_description = editText_description.getText().toString().trim();
            if(str_description.equals("")) {
                bool_desc = false;
            }else{
                bool_desc = true;
            }
            btn_save.setEnabled(enableButton());
        }
    };

    //Comprueba que todos los campos tienen información
    public boolean enableButton(){
        if (bool_desc == false || bool_rgchecked == false || (bool_cbchecked1 == false && bool_cbchecked2 == false && bool_cbchecked3 == false)) {
            btn_save.setBackgroundTintList(getResources().getColorStateList(R.color.disable_button));
            return false;
        }else {
            btn_save.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimaryDark));
            return true;
        }
    }

    public void clickButtonSave(){
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    SessionUser.getInstance().user.setStr_description(editText_description.getText().toString());
                    mListener.comunicationWithButtonClickSave(v);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PersonalDataFragment.OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    //Interfaz utilizada para comunicarnos con el activity
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void comunicationWithButtonClickSave(View view);
    }

}
