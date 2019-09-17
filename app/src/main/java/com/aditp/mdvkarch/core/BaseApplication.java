package com.aditp.mdvkarch.core;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.adit.mdvklibrary.MDVKPref;
import com.aditp.mdvkarch.R;
import com.facebook.stetho.Stetho;
import com.instacart.library.truetime.TrueTimeRx;
import com.squareup.leakcanary.LeakCanary;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.aditp.mdvkarch.MY_AWESOME_APP.IS_DEV_MODE;

/**
 * ------------------------------------------------------------------------------------
 *
 * @author : <Aditya Pratama>
 * @since : Mei 2019
 * ------------------------------------------------------------------------------------
 */
public class BaseApplication extends MultiDexApplication {

    private static BaseApplication mInstance;

    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;


        // tracking memory leak in dev mode ...
        if (IS_DEV_MODE) {
            // singleton
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);
        }


        // change font
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/samsung.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        // Network Web Debugger Stetho : chrome://inspect/#devices
        Stetho.initializeWithDefaults(this);

        // SharedPreference
        MDVKPref.init(getApplicationContext());

        // TrueTime
        initRxTrueTime();

    }

    // trueTime
    private void initRxTrueTime() {
        DisposableSingleObserver<Date> disposable = TrueTimeRx.build()
                .withConnectionTimeout(31_428)
                .withRetryCount(100)
                .withSharedPreferencesCache(this)
                .withLoggingEnabled(true)
                .initializeRx("time.google.com")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Date>() {
                    @Override
                    public void onSuccess(Date date) {
                        Log.d("TrueTime", "Success initialized TrueTime :" + date.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TrueTime", "something went wrong when trying to initializeRx TrueTime", e);
                    }
                });
    }

}
