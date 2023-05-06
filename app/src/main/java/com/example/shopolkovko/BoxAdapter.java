package com.example.shopolkovko;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.example.shopolkovko.databinding.ItemBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class BoxAdapter extends BaseAdapter {

    Context ctx;

    CheckBox cbBuy;

    LayoutInflater lInflater;
    ArrayList<Product> objects;

    int count = 0;

    int id = -1;

    private ArrayList<Integer> checked = new ArrayList<>(Arrays.asList(6));


    BoxAdapter(Context context, ArrayList<Product> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return objects.size();
    }


    public Object getItem(int i) {
        return objects.get(i);
    }


    public long getItemId(int i) {
        return i;
    }

    public int getPrice(){
        int sum = 0;
        for(int i = 0; i < objects.size(); i++){
            sum += objects.get(i).price;
        }
        return sum;
    }



    public View getView(int i, View convertview, ViewGroup parent) {
        ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        Product p = getProduct(i);

        binding.tvDescr.setText(p.name);
        binding.tvPrice.setText(p.price + " рублей");
        binding.tvAbil.setText(p.number);
        binding.ivImage.setImageResource(p.image);

        cbBuy = binding.cbBox;
        cbBuy.setTag(i);
        cbBuy.setChecked(p.box);

        count = 0;


        cbBuy.setOnClickListener(v -> {
            for(int q = 0; q < checked.size(); q++){
                if(i != checked.get(q)){
                    count++;
                }else{
                    id = q;
                }
            }
            if(count == checked.size()){
                MainActivity.onClick(1, i);
                checked.add(i);
            }else{
                MainActivity.onClick(2, id);
                checked.remove(id);
            }
        });

        return binding.getRoot();

    }


    Product getProduct(int position) {
        return ((Product) getItem(position));
    }

    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<Product>();
        for (Product p : objects) {
            if (p.box)
                box.add(p);
        }
        return box;

    }

}