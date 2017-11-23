package com.muelpatmore.movielist.messages;

import com.muelpatmore.movielist.MainActivity;
import com.muelpatmore.movielist.comunicationInterfaces.OnMovieSelected;

/**
 * Created by Samuel on 23/11/2017.
 */

public class MessageMovieSelected implements MessageInterface {

    private int id;

    public MessageMovieSelected(int id) {
        this.id=id;
        MainActivity.getInstance(this);
    }

    @Override
    public void giveTarget(OnMovieSelected target) {
        target.movieSelected(id);
    }
}
