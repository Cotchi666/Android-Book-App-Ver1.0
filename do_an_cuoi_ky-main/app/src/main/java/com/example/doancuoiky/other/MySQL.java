package com.example.doancuoiky.other;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doancuoiky.model.Account;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQL {
    public static final String EMAIL = "email";
    public static final String FULLNAME = "fullName";
    public static final String ADDRESS = "address";
    public static final String PHONENUMBER = "phoneNumber";


    public static void getData(String url, Context context, List<Account> accounts,String email,String fullName,String address,String phoneNumber) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        accounts.add(new Account(jsonObject.getString("email"),
                                jsonObject.getString("fullName"),
                                jsonObject.getString("address"),
                                jsonObject.getString("phoneNumber")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, null);
        requestQueue.add(jsonArrayRequest);
    }


    public static final void create_Account(Context context, String url, String email, String pass, String phoneNumber, String fullName, String address) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    ConnectWithFirebase.getInstance(context).createEmailwithPassword(email
                            , pass);
                } else {
                    Check.creatToat("Tài khoản đã tồn tại", context);
                }

            }
        }, null) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap();
                map.put("email", email);
                map.put("fullName", fullName);
                map.put("address", address);
                map.put("phoneNumber", phoneNumber);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
