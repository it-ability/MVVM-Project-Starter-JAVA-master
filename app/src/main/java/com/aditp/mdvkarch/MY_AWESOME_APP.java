package com.aditp.mdvkarch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.adit.mdvklibrary.MDVKHelper;
import com.aditp.mdvkarch.ui.clean_example.CleanActivity;


public class MY_AWESOME_APP extends AppCompatActivity {

    // ------------------------------------------------------------------------
    // set as <b>false</b> if you want to <i>release</i> this apps .
    // ------------------------------------------------------------------------
    public static final boolean IS_DEV_MODE = true;

    // initialization
    static {
        Log.wtf("", "-----------------------------------------------------------");
        // Log.wtf("TIMEZONE", TIME_ZONE);
        // Log.wtf("EXISTING TOKEN", CURRENT_TOKEN);
        Log.wtf(MY_AWESOME_APP.class.getSimpleName(), "initializer: App Started ..");
        Log.wtf("", "-----------------------------------------------------------");
    }


    // ------------------------------------------------------------------------
    // Splash Screens the Right Way :
    // https://www.bignerdranch.com/blog/splash-screens-the-right-way/
    // ------------------------------------------------------------------------
    protected void startApp(Class<?> cls) {
        startActivity(new Intent(MY_AWESOME_APP.this, cls));
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MDVKHelper.WINDOW_HELPER.setActivityToFullScreen(this);

        // check device is online ? -> coz online require
        if (MDVKHelper.NETWORK_HELPER.isOnline(this)) {
            // check user login session
            // String token = MDVKPref.getInstance().getString(KEY_USER_TOKEN, "");
            // if (token.isEmpty()) {
            //     startApp(LoginActivity.class);
            // } else {
            //     startApp(DashboardSalesActivity.class);
            // }
            startApp(CleanActivity.class);
        } else {
            MDVKHelper.DIALOG_HELPER.showCustomDialog(
                    this,
                    getResources().getString(R.string.msg_no_connection_title),
                    getResources().getString(R.string.msg_no_connection),
                    R.drawable.ic_logo,
                    View.GONE,
                    new MDVKHelper.ActionDialogListener() {
                        @Override
                        public void executeNo() {
                            // ignored
                        }

                        @Override
                        public void executeYes() {
                            finish();
                        }
                    });
        }
    }


}
