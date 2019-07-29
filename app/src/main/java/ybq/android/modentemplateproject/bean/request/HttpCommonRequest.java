package ybq.android.modentemplateproject.bean.request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import ybq.android.modentemplateproject.bean.info.BaseModelInfo;

public class HttpCommonRequest extends HttpRequest {

    private JSONObject jsonObject = new JSONObject();
    private Object object;
    private BaseRequest request;

    public HttpCommonRequest() {
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public HttpCommonRequest put(String name, String value) {
        try {
            jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HttpCommonRequest put(String name, int value) {
        try {
            jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HttpCommonRequest put(String name, boolean value) {
        try {
            jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HttpCommonRequest put(String name, double value) {
        try {
            jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HttpCommonRequest put(String name, long value) {
        try {
            jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HttpCommonRequest put(String name, Object value) {
        try {
            jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HttpCommonRequest putObject(Object object) {
        if (object == null) {
            this.object = "";
        } else {
            this.object = object;
        }
        return this;
    }

    public HttpCommonRequest putRequest(BaseModelInfo request) {
        this.request = new BaseRequest(request);
        return this;
    }

    @Override
    public RequestBody toRequestBody() {
        String request = null;
        if (this.request != null) {
            request = this.request.toString();
        } else {
            if (object != null) {
                request = object.toString();
            } else {
                request = jsonObject.toString();
            }
        }

        Log.i("request:" , request);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), request);
    }


}
