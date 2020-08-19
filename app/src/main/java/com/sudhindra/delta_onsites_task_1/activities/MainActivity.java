package com.sudhindra.delta_onsites_task_1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.sudhindra.delta_onsites_task_1.R;
import com.sudhindra.delta_onsites_task_1.databinding.ActivityMainBinding;
import com.sudhindra.delta_onsites_task_1.fragments.Fragment1;
import com.sudhindra.delta_onsites_task_1.fragments.Fragment2;
import com.sudhindra.delta_onsites_task_1.viewmodels.SharedViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SharedViewModel.SharedListener, AdapterView.OnItemSelectedListener {

    private ActivityMainBinding binding;

    private SharedViewModel sharedViewModel;

    private Fragment1 fragment1;
    private Fragment2 fragment2;

    private boolean eraserOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        fragment2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.setSharedListener(this);

        binding.spinner.setOnItemSelectedListener(this);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sharedViewModel.setColorIndex(i);
        if (eraserOn)
            handleEraser(binding.eraserBt);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPairsChanged(ArrayList<Pair<Path, Paint>> pairs) {
        fragment1.updateDrawing(pairs);
        fragment2.updateDrawing(pairs);
    }

    @Override
    public void onEraserChanged(boolean on) {
        eraserOn = on;
        fragment1.updateEraser(on);
        fragment2.updateEraser(on);
    }

    @Override
    public void onColorChanged(int index) {
        fragment1.updateColor(index);
        fragment2.updateColor(index);
    }
}