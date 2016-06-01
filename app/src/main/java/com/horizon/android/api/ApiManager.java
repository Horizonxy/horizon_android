package com.horizon.android.api;

import com.horizon.android.Application;
import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.model.bean.NewsVo;
import com.horizon.android.util.JuheResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import rx.Observable;

public class ApiManager {

    public static Observable<JuheResult<List<HistoryVo>>> getHistoryToday(int month, int day){
        return Application.getInstance().apiService.getHistoryToday("b2cafae46ea4625d49f3c06af3a4f2e0", "1.0", month, day);
    }

    public static Observable<JuheResult<List<NewsVo>>> getNews(String q){
        try {
            q = URLEncoder.encode(q, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            q = "";
        }
        return Application.getInstance().apiService.getNews("f16e04cca3846e7aa96de660eab2684e", q);
    }

}
