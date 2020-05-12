package com.carson.studioghiblimovie;

import android.content.Context;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class HomeFragment extends Fragment  {
    public interface SelectYearListener {
        void yearSelected(MovieRepository yearSelected);
    }

    private Spinner yearDropdown; //Found spinner solution here: https://stackoverflow.com/questions/13377361/how-to-create-a-drop-down-list
    private Button showMovieListButton;

    private static final String TAG = "Add Home Fragment";

    private SelectYearListener mSelectYearListener;

    public static HomeFragment newInstance() {
        return new HomeFragment();

    }



    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        Log.d(TAG, "onAttach");

        if (context instanceof SelectYearListener){
            mSelectYearListener = (SelectYearListener) context;
            Log.d(TAG,"Listener set");
        } else {
            throw new RuntimeException(context.toString() + " must implement SelectYearListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Log.d(TAG, "onCreatView");

        yearDropdown = (Spinner) v.findViewById(R.id.year_dropdown);
        showMovieListButton = v.findViewById(R.id.show_movie_list_button);
        //found spinner solution https://developer.android.com/guide/topics/ui/controls/spinner#java
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.years_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearDropdown.setAdapter(adapter);
        String yearSelection = String.valueOf(yearDropdown.getSelectedItem());


        showMovieListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSelectYearListener.yearSelected(yearSelection);
            }
        });

        return v;
    }

}
