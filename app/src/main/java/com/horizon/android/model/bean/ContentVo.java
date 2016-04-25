package com.horizon.android.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ContentVo implements Serializable {
	
	private static final long serialVersionUID = -9079597926016310424L;
	
	private Long id;
	private Long content_id;
	private Long column_id;
	private String column_name;
	private String title;
	private String url;
	private String pic_rsurl;
	private BigDecimal market_price;
	private BigDecimal member_price;
	private Integer peoples;
	private String intro1;
	private String intro2;
	private String intro3;
	private Integer sort;
	private Date create_time;
	private Date start_time;
	private Date end_time;
	private String keywords;
	private String logo_rsurl;
	private String content;
	private boolean expire;
	private String city_name;
	private String source;	
	
	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public boolean isExpire() {
		return expire;
	}

	public void setExpire(boolean expire) {
		this.expire = expire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContent_id() {
		return content_id;
	}

	public void setContent_id(Long content_id) {
		this.content_id = content_id;
	}
	
	public Long getColumn_id() {
		return column_id;
	}

	public void setColumn_id(Long column_id) {
		this.column_id = column_id;
	}

	public String getColumn_name() {
		if (column_name==null) {
			return "";
		}
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getTitle() {
		if (title==null) {
			return "";
		}
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPic_rsurl() {
		return pic_rsurl;
	}

	public void setPic_rsurl(String pic_rsurl) {
		this.pic_rsurl = pic_rsurl;
	}

	public BigDecimal getMarket_price() {
		return market_price;
	}

	public void setMarket_price(BigDecimal market_price) {
		this.market_price = market_price;
	}

	public BigDecimal getMember_price() {
		return member_price;
	}

	public void setMember_price(BigDecimal member_price) {
		this.member_price = member_price;
	}

	public Integer getPeoples() {
		return peoples;
	}

	public void setPeoples(Integer peoples) {
		this.peoples = peoples;
	}

	public String getIntro1() {
		if (intro1 == null) {
			return "";
		}
		return intro1;
	}

	public void setIntro1(String intro1) {
		this.intro1 = intro1;
	}

	public String getIntro2() {
		return intro2;
	}

	public void setIntro2(String intro2) {
		this.intro2 = intro2;
	}

	public String getIntro3() {
		return intro3;
	}

	public void setIntro3(String intro3) {
		this.intro3 = intro3;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLogo_rsurl() {
		return logo_rsurl;
	}

	public void setLogo_rsurl(String logo_rsurl) {
		this.logo_rsurl = logo_rsurl;
	}

	public String getContent() {
		return content == null ? "" : content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSource() {
		if (source == null) {
			return "";
		}
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	
	
	
}
