package ybq.android.modentemplateproject.bean.request;

import com.google.gson.Gson;

import okhttp3.RequestBody;
import ybq.android.modentemplateproject.bean.info.BaseModelInfo;

public class AbstractRequestModel extends BaseModelInfo {

    public RequestBody toRequestBody() {
        String route = new Gson().toJson(this);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
    }
}