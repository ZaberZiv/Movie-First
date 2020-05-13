package com.movieziv.moviefirst.eventbus;

import com.movieziv.moviefirst.retrofit.movies.Result;

public class ResultEventBus {

    public final Result result;

    public ResultEventBus(Result result) {
        this.result = result;
    }
}
