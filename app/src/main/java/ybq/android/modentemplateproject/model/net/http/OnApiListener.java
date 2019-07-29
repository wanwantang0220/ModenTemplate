package ybq.android.modentemplateproject.model.net.http;


public interface OnApiListener<T> {
    void onSuccess(T response);

    void onFault(String errorMsg);

}
