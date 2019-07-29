package com.nikvay.schooldemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nikvay.schooldemo.R;
import com.nikvay.schooldemo.domain.module.SuccessModule;
import com.nikvay.schooldemo.domain.module.VideoListModule;
import com.nikvay.schooldemo.domain.network.ApiClient;
import com.nikvay.schooldemo.domain.network.ApiInterface;
import com.nikvay.schooldemo.ui.adapter.VideoAdapter;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoListActivity extends AppCompatActivity
{
private String id;
RecyclerView videoList;
ApiInterface apiInterface;
    ArrayList<VideoListModule> videoListModuleArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        get_All_ids();
        get_videos();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        videoList.setLayoutManager(linearLayoutManager);
        videoList.setHasFixedSize(true);
    }

    public void get_videos()
    {
        Call<SuccessModule> call=apiInterface.videoTutorialsListCall(id);
        call.enqueue(new Callback<SuccessModule>() {
            @Override
            public void onResponse(Call<SuccessModule> call, Response<SuccessModule> response)
            {
                String str_respomnse=new Gson().toJson(response.body());
                try {
                    if (response.isSuccessful()) {
                        SuccessModule successModule = response.body();
                        if (successModule!=null)
                        {
                            String msg = successModule.getMsg();

                            videoListModuleArrayList = successModule.getVideoListModuleArrayList();
                            Collections.reverse(videoListModuleArrayList);
                            VideoAdapter videoAdapter = new VideoAdapter(VideoListActivity.this, videoListModuleArrayList);
                            videoList.setAdapter(videoAdapter);


                        }
                        else
                        {
                            Toast.makeText(VideoListActivity.this, "failed", Toast.LENGTH_SHORT).show();
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

    private void get_All_ids()
    {
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        videoList=findViewById(R.id.video_List);
        id=getIntent().getStringExtra("id");
        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
    }
}
