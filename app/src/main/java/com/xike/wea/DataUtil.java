package com.xike.wea;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.Response;


public class DataUtil {

    public static WeatherData TestData() throws Exception {
        WeatherData return_data = new WeatherData();
        JSONObject result = getNowWeather("绵阳");
        return_data.nowTime = getDay(result);
        return_data.nowTemperature = getTempCurr(result);
        return_data.today_min_Temperature = getTemperature(result)[1];
        return_data.today_max_Temperature = getTemperature(result)[0];
        return_data.today_condition = getWeather(result);
       // System.out.println(getDay(result) + "  " + getTempCurr(result)); //输出当前日期 + 当前温度
        // 输出当日的最低最高气温以及天气状况
       // System.out.println(getWeather(result) + "  " + getTemperature(result)[1] + "  " + getTemperature(result)[0]);

        JSONArray results = getWeathers("绵阳");

        for(int i = 1; i < results.length(); i++) {
         /*  System.out.println(getDay((JSONObject)results.get(i)) + "  " +
                    getWeather((JSONObject)results.get(i)) + "  " +
                    getTemperature((JSONObject)results.get(i))[0] + "  " +
                    getTemperature((JSONObject)results.get(i))[1]);*/
              return_data.time[i-1] = getDay((JSONObject)results.get(i));
              return_data.condition[i-1] = getWeather((JSONObject)results.get(i));
              return_data.minTemperature[i-1] = getTemperature((JSONObject)results.get(i))[1];
              return_data.maxTemperature[i-1] = getTemperature((JSONObject)results.get(i))[0];
        }
        return return_data;
    }

    //获取当天的信息
    public static JSONObject getNowWeather(String cityName) throws Exception {
        HttpUtil httpUtil = new HttpUtil();
        Response response = null;
        response = httpUtil.getSyn("http://api.k780.com/?app=weather.today&weaid="
                + cityName + "&appkey=35440&sign=be48f1000df6fc61eaa63f862aa21274&format=json");
        JSONObject json = new JSONObject(response.body().string());
        JSONObject data = new JSONObject(json.getString("result"));
        return data;
    }

    //获取一周的信息
    public static JSONArray getWeathers(String cityName) throws Exception {
        HttpUtil httpUtil = new HttpUtil();
        Response response = null;
        response = httpUtil.getSyn("http://api.k780.com/?app=weather.future&weaid="
                + cityName + "&&appkey=35440&sign=be48f1000df6fc61eaa63f862aa21274&format=json");
        JSONObject json = new JSONObject(response.body().string());
        JSONArray data = new JSONArray(json.getString("result"));
        return data;
    }

    //获取当前温度
    public static String getTempCurr(JSONObject result) throws JSONException {
        return result.getString("temp_curr") + "°C";
    }

    //获取当天温度的极值
    public static String[] getTemperature(JSONObject result) throws JSONException {
        String temperature = result.getString("temperature");
        String[] strings = temperature.split("/");
        String[] results = new String[2];
        for (int i = 0; i < strings.length; i++) {
            results[i] = strings[i].split("℃")[0] + "°C";
        }
        return results;
    }

    //获取当前时间
    public static String getDay(JSONObject result) throws JSONException {
        return result.getString("days") + " " + result.getString("week");
    }

    //获取天气状况
    public static String getWeather(JSONObject result) throws JSONException {
        return result.getString("weather");
    }

    //获取图片URL并改为本地的图片
    public static String getIcon(JSONObject result) throws JSONException {
        return result.getString("weather_icon");
    }
}
