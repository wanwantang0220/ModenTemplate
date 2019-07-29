package ybq.android.modentemplateproject.model.net.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        //chain里面包含了request和response
        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间
        Log.i("header :",request.url()+"\n"+request.headers());
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();//收到响应的时间
        return response;
    }
}
