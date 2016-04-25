package com.horizon.android.api;

import com.horizon.android.model.bean.ContentVo;
import com.horizon.android.model.bean.MovieVo;
import com.horizon.android.model.bean.WeatherVo;
import com.horizon.android.util.JuheResult;
import com.horizon.android.util.Page;
import com.horizon.android.util.RestResult;
import java.util.List;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface ApiService {  

	@GET("api/outer/router.json")
	Observable<RestResult<Page<ContentVo>>> getContentByColumnId(
			@Query("app_id") int app_id,
			@Query("partner_id") int partner_id,
			@Query("app_key") String app_key,
			@Query("method") String method,
			@Query("column_id") String column_id);
	
	@GET("api/outer/router.json")
	Call<RestResult<Page<ContentVo>>> getContentByColumnIdSync(
			@Query("app_id") int app_id,
			@Query("partner_id") int partner_id,
			@Query("app_key") String app_key,
			@Query("method") String method,
			@Query("column_id") String column_id);

	@GET("movie/index")
	Observable<JuheResult<List<MovieVo>>> getMovies(
			@Query("title") String title,
			@Query("smode") int smode,
			@Query("pagesize") int pagesize,
			@Query("offset") int offset,
			@Query("key") String key);

	@GET("onebox/weather/query")
	Observable<JuheResult<WeatherVo>> getWeather(
			@Query("cityname") String cityname,
			@Query("key") String key);
}  