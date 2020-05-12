package com.carson.studioghiblimovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HomeFragment.SelectYearListener,
        MovieListFragment.ListItemSelectedListener {

    private static final String TAG = "MAIN ACTIVITY";
    private static final String BUNDLE_KEY_MOVIE_LIST = "MOVIE ARRAY LIST";

    private static final String TAG_HOME_FRAG = "SELECT YEAR FRAGMENT";
    private static final String TAG_MOVIE_LIST_FRAG = "MOVIE LIST FRAGMENT";
    private static final String TAG_MOVIE_DETAIL_FRAG = "MOVIE DETAIL FRAGMENT";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment homeFragment = HomeFragment.newInstance();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.home_fragment, homeFragment, TAG_HOME_FRAG);

        ft.commit();
    }
    @Override
    public void yearSelected(MovieRepository yearSelected) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MovieListFragment movieListFragment = MovieListFragment.newInstance(yearSelected);
        ft.replace(android.R.id.content, movieListFragment, TAG_MOVIE_LIST_FRAG);
        ft.addToBackStack(TAG_HOME_FRAG);
        ft.commit();

    }

    @Override
    public void movieSelected(MovieRepository movieSelected) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(movieSelected);
        ft.replace(android.R.id.content, movieDetailFragment, TAG_MOVIE_DETAIL_FRAG);
        ft.addToBackStack(TAG_MOVIE_LIST_FRAG);
        ft.commit();
    }
}