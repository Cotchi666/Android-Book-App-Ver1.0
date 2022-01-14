package com.example.doancuoiky.activity.ui.accInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doancuoiky.activity.ChangePasswordActivity;
import com.example.doancuoiky.activity.MainActivity;
import com.example.doancuoiky.databinding.FragmentAccInfoBinding;
import com.example.doancuoiky.other.Check;

import java.util.HashMap;
import java.util.Map;

public class GalleryFragment extends Fragment {
    private Button button_chane_password, button_logout, button_update;
    private EditText editText_email, editText_fullName, editText_phoneNumber, editText_address;
    private GalleryViewModel galleryViewModel;
    private FragmentAccInfoBinding binding;
    View root = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentAccInfoBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        anhxa();
        button_chane_password.setOnClickListener(v -> mstartActivity(ChangePasswordActivity.class));
        button_logout.setOnClickListener(v -> getActivity().finish());
        button_update.setOnClickListener(v -> update("http://cruss.atwebpages.com/updateAccount.php"));
        getData();
        return root;
    }

    private void getData() {
        galleryViewModel.getText_Email().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                editText_email.setText(s);
            }
        });
        galleryViewModel.getText_FullName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                editText_fullName.setText(s);
            }
        });
        galleryViewModel.getText_Address().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                editText_address.setText(s);
            }
        });
        galleryViewModel.getText_phoneNumber().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                editText_phoneNumber.setText(s);
            }
        });
    }

    private void mstartActivity(Class a) {
        startActivity(new Intent(getActivity(), a));
    }

    private void anhxa() {
        editText_address = binding.editAddressInfo;
        editText_fullName = binding.editFullNameInfo;
        editText_phoneNumber = binding.editPhoneNumberInfo;
        editText_email = binding.editEmailInfo;
        button_chane_password = binding.buttonChangePass;
        button_logout = binding.buttonLogOut;
        button_update = binding.buttonUpdate;
    }

    private void update(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Check.creatToat("Cập nhật thông tin thành công", getActivity());
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }

            }
        }, null) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap();
                map.put("email", editText_email.getText().toString().trim());
                map.put("fullName", editText_fullName.getText().toString().trim());
                map.put("address", editText_address.getText().toString().trim());
                map.put("phoneNumber", editText_phoneNumber.getText().toString().trim());
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}