package com.example.hctpfpoly.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hctpfpoly.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ph13373_Fragment_collection extends Fragment {



    private TabLayout tabl;
    private ViewPager2 viewPager;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_ph13373__collection, container, false);


        tabl = (TabLayout) view.findViewById(R.id.tabl);
        viewPager = (ViewPager2) view.findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getActivity());
        viewPager.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabl,viewPager,(tab, position) -> {
            if (position==0){
                tab.setText("Lớp");
            }else {
                tab.setText("QL Sinh Viên");
            }
        });
        tabLayoutMediator.attach();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull  View view,  Bundle savedInstanceState) {

    }
    public class ViewPagerAdapter extends FragmentStateAdapter {
        int count = 2;

        public ViewPagerAdapter(@NonNull FragmentActivity fragment) {
            super(fragment);
        }

        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new ph13373_Fragment_lop();
                default:
                    return new ph13373_Fragment_sv();
            }
        }


        @Override
        public int getItemCount() {
            return count;
        }
    }
}