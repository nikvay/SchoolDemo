package com.nikvay.schooldemo.ui.fragment;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nikvay.schooldemo.R;
import com.nikvay.schooldemo.domain.module.SuccessModule;
import com.nikvay.schooldemo.domain.network.ApiClient;
import com.nikvay.schooldemo.domain.network.ApiInterface;
import com.nikvay.schooldemo.shared_pref.SharedPreference;
import com.nikvay.schooldemo.ui.adapter.FeesDueAdapter;
import com.nikvay.schooldemo.ui.adapter.FeesPaymentAdapter;
import com.nikvay.schooldemo.ui.adapter.FeesPaymentStructureAdapter;
import com.nikvay.schooldemo.ui.module.FeeDuesModule;
import com.nikvay.schooldemo.ui.module.FeeStructureModule;
import com.nikvay.schooldemo.ui.module.FeesModel;
import com.nikvay.schooldemo.ui.module.FeesPaymentDetailsModule;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class FeesFragment extends Fragment {

    Context mContext;
    private ApiInterface apiInterface;
TextView tvstudent_Name,tvTtotal_fees,tvpending_fees,tvPaid_fees,tv_year,standard;
  //  private RecyclerView recycler_view_fess_details;
  String uId;
    ArrayList<FeesPaymentDetailsModule> feesPaymentDetailsModules_arrayList =new ArrayList<>();
    FeesPaymentAdapter feesPaymentAdapter;
    ArrayList<FeesModel>feesModels=new ArrayList<>();
    ArrayList<FeeDuesModule>  feeDuesModules_arrayList=new ArrayList<>();
    FeesDueAdapter feesDueAdapter;
    String student_id,total_fees,pending_fees,paid_fees,due_date,year;
    ArrayList<FeeStructureModule> feeStructureModules_arrayList=new ArrayList<>();
    FeesPaymentStructureAdapter feesPaymentStructureAdapter;
    private String TAG = getClass().getSimpleName(),user_name,classDiv,isSelectUser;
    SharedPreferences sharedpreferences;
    public static String MyPREFERENCES = "Fast Connect";

    public FeesFragment()
    {
        // Required empty public constructor
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fees, container, false);
        mContext = getActivity();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedpreferences = mContext.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        isSelectUser = sharedpreferences.getString(SharedPreference.U_TYPE, "");
        uId = SharedPreference.getUserID(mContext);

        find_All_IDs(view);

            feesList();
        //recycler_view_fess_details.setLayoutManager(new LinearLayoutManager(mContext));
        //recycler_view_payment_details.setLayoutManager(new LinearLayoutManager(mContext));
        //recycler_view_due.setLayoutManager(new LinearLayoutManager(mContext));

        feesPaymentAdapter=new FeesPaymentAdapter(mContext, feesPaymentDetailsModules_arrayList);
        /*recycler_view_payment_details.setAdapter(feesPaymentAdapter);
        recycler_view_payment_details.hasFixedSize();
*/

        feesDueAdapter=new FeesDueAdapter(mContext,feeDuesModules_arrayList);
        //recycler_view_due.setAdapter(feesDueAdapter);
       // recycler_view_due.hasFixedSize();


        feesPaymentStructureAdapter=new FeesPaymentStructureAdapter(mContext,feeStructureModules_arrayList);
        //recycler_view_fess_details.setAdapter(feesPaymentStructureAdapter);
        //recycler_view_fess_details.hasFixedSize();

        //set Data to Module
        for(int i=0;i<=2;i++)
        {
            feesPaymentDetailsModules_arrayList.add(new FeesPaymentDetailsModule("paid fees(Rs)","2000"));
        }


        for(int i=0;i<=3;i++)
        {
            feeDuesModules_arrayList.add(new FeeDuesModule("03-04-2019","3000"));
        }

        for(int i=0;i<=4;i++)
        {
            feeStructureModules_arrayList.add(new FeeStructureModule("Admission Fees","3000","20-01-2019"));
        }

        //Fees payment Adapter Call



        return view;

    }//========== End onCreate () ==============

    private void feesList()
    {
        Call<SuccessModule>call=apiInterface.feesCall(uId);
        call.enqueue(new Callback<SuccessModule>()
        {
            @Override
            public void onResponse(Call<SuccessModule> call, Response<SuccessModule> response)
            {
                String str_response=new Gson().toJson(response.body());
                Log.e("" + TAG, "Response >>>>" + str_response);

try
{
if (response.isSuccessful())
{
SuccessModule successModule=response.body();
FeesModel feesModel;
if (successModule!=null)
{
String msg=successModule.getMsg(),error_code=successModule.getError_code();

    feesModels=successModule.getFeesModels();
    user_name = sharedpreferences.getString(SharedPreference.USER_FULL_NAME, "");
    classDiv = sharedpreferences.getString(SharedPreference.CLASS_DIV, "");
    Toast.makeText(getContext(), isSelectUser+"", Toast.LENGTH_SHORT).show();
    year=successModule.getAcademic_year();
    student_id=successModule.getId();
    total_fees=successModule.getTotal_fees();
    pending_fees=successModule.getPending_fees();
    paid_fees=successModule.getPaid_fees();
    tv_year.setText("Year                       "+year);
    tvPaid_fees.setText("Paid fees:                             "+paid_fees);
    tvpending_fees.setText("Pending Fees:                       "+pending_fees);
    tvTtotal_fees.setText("Total Fees:                        "+total_fees);
    tvstudent_Name.setText(""+user_name);
    if (isSelectUser.equals("3"))
    {
        standard.setText(classDiv);
    }

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

    private void find_All_IDs(View view) {
        standard=view.findViewById(R.id.standard);
        tv_year=view.findViewById(R.id.year);
        tvPaid_fees=view.findViewById(R.id.paid_fees);
        tvpending_fees=view.findViewById(R.id.pending_fees);
        tvTtotal_fees=view.findViewById(R.id.total_fees);
        tvstudent_Name=view.findViewById(R.id.student_name);
  //      recycler_view_due=view.findViewById(R.id.recycler_view_due);
       // recycler_view_fess_details=view.findViewById(R.id.recycler_view_fess_details);
      //  recycler_view_payment_details=view.findViewById(R.id.recycler_view_payment_details);
    }
}
