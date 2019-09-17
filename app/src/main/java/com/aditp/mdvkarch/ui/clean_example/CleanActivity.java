package com.aditp.mdvkarch.ui.clean_example;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.adit.mdvklibrary.MDVKColor;
import com.aditp.mdvkarch.R;
import com.aditp.mdvkarch.core.BaseActivity;
import com.aditp.mdvkarch.data.model.user_repos.ResponseRepos;
import com.aditp.mdvkarch.databinding.ActivityCleanBinding;
import com.aditp.mdvkarch.helper.GlideHelper;
import com.aditp.mdvkarch.helper.utils.SpacesItemDecoration;

import java.util.ArrayList;

import lombok.Cleanup;
import lombok.Synchronized;

import static com.adit.mdvklibrary.MDVKHelper.WINDOW_HELPER.changeMenuIconColor;

/**
 * ------------------------------
 *
 * @author : <Aditya Pratama>
 * @since : Mei 2019
 * ------------------------------
 */
public class CleanActivity extends BaseActivity<ActivityCleanBinding, CleanViewModel> {
    private CleanAdapter adapter;

    @Override
    public int LAYOUT() {
        return R.layout.activity_clean;
    }

    @Override
    public CleanViewModel viewModel() {
        return ViewModelProviders.of(this).get(CleanViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(viewModel().getCurrentVersion(), R.drawable.ic_menu);


        // M V VM
        binding.setModel(null);
        binding.setView(this);
        binding.setViewModel(viewModel());

        // setup RecyclerView
        binding.rvList.setHasFixedSize(true);
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvList.addItemDecoration(new SpacesItemDecoration(1));

        binding.searchView.setHint("Ex : MVVM");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.rvList.setAdapter(null); // fix memory leak
    }

    @Override
    public void onActionComponent() {
        binding.swipeRefreshLayout.setRefreshing(true);
        binding.swipeRefreshLayout.post(this::updateUI);
        binding.swipeRefreshLayout.setOnRefreshListener(this::updateUI);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (binding.searchView.onBackPressed()) return;
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        changeMenuIconColor(menu, MDVKColor.WHITE);
        binding.searchView.setMenuItem(item);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            Toast.makeText(this, "Menu Clicked", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }


    // ------------------------------------------------------------------------
    // PRIVATE METHOD
    // ------------------------------------------------------------------------
    @Synchronized
    private void updateUI() {

        // Get User Data
        viewModel().getUserGithub(this).observe(this, responseProfileUser -> {
            binding.setModel(responseProfileUser);
            GlideHelper.loadOriginal(this, responseProfileUser.getAvatarUrl(), binding.ivPhotoUser);
        });

        // Get User Repos
        viewModel().getUserRepos(this).observe(this, responseRepos -> {
            if (responseRepos != null) {
                adapter = new CleanAdapter(this, (ArrayList<ResponseRepos>) responseRepos, binding.searchView);
                binding.rvList.setAdapter(adapter);
            }

            binding.swipeRefreshLayout.setRefreshing(false);
            binding.rvList.hideShimmerAdapter();
        });

    }

}
