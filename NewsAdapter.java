package com.example.midterm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    ArrayList<News> newsmodel;
    Context context;
    int resource;

    public NewsAdapter(ArrayList<News> data, Context context){
        super (context,R.layout.activity_list_item,data);
        this.context=context;
        this.newsmodel=data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int newsIndex = position;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item,parent,false);
        }

        TextView heading = convertView.findViewById(R.id.header);
        ImageView newsImage = convertView.findViewById(R.id.Image_News);
        TextView desc = convertView.findViewById(R.id.descript);
        Button sharebtn = convertView.findViewById(R.id.shareit);

        News newNews = getItem(position);

        sharebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,newNews.getRef());
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent,null);
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(shareIntent);
            }


        });

        newsImage.setImageResource(newNews.getId());
        heading.setText(newNews.getHeader());
        desc.setText(newNews.getDescript());
        Picasso.get().load(newNews.getUrl()).into(newsImage);

        return convertView;
    }
}
