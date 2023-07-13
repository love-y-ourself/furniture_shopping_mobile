package com.bibon.furnitureshopping.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bibon.furnitureshopping.R;
import com.bibon.furnitureshopping.applications.CartApplication;
import com.bibon.furnitureshopping.models.Cart;
import com.bibon.furnitureshopping.models.CartList;
import com.bibon.furnitureshopping.models.Product;
import com.bibon.furnitureshopping.repositories.ProductRepository;
import com.bibon.furnitureshopping.services.ProductService;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    ProductService productService;
    Product product;
    TextView tv_product_name_detail, tv_price, tv_quantity, tv_description_detail;
    ImageView img_product_detail, img_plus_detail, img_minus_detail;
    ConstraintLayout btn_add_to_cart_detail;
    CartList cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        ImageView img_back = (ImageView) findViewById(R.id.img_back);
        img_plus_detail = findViewById(R.id.img_plus_detail);
        img_minus_detail = findViewById(R.id.img_minus_detail);
        btn_add_to_cart_detail = findViewById(R.id.btn_add_to_cart_detail);

        if (cartList == null) {
            cartList = new CartList();
        }
        cartList = ((CartApplication) this.getApplication()).getCartList();


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        int[] quantity = new int[1];
        quantity[0] = 1;
        tv_product_name_detail = (TextView) findViewById(R.id.tv_product_name_detail);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_quantity = (TextView) findViewById(R.id.tv_quantity);
        tv_description_detail = (TextView) findViewById(R.id.tv_description_detail);
        img_product_detail = (ImageView) findViewById(R.id.img_product_detail);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        product = (Product) args.getSerializable("Product");

        productService = ProductRepository.getProductService();

        if (product != null) {
            tv_product_name_detail.setText(product.getProductName());
            tv_price.setText(product.getPrice() + "");
            tv_description_detail.setText(product.getDescription());
            Picasso.get().load(product.getImg()).placeholder(R.drawable.armchair).error(R.drawable.armchair).fit().into(img_product_detail);
        }

        img_plus_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity[0]++;
                tv_quantity.setText(String.valueOf(quantity[0]));
            }
        });

        img_minus_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity[0] - 1 > 0) {
                    quantity[0]--;
                    tv_quantity.setText(String.valueOf(quantity[0]));
                }
            }
        });

        btn_add_to_cart_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                cartList.getCartList().add(new Cart(product.get_id(), product.getProductName(), product.getPrice(), quantity[0], product.getImg()));
//                Intent intent = new Intent(v.getContext(), MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("Fragment", "cart");
//                intent.putExtra("BUNDLE", bundle);
//                startActivity(intent);
            }
        });

    }

}