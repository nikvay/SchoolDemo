package com.nikvay.schooldemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nikvay.schooldemo.R;
import com.nikvay.schooldemo.ui.activity.VideoListActivity;
import com.nikvay.schooldemo.ui.module.VideoCategoryModel;

import java.util.ArrayList;

public class CategoriesAdapter extends BaseAdapter
{
    Context mContext;
    ArrayList<VideoCategoryModel>categoriesList=new ArrayList();
View view;
LayoutInflater layoutInflater;
    public CategoriesAdapter(Context mContext, ArrayList<VideoCategoryModel> categoriesList)
    {
        this.mContext = mContext;
        this.categoriesList = categoriesList;
        layoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount()
    {
        return categoriesList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view=layoutInflater.inflate(R.layout.video_category_layout,null);
        TextView textView=view.findViewById(R.id.tv_category);
        final VideoCategoryModel videoCategoryModel=categoriesList.get(i);
     //   textView.setText(videoCategoryModel.getName());
        CardView cardView=view.findViewById(R.id.card_vdo);
        cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent=new Intent(mContext, VideoListActivity.class);
                intent.putExtra("id",videoCategoryModel.getId());
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}