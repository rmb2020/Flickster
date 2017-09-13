package com.example.nb0.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nb0.flickster.R;
import com.example.nb0.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.R.attr.type;
import static android.media.CamcorderProfile.get;
import static android.os.Build.VERSION_CODES.M;
import static com.example.nb0.flickster.R.id.tvOverview;
import static com.example.nb0.flickster.R.id.tvTitle;
import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by nb0 on 9/1/2017.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public static final int MAX_LAYOUT_COUNT = 2;

    public static final int LAYOUT_1 = 0;
    public static final int LAYOUT_2 = 1;
    Movie movieList;


    // View lookup cache for viewadapter
    private static class ViewHolder {
        ImageView movieImage;
        TextView movieTitle;
        TextView movieOverview;
    }



    int popularity;


    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public int getItemViewType(int position) {
        movieList = getItem(position);


        if (movieList.getVoteAvg()> 5) {
        //if (popularity == 0)  {
            return LAYOUT_1;
        } else {
            return LAYOUT_2;
        }

    }

    @Override
    public int getViewTypeCount() {
        return MAX_LAYOUT_COUNT;  // return number of layouts
    }

    //@NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        //get data
        Movie movie = getItem(position);
        String strPosterPath;
        //= movie.getPosterPath();
        Integer txtLayout = 0;




        if (movie.getVoteAvg()>7){
            popularity = 0;

        } else {
            popularity = 1;

        }


        //chk if current view reused
        ViewHolder holder; // view lookup cache stored in a tag
        //ViewHolder viewHolder1;
        //ViewHolder1 viewHolder1;

        int type = getItemViewType(position);
        log.d ("type ", "is " + type + ".");

        if ( type == LAYOUT_2) {
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                log.d("type", "Layout-2");

                convertView = inflater.inflate(R.layout.item_movie,parent,false);
                holder.movieImage = (ImageView)
                        convertView.findViewById(R.id.ivMovieImage);
                holder.movieTitle = (TextView) convertView.findViewById(tvTitle);
                holder.movieOverview = (TextView) convertView.findViewById(tvOverview);

                strPosterPath = movie.getPosterPath();
                txtLayout = LAYOUT_2;
                convertView.setTag(holder);

            } else {
                //ViewHolder holder = (ViewHolder) convertView.getTag();
                holder = (ViewHolder) convertView.getTag();
                strPosterPath = movie.getPosterPath();
                log.d("DEBUG1", "else");


            }
            holder.movieTitle.setText(movie.getOriginalTitle());
            holder.movieOverview.setText(movie.getOverview());

        } else {

            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                log.d("type", "Layout-1");
                //log.d("DEBUG", movie.getVoteAvg().toString());
                convertView = inflater.inflate(R.layout.item_movie_popular,parent,false);
                //ViewHolder holder = new ViewHolder();
                holder.movieImage = (ImageView)
                        convertView.findViewById(R.id.ivMovieImage);
                txtLayout = LAYOUT_1;

                strPosterPath = movie.getBackDropPath();
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
                strPosterPath = movie.getBackDropPath();
                log.d("DEBUG2", "else-layout-1");
            }

        }




        //get image view
        //ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        //clear out image from convertview
        //ivImage.setImageResource(0);

        //TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        //TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        //Populate data
        /*tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());*/

        //Picasso.with(getContext()).load(movie.getPosterPath()).into(ivImage);

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(strPosterPath)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.movieImage);
        } else {
            Picasso.with(getContext()).load(movie.getBackDropPath()).resize(1000, 0)
                    .placeholder(R.drawable.placeholder)
                    //.error(R.drawable.placeholder_error)  Need to add error if needed
                    .into(holder.movieImage);
        }


        /*Picasso.with(getContext()).load(strPosterPath)
                .placeholder(R.drawable.placeholder)
                .into(holder.movieImage);*/


        //return view
        return convertView;

    }



    private void renderImg(ViewHolder holder, String strUrl) {
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(strUrl)
                    .placeholder(R.drawable.placeholder)
                    //.error( R.drawable.placeholder_error )
                    .into(holder.movieImage);
        } else {
            Picasso.with(getContext()).load(strUrl).resize(1000, 0)
                    .placeholder(R.drawable.placeholder)
                    //.error(R.drawable.placeholder_error)  Need to add error if needed
                    .into(holder.movieImage);
        }
    }
}
