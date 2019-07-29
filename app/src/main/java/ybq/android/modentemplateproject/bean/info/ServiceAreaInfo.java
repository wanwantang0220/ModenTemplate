package ybq.android.modentemplateproject.bean.info;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceAreaInfo implements Parcelable {

    // 所属县区code
    private String areaCode;
    // 县/区名称
    private String areaName;
    // 所属市code
    private String cityCode;
    // 市名称
    private String cityName;
    // 小区编码
    private String code;
    // 小区Id
    private String communityId;
    // 所属部门
    private String deptId;
    // 部门名称
    private String deptName;
    // 主键Id
    private String id;
    // 服务区域名称
    private String name;
    // 所属省份code
    private String provinceCode;
    // 省份名称
    private String provinceName;
    // 所属街道code
    private String streetCode;
    // 街道名称
    private String streetName;


    public ServiceAreaInfo() {
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.areaCode);
        dest.writeString(this.areaName);
        dest.writeString(this.cityCode);
        dest.writeString(this.cityName);
        dest.writeString(this.code);
        dest.writeString(this.communityId);
        dest.writeString(this.deptId);
        dest.writeString(this.deptName);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.provinceCode);
        dest.writeString(this.provinceName);
        dest.writeString(this.streetCode);
        dest.writeString(this.streetName);
    }

    protected ServiceAreaInfo(Parcel in) {
        this.areaCode = in.readString();
        this.areaName = in.readString();
        this.cityCode = in.readString();
        this.cityName = in.readString();
        this.code = in.readString();
        this.communityId = in.readString();
        this.deptId = in.readString();
        this.deptName = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.provinceCode = in.readString();
        this.provinceName = in.readString();
        this.streetCode = in.readString();
        this.streetName = in.readString();
    }

    public static final Parcelable.Creator<ServiceAreaInfo> CREATOR = new Parcelable.Creator<ServiceAreaInfo>() {
        @Override
        public ServiceAreaInfo createFromParcel(Parcel source) {
            return new ServiceAreaInfo(source);
        }

        @Override
        public ServiceAreaInfo[] newArray(int size) {
            return new ServiceAreaInfo[size];
        }
    };
}
