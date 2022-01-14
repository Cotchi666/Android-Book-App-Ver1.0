package com.example.doancuoiky.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.doancuoiky.R;
import com.example.doancuoiky.model.Book;
import com.example.doancuoiky.other.Check;
import com.example.doancuoiky.provider.MyProvider;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DetailBookActivity extends Activity {
    private TextView textView_tenSach, textView_gia, textView_mota;
    private EditText editText_soLuong;
    private Button button_back, button_addCart;
    ImageView imageView_ha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_book);
        anhxa();
        Bundle bundle = getIntent().getBundleExtra("data");
        Book b = (Book) bundle.getSerializable("book");
        textView_tenSach.setText(b.getTenSach().toUpperCase());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textView_gia.setText("Giá : " + decimalFormat.format(b.getDonGia()) + " Đ");
        textView_mota.setText(b.getMoTa());
        Picasso.get().load(b.getHinhAnhSach()).into(imageView_ha);
        button_addCart.setOnClickListener(
                v -> {
                    if (Check.checkNull(editText_soLuong, "Số lượng") && (Integer.parseInt(editText_soLuong.getText().toString().trim())) > 0) {
                        addCart(b);
                        finish();
                    } else {
                        Check.creatToat("Vui lòng nhập số lượng", this);
                    }
                }
        );
        button_back.setOnClickListener(v -> finish());

    }


    private void addCart(Book b) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSach", b.getMaSach());
        contentValues.put("tenSach", b.getTenSach());
        contentValues.put("donGia", b.getDonGia());
        contentValues.put("hinhAnh", b.getHinhAnhSach());
        contentValues.put("soLuong", editText_soLuong.getText().toString());
        getContentResolver().insert(Uri.parse(MyProvider.URL), contentValues);
    }

    private void anhxa() {
        button_addCart = findViewById(R.id.button_add_cart_detail);
        textView_gia = findViewById(R.id.textView_gia_detail);
        textView_mota = findViewById(R.id.textView_mota_detail);
        textView_tenSach = findViewById(R.id.textView_tenSach_detail);
        imageView_ha = findViewById(R.id.imageView_book_detail);
        button_back = findViewById(R.id.button_back_detail);
        editText_soLuong = findViewById(R.id.editTextNumber_sl_detail);
    }
}
