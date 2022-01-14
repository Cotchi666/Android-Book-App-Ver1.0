package com.example.doancuoiky.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doancuoiky.R;
import com.example.doancuoiky.model.Book;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends BaseAdapter implements Filterable {
    private int layout;
    private List<Book> itemsModelsl;
    private Context context;
    private List<Book> itemsModelListFiltered;


    public BookAdapter(int layout, List<Book> books, Context context) {
        this.layout = layout;
        this.itemsModelsl = books;
        this.itemsModelListFiltered = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemsModelListFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = itemsModelsl.size();
                    filterResults.values = itemsModelsl;

                } else {
                    List<Book> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (Book itemsModel : itemsModelsl) {
                        if (itemsModel.getTenSach().toLowerCase().contains(searchStr.toLowerCase())) {
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                itemsModelListFiltered = (List<Book>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    private class ViewHolder {
        ImageView imageView_ha;
        TextView textView_ten, textView_Gia, textView_moTa;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout, null);
            viewHolder.textView_ten = view.findViewById(R.id.textView_tenBook);
            viewHolder.textView_Gia = view.findViewById(R.id.textView_donGia);
            viewHolder.textView_moTa = view.findViewById(R.id.textView_mota);
            viewHolder.imageView_ha = view.findViewById(R.id.imageView_haBook);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Book book = itemsModelListFiltered.get(position);
        viewHolder.textView_ten.setText(book.getTenSach().toUpperCase());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.textView_Gia.setText("Giá : "+decimalFormat.format(book.getDonGia())+" Đ");
        viewHolder.textView_moTa.setMaxLines(2);
        viewHolder.textView_moTa.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.textView_moTa.setText(book.getMoTa());
        Picasso.get().load(book.getHinhAnhSach()).into(viewHolder.imageView_ha);
        return view;
    }
}
