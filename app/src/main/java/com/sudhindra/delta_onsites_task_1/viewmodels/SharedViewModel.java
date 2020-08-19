package com.sudhindra.delta_onsites_task_1.viewmodels;

import android.graphics.Paint;
import android.graphics.Path;

import androidx.core.util.Pair;
import androidx.lifecycle.ViewModel;

import com.sudhindra.delta_onsites_task_1.customviews.DrawingPad;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    private ArrayList<Pair<Path, Paint>> allPairs = new ArrayList<>();

    private SharedListener sharedListener;
    public DrawingPad.DrawingPadListener drawingPadListener = this::setAllPairs;

    public interface SharedListener {
        void onPairsChanged(ArrayList<Pair<Path, Paint>> pairs);

        void onEraserChanged(boolean on);

        void onColorChanged(int index);
    }

    public ArrayList<Pair<Path, Paint>> getAllPairs() {
        return allPairs;
    }

    public void setAllPairs(ArrayList<Pair<Path, Paint>> allPairs) {
        this.allPairs = allPairs;
        sharedListener.onPairsChanged(allPairs);
    }

    public void setEraserOn(boolean eraserOn) {
        sharedListener.onEraserChanged(eraserOn);
    }

    public void setColorIndex(int colorIndex) {
        sharedListener.onColorChanged(colorIndex);
    }

    public void setSharedListener(SharedListener sharedListener) {
        this.sharedListener = sharedListener;
    }
}
