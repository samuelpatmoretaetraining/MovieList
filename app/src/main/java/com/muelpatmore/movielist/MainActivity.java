package com.muelpatmore.movielist;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.muelpatmore.movielist.comunicationInterfaces.OnMovieSelected;
import com.muelpatmore.movielist.fragments.MovieListFragment;
import com.muelpatmore.movielist.model.TopMovieItemModel;
import com.muelpatmore.movielist.model.TopMovieListModel;
import com.muelpatmore.movielist.utils.ConnectionService;
import com.muelpatmore.movielist.utils.Constants;
import com.muelpatmore.movielist.utils.RequestInterface;
import com.muelpatmore.movielist.utils.ServerConnection;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements OnMovieSelected{

    public static final String TAG = "MainActivity";

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.frameLayout, new MovieListFragment())
                    .commit();
        }
    }



}
