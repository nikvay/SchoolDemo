package com.nikvay.schooldemo.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nikvay.schooldemo.R;
import com.nikvay.schooldemo.domain.module.ExamListModel;
import com.nikvay.schooldemo.domain.module.ResultExamNameModule;
import com.nikvay.schooldemo.domain.module.SuccessModule;
import com.nikvay.schooldemo.domain.network.ApiClient;
import com.nikvay.schooldemo.domain.network.ApiInterface;
import com.nikvay.schooldemo.shared_pref.SharedPreference;
import com.nikvay.schooldemo.ui.adapter.ExamListAdapter;
import com.nikvay.schooldemo.ui.adapter.GalleryAdapter;
import com.nikvay.schooldemo.ui.adapter.ResultAdapter;
import com.nikvay.schooldemo.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ResultFragment extends Fragment {

    Context mContext;
    ImageView iv_empty_list;
    ArrayList<ExamListModel>examList=new ArrayList<>();
    //======Interface Declaration=========
    String TAG = getClass().getSimpleName();
    ApiInterface apiInterface;
    ProgressDialog pd;
    String uId, isSelectUser;
    SharedPreferences sharedpreferences;
    public static String MyPREFERENCES = "Fast Connect";

    ArrayList<ResultExamNameModule> resultExamNameModuleArrayList = new ArrayList<>();
    ResultAdapter resultAdapter;
    RecyclerView recycler_view_result;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        mContext = getActivity();

        find_All_IDs(view);

        sharedpreferences = mContext.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        isSelectUser = sharedpreferences.getString(SharedPreference.U_TYPE, "");
        uId = SharedPreference.getUserID(mContext);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        recycler_view_result.setLayoutManager(linearLayoutManager);
        recycler_view_result.setHasFixedSize(true);

        if (NetworkUtils.isNetworkAvailable(mContext)) {
            doResultList();
        } else {
            NetworkUtils.isNetworkNotAvailable(mContext);
        }

        return view;
    }//========== End onCreate () ==============


    private void find_All_IDs(View view) {
        recycler_view_result=view.findViewById(R.id.recycler_view_result);
        iv_empty_list=view.findViewById(R.id.iv_empty_list);
    }

    private void doResultList()
    {
        /*pd = new ProgressDialog(mContext);
        pd.setMessage("Loading Please Wait...");
        pd.setCancelable(false);
        pd.show();*/

        Call<SuccessModule>call=apiInterface.getExamList();
        call.enqueue(new Callback<SuccessModule>() {
            @Override
            public void onResponse(Call<SuccessModule> call, Response<SuccessModule> response)
            {

                String str_response=new Gson().toJson(response.body());
                try {
                    if (response.isSuccessful())
                    {
                        SuccessModule successModule=response.body();
                        String msg=null,error_code=null;
                        if (successModule!=null)
                        {
                            msg=successModule.getMsg();
                            error_code=successModule.getError_code();

                            examList=successModule.getExamListModelArrayList();
                            Collections.reverse(examList);
                            ExamListAdapter examListAdapter=new ExamListAdapter(getContext(),examList);
                            recycler_view_result.setAdapter(examListAdapter);
                            examListAdapter.notifyDataSetChanged();


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


       /* Call<SuccessModule> call = apiInterface.resultListCall(uId);

        call.enqueue(new Callback<SuccessModule>() {
            @Override
            public void onResponse(Call<SuccessModule> call, Response<SuccessModule> response) {
                pd.dismiss();
                String str_response = new Gson().toJson(response.body());
                Log.e("" + TAG, "Response >>>>" + str_response);
                try {
                    if (response.isSuccessful()) {
                        SuccessModule successModule = response.body();

                        String message = null, errorCode = null;
                        if (successModule != null) {
                            message = successModule.getMsg();
                            errorCode = successModule.getError_code();

                            if (errorCode.equalsIgnoreCase("1")) {

                                resultExamNameModuleArrayList = successModule.getResultExamNameModuleArrayList();
//                                Collections.reverse(resultExamNameModuleArrayList);

                                resultAdapter = new ResultAdapter(mContext, resultExamNameModuleArrayList);
                                recycler_view_result.setAdapter(resultAdapter);
                                resultAdapter.notifyDataSetChanged();

                                if (resultExamNameModuleArrayList.size() != 0) {
                                    recycler_view_result.setVisibility(View.VISIBLE);
                                    iv_empty_list.setVisibility(View.GONE);
                                } else {
                                    recycler_view_result.setVisibility(View.GONE);
                                    iv_empty_list.setVisibility(View.VISIBLE);
                                }
                              //  Toasty.success(mContext, "Successfully !!", Toast.LENGTH_SHORT,true).show();

                            }
                        }
                    } else
                        {
                        Toasty.warning(mContext, "Service Unavailable !!", Toast.LENGTH_SHORT, true).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SuccessModule> call, Throwable t) {
                pd.dismiss();
                Toasty.error(mContext, "" + t.getMessage(), Toast.LENGTH_SHORT, true).show();
            }
        });*/
    }

}
