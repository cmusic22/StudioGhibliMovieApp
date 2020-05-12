package com.carson.studioghiblimovie;

import android.os.Parcelable;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieRepository extends ArrayList<Parcelable> {

    private static final String TAG = "MOVIE_REPOSITORY";

    private final String baseURL = "https://ghibliapi.herokuapp.com/";
    public JSONArray mMovieList;
    public JSONArray mMovie;

    public getMovieListByYear(final String yearSelected){
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest movieByYearRequest = new JsonObjectRequest(Request.Method.GET, baseURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mMovieList = response.getJSONArray(yearSelected);

                        } catch (JSONException e) {
                            Log.e(TAG, "Error processing JSON response", e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error fetching data from Studio Gihbli API server", error);
                    }
                }
        );
        queue.add(movieByYearRequest);
    }

    public getSingleMovieList(final String movieSelected){
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest movieByTitleRequest = new JsonObjectRequest(Request.Method.GET, baseURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mMovie = response.getJSONArray(movieSelected);
                        } catch (JSONException e) {
                            Log.e(TAG, "Error processing JSON response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error fetching data from Studio Gihbli API ");
                    }
                }
        );
        queue.add(movieByTitleRequest);
    }




}
