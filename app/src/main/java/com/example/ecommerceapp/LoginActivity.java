package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerceapp.Model.UserModel;
import com.example.ecommerceapp.RetrofitAPI.ProductAPI;
import com.example.ecommerceapp.RetrofitAPI.RetrofitClientinstance;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    ProductAPI productAPI;
    ArrayList<UserModel> arrayList=new ArrayList<>();
    KProgressHUD hud;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SplashScreen splashScreen=SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);


    }

    public void btnLogin(View view)
    {
        getUserData();

        if (email.getText().toString().equals("") && password.getText().toString().equals(""))
        {
            email.setError("Enter Your Email");
            password.setError("Enter Your Password");

        }
        else
        {
            for (int i=0;i<arrayList.size();i++)
            {
                if (arrayList.get(i).getEmail().equals(email.getText().toString()) &&
                        arrayList.get(i).getPassword().equals(password.getText().toString()))
                {
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(),"Login Sucessful",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Inavlid Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        }
        //startActivity(new Intent(this,MainActivity.class));
    }

    public void getUserData(){
        //set progress bar
        hud = KProgressHUD.create(LoginActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        productAPI= RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getUsers().enqueue(new Callback<ArrayList<UserModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                hud.dismiss();
                if (response.body().size()>0)
                {
                    arrayList=response.body();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error in Fetching data",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                 Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}