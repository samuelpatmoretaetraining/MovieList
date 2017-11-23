package com.muelpatmore.movielist;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.muelpatmore.movielist.comunicationInterfaces.OnMovieSelected;
import com.muelpatmore.movielist.fragments.MovieListFragment;

public class MainActivity extends AppCompatActivity implements OnMovieSelected{

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
