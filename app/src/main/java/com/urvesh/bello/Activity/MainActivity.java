package com.urvesh.bello.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.urvesh.bello.Fragment.WeekDay;
import com.urvesh.bello.R;
import com.urvesh.bello.helper.ParseUtils;
import com.urvesh.bello.helper.PrefManager;
import com.urvesh.bello.model.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    View baseView;
    TabLayout tabLayout;
    DrawerLayout drawerLayout;
    ViewPager viewPager;
    TextView txtUsername;
    ArrayList<String> dayName = new ArrayList<>();
    NavigationView navigationView;
    private PrefManager pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        baseView = findViewById(android.R.id.content);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);


        //creating tabs
        addDayNames();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < dayName.size(); i++) {
            adapter.addFrag(new WeekDay(i), dayName.get(i));
        }
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        goToToday();
        viewPager.setOffscreenPageLimit(7);
        int intialPosition = viewPager.getCurrentItem();
        Log.e("IntialPosition", intialPosition + "");


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Coming soon!", Snackbar.LENGTH_SHORT).show();
            }
        });

        View header = navigationView.getHeaderView(0);
        txtUsername = (TextView) header.findViewById(R.id.userEmail);
        pref = new PrefManager(getApplicationContext());
        String email = pref.getEmail();
        if (email != null) {
            ParseUtils.subscribeWithEmail(pref.getEmail(),"ComputerScience");
            /*txtUsername.setText("Hello");*/
            Log.e("Name", email + "");
            txtUsername.setText(email);
        } else {
            Log.e("Error", "Email is null. Not subscribing to parse!");
        }

    }

    public void addDayNames() {
        dayName.add("Monday");
        dayName.add("Tuesday");
        dayName.add("Wednesday");
        dayName.add("Thursday");
        dayName.add("Friday");
        dayName.add("Saturday");
        dayName.add("Sunday");

    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

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

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_today) {
            goToToday();
        }
        if (id == R.id.action_notification) {
            startActivity(new Intent(MainActivity.this, Notification.class));
            return true;
        }
        if (id == R.id.logout) {
            pref.logout();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToToday() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.MONDAY:
                TabLayout.Tab tab = tabLayout.getTabAt(0);
                if (tab != null) {
                    tab.select();
                }
                viewPager.setCurrentItem(0, true);
                break;
            case Calendar.TUESDAY:
                tab = tabLayout.getTabAt(1);
                if (tab != null) {
                    tab.select();
                }
                viewPager.setCurrentItem(1, true);
                break;
            case Calendar.WEDNESDAY:
                tab = tabLayout.getTabAt(2);
                if (tab != null) {
                    tab.select();
                }
                viewPager.setCurrentItem(2, true);
                break;
            case Calendar.THURSDAY:
                tab = tabLayout.getTabAt(3);
                if (tab != null) {
                    tab.select();
                }
                viewPager.setCurrentItem(3, true);

                break;
            case Calendar.FRIDAY:
                tab = tabLayout.getTabAt(4);
                if (tab != null) {
                    tab.select();
                }
                viewPager.setCurrentItem(4, true);
                break;
            case Calendar.SATURDAY:
                tab = tabLayout.getTabAt(5);
                if (tab != null) {
                    tab.select();
                }
                viewPager.setCurrentItem(5, true);
                break;
            case Calendar.SUNDAY:
                tab = tabLayout.getTabAt(6);
                if (tab != null) {
                    tab.select();
                }
                viewPager.setCurrentItem(6, true);
                break;
        }

    }
}
