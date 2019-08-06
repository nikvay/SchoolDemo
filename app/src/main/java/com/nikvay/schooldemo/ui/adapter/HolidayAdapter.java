package com.nikvay.schooldemo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.nikvay.schooldemo.R;
import com.nikvay.schooldemo.domain.module.HolidayListModule;
import com.nikvay.schooldemo.domain.module.SuccessModule;
import com.nikvay.schooldemo.domain.network.BaseApi;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Callback;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.HolidayViewHolder>
{
    Context context;
    String pdf_name,url,myurl;

    ArrayList<HolidayListModule> holidayListModuleArrayList=new ArrayList<>();
    public HolidayAdapter(Context context, ArrayList<HolidayListModule> holidayListModuleArrayList)
    {

        this.context=context;
        this.holidayListModuleArrayList=holidayListModuleArrayList;
    }

    @NonNull
    @Override
    public HolidayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.holiday_layout,viewGroup,false);
        return new HolidayAdapter.HolidayViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HolidayViewHolder holidayViewHolder, int i)
    {
        HolidayListModule holidayListModule=holidayListModuleArrayList.get(i);





      /*  holidayViewHolder.webView.getSettings().setJavaScriptEnabled(true);
        holidayViewHolder.webView.getSettings().setBuiltInZoomControls(true);
        holidayViewHolder.webView.setVerticalScrollBarEnabled(true);
        holidayViewHolder.webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        holidayViewHolder.webView.setInitialScale(100);*/
        pdf_name=holidayListModule.getPdf_name();
        url=BaseApi.BASE_URL+"media/acadamic_calender/"+pdf_name;

        url="http://nikvay.com/demo/fastconnect/media//acadamic_calender//"+pdf_name;
        holidayViewHolder.webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        holidayViewHolder.textView.setText(holidayListModule.getTitle());
    }

    @Override
    public int getItemCount() {
        return holidayListModuleArrayList.size();
    }

    public class HolidayViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView;
        WebView webView;
        public HolidayViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textView=itemView.findViewById(R.id.holiday_title);
            webView=itemView.findViewById(R.id.webview);
        }
    }
}
