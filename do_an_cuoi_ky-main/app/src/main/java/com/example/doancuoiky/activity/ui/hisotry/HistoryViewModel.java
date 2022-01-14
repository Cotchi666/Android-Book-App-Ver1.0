package com.example.doancuoiky.activity.ui.hisotry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doancuoiky.activity.LoginActivity;
import com.example.doancuoiky.activity.MainActivity;
import com.example.doancuoiky.model.Book;

import java.util.List;

public class HistoryViewModel extends ViewModel {

    private MutableLiveData<List<Book>> mBooks;

    public HistoryViewModel() {
        mBooks = new MutableLiveData<>();
        mBooks.setValue(MainActivity.books);
    }


    public LiveData<List<Book>> getList() {
        return mBooks;
    }


}