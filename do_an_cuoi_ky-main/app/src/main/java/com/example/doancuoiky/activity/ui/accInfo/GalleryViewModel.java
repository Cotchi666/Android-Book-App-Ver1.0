package com.example.doancuoiky.activity.ui.accInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doancuoiky.activity.MainActivity;
import com.example.doancuoiky.model.Account;
import com.example.doancuoiky.other.MySQL;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText_email, mText_fullName, mText_address, mText_phoneNumber;
    Account a;

    public GalleryViewModel() {
        a = MainActivity.account;
        khoiTao();
        setData();
    }

    private void setData() {
        if(a !=null) {
            mText_email.setValue(a.getEmail());
            mText_address.setValue(a.getDiachi());
            mText_fullName.setValue(a.getHoten());
            mText_phoneNumber.setValue(a.getSdt());
        }
    }

    private void khoiTao() {
        mText_email = new MutableLiveData<>();
        mText_address = new MutableLiveData<>();
        mText_phoneNumber = new MutableLiveData<>();
        mText_fullName = new MutableLiveData<>();
    }

    public LiveData<String> getText_Email() {
        return mText_email;
    }

    public LiveData<String> getText_FullName() {
        return mText_fullName;
    }

    public LiveData<String> getText_Address() {
        return mText_address;
    }

    public LiveData<String> getText_phoneNumber() {
        return mText_phoneNumber;
    }


}