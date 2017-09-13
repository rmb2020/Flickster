package com.example.nb0.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by nb0 on 9/11/2017.
 */

public class DetailsActivity extends AppCompatActivity {
    TextView movieTitle;
    TextView movieSynopsis;
    TextView moviePopularity;
    RatingBar movieRating;

    String mvTitle;
    String mvSynopsis;
    Double mvPopularity;
    Double mvRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        movieTitle = (TextView) findViewById(R.id.txtTitle);
        movieSynopsis = (TextView) findViewById(R.id.tvSynopsis);
        moviePopularity = (TextView) findViewById(R.id.tvPopularity);
        movieRating = (RatingBar) findViewById(R.id.rbMovieRating);

        Intent i = getIntent();
        mvTitle = i.getStringExtra("movie_title");
        mvSynopsis = i.getStringExtra("movie_synopsis");
        mvPopularity = i.getDoubleExtra("movie_popularity", -1);
        mvRating = i.getDoubleExtra("movie_rating", -1);


        movieTitle.setText(mvTitle);
        movieSynopsis.setText(mvSynopsis);
        //movieSynopsis.setText("some text goes here");
        moviePopularity.setText("Popularity " + mvPopularity.toString());
        movieRating.setRating(mvRating.floatValue());

    }
}
