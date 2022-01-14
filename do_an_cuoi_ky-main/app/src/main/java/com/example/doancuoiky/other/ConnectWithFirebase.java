package com.example.doancuoiky.other;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.doancuoiky.activity.LoginActivity;
import com.example.doancuoiky.activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConnectWithFirebase {
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private Context context;
    private boolean checkPass = false;

    private static ConnectWithFirebase instance;

    private ConnectWithFirebase(Context context) {
        this.mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
    }

    public static ConnectWithFirebase getInstance(Context context) {
        if (instance == null) {
            instance = new ConnectWithFirebase(context);
        }
        instance.context = context;
        return instance;
    }

    public void createEmailwithPassword(String email, String password) {
        if (!((Activity) context).isFinishing()) {
            //show dialog
            progressDialog.show();
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, (OnCompleteListener<AuthResult>) task -> {
            progressDialog.dismiss();
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Check.creatToat("Đăng ký thành công", context);
                ((Activity) context).finish();
            } else {
                // If sign in fails, display a message to the user.
                Check.creatToat("Tài khoản đã tồn tại", context);
            }
        });
    }


    public void signInwithEmail(String email, String password) {
        if (!((Activity) context).isFinishing()) {
            //show dialog
            try {
                progressDialog.show();
            } catch (WindowManager.BadTokenException e) {

            }

        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's informations
                            startMainActivity(email);
                        } else {
                            Check.creatToat("Tài khoản hoặc mật khẩu không chính xác!", context);
                        }
                    }
                });
    }

    public void sendResetPassword(String email) {
        if (!((Activity) context).isFinishing()) {
            //show dialog
            progressDialog.show();
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Check.creatToat("Vui lòng kiểm tra email", context);

                    context.startActivity(new Intent(context, LoginActivity.class));
                } else {
                    Check.creatToat("Email không chính xác!", context);
                }
            }
        });
    }

    public void changePasswordEmail(String newPassword, String currentPass, Activity activity) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (checkPass(user.getEmail(), currentPass)) {
            user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Check.creatToat("Mật khẩu đã được thay đổi.", context);
                        activity.finish();
                    } else {
                        Check.creatToat("Mật khẩu lớn hơn 6 ký tự", context);
                    }
                }
            });
        } else {
            Check.creatToat("Vui lòng thử lại!!!", context);
        }

    }

    private boolean checkPass(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    checkPass = true;
                }
            }
        });
        return checkPass;
    }

    private void startMainActivity(String str) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("email", str);
        context.startActivity(intent);
    }
}
