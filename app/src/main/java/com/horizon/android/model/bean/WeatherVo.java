package com.horizon.android.model.bean;

import java.io.Serializable;

public class WeatherVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private WearherData data;

    public WearherData getData() {
        return data;
    }

    public void setData(WearherData data) {
        this.data = data;
    }

    public static class WearherData implements Serializable {
        private static final long serialVersionUID = 1L;
        private RealTime realtime;

        public RealTime getRealtime() {
            return realtime;
        }

        public void setRealtime(RealTime realtime) {
            this.realtime = realtime;
        }
    }

    public static class RealTime implements Serializable {
        private static final long serialVersionUID = 1L;

        private String city_code;
        private String city_name;
        private String date;
        private String time;
        private int week;
        private String moon;
        private long dataUptime;


        private Weather weather;
        private Wind wind;

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        public String getMoon() {
            return moon;
        }

        public void setMoon(String moon) {
            this.moon = moon;
        }

        public long getDataUptime() {
            return dataUptime;
        }

        public void setDataUptime(long dataUptime) {
            this.dataUptime = dataUptime;
        }

        public Weather getWeather() {
            return weather;
        }

        public void setWeather(Weather weather) {
            this.weather = weather;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }
    }

    public static class Weather implements Serializable {
        private static final long serialVersionUID = 1L;
        private String temperature;
        private String humidity;
        private String info;
        private String img;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class Wind implements Serializable {
        private static final long serialVersionUID = 1L;
        private String direct;
        private String power;
        private String offset;
        private String windspeed;

        public String getDirect() {
            return direct;
        }

        public void setDirect(String direct) {
            this.direct = direct;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getOffset() {
            return offset;
        }

        public void setOffset(String offset) {
            this.offset = offset;
        }

        public String getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(String windspeed) {
            this.windspeed = windspeed;
        }
    }
}
