package com.sudhindra.delta_onsites_task_1.fragments;

import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sudhindra.delta_onsites_task_1.databinding.Fragment1Binding;
import com.sudhindra.delta_onsites_task_1.viewmodels.SharedViewModel;

import java.util.ArrayList;

public class Fragment1 extends Fragment{

    private Fragment1Binding binding;

    private SharedViewModel viewModel;

    public void updateDrawing(ArrayList<Pair<Path, Paint>> newPairs) {
        binding.drawingPad.setAllPairs(newPairs);
    }

    public void updateEraser(boolean on) {
        binding.drawingPad.setEraserOn(on);
    }

    public void updateColor(int index) {
        binding.drawingPad.setColorIndex(index);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        binding.drawingPad.setAllPairs(viewModel.getAllPairs());
        binding.drawingPad.setListener(viewModel.drawingPadListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}