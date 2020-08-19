package com.sudhindra.delta_onsites_task_1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

import com.sudhindra.delta_onsites_task_1.databinding.ActivityMainBinding;
import com.sudhindra.delta_onsites_task_1.viewmodels.SharedViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
    }

    public void resetDrawings(View view) {
        sharedViewModel.setLivePath(new Path());
    }
}