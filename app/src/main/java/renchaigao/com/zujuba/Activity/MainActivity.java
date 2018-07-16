package renchaigao.com.zujuba.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import renchaigao.com.zujuba.R;
import renchaigao.com.zujuba.util.BottomNavigationViewHelper;
import renchaigao.com.zujuba.widgets.CustomViewPager;

public class MainActivity extends AppCompatActivity {

    protected static String TAG = "MainActivity";
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private CustomViewPager customViewPager;

    public void loadImage(View view) {
//        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
//        Glide.with(this).load(url).into(testView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initBottomNavigationView();
//        mTextMessage = (TextView) findViewById(R.id.message);
//        testView = findViewById(R.id.test_image);
    }

    private void initToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.nav_call);
        }

    }

    private void initNavigationView() {

        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initView() {
//        init navigationView
        initToolBar();
        drawerLayout = findViewById(R.id.main_drawerLayout);
        navigationView = findViewById(R.id.main_navigationView);
        customViewPager = findViewById(R.id.main_customView);
        initNavigationView();
//        init bootom
        bottomNavigationView = findViewById(R.id.main_bootomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

    }

    private void initBottomNavigationView() {
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_message:
                        Log.e(TAG, "this is : navigation_message");
                        return true;
                    case R.id.navigation_team:
                        Log.e(TAG, "this is : navigation_team");
                        return true;
                    case R.id.navigation_dating:
                        Log.e(TAG, "this is : navigation_dating");
                        return true;
                    case R.id.navigation_game:
                        Log.e(TAG, "this is : navigation_game");
                        return true;
                    case R.id.navigation_shop:
                        Log.e(TAG, "this is : navigation_shop");
                        return true;
                }
                return true;
            }
        };
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    static class CustomViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();

        public CustomViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }

}
