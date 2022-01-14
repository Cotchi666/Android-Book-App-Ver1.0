package com.example.doancuoiky.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doancuoiky.R;
import com.example.doancuoiky.model.Book;

import java.text.DecimalFormat;
import java.util.List;

public class LichSuAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private List<Book> bookList;

    public LichSuAdapter(int layout, Context context, List<Book> bookList) {
        this.layout = layout;
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
private class ViewHolder{
        ImageView imageView;
        TextView textView_tenSach,textView_sl,textView_donGgia;
}
    @Override
    public View getView(int id, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout, null);
            viewHolder.imageView = view.findViewById(R.id.imageView_tt_ls);
            viewHolder.textView_donGgia = view.findViewById(R.id.textView2_donGia_ls);
            viewHolder.textView_tenSach = view.findViewById(R.id.textView_tenBook_ls);
            viewHolder.textView_sl = view.findViewById(R.id.textView_sl_ls);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Book book = bookList.get(id);
        viewHolder.textView_tenSach.setText(book.getTenSach().toUpperCase());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.textView_donGgia.setText("Giá : "+decimalFormat.format(book.getDonGia())+" Đ");
        viewHolder.textView_sl.setText(book.getSoLuong()+"");
        if(book.getTingTrang() == 0){
            viewHolder.imageView.setImageResource(R.drawable.error);
        }else {
            viewHolder.imageView.setImageResource(R.drawable.apply);
        }

        return view;
    }
}
