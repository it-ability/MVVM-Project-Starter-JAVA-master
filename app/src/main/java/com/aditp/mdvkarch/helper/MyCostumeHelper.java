package com.aditp.mdvkarch.helper;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * <Custom Helper> | Write On Here ,
 * When You Can't Found What You Need On {@link com.adit.mdvklibrary.MDVKHelper}
 */
public class MyCostumeHelper {


    // ------------------------------------------------------------------------
    // formatCurrency
    // ------------------------------------------------------------------------
    public static String formatCurrency(double val) {
        try {
            Locale locale = new Locale("id", "ID");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

            return currencyFormatter.format(val);

        } catch (Exception e) {

        }
        return String.valueOf(0);
    }


    // ------------------------------------------------------------------------
    // showDatePicker
    // ------------------------------------------------------------------------
    public static void showDatePicker(Context context, FancyButton fancyButton) {
        String formatDate = "yyyy-MM-dd";
        DatePickerDialog datePickerDialog;
        SimpleDateFormat dateFormatter = new SimpleDateFormat(formatDate, Locale.US);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            fancyButton.setText(dateFormatter.format(newDate.getTime()));
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    // ------------------------------------------------------------------------
    // convertDateFromServer
    // ------------------------------------------------------------------------
    public static String convertDateFromServer(String utcTime) {
        try {
            // template server : yyyy MM dd
            String tahun = utcTime.substring(0, 4);
            String bulan = utcTime.substring(4, 6);
            String hari = utcTime.substring(6, 8);
            return tahun + "-" + bulan + "-" + hari;
        } catch (Exception ignored) {
        }
        return "";

    }

    // ------------------------------------------------------------------------
    // HIDE KEYBOARD
    // ------------------------------------------------------------------------
    public static void forceCloseKeyboard(AppCompatActivity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    // ------------------------------------------------------------------------
    // formatDate
    // ------------------------------------------------------------------------
    public static String formatDate(Date date, String pattern, TimeZone timeZone) {
        DateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        format.setTimeZone(timeZone);
        return format.format(date);
    }
}
