package com.example.doancuoiky.activity.ui.hisotry;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.LoginActivity;
import com.example.doancuoiky.activity.ui.home.HomeViewModel;
import com.example.doancuoiky.adapter.LichSuAdapter;
import com.example.doancuoiky.databinding.LichSuDonHangBinding;
import com.example.doancuoiky.model.Book;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
private HistoryViewModel historyViewModel;
    private ListView listView;
    private LichSuDonHangBinding binding;
    public LichSuAdapter lichSuAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel =
                new ViewModelProvider(this).get(HistoryViewModel.class);
        binding = LichSuDonHangBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.listViewLs;
        historyViewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                System.out.println(books.size()+"XXX");
                lichSuAdapter = new LichSuAdapter(R.layout.chi_tiet_don_hang, getActivity(), books);
                listView.setAdapter(lichSuAdapter);
            }
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}