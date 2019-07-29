package ybq.android.modentemplateproject.bean.info;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable {
    private String userId;
    private String userName;
    private String mobile;
    private String identity;
    private String token;
    private long expire;
    private ServiceAreaInfo serverArea;


    public UserInfo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public ServiceAreaInfo getServerArea() {
        return serverArea;
    }

    public void setServerArea(ServiceAreaInfo serverArea) {
        this.serverArea = serverArea;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.mobile);
        dest.writeString(this.identity);
        dest.writeString(this.token);
        dest.writeLong(this.expire);
        dest.writeParcelable(this.serverArea, flags);
    }


    protected UserInfo(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.mobile = in.readString();
        this.identity = in.readString();
        this.token = in.readString();
        this.expire = in.readLong();
        this.serverArea = in.readParcelable(ServiceAreaInfo.class.getClassLoader());
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
