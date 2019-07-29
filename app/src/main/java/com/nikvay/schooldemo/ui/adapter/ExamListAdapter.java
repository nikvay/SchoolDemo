package com.nikvay.schooldemo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nikvay.schooldemo.R;
import com.nikvay.schooldemo.domain.module.ExamListModel;

import java.util.ArrayList;

public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.ListViewHolder>
{
Context context;
ArrayList<ExamListModel>examListModelArrayList;

    public ExamListAdapter(Context context, ArrayList<ExamListModel> examListModelArrayList)
    {
        this.context = context;
        this.examListModelArrayList = examListModelArrayList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exam_list,viewGroup,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i)
    {
        ExamListModel examListModel=examListModelArrayList.get(i);
        listViewHolder.textView.setText(examListModel.getName());
        final String id=examListModel.getExam_id();
        listViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return examListModelArrayList.size();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView;
        CardView cardView;
        public ListViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.exam_name);
            cardView=itemView.findViewById(R.id.card);

        }
    }
}
