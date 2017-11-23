package com.muelpatmore.movielist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muelpatmore.movielist.model.TopMovieItemModel;

import java.util.ArrayList;

/**
 * Created by Samuel on 23/11/2017.
 */

public class TopMovieListAdapter extends RecyclerView.Adapter<TopMovieListAdapter.MyViewHolder>{
    private ArrayList<TopMovieItemModel> topMovies;
    private int row_movie;
    private Context applicationContext;

    public TopMovieListAdapter(ArrayList<TopMovieItemModel> topMovieList, int row_movie, Context applicationContext) {
        this.topMovies = topMovieList;
        this.row_movie = row_movie;
        this.applicationContext = applicationContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(R.layout.top_movie_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TopMovieItemModel joke = topMovies.get(position);
    }

    @Override
    public int getItemCount() {
        return topMovies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvJoke, tvCatagory, tvID;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
