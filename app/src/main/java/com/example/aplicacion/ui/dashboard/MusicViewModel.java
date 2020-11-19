package com.example.aplicacion.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MusicViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List <MusicElemnt>> musicas;


    public MusicViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<List<MusicElemnt>> getText2() {

        return musicas;
    }
}