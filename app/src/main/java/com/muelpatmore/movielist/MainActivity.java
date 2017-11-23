package com.muelpatmore.movielist;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.muelpatmore.movielist.comunicationInterfaces.OnMovieSelected;
import com.muelpatmore.movielist.fragments.MovieDetailsFragment;
import com.muelpatmore.movielist.fragments.MovieListFragment;
import com.muelpatmore.movielist.messages.MessageInterface;

public class MainActivity extends AppCompatActivity implements OnMovieSelected {

    public static final String TAG = "MainActivity";

    private static MainActivity instance = null;
    private static FragmentManager fragmentManager;

    /**
     * give a target for Message object to submit request.
     * @param i Message requesting access.
     */
    public static void getInstance(MessageInterface i) {
        i.giveTarget(instance);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.frameLayout, new MovieListFragment())
                    .commit();
        }
    }


    @Override
    public void movieSelected(int id) {
        MovieDetailsFragment detailsFragment = MovieDetailsFragment.getInstance();
        detailsFragment.showMovieDetails(id);
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, detailsFragment)
                .addToBackStack("Top Movie List")
                .commit();
    }
}
