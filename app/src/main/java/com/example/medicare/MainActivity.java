package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.medicare.Adapters.SlideAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager mview;
    private LinearLayout mlayout;
    private SlideAdapter madapter;
    TextView[] mDots;
    private int current;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mview = findViewById(R.id.viewpager);
        mlayout = findViewById(R.id.linearlayout);
        sp = getSharedPreferences("checker", MODE_PRIVATE);

        if(sp.getBoolean("isChecked",false)){
            Intent intent= new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();

        }

        madapter = new SlideAdapter(this);
        mview.setAdapter(madapter);
        addDotsIndicator(0);

        mview.addOnPageChangeListener(viewListener);


    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mlayout.removeAllViews();

        for(int i=0 ; i<mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mlayout.addView(mDots[i]);
        }

        if(mDots.length > 0){

            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));

        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            current = position;
            if(position == 0){



            } else if(position == mDots.length - 1){

                sp.edit().putBoolean("isChecked",true).apply();
                Intent intent= new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();

            } else {

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };




}

