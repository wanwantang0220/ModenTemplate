package ybq.android.modentemplateproject.bean.request;

import com.google.gson.annotations.SerializedName;

import ybq.android.modentemplateproject.bean.info.BaseModelInfo;

public class BaseRequest extends BaseModelInfo {
    @SerializedName("object")
    private BaseModelInfo data;

    BaseRequest(BaseModelInfo data) {
        this.data = data;
    }
}

