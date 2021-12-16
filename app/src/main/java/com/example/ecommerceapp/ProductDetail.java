package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ProductDetail extends AppCompatActivity {

    TextView pTitle,pDesc,pPrice;
    ImageView pimage;
    String productTitle,productDesc;
    double productPrice;
    String productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        pTitle = findViewById(R.id.textProucttitle);
        pDesc = findViewById(R.id.textProductDescription);
        pPrice = findViewById(R.id.textProductPrice);
        pimage = findViewById(R.id.imageProductDetail);
        getData();
        setData();
    }
    public void getData(){
        if (getIntent().hasExtra("Product_Title") && getIntent().hasExtra("Product_Desc")
                && getIntent().hasExtra("Product_Price") && getIntent().hasExtra("Product_Image"))
        {
            productTitle=getIntent().getStringExtra("Product_Title");
            productDesc=getIntent().getStringExtra("Product_Desc");
            productPrice=getIntent().getDoubleExtra("Product_Price",1);
            productImage=getIntent().getStringExtra("Product_Image");
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_SHORT).show();
        }

    }
    public void setData(){

        pTitle.setText(productTitle);
        pDesc.setText(productDesc);
        String price =String.valueOf(productPrice);
        pPrice.setText("$"+price);
        Glide.with(getApplicationContext())
                .load(productImage)
                .fitCenter()
                .into(pimage);

    }
}