package vane.micasa.co.elementsaplication.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import vane.micasa.co.elementsaplication.R;

/**
 * Created by Michael Garcia on 1/06/2017.
 */

public class DateFechaPrepa extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    EditText text;

    public DateFechaPrepa() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        text = (EditText) getActivity().findViewById(R.id.addFechaPreparacion_catalogo);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        text.setText(day + "/" + month + "/" + year);
    }
}