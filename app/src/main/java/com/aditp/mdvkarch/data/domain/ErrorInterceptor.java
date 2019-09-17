package com.aditp.mdvkarch.data.domain;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.error.ANError;

import org.json.JSONObject;

import java.util.Locale;

import lombok.SneakyThrows;

import static com.adit.mdvklibrary.MDVKHelper.DIALOG_HELPER.showAlertDialog;
import static com.aditp.mdvkarch.data.domain.Endpoint.BAD_REQUEST;
import static com.aditp.mdvkarch.data.domain.Endpoint.INTERNAL_SERVER_ERROR;
import static com.aditp.mdvkarch.data.domain.Endpoint.NO_CONNECTION;
import static com.aditp.mdvkarch.data.domain.Endpoint.WRONG_METHOD;

/**
 * @author <Aditya Pratama>
 * @version 1.0.0
 * @implSpec Http ErrorInterceptor
 * @since mei 2019
 */
public class ErrorInterceptor {

    private static ErrorInterceptor instance;



    // Singleton
    public synchronized static ErrorInterceptor getInstance() {
        if (instance == null) instance = new ErrorInterceptor();
        return instance;
    }

    @SneakyThrows
    public void errHandling(Context context, Throwable e) {
        ANError err = (ANError) e;

        switch (err.getErrorCode()) {
            case BAD_REQUEST:
                JSONObject jsonObj = new JSONObject(err.getErrorBody());
                String message = jsonObj.getJSONArray("errors").getJSONObject(0).getString("message");
                String documentation_url = jsonObj.getJSONArray("errors").getJSONObject(0).getString("documentation_url");
                showAlertDialog(context, message, documentation_url, "OK");

                break;

            case WRONG_METHOD:
                showAlertDialog(context, "WRONG METHOD", "Method Not Allowed");
                break;

            case INTERNAL_SERVER_ERROR:
                showAlertDialog(context, "Server Offline", "Internal Server Error ");
                break;

            case NO_CONNECTION:
                showAlertDialog(context, "Connection Problem", "Mohon periksa jaringan koneksi Anda.");
                break;

            default:
                Toast.makeText(context, String.format(Locale.getDefault(), "Error : %d", err.getErrorCode()) + " | " + err.getErrorBody(), Toast.LENGTH_SHORT).show();
                showAlertDialog(context, String.format(Locale.getDefault(), "Error : %d", err.getErrorCode()), err.getErrorBody(), "OK");
                break;

        }

    }
}
