package com.example.doancuoiky.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.ui.cart.CartFragment;
import com.example.doancuoiky.model.Account;
import com.example.doancuoiky.other.Check;
import com.example.doancuoiky.provider.MyProvider;

import java.util.HashMap;
import java.util.Map;

public class BillActivity extends Activity {
    private EditText editText_hoten, editText_diachi, editText_sdt;
    private Button button_dathang, button_huy;
    private Account a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_activity);
        anhxa();
        setGiaTri();
        button_dathang.setOnClickListener(v -> {
            for (int i = 0; i < CartFragment.books.size(); i++) {
                duaDataLenSever("http://cruss.atwebpages.com/insertBill.php", i);
            }
        });
        button_huy.setOnClickListener(v -> finish());

    }


    private void setGiaTri() {
        if (a != null){
        editText_diachi.setText(a.getDiachi());
        editText_sdt.setText(a.getSdt());
        editText_hoten.setText(a.getHoten());}
    }

    private void anhxa() {
        a = MainActivity.account;
        editText_diachi = findViewById(R.id.edit_diachi_bill);
        editText_hoten = findViewById(R.id.edit_hoten_bill);
        editText_sdt = findViewById(R.id.edit_sdt_bill);
        button_dathang = findViewById(R.id.button_dathang_bill);
        button_huy = findViewById(R.id.button_huy_bil);
    }

    private void duaDataLenSever(String url, int i) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Check.creatToat("Bạn đã đặt hàng thành công!!!", getApplicationContext());
                    Check.creatToat("Xin mời bạn tiếp tục trở lại mua hàng.", getApplicationContext());
                    getContentResolver().delete(Uri.parse(MyProvider.URL), null, null);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    Check.creatToat("Lỗi vui lòng kiểm tra lại!!!", getApplicationContext());
                }
            }
        }, null) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap();
                map.put("email", a.getEmail());
                map.put("hoten", editText_hoten.getText().toString());
                map.put("sdt", editText_sdt.getText().toString());
                map.put("diachi", editText_diachi.getText().toString());
                map.put("masach", String.valueOf(CartFragment.books.get(i).getMaSach()));
                map.put("tensach", CartFragment.books.get(i).getTenSach());
                map.put("dongia", String.valueOf(CartFragment.books.get(i).getDonGia() * CartFragment.books.get(i).getSoLuong()));
                map.put("soluong", String.valueOf(CartFragment.books.get(i).getSoLuong()));
                map.put("tinhTrang", String.valueOf(0));
                return map;
            }
        };
        requestQueue.add(request);
    }

}
