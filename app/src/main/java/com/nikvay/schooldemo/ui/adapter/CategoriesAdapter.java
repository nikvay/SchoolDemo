package com.nikvay.schooldemo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nikvay.schooldemo.R;
import com.nikvay.schooldemo.ui.module.VideoCategoryModel;

import java.util.ArrayList;
/*

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>
{
Context mContext;
    ArrayList<VideoCategoryModel>categoriesList=new ArrayList();

    public CategoriesAdapter(Context mContext, ArrayList<VideoCategoryModel> categoriesList)
    {
        this.mContext = mContext;
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_category_layout,viewGroup,false);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder categoryHolder, int i)
    {
            VideoCategoryModel videoCategoryModel=categoriesList.get(i);
            categoryHolder.textView.setText(videoCategoryModel.getName());
    }

    @Override
    public int getItemCount()
    {
        return categoriesList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        public CategoryHolder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_category);
        }
    }

}
*/
public class CategoriesAdapter extends BaseAdapter
{
    Context mContext;
    ArrayList<VideoCategoryModel>categoriesList=new ArrayList();
View view;
LayoutInflater layoutInflater;
    public CategoriesAdapter(Context mContext, ArrayList<VideoCategoryModel> categoriesList) {
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
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view=layoutInflater.inflate(R.layout.video_category_layout,null);
        TextView textView=view.findViewById(R.id.tv_category);
        VideoCategoryModel videoCategoryModel=categoriesList.get(i);
        textView.setText(videoCategoryModel.getName());




        return view;
    }
}