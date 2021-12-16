package com.example.ecommerceapp.RetrofitAPI;

import com.example.ecommerceapp.Model.CategoryModel;
import com.example.ecommerceapp.Model.ProductModel;
import com.example.ecommerceapp.Model.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductAPI {

    @GET("products")
    Call<ArrayList<ProductModel>> getMyProduct();

    @GET("products/categories")
    Call<List<String>> getMYCategory();

    @GET("products/category/{type}")
    Call<ArrayList<ProductModel>> getSpecificCatagory(@Path("type") String type);


    @GET("users")
    Call<ArrayList<UserModel>> getUsers();

    @GET("products?sort=desc")
    Call<ArrayList<ProductModel>> getDescending();

    @GET("products?sort=asec")
    Call<ArrayList<ProductModel>> getAsecinding();

    @GET("products?limit=5")
    Call<ArrayList<ProductModel>> getLimited();
}
