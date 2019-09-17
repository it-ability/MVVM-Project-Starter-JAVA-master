package com.aditp.mdvkarch.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aditp.mdvkarch.data.domain.Endpoint;
import com.aditp.mdvkarch.data.domain.ErrorInterceptor;
import com.aditp.mdvkarch.data.model.user_profile.ResponseProfileUser;
import com.aditp.mdvkarch.data.model.user_repos.ResponseRepos;
import com.google.gson.Gson;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;

import static com.aditp.mdvkarch.data.domain.Endpoint.BASE_URL;
import static com.aditp.mdvkarch.data.domain.Endpoint.CONNECTION_RETRY_TIMES;

/**
 * @author <aditya pratama>
 * @implNote : Kalo dalam 1 repo kebanyakan method di buat partial aja, ini cuma contoh ~
 * @see Endpoint to edit baseUrl
 */
public class RepositoryGithub {
    private static RepositoryGithub instance;
    private Gson gson = new Gson();

    /**
     * @return instance
     */
    // Singleton
    public synchronized static RepositoryGithub getInstance() {
        if (instance == null) instance = new RepositoryGithub();
        return instance;
    }


    /**
     * @implSpec <NETWORK LAYER>
     * @implNote example methodName :refreshUserProfile
     * @implNote clue -> refresh
     * @implNote refresh means mengambil ulang dari network layer .
     */
    // ------------------------------------------------------------------------
    // GET_USER_PROFILE
    // ------------------------------------------------------------------------
    public LiveData<ResponseProfileUser> refreshUserProfile(Context context, String username) {
        final MutableLiveData<ResponseProfileUser> data = new MutableLiveData<>();
        Rx2AndroidNetworking.get(BASE_URL.concat("users/{username}"))
                .addPathParameter("username", username)
                .build()
                .getObjectObservable(ResponseProfileUser.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(CONNECTION_RETRY_TIMES)
                .subscribe(new ResourceObserver<ResponseProfileUser>() {
                    @Override
                    public void onNext(ResponseProfileUser responseProfileUser) {
                        data.setValue(responseProfileUser);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorInterceptor.getInstance().errHandling(context, e);
                        data.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return data;
    }


    // ------------------------------------------------------------------------
    // GET_USER_REPOS
    // ------------------------------------------------------------------------
    public LiveData<List<ResponseRepos>> refreshUserRepos(Context context, String username) {
        final MutableLiveData<List<ResponseRepos>> data = new MutableLiveData<>();
        Rx2AndroidNetworking.get(BASE_URL.concat("users/{username}/repos"))
                .addPathParameter("username", username)
                .build()
                .getObjectListObservable(ResponseRepos.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(CONNECTION_RETRY_TIMES)
                .subscribe(new ResourceObserver<List<ResponseRepos>>() {
                    @Override
                    public void onNext(List<ResponseRepos> responseRepos) {
                        data.setValue(responseRepos);

                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorInterceptor.getInstance().errHandling(context, e);
                        data.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return data;
    }

}
