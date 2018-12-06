package com.utad.david.task_3_fragments_lists.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{

    MutableLiveData selectedDate = new MutableLiveData<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
     //Pasamos el valor (Date) al MutableLiveData
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        GregorianCalendar date = new GregorianCalendar(year,month,dayOfMonth);
        selectedDate.setValue(date);
    }
}
