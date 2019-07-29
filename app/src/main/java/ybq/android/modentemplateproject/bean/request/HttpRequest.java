package ybq.android.modentemplateproject.bean.request;

import com.google.gson.Gson;

import okhttp3.RequestBody;

public abstract class HttpRequest extends AbstractRequestModel {

    @Override
    public RequestBody toRequestBody() {
        BaseRequest request = new BaseRequest(this);
        String route = new Gson().toJson(request);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
    }

}

