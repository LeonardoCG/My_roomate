package com.example.my_roomate.ui.guardados;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GuardadosViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public GuardadosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Guardados");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
