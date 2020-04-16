package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.medicare.Fragments.AlarmFragment;
import com.example.medicare.Fragments.DashboardFragment;
import com.example.medicare.Fragments.DiseaseFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    DiseaseFragment diseaseFragment;
    AlarmFragment alarmFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        diseaseFragment = new DiseaseFragment();
        alarmFragment = new AlarmFragment();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setCheckedItem(R.id.home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home :
                        setFragment(new DashboardFragment());
                        return true;
                    case R.id.tips :
                        getSupportActionBar().setTitle("Health Tips");
                        setFragment(diseaseFragment);
                        return true;
                    case R.id.alarm :
                        getSupportActionBar().setTitle("Medicine Alarm");
                        setFragment(alarmFragment);
                        return true;
                    case R.id.share :
                        Intent intentInvite = new Intent(Intent.ACTION_SEND);
                        intentInvite.setType("text/plain");
                        String body = "APP Link";
                        String subject =" Share Medicare to your loved ones ";
                        intentInvite.putExtra(Intent.EXTRA_SUBJECT,subject);
                        intentInvite.putExtra(Intent.EXTRA_TEXT,body);
                        startActivity(Intent.createChooser(intentInvite,"Share Using"));
                        return true;
                    default:
                        addFragment(new DashboardFragment());
                        return false;
                }
            }
        });

        if(navigationView.isSelected() == false){
            addFragment(new DashboardFragment());
        }


    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
