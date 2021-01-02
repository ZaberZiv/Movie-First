package com.movieziv.moviefirst.model.eventbus;

import com.movieziv.moviefirst.model.retrofit.movies.Result;

public class ResultEventBus {

    public final Result result;

    public ResultEventBus(Result result) {
        this.result = result;
    }
}
