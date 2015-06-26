package com.pictorial.View;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;
import com.pictorial.R;
import com.pictorial.View.Fragment.NewsFragment;

/**
 * Created by zhangbing1 on 2015/6/26.
 */
public class IndexActivity extends ActionBarActivity {


    Toolbar toolbar;
    PagerSlidingTabStrip tabs;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager pager = (ViewPager) this.findViewById(R.id.pager);
        tabs = (PagerSlidingTabStrip) this.findViewById(R.id.tabs);
        changeColor(getResources().getColor(R.color.green));
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);

    }

    private void changeColor(int newColor) {
        tabs.setBackgroundColor(newColor);
        Drawable colorDrawable = new ColorDrawable(newColor);
        Drawable bottomDrawable = new ColorDrawable(getResources().getColor(android.R.color.transparent));
        LayerDrawable ld = new LayerDrawable(new Drawable[]{colorDrawable, bottomDrawable});
        getSupportActionBar().setBackgroundDrawable(ld);
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"Categories", "Home", "Top Paid", "Top Free"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            return NewsFragment.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
