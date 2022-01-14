package com.example.doancuoiky.activity;

import android.Manifest;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doancuoiky.R;
import com.example.doancuoiky.activity.ui.home.HomeFragment;
import com.example.doancuoiky.databinding.ActivityMainBinding;
import com.example.doancuoiky.model.Account;
import com.example.doancuoiky.model.Book;
import com.example.doancuoiky.other.Check;
import com.example.doancuoiky.other.MySQL;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private static final int CODE_IMAGE = 111;
    private List<Account> accounts = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    private TextView textView_user, textView_fullName;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static Account account;
    private NavController navController;
    private CircleImageView imageView;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        accounts.clear();

        MySQL.getData("http://cruss.atwebpages.com/getData.php", MainActivity.this, accounts, MySQL.EMAIL, MySQL.FULLNAME, MySQL.ADDRESS, MySQL.PHONENUMBER);
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_accInfo, R.id.nav_Cart, R.id.nav_Contacts, R.id.nav_Logout, R.id.action_carts)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public void onBackPressed() {
        Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        searchView = (SearchView) menu.findItem(R.id.find_book).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                HomeFragment.bookAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                HomeFragment.bookAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setData();
        if (item.getItemId() == R.id.action_carts) {

            Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_Cart);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        Bitmap bitmap = stringToBitMap(LoginActivity.sharedPreferences.getString("image", ""));
        anhxa();
        getData("http://cruss.atwebpages.com/getDonHang.php");
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        imageView.setOnClickListener(v -> {
            if (Check.checkPerMission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                getImage();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
                    getImage();
                }
            }

        });
        setData();
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setData() {
        if (accounts.size() > 0) {
            for (Account a : accounts
            ) {
                if (a.getEmail().equals(getEmail())) {
                    account = a;
                    if (textView_user != null && textView_fullName != null) {
                        textView_user.setText(a.getEmail());
                        textView_fullName.setText(a.getHoten());
                    }
                }
            }

        }
    }

    private void getImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, CODE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_IMAGE && data != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                imageView.setImageBitmap(bitmap);
                saveImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveImage(Bitmap bitmap) {
        SharedPreferences.Editor editor = LoginActivity.sharedPreferences.edit();
        editor.putString("image", bitMapToString(bitmap));
        editor.commit();
    }


    @Override
    public boolean enterPictureInPictureMode(@NonNull PictureInPictureParams params) {
        return super.enterPictureInPictureMode(params);
    }

    private void anhxa() {
        textView_fullName = findViewById(R.id.textView_fullName);
        textView_user = findViewById(R.id.textView_user);
        imageView = findViewById(R.id.imageView_avt);
    }

    private String bitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    private Bitmap stringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public String getEmail() {
        LoginActivity.sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        return LoginActivity.sharedPreferences.getString("user", "");
    }

    private void getData(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                books.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        if (jsonObject.getString("email").equals(getEmail())) {
                            books.add(new Book(jsonObject.getInt("soLuong"),
                                    jsonObject.getString("tenSach"),
                                    jsonObject.getDouble("donGia"),
                                    jsonObject.getInt("tinhTrang")));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, null);
        requestQueue.add(jsonArrayRequest);
    }


}