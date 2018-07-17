package renchaigao.com.zujuba.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import java.util.Map;

import renchaigao.com.zujuba.Fragment.GameFragment;
import renchaigao.com.zujuba.Fragment.HallFragment;
import renchaigao.com.zujuba.Fragment.MessageFragment;
import renchaigao.com.zujuba.Fragment.ShopFragment;
import renchaigao.com.zujuba.Fragment.TeamFragment;
import renchaigao.com.zujuba.R;
import renchaigao.com.zujuba.util.BottomNavigationViewHelper;
import renchaigao.com.zujuba.widgets.CustomViewPager;

public class MainActivity extends AppCompatActivity {

    protected static String TAG = "MainActivity";
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private CustomViewPager customViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolBar();
        initView();
        setViewPager();
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
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
        drawerLayout = findViewById(R.id.main_drawerLayout);
        navigationView = findViewById(R.id.main_navigationView);
        customViewPager = findViewById(R.id.main_customView);
        initNavigationView();
//        init bootom
        bottomNavigationView = findViewById(R.id.main_bootomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

    }

    private void setViewPager() {
        final CustomViewPager customViewPager = findViewById(R.id.main_customView);
        final HallFragment hallFragment = new HallFragment();
        final GameFragment gameFragment = new GameFragment();
        final MessageFragment messageFragment = new MessageFragment();
        final ShopFragment shopFragment = new ShopFragment();
        final TeamFragment teamFragment = new TeamFragment();
        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        customViewPagerAdapter.addFragment(messageFragment);
        customViewPagerAdapter.addFragment(teamFragment);
        customViewPagerAdapter.addFragment(hallFragment);
        customViewPagerAdapter.addFragment(gameFragment);
        customViewPagerAdapter.addFragment(shopFragment);
        customViewPager.setAdapter(customViewPagerAdapter);
        customViewPager.setCurrentItem(2);
        bottomNavigationView.setSelectedItemId(R.id.navigation_dating);
        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_message);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_team);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_dating);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_game);
                        break;
                    case 4:
                        bottomNavigationView.setSelectedItemId(R.id.navigation_shop);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initBottomNavigationView(customViewPager);
    }

    private void initBottomNavigationView(final CustomViewPager customViewPager) {
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_message:
                        customViewPager.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customViewPager.setCurrentItem(0);
                            }
                        });
                        Log.e(TAG, "this is : navigation_message");
                        return true;
                    case R.id.navigation_team:
                        customViewPager.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customViewPager.setCurrentItem(1);
                            }
                        });
                        Log.e(TAG, "this is : navigation_team");
                        return true;
                    case R.id.navigation_dating:
                        customViewPager.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customViewPager.setCurrentItem(2);
                            }
                        });
                        Log.e(TAG, "this is : navigation_dating");
                        return true;
                    case R.id.navigation_game:
                        customViewPager.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customViewPager.setCurrentItem(3);
                            }
                        });
                        Log.e(TAG, "this is : navigation_game");
                        return true;
                    case R.id.navigation_shop:
                        customViewPager.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customViewPager.setCurrentItem(4);
                            }
                        });
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
