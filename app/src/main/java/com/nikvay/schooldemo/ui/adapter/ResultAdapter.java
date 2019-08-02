package com.nikvay.schooldemo.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nikvay.schooldemo.R;
import com.nikvay.schooldemo.domain.module.ResultExamNameModule;
import com.nikvay.schooldemo.domain.module.ResultMarkModule;
import com.nikvay.schooldemo.shared_pref.SharedPreference;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {
    Context mContext;
    SharedPreferences sharedpreferences;
    private ArrayList<ResultExamNameModule> resultExamNameModuleArrayList;
    private ResultChildAdapter resultChildAdapter;
    int marksObtain, outOfMarks;
    double finalPer, m, o;
    public static String MyPREFERENCES = "Fast Connect";

    String userFullName;
    public ResultAdapter(Context mContext, ArrayList<ResultExamNameModule> resultExamNameModuleArrayList)
    {
        this.mContext = mContext;
        this.resultExamNameModuleArrayList = resultExamNameModuleArrayList;
        sharedpreferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        userFullName = sharedpreferences.getString(SharedPreference.USER_FULL_NAME, "");

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_item_result_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        final ResultExamNameModule resultExamNameModule = resultExamNameModuleArrayList.get(position);
        Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext, ""+resultExamNameModule.getObtain_marks_1(), Toast.LENGTH_SHORT).show();

        int s1_marks,s2_marks,s3_marks,s4_marks,s5_marks,total;
        String ss1_marks,ss2_marks,ss3_marks,ss4_marks,ss5_marks;

        ss1_marks=resultExamNameModule.getObtain_marks_1();
        ss2_marks=resultExamNameModule.getObtain_marks_2();
        ss3_marks=resultExamNameModule.getObtain_marks_3();
      //  ss4_marks=resultExamNameModule.getObtain_marks_4();
       // ss5_marks=resultExamNameModule.getObtain_marks_5();

        s1_marks=Integer.parseInt(ss1_marks);
        s2_marks=Integer.parseInt(ss2_marks);
        s3_marks=Integer.parseInt(ss3_marks);
      //  s4_marks=Integer.parseInt(ss4_marks);
        //s5_marks=Integer.parseInt(ss5_marks);

        total=s1_marks+(s2_marks)+(s3_marks);

        holder.total.setText(String.valueOf(total));

        holder.sub1.setText(resultExamNameModule.getSub1());
        holder.sub2.setText(resultExamNameModule.getSub2());
        holder.sub3.setText(resultExamNameModule.getSub3());
        holder.sub4.setText(resultExamNameModule.getSub4());
        holder.sub5.setText(resultExamNameModule.getSub5());

        holder.sub1_marks.setText(resultExamNameModule.getObtain_marks_1());
        holder.sub2_marks.setText(resultExamNameModule.getObtain_marks_2());
        holder.sub3_marks.setText(resultExamNameModule.getObtain_marks_3());
        holder.sub4_marks.setText(resultExamNameModule.getObtain_marks_4());
        holder.sub5_marks.setText(resultExamNameModule.getObtain_marks_5());

        holder.sub1_total.setText(resultExamNameModule.getTotal_marks_1());
        holder.sub2_total.setText(resultExamNameModule.getTotal_marks_2());
        holder.sub3_total.setText(resultExamNameModule.getTotal_marks_3());
        holder.sub4_total.setText(resultExamNameModule.getTotal_marks_4());
        holder.sub5_total.setText(resultExamNameModule.getTotal_marks_5());

        holder.student_name.setText(userFullName);


    }

    @Override
    public int getItemCount()
    {
        return resultExamNameModuleArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {


        TextView sub1,sub2,sub3,sub4,sub5,
                sub1_marks,sub2_marks,sub3_marks,sub4_marks,sub5_marks,
                sub1_total,sub2_total,sub3_total,sub4_total,sub5_total,student_name,total;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            student_name=itemView.findViewById(R.id.student_name);
            total=itemView.findViewById(R.id.t_marks);
            sub1=itemView.findViewById(R.id.sub1);
            sub2=itemView.findViewById(R.id.sub2);
            sub3=itemView.findViewById(R.id.sub3);
            sub4=itemView.findViewById(R.id.sub4);
            sub5=itemView.findViewById(R.id.sub5);

            sub1_marks=itemView.findViewById(R.id.s1_marks);
            sub2_marks=itemView.findViewById(R.id.s2_marks);
            sub3_marks=itemView.findViewById(R.id.s3_marks);
            sub4_marks=itemView.findViewById(R.id.s4_marks);
            sub5_marks=itemView.findViewById(R.id.s5_marks);

            sub1_total=itemView.findViewById(R.id.s1_total_marks);
            sub2_total=itemView.findViewById(R.id.s2_total_marks);
            sub3_total=itemView.findViewById(R.id.s3_total_marks);
            sub4_total=itemView.findViewById(R.id.s4_total_marks);
            sub5_total=itemView.findViewById(R.id.s5_total_marks);
        }
    }
}
