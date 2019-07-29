package ybq.android.modentemplateproject.model.net.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ybq.android.modentemplateproject.util.NetWorkUtil;

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        int maxAge = 60 * 60;
        int maxStale = 60 * 60 * 24;
        Request request = chain.request();//求链中获得请求
        //有网络时从网络中获取
        if(NetWorkUtil.isNetworkConnected()){
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
            Log.d("TAG","request ->");
        }else{
            //无网络时从缓存中获取
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);//执行链中请求获得响应结果
        Log.d("TAG","chain.proceed");
        if(NetWorkUtil.isNetworkConnected()){
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .addHeader("Cache-Control","public, max-age=" + maxAge)
                    .build();
        }else{
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .addHeader("Cache-Control","public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
        return response;
    }
}
