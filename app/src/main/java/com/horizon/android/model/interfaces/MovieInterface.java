package com.horizon.android.model.interfaces;


import com.horizon.android.model.bean.MovieVo;
import com.horizon.android.util.JuheResult;

import java.util.List;

import rx.Subscriber;

public interface MovieInterface {

    void getMovies(String title, int pagesize, int offset, Subscriber<JuheResult<List<MovieVo>>> subscriber);

}
