package com.example.doancuoiky.activity.ui.home;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doancuoiky.activity.LoginActivity;
import com.example.doancuoiky.activity.MainActivity;
import com.example.doancuoiky.model.Book;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Book>> mBooks;

    public HomeViewModel() {
        mBooks = new MutableLiveData<>();
        mBooks.setValue(LoginActivity.books);
    }


    public LiveData<List<Book>> getList() {
        return mBooks;
    }


}