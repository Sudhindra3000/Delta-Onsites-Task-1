package com.sudhindra.delta_onsites_task_1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;

import com.sudhindra.delta_onsites_task_1.R;
import com.sudhindra.delta_onsites_task_1.databinding.ActivityMainBinding;
import com.sudhindra.delta_onsites_task_1.viewmodels.SharedViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private SharedViewModel sharedViewModel;

    private boolean eraserOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.getEraserOn().observe(this, on -> eraserOn = on);
    }

    public void resetDrawings(View view) {
        sharedViewModel.setAllPairs(new ArrayList<>());
        if (eraserOn)
            handleEraser(binding.eraserBt);
    }

    public void handleEraser(View view) {
        if (eraserOn) {
            binding.eraserBt.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        } else {
            binding.eraserBt.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        }
        sharedViewModel.setEraserOn(!eraserOn);
    }
}