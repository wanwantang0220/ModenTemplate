package ybq.android.modentemplateproject.base;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import ybq.android.modentemplateproject.bean.info.BaseModel;
import ybq.android.modentemplateproject.bean.response.BaseResponse;
import ybq.android.modentemplateproject.model.net.exception.BaseException;
import ybq.android.modentemplateproject.model.net.http.OnApiCancelListener;
import ybq.android.modentemplateproject.model.net.http.OnApiListener;
import ybq.android.modentemplateproject.util.CompressUtils;

import static ybq.android.modentemplateproject.app.AppConstants.CODE_EXPIRE;

public class BaseObserver<T> extends DisposableObserver<ResponseBody> implements OnApiCancelListener {

    protected BaseView view;

    private BaseActivity mActivity;
    private Class mClazz;
    private OnApiListener mOnApiListener;


    public BaseObserver(BaseView view) {
        this.view = view;
    }

    public BaseObserver(BaseView view, boolean isShowDialog) {
        this.view = view;
    }

    public BaseObserver(OnApiListener listener, BaseView view, boolean isShowDialog,
                        BaseActivity activity, Class mclazz) {
        this.mOnApiListener = listener;
        this.view = view;
        this.mActivity = activity;
        this.mClazz = mclazz;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(ResponseBody body) {

        try {
            String parsed = CompressUtils.decompress(body.byteStream());
            BaseResponse baseResponse = JSON.parseObject(parsed, BaseResponse.class);
            int code = baseResponse.getCode();
            if (CODE_EXPIRE == code) {
                //TODO 登录过期
            } else {
                mOnApiListener.onSuccess(JSON.parseObject(parsed, mClazz));
            }

        } catch (Exception e) {
            Logger.e(e.toString());

            mOnApiListener.onFault(e.getMessage());
        }

    }

    @Override
    public void onError(Throwable e) {

        BaseException be = null;
        if (e != null) {
            if (e instanceof BaseException) {
                be = (BaseException) e;

                //回调到view层 处理 或者根据项目情况处理
                if (view != null) {
                    view.onFault(be.getErrorMsg());
                } else {
                    mOnApiListener.onFault(be.getErrorMsg());
                }

            } else {
                if (e instanceof HttpException) {
                    //   HTTP错误
                    be = new BaseException(BaseException.BAD_NETWORK_MSG, e, BaseException.BAD_NETWORK);
                } else if (e instanceof ConnectException
                        || e instanceof UnknownHostException) {
                    //   连接错误
                    be = new BaseException(BaseException.CONNECT_ERROR_MSG, e, BaseException.CONNECT_ERROR);
                } else if (e instanceof InterruptedIOException) {
                    //  连接超时
                    be = new BaseException(BaseException.CONNECT_TIMEOUT_MSG, e, BaseException.CONNECT_TIMEOUT);
                } else if (e instanceof JsonParseException
                        || e instanceof JSONException
                        || e instanceof ParseException) {
                    //  解析错误
                    be = new BaseException(BaseException.PARSE_ERROR_MSG, e, BaseException.PARSE_ERROR);
                } else {
                    be = new BaseException(BaseException.OTHER_MSG, e, BaseException.OTHER);
                }
            }
        } else {
            be = new BaseException(BaseException.OTHER_MSG, e, BaseException.OTHER);
        }

        mOnApiListener.onFault(be.getErrorMsg());
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onCancelProgress() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }


    private void showDialog() {
    }

    private void hideDialog() {
    }


}
