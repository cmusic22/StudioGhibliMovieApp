package com.carson.studioghiblimovie;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;


public class MovieDetailFragment extends Fragment {

    private static final String ARG_MOVIE = "movie argument";
    private static final String TAG = "MOVIE DETAIL FRAG";

    private TextView movieTitle;
    private TextView movieDirector;
    private TextView movieProducer;
    private TextView movieReleaseDate;
    private TextView movieDescription;
    private JSONArray mMovie;
    public static MovieDetailFragment newInstance(MovieRepository movie){
        final Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        final MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        if (getArguments() != null){
            MovieRepository mMovie = new MovieRepository().getSingleMovieList(ARG_MOVIE);

            movieTitle = v.findViewById(R.id.movieTitle);
            movieDirector = v.findViewById(R.id.movieDirector);
            movieProducer = v.findViewById(R.id.movieProducer);
            movieReleaseDate = v.findViewById(R.id.movieReleaseDate);
            movieDescription = v.findViewById(R.id.movieDescription);

            setMovieItem(nMovie);
        } else {
            Log.w(TAG, "Did not receive a Movie");
        }
        return v;
    }

    public void setMovieItem(MovieRepository mMovie){
        final MovieRepository movieItem = mMovie;

        movieTitle.setText(mMovie.getText());
        movieDirector.setText(mMovie.getText());
        movieProducer.setText(mMovie.getText());
        movieReleaseDate.setText(mMovie.getText());
        movieDescription.setText(mMovie.getText());
    }

}
