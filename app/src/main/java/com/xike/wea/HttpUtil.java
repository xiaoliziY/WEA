package com.xike.wea;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpUtil {

    private OkHttpClient mOkHttpClient;
    
    HttpUtil() {
        mOkHttpClient = new OkHttpClient();
    }

    //同步的get请求
    public Response getSyn(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        Response response = call.execute();
        return response;
    }
}