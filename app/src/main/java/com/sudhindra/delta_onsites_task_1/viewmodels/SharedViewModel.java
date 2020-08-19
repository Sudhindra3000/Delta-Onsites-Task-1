package com.sudhindra.delta_onsites_task_1.viewmodels;

import android.graphics.Path;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sudhindra.delta_onsites_task_1.customviews.DrawingPad;


public class SharedViewModel extends ViewModel {

    private MutableLiveData<Path> livePath = new MutableLiveData<>();
    public DrawingPad.DrawingPadListener listener = path -> livePath.setValue(path);

    public MutableLiveData<Path> getLivePath() {
        return livePath;
    }

    public void setLivePath(Path path) {
        livePath.setValue(path);
    }
}
