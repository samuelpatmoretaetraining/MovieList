package com.muelpatmore.movielist.messages;

import com.muelpatmore.movielist.comunicationInterfaces.OnMovieSelected;

/**
 * Created by Samuel on 23/11/2017.
 */

public interface MessageInterface {
    void giveTarget(OnMovieSelected target);
}
