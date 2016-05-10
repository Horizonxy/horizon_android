package com.horizon.android.api;

import com.horizon.android.Application;
import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.model.bean.MovieVo;
import com.horizon.android.model.bean.WeatherVo;
import com.horizon.android.util.JuheResult;

import java.util.List;

import rx.Observable;

public class ApiManager {

    public static Observable<JuheResult<List<MovieVo>>> getMovies(String title, int pagesize, int offset){
         return Application.getInstance().apiService.getMovies(title, 0, pagesize, offset, "cb9b2b5698a30a9edac747136008dcfc");
    }

    public static Observable<JuheResult<WeatherVo>> getWeather(String cityname){
        return Application.getInstance().apiService.getWeather(cityname, "c2b7249316460407515040193b826808");
    }

    public static Observable<JuheResult<List<HistoryVo>>> getHistoryToday(int month, int day){
        return Application.getInstance().apiService.getHistoryToday("b2cafae46ea4625d49f3c06af3a4f2e0", "1.0", month, day);
    }

}
