package renchaigao.com.zujuba.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import renchaigao.com.zujuba.util.Permission;
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
        SharedPreferences pref = getSharedPreferences("userData",MODE_PRIVATE);

        Log.e(TAG,pref.getString("token","fail find"));

//        Permission.selfPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION, getApplicationContext())){
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
//                    1);
//                    /*Constant.LOCATION_STATE 为自己定义的一个常量，为权限弹窗回调时使用*/
//        }


        drawerLayout = findViewById(R.id.main_drawerLayout);
        customViewPager = findViewById(R.id.main_customView);
        bottomNavigationView = findViewById(R.id.main_bootomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        setNavigationView();
        setToolBar();
        setViewPager();
        setUpDrawer();
    }
    private LinearLayout nva_1;
    private void setNavigationView(){
        navigationView = findViewById(R.id.main_navigationView);
//        Menu  menu =navigationView.getMenu();
//        MenuItem menuItem = menu.findItem(R.id.nav_person);
//        View actionView = menuItem.getActionView();
//        nva_1 = (LinearLayout)actionView;
        nva_1=(LinearLayout) navigationView.getMenu().findItem(R.id.nav_person).getActionView();
        TextView textView= (TextView) nva_1.findViewById(R.id.menu_use_text);
        textView.setText("1");
    }

    private void updateSystemData(){
        updateUserData();
        updateTeamData();
        updateBusinessData();
        updateGameData();
    }
    private void updateUserData(){

    }
    private void updateTeamData(){

    }
    private void updateBusinessData(){

    }
    private void updateGameData(){

    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void setUpDrawer() {
//        navigationView.setCheckedItem(R.id.nav_call);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                drawerLayout.closeDrawers();
//                return true;
//            }
//        });
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

//        hallFragment.reloadAdapter();
    }

    private void initBottomNavigationView(final CustomViewPager customViewPager) {
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_message:
                        customViewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_team:
                        customViewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_dating:
                        customViewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_game:
                        customViewPager.setCurrentItem(3);
                        return true;
                    case R.id.navigation_shop:
                        customViewPager.setCurrentItem(4);
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

    /**
     * 双击返回桌面
     */
    private long time = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - time > 1000)) {
                Toast.makeText(this, "再按一次返回桌面", Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

}
