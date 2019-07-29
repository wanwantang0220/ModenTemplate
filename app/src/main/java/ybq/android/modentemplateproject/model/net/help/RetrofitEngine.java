package ybq.android.modentemplateproject.model.net.help;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ybq.android.modentemplateproject.app.App;
import ybq.android.modentemplateproject.model.net.interceptor.NetworkInterceptor;
import ybq.android.modentemplateproject.model.net.service.ApiService;
import ybq.android.modentemplateproject.util.NetWorkUtil;
import ybq.android.modentemplateproject.util.SharedPreferencesUitl;

import static ybq.android.modentemplateproject.app.AppConstants.API_VERSION;
import static ybq.android.modentemplateproject.app.AppConstants.TOKEN;

public class RetrofitEngine {

    private static final long DEFAULT_TIMEOUT = 10;
    public static final String CACHE_NAME = "com.yunlin.xihai.staff";

    private final int cacheSize = 10 * 1024 * 1024;
    private final long connectTimeoutMills = 10 * 1000L;
    private final long readTimeoutMills = 10 * 1000L;
    private volatile static RetrofitEngine retrofitEngine;
    private OkHttpClient client;
    private  Retrofit retrofit;

    private RetrofitEngine() {

        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {

            try {
                String text = URLDecoder.decode(message, "utf-8");
                Log.e("OKHttp-----", text);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e("OKHttp-----", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(App.getContext().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (!NetWorkUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .removeHeader(CACHE_NAME)
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader(CACHE_NAME)
                            .build();
                }
                return response;
            }
        };


        /**设置头信息**/
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("api-version", API_VERSION)
//                        .addHeader("Accept-Encoding", "gzip")
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .method(originalRequest.method(), originalRequest.body());
                //添加请求头信息，服务器进行token有效性验证
                requestBuilder.addHeader("token", SharedPreferencesUitl.getStringValue(App.getContext(), TOKEN));
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeoutMills, TimeUnit.SECONDS)
                .writeTimeout(readTimeoutMills, TimeUnit.SECONDS)
                .readTimeout(readTimeoutMills, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkInterceptor())
//                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .cache(cache)
                .addInterceptor(cacheInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(client)
                //然后将下面的GsonConverterFactory.create()替换成我们自定义的ResponseConverterFactory.create()
                //.addConverterFactory(ResponseConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


    }

    public static RetrofitEngine getInstance() {
        if (retrofitEngine == null) {
            synchronized (RetrofitEngine.class) {
                if (retrofitEngine == null) {
                    retrofitEngine = new RetrofitEngine();
                }
            }
        }
        return retrofitEngine;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}
