package ybq.android.modentemplateproject.model.net.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    // 填上需要访问的服务器地址
//    public static String BASE_URL = "http://192.168.1.168:91/sportii-api/app/";
    String BASE_URL = "http://appapi.yunlinyanglao.com/app/";
//    String BASE_URL = "http://apitest.yunlinlifeapartment.com/app/";


    @POST("login")
    Observable<ResponseBody> login(@Query("mobile") String mobile, @Query("password") String password);


    @POST("customer/index")
    Observable<ResponseBody> index(@Query("serverAreaId") String serverAreaId);

}
