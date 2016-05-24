package com.horizon.android.api;

import com.horizon.android.Application;
import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.util.JuheResult;

import java.util.List;

import rx.Observable;

public class ApiManager {

    public static Observable<JuheResult<List<HistoryVo>>> getHistoryToday(int month, int day){
        return Application.getInstance().apiService.getHistoryToday("b2cafae46ea4625d49f3c06af3a4f2e0", "1.0", month, day);
    }

}
