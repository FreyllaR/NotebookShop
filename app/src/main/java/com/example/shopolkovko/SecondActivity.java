package com.example.shopolkovko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.shopolkovko.databinding.ActivitySecondBinding;
import com.example.shopolkovko.databinding.FooterBinding;
import com.example.shopolkovko.databinding.HeaderBinding;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity{

    private ActivitySecondBinding binding;

    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;

    ListView lvMain2;

    private static FooterBinding binding1;

    private static HeaderBinding binding2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding1 = FooterBinding.inflate(getLayoutInflater());
        binding2 = HeaderBinding.inflate(getLayoutInflater());

        Intent intent = getIntent();
        products = (ArrayList<Product>) intent.getSerializableExtra("Items");


        createHeader("Корзина");

        boxAdapter = new BoxAdapter(this, products);

        createFooter("Цена: " + boxAdapter.getPrice() + " рублей");

        lvMain2 = binding.lvMain2;
        lvMain2.addHeaderView(binding2.getRoot());
        lvMain2.addFooterView(binding1.getRoot());
        lvMain2.setAdapter(boxAdapter);
    }

    void createHeader(String text){
        binding2.tvText1.setText(text);
    }

    void createFooter(String text){
        binding1.tvText2.setText(text);
    }


}