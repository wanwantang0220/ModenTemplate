package ybq.android.modentemplateproject.bean.info;

import com.google.gson.Gson;

import java.io.Serializable;

public class BaseModelInfo implements Serializable, Cloneable {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public Object clone() {
        try {
            return  super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(toString(), this.getClass());
    }
}
