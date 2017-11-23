package com.muelpatmore.movielist.utils;

import com.muelpatmore.movielist.model.TopMovieListModel;
import com.muelpatmore.movielist.model.movieDetailModels.MovieDetailModel;
import com.muelpatmore.movielist.utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestInterface {



    @GET(Constants.API_TOP_RATED_MOVIE_LIST)
    Observable<TopMovieListModel> getMovies(@Query("api_key") String API_KEY);

    @GET(Constants.API_TOP_RATED_MOVIE_DETAIL)
    Observable<MovieDetailModel> getMovieDetails(@Path("id") int id, @Query("api_key") String API_KEY);

}
