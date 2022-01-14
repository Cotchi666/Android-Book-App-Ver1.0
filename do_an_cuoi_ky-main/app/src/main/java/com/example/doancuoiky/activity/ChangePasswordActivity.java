package com.example.doancuoiky.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.doancuoiky.R;
import com.example.doancuoiky.other.Check;
import com.example.doancuoiky.other.ConnectWithFirebase;

public class ChangePasswordActivity extends Activity {
    EditText editText_passCurrent, editText_newPass, editText_newCofirmPass;
    Button button_change, button_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        anhxa();
        button_change.setOnClickListener(v -> {
            if (!Check.check2Chuoi(editText_newPass.getText().toString().trim(), editText_newCofirmPass.getText().toString().trim())) {
                Check.creatToat("new password với new confirm password không giống nhau!", getApplicationContext());
            } else {
                ConnectWithFirebase.getInstance(this).changePasswordEmail(editText_newPass.getText().toString().trim(),
                        editText_passCurrent.getText().toString().trim(), this);
            }
        });
        button_back.setOnClickListener(v -> finish());

    }

    private void anhxa() {
        editText_newPass = findViewById(R.id.edit_newPass);
        editText_passCurrent = findViewById(R.id.edit_pasCurrent);
        button_change = findViewById(R.id.button_change_pass);
        button_back = findViewById(R.id.button_back_changePass);
        editText_newCofirmPass = findViewById(R.id.edit_newConfirmPass_change);
    }
}
