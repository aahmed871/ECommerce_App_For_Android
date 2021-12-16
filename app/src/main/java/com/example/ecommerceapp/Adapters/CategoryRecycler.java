package com.example.ecommerceapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.MainActivity;
import com.example.ecommerceapp.Model.CategoryModel;
import com.example.ecommerceapp.Model.ProductModel;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.RetrofitAPI.ProductAPI;
import com.example.ecommerceapp.RetrofitAPI.RetrofitClientinstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRecycler extends RecyclerView.Adapter<CategoryRecycler.MyViewHolder> {
    Context context;
    List<String> categoryList;
    ProductAPI productAPI;
    ArrayList<ProductModel> arrayList;
    ProductRecycler productRecycler;
    RecyclerView recyclerView;
    public CategoryRecycler(Context context, List<String> categoryList, ArrayList<ProductModel> arrayList, RecyclerView recyclerView){
        this.context=context;
       this.categoryList=categoryList;
       this.recyclerView=recyclerView;
       this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recycler_horizontal,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String myCategory=categoryList.get(position);
        holder.txtCategory.setText(myCategory);

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,categoryList.get(position),Toast.LENGTH_SHORT).show();
                if (categoryList.get(position).equals("electronics") )
                {
                    //Toast.makeText(context,"This is electronices",Toast.LENGTH_SHORT).show();
                    getElectronics(categoryList.get(position));
                }
                else if (categoryList.get(position).equals("jewelery") )
                {
                    getElectronics(categoryList.get(position));
                    //Toast.makeText(context,"This is jewelery",Toast.LENGTH_SHORT).show();
                }
                else if (categoryList.get(position).equals("men's clothing") )
                {
                    getElectronics(categoryList.get(position));
                    //Toast.makeText(context,"This is men's clothing",Toast.LENGTH_SHORT).show();
                }
                else if (categoryList.get(position).equals("women's clothing") )
                {
                    getElectronics(categoryList.get(position));
                    //Toast.makeText(context,"This is women's clothing",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        getAllProduct();
                    //Toast.makeText(context,"This is .....pta ni kia",Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtCategory;
        ConstraintLayout parent_layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCategory=itemView.findViewById(R.id.textCategory);
            parent_layout = itemView.findViewById(R.id.constraint_parent);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,getAdapterPosition(),Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    public void getElectronics(String type)
    {
        productAPI=RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getSpecificCatagory(type).enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0)
                {
                    MainActivity.productRecycler.updateList(response.body());
                    //arrayList=response.body();
                   // productRecycler=new ProductRecycler(context.getApplicationContext(), arrayList);
                   // recyclerView.setAdapter(productRecycler);
                   // recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
                else
                {
                    Toast.makeText(context,"Error in Fetching Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getAllProduct()
    {
        productAPI=RetrofitClientinstance.getRetrofitInstance().create(ProductAPI.class);
        productAPI.getMyProduct().enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.body().size()>0)
                {
                    MainActivity.productRecycler.updateList(response.body());
                }
                else
                {
                    Toast.makeText(context,"Error in Fetching Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}
