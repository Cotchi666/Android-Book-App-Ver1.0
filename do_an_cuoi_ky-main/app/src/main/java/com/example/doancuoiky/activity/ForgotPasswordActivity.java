package com.example.doancuoiky.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.doancuoiky.R;
import com.example.doancuoiky.other.ConnectWithFirebase;

public class ForgotPasswordActivity extends Activity {
    EditText editText_email;
    Button button_back, button_send;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        anhxa();
        button_send.setOnClickListener(v -> ConnectWithFirebase.getInstance(this).sendResetPassword(editText_email.getText().toString().trim()));
        button_back.setOnClickListener(v -> finish());
    }

    private void anhxa() {
        editText_email = findViewById(R.id.edit_email_forgot);
        button_back = findViewById(R.id.button_back);
        button_send = findViewById(R.id.button_send);
    }
}
