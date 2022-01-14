package com.example.doancuoiky.activity.ui.logout;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doancuoiky.databinding.FindFragmentBinding;


public class LogOutFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FindFragmentBinding binding = FindFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getActivity().finish();
        return  root;
    }
}