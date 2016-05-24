package com.horizon.android.api;

import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.util.JuheResult;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface ApiService {

	@GET("japi/toh")
	Observable<JuheResult<List<HistoryVo>>> getHistoryToday(
		@Query("key") String key,
		@Query("v") String v,
		@Query("month") int month,
		@Query("day") int day
	);
}  