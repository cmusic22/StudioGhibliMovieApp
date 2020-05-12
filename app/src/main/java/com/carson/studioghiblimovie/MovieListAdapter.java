package com.carson.studioghiblimovie;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private static final String TAG = "MOVIE LIST ADAPTER";
    private final String baseURL = "https://ghibliapi.herokuapp.com/";

    interface OnMovieListItemClickListener {
        void onMovieListItemClick(int position);
    }

    public JSONObject mMovieList;

    private OnMovieListItemClickListener listener;


    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        TextView date;

        private OnMovieListItemClickListener listener;

        public MovieViewHolder(@NonNull View movieView, OnMovieListItemClickListener listener) {
            super(movieView);

            this.listener = listener;

            ConstraintLayout layout = (ConstraintLayout) movieView;
            title = layout.findViewById(R.id.movieTitle);
            date = layout.findViewById(R.id.releaseDate);

            movieView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {listener.onMovieListItemClick(getAdapterPosition());}
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(layout, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

                            mMovieList movie = mMovieList.get(position);
                            holder.title.setText(movie.getString());
                            holder.date.setText(movie.getText());


    }

    @Override
    public int getItemCount() {return mMovieList.size();}

}
