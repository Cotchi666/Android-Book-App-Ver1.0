package com.example.doancuoiky.activity.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.DetailBookActivity;
import com.example.doancuoiky.activity.MainActivity;
import com.example.doancuoiky.adapter.BookAdapter;
import com.example.doancuoiky.databinding.FragmentHomeBinding;
import com.example.doancuoiky.model.Book;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ListView listView;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    public static BookAdapter bookAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listView = binding.listViewBook;
        homeViewModel.getList().observe(getViewLifecycleOwner(), new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                bookAdapter = new BookAdapter(R.layout.row_book, books, getActivity());
                listView.setAdapter(bookAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startDetailActivity(books.get(position));
                    }
                });
            }
        });

        return root;
    }

    private void startDetailActivity(Book book) {
        Intent intent = new Intent(getActivity(), DetailBookActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", book);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}