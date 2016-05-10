package com.horizon.android.model.interfaces;

import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.util.JuheResult;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

public interface HistoryTodayInterface {

    Subscription getHistoryToday(int month, int day, Subscriber<JuheResult<List<HistoryVo>>> subscriber);
}
