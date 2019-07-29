package ybq.android.modentemplateproject.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import ybq.android.modentemplateproject.bean.info.BaseModelInfo;

public class BaseResponse  extends BaseModelInfo implements Parcelable {

    private int code;
    private String msg;
    private String currentTime;

    public BaseResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeString(this.currentTime);
    }

    protected BaseResponse(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.currentTime = in.readString();
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel source) {
            return new BaseResponse(source);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };
}
