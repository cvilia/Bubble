package com.cvilia.bubble.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author: lzy
 * date: 2020/8/19
 * describe：描述
 */
public class HttpManager {

    private OkHttpClient client;
    private static HttpManager mClient;

    private HttpManager() {
        client = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public static HttpManager getInstance() {
        if (mClient == null) {
            synchronized (HttpManager.class) {
                if (mClient == null) {
                    mClient = new HttpManager();
                }
            }
        }
        return mClient;
    }


    // GET方法
    public void get(String url, HashMap<String, String> param, MyCallback callback) {
        if (!param.isEmpty()) {
            StringBuffer buffer = new StringBuffer(url);
            buffer.append('?');
            for (Map.Entry<String, String> entry : param.entrySet()) {
                buffer.append(entry.getKey());
                buffer.append('=');
                buffer.append(entry.getValue());
                buffer.append('&');
            }
            buffer.deleteCharAt(buffer.length() - 1);
            url = buffer.toString();
        }
        Request.Builder builder = new Request.Builder().url(url);
        builder.method("GET", null);
        Request request = builder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.failed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.success(response);
            }
        });
    }

    public void get(String url, MyCallback callback) {
        get(url, new HashMap<String, String>(), callback);
    }

    // POST 方法
    public void post(String url, HashMap<String, String> param, MyCallback callback) {
        FormBody.Builder formBody = new FormBody.Builder();
        if (!param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                formBody.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody form = formBody.build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.post(form)
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.failed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.success(response);
            }
        });
    }

    public interface MyCallback {
        void success(Response res) throws IOException;

        void failed(IOException e);
    }
}
