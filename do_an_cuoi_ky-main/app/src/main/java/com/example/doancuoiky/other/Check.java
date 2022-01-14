package com.example.doancuoiky.other;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.EditText;
import android.widget.Toast;

public class Check {
    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public static boolean checkPerMission(Context context, String str) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkNull(EditText editText, String key) {
        String str = editText.getText().toString().trim();
        if (str.isEmpty() || str.equals("")) {
            editText.setError(key + " không được để rỗng");
            return false;
        }
        return true;
    }

    public static boolean checkKhoangTrang(EditText editText, String key) {
        String str = editText.getText().toString().trim();
        boolean check = false;
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (Character.isSpaceChar(c)) {
                check = true;
                break;
            }
        }
        if (check) {
            editText.setError(key + " không được chứa khoảng trắng");
            return false;
        }
        return true;
    }

    public static boolean checkEmail(EditText editText) {
        String str = editText.getText().toString().trim();
        String[] a = str.split("@");
        if (a[a.length - 1].equals("gmail.com")) {
            return true;
        }
        editText.setError("Email chưa chính xác => ví dụ: a@gmail.com");
        return false;
    }

    public static boolean checkSizeNhoHon6(String str) {
        return str.length() < 6 ? false : true;
    }

    public static void creatToat(String str, Context context) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static boolean check2Chuoi(String str1, String str2) {
        return str1.equals(str2);
    }

}
