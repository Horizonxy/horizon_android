package com.horizon.android.presenter;

import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.model.interfaces.HistoryTodayInterface;
import com.horizon.android.simple.SimpleSubscriber;
import com.horizon.android.util.JuheResult;
import com.horizon.android.view.HistoryTodayView;

import java.util.List;

import rx.Subscription;

public class HistoryTodayPresenter {

    HistoryTodayInterface mHistoryToday;
    HistoryTodayView vHistoryToday;

    public HistoryTodayPresenter(HistoryTodayInterface mHistoryToday, HistoryTodayView vHistoryToday){
        this.vHistoryToday = vHistoryToday;
        this.mHistoryToday = mHistoryToday;
    }

    public void getHistoryToday(){
        Subscription subscription = mHistoryToday.getHistoryToday(vHistoryToday.getMonth(), vHistoryToday.getDay(), new SimpleSubscriber<JuheResult<List<HistoryVo>>>(){
            @Override
            public void onError(Throwable e) {
                vHistoryToday.failure();
            }

            @Override
            public void onNext(JuheResult<List<HistoryVo>> obj) {
                List<HistoryVo> histories = obj.getResult();
                if(histories != null && histories.size() > 0){
                    vHistoryToday.success(histories);
                } else {
                    vHistoryToday.empty();
                }
            }
        });
        vHistoryToday.addSubscriberToComposite(subscription);
    }
}
