package ybq.android.modentemplateproject.bean.info;

import java.io.Serializable;

public class BaseModel<T> implements Serializable {

    public BaseModel() {
    }

    public BaseModel(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    private int errcode;
    private String errmsg;
    private T result;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
