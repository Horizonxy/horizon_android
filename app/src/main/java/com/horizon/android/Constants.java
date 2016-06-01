package com.horizon.android;

public class Constants {

	public static final String END_POIND = /*笑话*/"http://japi.juhe.cn"/*新闻"http://op.juhe.cn"*//*历史上的今天"http://api.juheapi.com"*/;

	public static final String BASE_DIR = "horizon";
	public static final String IMG_CACHE_DIR = BASE_DIR.concat("/image_cache");

	public final static int REQ_SCAN = 0x1000;
	/** 二维码扫描结果. */
	public final static String BUNDLE_SCAN_RESULT = "bundle_scan_result";

	public final static String BUNDLE_WEBVIEW_URL = "bundle_webview_url";

	public final static String BUNDLE_PIC_LEFT = "bundle_picture_left";
	public final static String BUNDLE_PIC_TOP = "bundle_picture_right";
	public final static String BUNDLE_PIC_WIDTH = "bundle_picture_width";
	public final static String BUNDLE_PIC_HEIGHT = "bundle_picture_height";
	public final static String BUNDLE_PIC_URL = "bundle_picture_url";
}
