package com.carson.studioghiblimovie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import com.carson.studioghiblimovie.MovieRepository;

import java.util.ArrayList;


public class MovieListFragment extends Fragment implements MovieListAdapter.OnMovieListItemClickListener {

    public interface ListItemSelectedListener {
        void movieSelected(MovieRepository selected);
    }

    private ListItemSelectedListener mItemSelectedListener;

    private static final String TAG = "MOVIE LIST FRAGMENT";
    private static final String ARGS_MOVIE_LIST = "movie list arguments";
    MovieRepository movieRepository = new MovieRepository();


    private RecyclerView mListView;
    private MovieListAdapter mMovieListAdapter;
    private JSONArray mMovieList;

    public static MovieListFragment newInstance(MovieRepository movieItems){
        final Bundle args = new Bundle();
        args.putParcelableArrayList(ARGS_MOVIE_LIST, movieItems);
        final MovieListFragment fragment = new MovieListFragment();
        fragment.setAllowReturnTransitionOverlap(args);
        return fragment;
    }

    private void setAllowReturnTransitionOverlap(Bundle args) {
    }

    @Override
    public void onAttach(@NonNull Context context){
        Log.d(TAG, "onAttach");

        super.onAttach(context);

        if (context instanceof ListItemSelectedListener){
            mItemSelectedListener = (ListItemSelectedListener) context;
            Log.d(TAG, "On attach configured listener " + mItemSelectedListener);
        } else {
            throw new RuntimeException(context.toString() + " must implement ListItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.fragment_movie_list, container, false);

        mListView = v.findViewById(R.id.movieRecyclerView);
        mListView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mListView.setLayoutManager(layoutManager);



        if (getArguments() != null){
            mMovieList = movieRepository.getMovieListByYear(ARGS_MOVIE_LIST);
        }

        mMovieListAdapter = new MovieListAdapter(mMovieList, this);
        mListView.setAdapter(mMovieListAdapter);
        Log.d(TAG, "onCreatView, ArrayList " + mMovieList);
        return v;
    }

    @Override
    public void onMovieListItemClick(int itemPosition) {
        MovieRepository movie = mMovieList.get(itemPosition);
        mItemSelectedListener.movieSelected(movie);
    }

}


