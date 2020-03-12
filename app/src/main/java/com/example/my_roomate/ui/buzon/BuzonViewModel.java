package com.example.my_roomate.ui.buzon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuzonViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public BuzonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Buzon");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
