package com.example.my_roomate.ui.myrooms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyRoomsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MyRoomsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("My Rooms");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
