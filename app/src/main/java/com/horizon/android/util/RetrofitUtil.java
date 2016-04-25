package com.horizon.android.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.annotation.SuppressLint;

public class RetrofitUtil {

	public static OkHttpClient createOkHttpClient(){
		Interceptor interceptor = new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request newRequest = chain.request()
						.newBuilder()
						.addHeader("User-Agent", "YooYo/1.0")
						.build();
			    return chain.proceed(newRequest);
			}

		};
		OkHttpClient client = new OkHttpClient();
		client.interceptors().add(interceptor);
		
		return client;
	}
	
	public static Gson createGson(){
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
				@SuppressLint("UseValueOf")
				@Override
				public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
					String date = json.getAsJsonPrimitive().getAsString();
					String JSONDateToMilliseconds = "\\/(Date\\((.*?)(\\+.*)?\\))\\/";
					Pattern pattern = Pattern.compile(JSONDateToMilliseconds);
					Matcher matcher = pattern.matcher(date);
					String result = matcher.replaceAll("$2");
					try {
						return new Date(new Long(result));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			});

		return builder.create();
	}
	
}
