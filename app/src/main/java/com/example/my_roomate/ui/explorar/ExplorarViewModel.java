package com.example.my_roomate.ui.explorar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExplorarViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ExplorarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Explorar");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
