package com.example.shopolkovko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.shopolkovko.databinding.ActivityMainBinding;
import com.example.shopolkovko.databinding.FooterBinding;
import com.example.shopolkovko.databinding.HeaderBinding;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements Serializable {

    ArrayList<Product> products = new ArrayList<Product>();

    ArrayList<Serializable> ready_products = new ArrayList<Serializable>();
    BoxAdapter boxAdapter;

    static int temp = 0;
    private static ActivityMainBinding binding;
    private static FooterBinding binding1;

    private static HeaderBinding binding2;


    String [] notes = {"Ноутбук Dell", "Ноутбук Alienware", "Ноутбук Asus", "Ноутбук Macbook", "Ноутбук Huawei"};

    int[] myImageList = new int[]{R.drawable.alienware, R.drawable.asus, R.drawable.dell, R.drawable.huawei, R.drawable.macbook};

    String [] numbers = {"4573745", "2546775", "8563786", "5968796", "0875824"};

    int[] price = {132475, 223395, 349045, 451209, 98527};


    ListView lvMain;

    private static ArrayList<Integer> checked = new ArrayList<>(Arrays.asList(6));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding1 = FooterBinding.inflate(getLayoutInflater());
        binding2 = HeaderBinding.inflate(getLayoutInflater());



        createHeader("Ассортимент");
        createFooter("Товаров в корзине: " + temp + "\n");


        fillData();
        boxAdapter = new BoxAdapter(this, products);

        lvMain = binding.lvMain;
        lvMain.addHeaderView(binding2.getRoot());
        lvMain.addFooterView(binding1.getRoot());
        lvMain.setAdapter(boxAdapter);


    }
    void createHeader(String text){
       binding2.tvText1.setText(text);
    }



    void createFooter(String text){
        binding1.tvText2.setText(text);
    }


    void fillData(){
        for(int i = 0; i < 5; i++){
            products.add(new Product(notes[i], numbers[i], price[i],  myImageList[i], false));
        }
    }

    public void showResult(View v){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("Items", check());
        startActivity(intent);
    }

    public static void onClick(int pos, int id) {
        if(pos == 1){
            temp += 1;
            binding1.tvText2.setText("Товаров в корзине: " + Integer.toString(temp) + "\n");
            checked.add(id);
        }else{
            temp -= 1;
            if(temp <= 0){
                binding1.tvText2.setText("Товаров в корзине: " + Integer.toString(0) + "\n");
                checked.remove(id);
            }else{
                binding1.tvText2.setText("Товаров в корзине: " + Integer.toString(temp) + "\n");
                checked.remove(id);
            }
        }
    }

    public ArrayList<Serializable> check(){
        int i = 0;
        while(i != products.size()){
            for(int q = 0; q < checked.size(); q++) {
                if (checked.get(q) == i) {
                    ready_products.add(products.get(i));
                }
            }
            i++;
        }
        return ready_products;
    }
}