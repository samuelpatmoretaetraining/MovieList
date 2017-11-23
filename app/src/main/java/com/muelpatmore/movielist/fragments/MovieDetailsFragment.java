package com.muelpatmore.movielist.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muelpatmore.movielist.R;
import com.muelpatmore.movielist.model.TopMovieListModel;
import com.muelpatmore.movielist.model.movieDetailModels.MovieDetailModel;
import com.muelpatmore.movielist.utils.ConnectionService;
import com.muelpatmore.movielist.utils.Constants;
import com.muelpatmore.movielist.utils.RequestInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends Fragment {

    public static final String TAG = "MovieDetailsFragment";


    private RequestInterface requestInterface;
    private CompositeDisposable comDisposable;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    public void showMovieDetails(int id) {
        pullMovieDetailsFromServer(id);
    }

    private void pullMovieDetailsFromServer(int id) {
        requestInterface = ConnectionService.BackendService();
        comDisposable = new CompositeDisposable();
        comDisposable.add(
                requestInterface.getMovieDetails(id, Constants.API_KEY)
                        .observeOn(AndroidSchedulers.mainThread())
                        // ensure to reference plural Schedulers class
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<MovieDetailModel>() {
                            @Override
                            public void accept(MovieDetailModel movieDetailModel) throws Exception {

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

}
