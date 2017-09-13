package com.example.nb0.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nb0 on 9/1/2017.
 */

public class Movie {

    String posterPath;
    String originalTitle;
    String overview;


    String backDropPath;
    Double voteAvg;
    Double popularity;

    public Double getPopularity() {
        return popularity;
    }

    public Double getVoteAvg() {
        return voteAvg;
    }


    public String getBackDropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backDropPath);
    }

    public String getPosterPath() {
        //return posterPath;
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }



    public Movie(JSONObject jsonObject) throws JSONException {

        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backDropPath = jsonObject.getString("backdrop_path");
        this.voteAvg = jsonObject.getDouble("vote_average");
        this.popularity = jsonObject.getDouble("popularity");


    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList();

        for (int x = 0; x<array.length(); x++) {
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return results;
    }
}
