package ybq.android.modentemplateproject.base;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ybq.android.modentemplateproject.model.net.help.RetrofitEngine;
import ybq.android.modentemplateproject.model.net.service.ApiService;

public class BasePresenter<T extends BaseView> {
    //请求失败重连次
    private int RETRY_COUNT = 1;
    public T mView;
    private CompositeDisposable mCompositeDisposable;
    protected ApiService apiServer = RetrofitEngine.getInstance().getApiService();


    public BasePresenter(T view) {
        this.mView = view;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        this.mView = null;
        removeDisposable();
    }

    /**
     * 返回 view
     *
     * @return
     */
    public T getBaseView() {
        return mView;
    }

    /**
     * 添加订阅
     * 请求入列
     */
    public void addDisposable(Observable<?> observable, BaseObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_COUNT)//请求失败重连次数
                .subscribeWith(observer));
    }

    /**
     * 解除订阅
     * 停止请求
     */
    private void removeDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }


}
