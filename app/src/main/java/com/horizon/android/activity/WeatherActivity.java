package com.horizon.android.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.horizon.android.R;
import com.horizon.android.component.DaggerActivityComponent;
import com.horizon.android.model.bean.WeatherVo;
import com.horizon.android.module.ActivityModule;
import com.horizon.android.presenter.WeatherPersenter;
import com.horizon.android.view.WeatherView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;

public class WeatherActivity extends BaseActivity implements WeatherView {

    String cityname = "";

    @Bind(R.id.et_cityname)
    EditText etCityName;
    @Bind(R.id.tv_result)
    TextView tvResult;

    @Inject
    WeatherPersenter persenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setTitle("Weather");
        DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build().inject(this);

    }

    @OnClick(R.id.btn_query)
    void weacherClick() {
        persenter.getWeather();
        finish();
    }

    @Override
    public String getCityName() {
        cityname = etCityName.getText().toString();
        return cityname;
    }

    @Override
    public void success(WeatherVo weather) {
        WeatherVo.RealTime realTime = weather.getData().getRealtime();
        tvResult.setText(realTime.getDate() + "\n"
                + realTime.getWeather().getTemperature() + "\n"
                + realTime.getWind().getDirect() + " " + realTime.getWind().getPower());
    }

    @Override
    public void failure(String error) {
        tvResult.setText(error);
    }

    @Override
    public void addSubscriberToComposite(Subscription subscription) {
        addSubscription(subscription);
    }
}
