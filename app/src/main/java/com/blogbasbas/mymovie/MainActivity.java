package com.blogbasbas.mymovie;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.blogbasbas.mymovie.adapter.AdapterRecyclerview;
import com.blogbasbas.mymovie.ui_fragment.NowPlayingFragment;
import com.blogbasbas.mymovie.ui_fragment.UpComingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.vp_mainactivity)
    ViewPager vpMainactivity;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

   /* @BindView(R.id.rv_rcyclerview)
    RecyclerView rvRcyclerview;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        //       rvRcyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

        AdapterRecyclerview adapterRecyclerview = new AdapterRecyclerview(MainActivity.this);

        // rvRcyclerview.setAdapter(adapterRecyclerview);

        //   getMovie();

        tabLayout.addTab(tabLayout.newTab().setText("UpComing Movie").setIcon(R.drawable.ic_menu_camera));
        tabLayout.addTab(tabLayout.newTab().setText("Now Playing Movie").setIcon(R.drawable.ic_menu_camera));

        PagerAdapter adapter = new TabAdapter (getSupportFragmentManager());
        vpMainactivity.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpMainactivity.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vpMainactivity.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));









        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

 /*   private void getMovie() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ResponseMovie> call = service.getUpComingMoviee(CONSTANT.APIKEY, CONSTANT.LANGUAGE);
        call.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {

                List<ResultsItem> dataMovie = response.body().getResults();
                String data1 = response.body().getResults().toString();
                ResponseMovie responseMovie = response.body();
                Log.d(" ", "onResponse data: "+dataMovie);


                AdapterMovie adapterMovie = new AdapterMovie(MainActivity.this,dataMovie);
                rvRcyclerview.setAdapter(adapterMovie);

            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Log.d("", "onFailure: "+t.toString());


            }
        });



    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent myIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(MainActivity.this, SearchMovieActivity.class));
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class TabAdapter extends FragmentPagerAdapter {
        public TabAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
           Fragment fragment = null;
           switch (position){
               case 0 : fragment= new UpComingFragment(); break;
               case 1 : fragment= new NowPlayingFragment(); break;
           }

           return fragment;

        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
