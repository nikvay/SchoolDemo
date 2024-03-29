package com.nikvay.schooldemo.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.nikvay.schooldemo.R;
import com.nikvay.schooldemo.domain.module.SuccessModule;
import com.nikvay.schooldemo.domain.module.VideoListModule;
import com.nikvay.schooldemo.domain.network.ApiClient;
import com.nikvay.schooldemo.domain.network.ApiInterface;
import com.nikvay.schooldemo.shared_pref.SharedPreference;
import com.nikvay.schooldemo.ui.adapter.CategoriesAdapter;
import com.nikvay.schooldemo.ui.adapter.VideoAdapter;
import com.nikvay.schooldemo.ui.module.VideoCategoryModel;
import com.nikvay.schooldemo.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class VideoTutorialsFragment extends Fragment {

    Context mContext;
    ImageView iv_empty_list;
    GridView category_grid;
    //======Interface Declaration=========
    String TAG = getClass().getSimpleName();
    ApiInterface apiInterface;
    ProgressDialog pd;
    ArrayList<VideoCategoryModel>categoriesList=new ArrayList();

    String token, isSelectUser;
    SharedPreferences sharedpreferences;
    public static String MyPREFERENCES = "Fast Connect";

    ArrayList<VideoListModule> videoListModuleArrayList = new ArrayList<>();
    VideoAdapter videoAdapter;
   // RecyclerView recycler_view_video;

    public VideoTutorialsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_tutorials, container, false);
        mContext = getActivity();

        find_All_IDs(view);

        sharedpreferences = mContext.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        isSelectUser = sharedpreferences.getString(SharedPreference.U_TYPE, "");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
      //  recycler_view_video.setLayoutManager(linearLayoutManager);
        //recycler_view_video.setHasFixedSize(true);

        //============ Api Call ================
        if (NetworkUtils.isNetworkAvailable(mContext)) {
            doVideoList();
        }else
            NetworkUtils.isNetworkNotAvailable(mContext);

        return view;
    }//========== End onCreate () ==============


    private void find_All_IDs(View view) {
       // recycler_view_video = view.findViewById(R.id.recycler_view_video);
        category_grid=view.findViewById(R.id.grid_category);
        iv_empty_list = view.findViewById(R.id.iv_empty_list);
    }

    private void doVideoList() {
        pd = new ProgressDialog(mContext);
        pd.setMessage("Loading Please Wait...");
        pd.setCancelable(false);
        //pd.show();
        Call<SuccessModule>call=apiInterface.categoriesList();
        call.enqueue(new Callback<SuccessModule>() {
            @Override
            public void onResponse(Call<SuccessModule> call, Response<SuccessModule> response)
            {
            String str_response=new Gson().toJson(response.body());
            Log.e(""+TAG,"Response>>>>"+str_response);

            try
            {
             if (response.isSuccessful())
             {
                SuccessModule successModule=response.body();
                String msg=successModule.getMsg();
                String error_code=successModule.getError_code();
                 Toast.makeText(getContext(), error_code+""+msg, Toast.LENGTH_SHORT).show();
                 if (successModule!=null)
                 {
                     categoriesList=successModule.getVideoCategoryModelArrayList();
                     Collections.reverse(categoriesList);
                     CategoriesAdapter categoriesAdapter=new CategoriesAdapter(mContext,categoriesList);
                  category_grid.setAdapter((ListAdapter) categoriesAdapter);
                   //  recycler_view_video.setAdapter(categoriesAdapter);
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
     /*   Call<SuccessModule> call = apiInterface.videoTutorialsListCall();

        call.enqueue(new Callback<SuccessModule>() {
            @Override
            public void onResponse(Call<SuccessModule> call, Response<SuccessModule> response) {
                pd.dismiss();
                String str_response = new Gson().toJson(response.body());
                Log.e("" + TAG, "Response >>>>" + str_response);
                try {
                    if (response.isSuccessful()) {
                        SuccessModule loginModule = response.body();

                        String message = null, errorCode = null;
                        if (loginModule != null) {
                            message = loginModule.getMsg();
                            errorCode = loginModule.getError_code();

                            if (errorCode.equalsIgnoreCase("1")) {

                                videoListModuleArrayList = loginModule.getVideoListModuleArrayList();
                                Collections.reverse(videoListModuleArrayList);

                                videoAdapter = new VideoAdapter(mContext, videoListModuleArrayList);
                                recycler_view_video.setAdapter(videoAdapter);
                                videoAdapter.notifyDataSetChanged();

                                if (videoListModuleArrayList.size() != 0) {
                                    recycler_view_video.setVisibility(View.VISIBLE);
                                    iv_empty_list.setVisibility(View.GONE);
                                } else {
                                    recycler_view_video.setVisibility(View.GONE);
                                    iv_empty_list.setVisibility(View.VISIBLE);
                                }

//                                Toasty.success(mContext, "Video List Successfully !!", Toast.LENGTH_SHORT,true).show();

                            }
                        }
                    } else {
                        Toasty.info(mContext, "Service Unavailable !!", Toast.LENGTH_SHORT,true).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SuccessModule> call, Throwable t) {
                pd.dismiss();
                Toasty.error(mContext, "" + t.getMessage(), Toast.LENGTH_SHORT,true).show();
            }
        });*/
    }
}
