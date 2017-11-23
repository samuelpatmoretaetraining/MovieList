package com.muelpatmore.movielist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.muelpatmore.movielist.MovieListApp;
import com.muelpatmore.movielist.R;
import com.muelpatmore.movielist.TopMovieListAdapter;
import com.muelpatmore.movielist.comunicationInterfaces.OnMovieSelected;
import com.muelpatmore.movielist.model.TopMovieItemModel;
import com.muelpatmore.movielist.model.TopMovieListModel;
import com.muelpatmore.movielist.utils.ConnectionService;
import com.muelpatmore.movielist.utils.Constants;
import com.muelpatmore.movielist.utils.RequestInterface;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Samuel on 23/11/2017.
 */

public class MovieListFragment extends Fragment {

    public static final String TAG = "MovieListFragment";

    private OnMovieSelected onMovieSelected;
    private RequestInterface requestInterface;
    private CompositeDisposable comDisposable;
    private RecyclerView recyclerView;
    private TopMovieListAdapter recyclerAdapter;

    private ArrayList<TopMovieItemModel> movieList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.top_movie_list_fragment, container, false);

    }

    @Override
    public void onViewCreated(android.view.View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);

        recyclerView = (RecyclerView) view.findViewById(R.id.topMovieListRecycler);

        pullMovieListFromServer();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onMovieSelected = (OnMovieSelected) getActivity();


        // Retrieve data from movie db web service.
        pullMovieListFromServer();
    }

    public void updateRecyclerView() {

        if (recyclerAdapter == null) {
            Log.i(TAG, "number of movies: " + movieList.size());
            recyclerView.setLayoutManager(new LinearLayoutManager((MovieListApp.getApplication())));
            recyclerAdapter = new TopMovieListAdapter(movieList, R.layout.top_movie_card, MovieListApp.getApplication());
            recyclerView.setAdapter(recyclerAdapter);
        } else {
            recyclerAdapter.notifyDataSetChanged();
        }

    }


    /**
     * Create a connection to the moves database and request the top list of movies. Asynchronously
     * listen and store.
     */
    private void pullMovieListFromServer() {
        requestInterface = ConnectionService.BackendService();
        comDisposable = new CompositeDisposable();
        comDisposable.add(
                requestInterface.getMovies(Constants.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                // ensure to reference plural Schedulers class
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<TopMovieListModel>() {
                               @Override
                               public void accept(TopMovieListModel topMovieListModel) throws Exception {
                                   Log.i(TAG, "Success");
                                   Toast.makeText(MovieListApp.getContext(), "Success", Toast.LENGTH_SHORT).show();
                                   movieList = new ArrayList<>(topMovieListModel.getResults());
                                   Log.i(TAG, (movieList.size() + " movies loaded."));
                                   updateRecyclerView();
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }
                ));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(comDisposable != null) {
            comDisposable.dispose();
            comDisposable.clear();
        }
    }




}
