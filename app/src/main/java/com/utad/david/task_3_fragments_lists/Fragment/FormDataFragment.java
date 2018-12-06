package com.utad.david.task_3_fragments_lists.Fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.utad.david.task_3_fragments_lists.R;
import com.utad.david.task_3_fragments_lists.SessionUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormDataFragment extends Fragment {

    @BindView(R.id.editText_name)
    EditText et_name;
    @BindView(R.id.editText_surname)
    EditText et_surname;
    @BindView(R.id.editText_surname2)
    EditText et_second_surname;
    @BindView(R.id.editText_address)
    EditText et_address;
    @BindView(R.id.editText_postalCode)
    EditText et_postalcode;
    @BindView(R.id.editText_city)
    EditText et_city;
    @BindView(R.id.editText_phone)
    EditText et_phone;
    @BindView(R.id.textViewDate)
    TextView tv_date;
    @BindView(R.id.btn_next)
    FloatingActionButton btn_next;

    private String str_name;
    private String str_surname;
    private String str_second_surname;
    private String str_birthday;
    private String str_address;
    private String str_postalcode;
    private String str_city;
    private String str_phone_type;
    private String str_phone;
    private boolean bool_name;
    private boolean bool_surname;
    private boolean bool_second_surname;
    private boolean bool_birthday;
    private boolean bool_address;
    private boolean bool_postalcode;
    private boolean bool_city;
    private boolean bool_phone_type;
    private boolean bool_phone;
    private int int_age;

    private FormDataFragment.OnFragmentInteractionListener mListener;
    private DatePickerFragment datePickerFragment;

    public FormDataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_data, container, false);
        ButterKnife.bind(this,view);
        btn_next.setBackgroundTintList(getResources().getColorStateList(R.color.disable_button));
        btn_next.setEnabled(false);
        setFalse();
        takeAge();
        clickButtonNext();
        textListener();
        return view;
    }

    public void setTrue(){
        bool_name = true;
        bool_surname = true;
        bool_second_surname = true;
        bool_address = true;
        bool_postalcode = true;
        bool_city = true;
        bool_phone_type = true;
        bool_phone = true;
    }
    public void setFalse(){
        bool_name = false;
        bool_surname = false;
        bool_second_surname = false;
        bool_address = false;
        bool_postalcode = false;
        bool_city = false;
        bool_phone_type = false;
        bool_phone = false;
    }
    //Método para calcular la edad y mostrar el Calendario
    public void takeAge(){
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(),"datePicker");
                //Hacemos el observer del MutableData(DatePickerFragment)
                datePickerFragment.selectedDate.observe(getViewLifecycleOwner(), new Observer<GregorianCalendar>() {
                    @Override
                    public void onChanged(@Nullable GregorianCalendar gregorianCalendar) {
                        if(gregorianCalendar != null){
                            str_birthday = format(gregorianCalendar);
                            String [] arrDate = str_birthday.split("-");
                            int int_selected_year = Integer.parseInt(arrDate[2]);
                            Calendar cl = Calendar.getInstance();
                            int_age = cl.get(Calendar.YEAR) - int_selected_year;
                            tv_date.setText(str_birthday);
                            bool_birthday = true;
                            btn_next.setEnabled(enableButton());
                        }
                    }
                });
            }
        });
    }

    public void clickButtonNext(){
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    setterDataUser();
                    mListener.comunicationWithButtonClickNext(v);
                }
            }
        });
    }
    //Seteamos Datos
    public void setterDataUser(){
        SessionUser.getInstance().user.setStr_name(et_name.getText().toString());
        SessionUser.getInstance().user.setStr_surname(et_surname.getText().toString());
        SessionUser.getInstance().user.setStr_second_surname(et_second_surname.getText().toString());
        SessionUser.getInstance().user.setInt_age(int_age);
        SessionUser.getInstance().user.setStr_address(et_address.getText().toString());
        SessionUser.getInstance().user.setStr_postal_code(et_postalcode.getText().toString());
        SessionUser.getInstance().user.setStr_city(et_city.getText().toString());
        SessionUser.getInstance().user.setStr_phone(et_phone.getText().toString());
    }
    //Añadimos los listeners
    public void textListener(){
        et_name.addTextChangedListener(enableButtonNext);
        et_surname.addTextChangedListener(enableButtonNext);
        et_second_surname.addTextChangedListener(enableButtonNext);
        et_address.addTextChangedListener(enableButtonNext);
        //et_birthday.addTextChangedListener(enableButtonSave);
        et_postalcode.addTextChangedListener(enableButtonNext);
        et_city.addTextChangedListener(enableButtonNext);
        et_phone.addTextChangedListener(enableButtonNext);
    }

    public TextWatcher enableButtonNext = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

         // Guardamos el contenido del fragment
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            str_name = et_name.getText().toString().trim();
            str_surname = et_surname.getText().toString().trim();
            str_second_surname = et_second_surname.getText().toString().trim();
            str_address = et_address.getText().toString().trim();
            str_postalcode = et_postalcode.getText().toString().trim();
            str_city = et_city.getText().toString().trim();
            str_phone = et_phone.getText().toString().trim();

            findEmpty();
            btn_next.setEnabled(enableButton());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

     //Comprueba si los Strings están vacíos
    public void findEmpty(){
        if(str_name.equals("") || str_surname.equals("")||str_second_surname.equals("")||str_address.equals("")||
                str_postalcode.equals("")||str_city.equals("")||str_phone.equals("")){
            setFalse();
        }else {
            setTrue();
        }
    }
    //Comprueba los booleanos de los campos
    public boolean enableButton(){
        if(bool_birthday == false || bool_phone == false || bool_city == false || bool_postalcode == false || bool_address == false || bool_second_surname == false
                || bool_surname == false  || bool_name == false){
            btn_next.setBackgroundTintList(getResources().getColorStateList(R.color.disable_button));
            return false;
        }else{
            btn_next.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimaryDark));
            return true;
        }
    }

    //Parsea al calendario de Date a String
    public  String format(GregorianCalendar calendar){
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }


    //Comprobamos si UserData esta implementando la interfaz
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
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
        public void comunicationWithButtonClickNext(View view);
    }
}
