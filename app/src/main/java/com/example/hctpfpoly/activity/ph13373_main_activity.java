package com.example.hctpfpoly.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.hctpfpoly.R;
import com.example.hctpfpoly.fragment.ph13373_FragmentNews;
import com.example.hctpfpoly.fragment.ph13373_Fragment_LoginFB;
import com.example.hctpfpoly.fragment.ph13373_Fragment_collection;
import com.example.hctpfpoly.fragment.ph13373_MapsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ph13373_main_activity extends AppCompatActivity {

    private FrameLayout frl1;
    private BottomNavigationView navBottom;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph13373_main);


        frl1 = (FrameLayout) findViewById(R.id.frl_1);
        navBottom = (BottomNavigationView) findViewById(R.id.nav_bottom);
         fm=getSupportFragmentManager();




        fm.beginTransaction().add(R.id.frl_1,new ph13373_FragmentNews()).commit();
        navBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_course:

                        loadFragment(new ph13373_Fragment_collection());
                        return true;
                    case R.id.nav_news:
                        loadFragment(new ph13373_FragmentNews());
                        return true;
                    case R.id.nav_map:
                        loadFragment(new ph13373_MapsFragment());
                        return true;
                    case R.id.nav_fb:
                        loadFragment(new ph13373_Fragment_LoginFB());
                        return true;
                }
                return false;
            }
        });




    }
    public void loadFragment(Fragment fragment){
        fm.beginTransaction().replace(R.id.frl_1,fragment).commit();
    }
}