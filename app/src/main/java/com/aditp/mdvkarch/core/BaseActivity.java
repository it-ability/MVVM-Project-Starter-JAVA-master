package com.aditp.mdvkarch.core;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.adit.mdvklibrary.MDVKHelper;
import com.aditp.mdvkarch.R;
import com.aditp.mdvkarch.helper.utils.IMMLeaks;
import com.androidnetworking.AndroidNetworking;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.Objects;

import okhttp3.OkHttpClient;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.adit.mdvklibrary.MDVKHelper.DIALOG_HELPER.showProgressDialog;
import static com.aditp.mdvkarch.MY_AWESOME_APP.IS_DEV_MODE;


/**
 * ------------------------------------------------------------------------------------
 *
 * @param <VDB> ViewDataBinding
 * @param <VM>  ViewModel
 *              _______________
 * @author : <Aditya Pratama>
 * @since : Mei 2019
 * ------------------------------------------------------------------------------------
 */
public abstract class BaseActivity<VDB extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity implements BaseImpl {
    protected VDB binding;

    public abstract @LayoutRes
    int LAYOUT();

    public abstract VM viewModel();

    private Dialog dialog;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IMMLeaks.fixFocusedViewLeak(getApplication());


        // Adding a Network Interceptor for Debugging purpose :
        OkHttpClient tester = new OkHttpClient().newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        AndroidNetworking.initialize(getApplicationContext(), tester); //MDVKOkHttpClient.CLIENT());
        if (IS_DEV_MODE) AndroidNetworking.enableLogging();

        isChangeSystemBarColor(true);
        performDataBinding();
        onActionComponent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        hideLoading();
    }

    @Override
    public void isFullScreen(boolean val) {
        if (val) MDVKHelper.WINDOW_HELPER.setActivityToFullScreen(this);
    }

    @Override
    public void isChangeSystemBarColor(boolean val) {
        if (val) {
            MDVKHelper.WINDOW_HELPER.setSystemBarColor(this, R.color.mdvk_main);
            // MDVKHelper.WINDOW_HELPER.setSystemBarLight(this);
        }
    }


    private void performDataBinding() {
        binding = DataBindingUtil.setContentView(this, LAYOUT());
    }


    // ------------------------------------------------------------------------
    // GLOBAL METHOD
    // ------------------------------------------------------------------------
    protected void initToolbar(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        Objects.requireNonNull(toolbar.getNavigationIcon()).setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    protected void initToolbar(String title, int drawable) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(drawable);
        toolbar.setTitle(title);
        Objects.requireNonNull(toolbar.getNavigationIcon()).setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    protected void hideLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
        }
    }


    protected void showLoading() {
        hideLoading();
        dialog = showProgressDialog(this);
        dialog.setCancelable(false);
        dialog.show();

    }

    protected void showLoading(String msg) {
        hideLoading();
        dialog = showProgressDialog(this, msg);
        dialog.setCancelable(false);
        dialog.show();
    }


}

