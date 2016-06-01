package com.horizon.android.api;

import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.model.bean.JokeVo;
import com.horizon.android.model.bean.NewsVo;
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

	@GET("onebox/news/query")
	Observable<JuheResult<List<NewsVo>>> getNews(
			@Query("key") String key,
			@Query("q") String q
	);

	@GET("joke/content/list.from")
	Observable<JuheResult<JokeVo>> getJokeList(
			@Query("sort") String sort,
			@Query("page") int page,
			@Query("pagesize") int pagesize,
			@Query("time") String time,
			@Query("key") String key
	);
}  