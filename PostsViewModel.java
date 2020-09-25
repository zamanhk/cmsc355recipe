package com.example.sharables.ui.posts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PostsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is posts fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
