package com.example.doancuoiky.activity.ui.cart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.BillActivity;
import com.example.doancuoiky.adapter.BookCartAdapter;
import com.example.doancuoiky.databinding.CartActivityBinding;
import com.example.doancuoiky.model.Book;
import com.example.doancuoiky.other.Check;
import com.example.doancuoiky.provider.MyProvider;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {

    private CartActivityBinding binding;
    public static List<Book> books;
    private static TextView textView_tongTien;
    private static BookCartAdapter bookCartAdapte;
    private ListView listView;
    private Button button_tt, button_back;
    private TextView textView;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        books = new ArrayList<>();
        addBooks(getActivity());
    }

    private static void addBooks(Context context) {
        books.clear();
        Cursor c = context.getContentResolver().query(Uri.parse(MyProvider.URL), null, null, null, null);
        while (c != null && c.moveToNext()) {
            books.add(new Book(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(3), Double.parseDouble(c.getString(2)), Integer.parseInt(c.getString(4))));
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = CartActivityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.listViewBookCart;
        textView = binding.textViewNull;
        textView_tongTien = binding.textViewTongTienCart;
        button_back = binding.buttonBackMain;
        button_tt = binding.buttonThanhToaan;
        bookCartAdapte = new BookCartAdapter(books, getActivity(), R.layout.row_book_cart);
        listView.setAdapter(bookCartAdapte);
        if (books.size() != 0) {
            textView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
        tongTien();
        button_back.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.nav_home);
        });
        button_tt.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), BillActivity.class));
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static void tongTien() {
        double tongTien = 0;
        for (int i = 0; i < books.size(); i++) {
            tongTien += books.get(i).getDonGia() * books.get(i).getSoLuong();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textView_tongTien.setText(decimalFormat.format(tongTien)+" Đ");
    }

    public static void deleteItem(int i, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo!!!");
        builder.setMessage("Bạn chắc chắn muốn xóa?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.getContentResolver().delete(Uri.parse(MyProvider.URL), "maSach = " + books.get(i).getMaSach(), null);
                addBooks(context);
                bookCartAdapte.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }



}