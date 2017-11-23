package com.muelpatmore.movielist.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muelpatmore.movielist.R;
import com.muelpatmore.movielist.comunicationInterfaces.OnMovieSelected;
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

    public static MovieDetailsFragment instance;

    private OnMovieSelected onMovieSelected;
    private RequestInterface requestInterface;
    private CompositeDisposable comDisposable;
    private MovieDetailModel movieDetails;

    private TextView title, overview;

    public MovieDetailsFragment() {
    }

    public static MovieDetailsFragment getInstance() {
        if (instance == null) {
            instance = new MovieDetailsFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = (TextView) view.findViewById(R.id.tvDetailTitle);
        overview = (TextView) view.findViewById(R.id.tvDetailOverview);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onMovieSelected = (OnMovieSelected) getActivity();
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
                                // ToDo update Fragment views with movie details
                                Log.i(TAG, "Success loading details of movie no. "+id);
                                movieDetails = movieDetailModel;
                                updateMovieInformation(movieDetailModel);
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

    private void updateMovieInformation(MovieDetailModel details) {
        title.setText(details.getTitle());
        overview.setText(details.getOverview());
    }

}
