package com.example.auktion;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.auktion.intro.SliderAdapter;

public class AppIntroActivity extends AppCompatActivity {

    private ViewPager mSliderViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private TextView tvNext, tvBack;

    private int mCurrentPage;
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;

            if (position == 0) {
                tvNext.setEnabled(true);
                tvBack.setEnabled(false);
                tvBack.setVisibility(View.INVISIBLE);

            } else {
                tvNext.setEnabled(true);
                tvNext.setOnClickListener(onClickListener());
                tvBack.setEnabled(true);
                tvBack.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_intro);

        mSliderViewPager = findViewById(R.id.viewPager_intro);
        mDotLayout = findViewById(R.id.linear_intro);
        tvNext = findViewById(R.id.tv_next_intro);
        tvBack = findViewById(R.id.tv_back_intro);

        sliderAdapter = new SliderAdapter(this);

        mSliderViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(1);

        mSliderViewPager.addOnPageChangeListener(viewListener);

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSliderViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSliderViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[2];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.black));

            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.bgShape));
        }
    }

    private View.OnClickListener onClickListener() {
        Intent intent = new Intent(AppIntroActivity.this, RegisterOrLoginActivity.class);
        startActivity(intent);
        return null;
    }
}
