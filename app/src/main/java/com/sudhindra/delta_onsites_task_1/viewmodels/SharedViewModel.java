package com.sudhindra.delta_onsites_task_1.viewmodels;

import android.graphics.Paint;
import android.graphics.Path;

import androidx.core.util.Pair;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sudhindra.delta_onsites_task_1.customviews.DrawingPad;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Pair<Path, Paint>>> allPairs = new MutableLiveData<>();
    private MutableLiveData<Boolean> eraserOn = new MutableLiveData<>();
    private MutableLiveData<Integer> colorIndex = new MutableLiveData<>();

    public DrawingPad.DrawingPadListener listener = newList -> allPairs.setValue(newList);

    public MutableLiveData<ArrayList<Pair<Path, Paint>>> getAllPairs() {
        return allPairs;
    }

    public void setAllPairs(ArrayList<Pair<Path, Paint>> allPairs) {
        this.allPairs.setValue(allPairs);
    }

    public MutableLiveData<Boolean> getEraserOn() {
        return eraserOn;
    }

    public void setEraserOn(Boolean eraserOn) {
        this.eraserOn.setValue(eraserOn);
    }

    public MutableLiveData<Integer> getColorIndex() {
        return colorIndex;
    }

    public void setColorIndex(Integer colorIndex) {
        this.colorIndex.setValue(colorIndex);
    }
}
