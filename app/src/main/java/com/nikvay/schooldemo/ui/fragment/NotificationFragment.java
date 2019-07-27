package com.nikvay.schooldemo.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nikvay.schooldemo.R;
import com.nikvay.schooldemo.shared_pref.SharedPreference;
import com.nikvay.schooldemo.ui.activity.NotificationAddActivity;
import com.nikvay.schooldemo.utils.ZoomOutTransformation;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class NotificationFragment extends Fragment {

    Context mContext;
    TabLayout tabLayout;
    ViewPager viewPager;
    Button btn_notification;

    String isSelectUser,user;
    private SharedPreferences sharedpreferences;
    public static String MyPREFERENCES = "Fast Connect";

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        mContext = getActivity();

        sharedpreferences = mContext.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        find_All_IDs(view);
        if (user.equals("student"))
        {
            btn_notification.setVisibility(View.GONE);
        }
        isSelectUser = sharedpreferences.getString(SharedPreference.U_TYPE, "");
        if (isSelectUser.equalsIgnoreCase("3")) {
            btn_notification.setVisibility(View.GONE);
        }

        ZoomOutTransformation zoomOutTransformation = new ZoomOutTransformation();

        setupViewPager(viewPager);  // ==== method ====
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        viewPager.setPageTransformer(true, zoomOutTransformation); // add animation to viewpager

        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NotificationAddActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }//========== End onCreate () ==============

    private void find_All_IDs(View view)
    {
        SharedPreferences sharedUser=getContext().getSharedPreferences("sharedUser",MODE_PRIVATE);
        user=sharedUser.getString("user"," ");
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        btn_notification = view.findViewById(R.id.btn_notification);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new NotificationTypeFragment(), "Public");
        adapter.addFragment(new NotificationPrivateFragment(), "Private");
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
