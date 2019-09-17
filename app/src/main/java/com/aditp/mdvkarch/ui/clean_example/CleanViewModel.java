package com.aditp.mdvkarch.ui.clean_example;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adit.mdvklibrary.MDVKHelper;
import com.aditp.mdvkarch.BuildConfig;
import com.aditp.mdvkarch.data.model.user_profile.ResponseProfileUser;
import com.aditp.mdvkarch.data.model.user_repos.ResponseRepos;
import com.aditp.mdvkarch.data.repository.RepositoryGithub;
import com.aditp.mdvkarch.helper.CONSTANS;
import com.aditp.mdvkarch.helper.MyCostumeHelper;
import com.instacart.library.truetime.TrueTime;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import lombok.val;

/*** just example ~ {@link RepositoryGithub} */

public class CleanViewModel extends ViewModel {
    public String username = "abehbatre";

    LiveData<ResponseProfileUser> getUserGithub(Context context) {
        return RepositoryGithub.getInstance().refreshUserProfile(context, username);
    }


    LiveData<List<ResponseRepos>> getUserRepos(Context context) {
        return RepositoryGithub.getInstance().refreshUserRepos(context, username);
    }
    private String checkWaktu() {
        Date date = TrueTime.now();
        int timeNow = Integer.parseInt(MyCostumeHelper.formatDate(date, "HH", TimeZone.getTimeZone(CONSTANS.TIME_ZONE)));

        if (timeNow >= 0 & timeNow <= 10) return "Pagi, ";
        else if (timeNow >= 11 & timeNow <= 14) return "Siang, ";
        else if (timeNow >= 15 & timeNow <= 18) return "Sore, ";
        else if (timeNow >= 19 & timeNow <= 23) return "Malam, ";
        return "Halo, ";
    }

    public String getCurrentVersion() {
        // get Current Version
        String VER_CODE = String.valueOf(BuildConfig.VERSION_CODE);
        String VER_NAME = BuildConfig.VERSION_NAME;

        return String.format("Version %s   (%s)", VER_NAME, VER_CODE);
    }

    public void showDialog(Context context, String name) {
        MDVKHelper.DIALOG_HELPER.showCustomDialog(context,
                "Tester",
                "Selamat " + checkWaktu() + " " + name,
                new MDVKHelper.ActionDialogListener() {
                    @Override
                    public void executeNo() {
                        // ignore
                    }

                    @Override
                    public void executeYes() {
                        Toast.makeText(context, "Ok Clicked", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public Boolean testLogic2() {
        return checkWaktu().equalsIgnoreCase("Pagi");
    }

}
