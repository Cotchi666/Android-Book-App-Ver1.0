package com.example.doancuoiky.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.doancuoiky.R;
import com.example.doancuoiky.other.Check;
import com.example.doancuoiky.other.MySQL;

public class SignUpActivity extends Activity {
    TextView textView_signIn;
    Button button_back, button_SignUp;
    EditText editText_user, editText_fullName, editText_address, editText_phoneNumber, editText_pass, editText_confirmPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        anhxa();
        button_SignUp.setOnClickListener(v -> {
            if (check()) {
                MySQL.create_Account(this, "http://cruss.atwebpages.com/insert.php",
                        editText_user.getText().toString().trim(),
                        editText_pass.getText().toString().trim(),
                        editText_phoneNumber.getText().toString().trim(),
                        editText_fullName.getText().toString().trim(),
                        editText_address.getText().toString().trim());
            }
        });
        button_back.setOnClickListener(v -> finish());
        textView_signIn.setOnClickListener(v -> finish());
    }

    private void anhxa() {

        button_back = findViewById(R.id.button_back);
        button_SignUp = findViewById(R.id.button_signUp);
        textView_signIn = findViewById(R.id.textView_SignIn);
        editText_address = findViewById(R.id.edit_address_signUp);
        editText_user = findViewById(R.id.edit_user_sigUp);
        editText_fullName = findViewById(R.id.edit_fullName_SigUp);
        editText_phoneNumber = findViewById(R.id.edit_phone_signUp);
        editText_pass = findViewById(R.id.edit_pass_SignUp);
        editText_confirmPass = findViewById(R.id.edit_confirmPass_signUp);
    }


    /* private void check1() {
         if (!Check.checkNull(editText_user.getText().toString().trim()) ||
                 !Check.checkNull(editText_pass.getText().toString().trim()) ||
                 !Check.checkNull(editText_confirmPass.getText().toString().trim())) {
             Check.creatToat("Thông tin không được để rỗng và ghi liền không dấu!!!", getApplicationContext());
             editText_pass.setError("Thông tin không được để rỗng và ghi liền không dấu!!!");
         } else {
             if (!Check.check2Chuoi(editText_pass.getText().toString().trim(), editText_confirmPass.getText().toString().trim())) {
                 Check.creatToat("Pass với confirmPass phải giống nhau", getApplicationContext());
             } else {

             }
         }
     }*/
    private boolean check() {
      /*  boolean check = false;
        if (Check.checkNull(editText_user, "email")) {
            check = true;
            if (Check.checkKhoangTrang(editText_user, "email")) {
                check = true;
                if (!Check.checkEmail(editText_user.getText().toString().trim())) {
                    editText_user.setError("Email chưa chính xác => ví dụ: a@gmail.com");
                    check = false;
                }
            } else {
                check = false;
            }

        } else {
            check = false;
        }
        if (Check.checkNull(editText_fullName, getResources().getString(R.string.fullName_infoACC))) {
            check = true;
        } else {
            check = false;
        }
        if (Check.checkNull(editText_address, getResources().getString(R.string.address_infoACC))) {
            check = true;
        } else {
            check = false;
        }
        if (Check.checkNull(editText_pass, getResources().getString(R.string.password))) {
            check = true;
            if (Check.checkKhoangTrang(editText_pass, getResources().getString(R.string.password))) {
                check = true;
            } else {
                check = false;
            }
        } else {
            check = false;
        }

        if (Check.checkNull(editText_phoneNumber, "Số điện thoại")) {
            check = true;
            if (Check.checkKhoangTrang(editText_phoneNumber, getResources().getString(R.string.phoneNumber_infoACC))) {
                check = true;
            } else {
                check = false;
            }
        } else {
            check = false;
        }
        if (Check.checkNull(editText_confirmPass, "Xác nhận mật khẩu")) {
            check = true;
            if (Check.checkKhoangTrang(editText_confirmPass, getResources().getString(R.string.confirm_pass))) {
                check = true;
                if (!Check.check2Chuoi(editText_pass.getText().toString().trim(),
                        editText_confirmPass.getText().toString().trim())) {
                    editText_confirmPass.setError("Xác nhận mật khẩu phải giống với mật khẩu");
                    check = false;
                } else {
                    check = true;
                }
            } else {
                check = false;
            }
        } else {
            check = false;
        }*/


        if (Check.checkNull(editText_user, "email") &&
                Check.checkNull(editText_fullName, getResources().getString(R.string.fullName_infoACC)) &&
                Check.checkNull(editText_address, getResources().getString(R.string.address_infoACC)) &&
                Check.checkNull(editText_phoneNumber, getResources().getString(R.string.phoneNumber_infoACC)) &&
                Check.checkNull(editText_pass, getResources().getString(R.string.password)) &&
                Check.checkNull(editText_confirmPass, getResources().getString(R.string.confirm_pass))
        ) {
            if (Check.checkKhoangTrang(editText_user, "email") &&
                    Check.checkKhoangTrang(editText_phoneNumber, getResources().getString(R.string.phoneNumber_infoACC)) &&
                    Check.checkKhoangTrang(editText_pass, getResources().getString(R.string.password)) &&
                    Check.checkKhoangTrang(editText_confirmPass, getResources().getString(R.string.confirm_pass)) &&
                    Check.checkEmail(editText_user)
            ) {
                if (Check.check2Chuoi(editText_pass.getText().toString().trim(), editText_confirmPass.getText().toString().trim())) {
                    return true;
                }else {
                    editText_confirmPass.setError("Xác nhận mật khẩu phải giống với mật khẩu!");
                }
            }
        }

        return false;
    }


}
