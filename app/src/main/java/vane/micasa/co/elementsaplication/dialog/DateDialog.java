package vane.micasa.co.elementsaplication.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import vane.micasa.co.elementsaplication.R;

/**
 * Created by Michael Garcia on 31/05/2017.
 */

public class DateDialog extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    public DateDialog() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
//        String date = ""  + year + "-" + month + "-" + day + "";
//        editor = sharedPref.edit();
//        editor.putString(getString(R.string.date),date);
//        editor.commit();

        Toast.makeText(
                this.getActivity(),
                "Fecha: " + year + "-" + month + "-" + day,
                Toast.LENGTH_LONG)
                .show();
    }
}