package com.aditp.mdvkarch.ui.clean_example;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.adit.mdvklibrary.MDVKAdapter;
import com.aditp.mdvkarch.R;
import com.aditp.mdvkarch.data.model.user_repos.ResponseRepos;
import com.aditp.mdvkarch.databinding.ItemCleanSampleBinding;

import java.util.ArrayList;

public class CleanAdapter extends MDVKAdapter<ResponseRepos, ItemCleanSampleBinding> {

    @Override
    public int getLayoutResId() {
        return R.layout.item_clean_sample;
    }

    @Override
    public void onBindData(ResponseRepos model, int position, ItemCleanSampleBinding dataBinding) {
        dataBinding.setModel(model);
    }

    @Override
    public void onItemClick(ResponseRepos model, int position) {
        Toast.makeText(context, String.format(
                "Item %s Clicked", model.getFullName()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View getSearchField() {
        return view;
    }

    @Override
    public ArrayList<ResponseRepos> performFilter(String searchText, ArrayList<ResponseRepos> originalList) {
        ArrayList<ResponseRepos> filteredList = new ArrayList<>();
        for (ResponseRepos row : originalList)
            if (row.getName().toLowerCase().contains(searchText.toLowerCase()))
                filteredList.add(row);

        return filteredList;
    }


    private Context context;
    private View view;

    public CleanAdapter(Context context, ArrayList<ResponseRepos> arrayList) {
        super(context, arrayList);
        this.context = context;
    }

    public CleanAdapter(Context context, ArrayList<ResponseRepos> arrayList, View view) {
        super(context, arrayList);
        this.context = context;
        this.view = view;
    }

}
