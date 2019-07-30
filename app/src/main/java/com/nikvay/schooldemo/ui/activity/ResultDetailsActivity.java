package com.nikvay.schooldemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nikvay.schooldemo.R;
import com.nikvay.schooldemo.domain.module.ResultExamNameModule;
import com.nikvay.schooldemo.domain.module.SuccessModule;
import com.nikvay.schooldemo.domain.network.ApiClient;
import com.nikvay.schooldemo.domain.network.ApiInterface;
import com.nikvay.schooldemo.ui.adapter.ResultAdapter;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultDetailsActivity extends AppCompatActivity
{
    ApiInterface apiInterface;
    String id;
    ArrayList<ResultExamNameModule>resultExamNameModules=new ArrayList<>();
    RecyclerView list_result;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_details);
        get_all_ids();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        list_result.setLayoutManager(linearLayoutManager);
        list_result.setHasFixedSize(true);
        events();
    }

    private void events()
    {
        String exam_id="1";
String student_id="2";
        Call<SuccessModule>call=apiInterface.resultListCall(student_id,exam_id);
        call.enqueue(new Callback<SuccessModule>() {
            @Override
            public void onResponse(Call<SuccessModule> call, Response<SuccessModule> response)
            {
                String str_response=new Gson().toJson(response.body());
                try
                {
                    if(response.isSuccessful())
                    {
                        SuccessModule successModule = response.body();
                        if (successModule != null)
                        {


                            String msg = successModule.getMsg();
                            Toast.makeText(ResultDetailsActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                            resultExamNameModules = successModule.getResultExamNameModuleArrayList();
                            ResultAdapter resultAdapter = new ResultAdapter(ResultDetailsActivity.this, resultExamNameModules);
                            list_result.setAdapter(resultAdapter);
                            resultAdapter.notifyDataSetChanged();
                        }
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SuccessModule> call, Throwable t)
            {

            }
        });
    }

    private void get_all_ids()
    {
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        id=getIntent().getStringExtra("id");
        list_result=findViewById(R.id.result_list);

    }
}
