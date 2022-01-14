package com.example.doancuoiky.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.ui.cart.CartFragment;
import com.example.doancuoiky.model.Book;
import com.example.doancuoiky.provider.MyProvider;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class BookCartAdapter extends BaseAdapter {
    private List<Book> books;
    private Activity context;
    private int layout;
    private double tongTien;
    private DecimalFormat decimalFormat;

    public BookCartAdapter(List<Book> books, Activity context, int layout) {
        this.books = books;
        this.context = context;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView imageView_ha, imageView_deltete;
        Button button_tru, button_cong;
        TextView textView_sl, textView_ten, textView_gia;
    }

    @Override
    public View getView(int i, View v, ViewGroup parent) {
        ViewHolder viewHolder;
        if (v == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(layout, null);
            viewHolder.button_cong = v.findViewById(R.id.button_cong_cart);
            viewHolder.button_tru = v.findViewById(R.id.button_tru_cart);
            viewHolder.textView_sl = v.findViewById(R.id.textView_sl_cart);
            viewHolder.textView_ten = v.findViewById(R.id.textView_tenBook_cart);
            viewHolder.imageView_ha = v.findViewById(R.id.imageView_haBook_cart);
            viewHolder.textView_gia = v.findViewById(R.id.textView_donGia_book_cart);
            viewHolder.imageView_deltete = v.findViewById(R.id.imageView_delete_cartt);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        Book book = books.get(i);
        viewHolder.textView_ten.setText(book.getTenSach().toUpperCase());
        decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.textView_gia.setText("Giá : " + decimalFormat.format(book.getDonGia() * book.getSoLuong()) + " Đ");
        Picasso.get().load(book.getHinhAnhSach()).into(viewHolder.imageView_ha);
        viewHolder.textView_sl.setText(book.getSoLuong() + "");
        viewHolder.button_tru.setOnClickListener(v1 -> {
            tru(viewHolder, i, book);

        });
        viewHolder.imageView_deltete.setOnClickListener(v1 -> {
            CartFragment.deleteItem(i,context);
        });
        viewHolder.button_cong.setOnClickListener(v1 -> {
            cong(viewHolder, i, book);
        });
        return v;
    }

    private void cong(ViewHolder viewHolder, int i, Book book) {
        int soluong = Integer.parseInt(viewHolder.textView_sl.getText().toString()) + 1;
        viewHolder.textView_sl.setText(soluong + "");
        tongTien(viewHolder, book, decimalFormat, i, soluong);
    }

    private void tongTien(ViewHolder viewHolder, Book b, DecimalFormat decimalFormat, int i, int sl) {
        tongTien = (Integer.parseInt(viewHolder.textView_sl.getText().toString()) * b.getDonGia());
        viewHolder.textView_gia.setText("Giá : " + decimalFormat.format(tongTien) + " Đ");
        CartFragment.books.get(i).setSoLuong(sl);
        CartFragment.tongTien();
        ContentValues contentValues = new ContentValues();
        contentValues.put("soLuong", sl);
        context.getContentResolver().update(Uri.parse(MyProvider.URL), contentValues, "maSach=" + books.get(i).getMaSach(), null);
        if (sl <= 1) {
            viewHolder.button_tru.setEnabled(false);
        } else {
            viewHolder.button_tru.setEnabled(true);
        }
        if (sl >= 10) {
            viewHolder.button_cong.setEnabled(false);
        } else {
            viewHolder.button_cong.setEnabled(true);
        }
    }

    private void tru(ViewHolder viewHolder, int i, Book book) {
        int soluong = Integer.parseInt(viewHolder.textView_sl.getText().toString()) - 1;
        viewHolder.textView_sl.setText(soluong + "");
        tongTien(viewHolder, book, decimalFormat, i, soluong);
    }


}
