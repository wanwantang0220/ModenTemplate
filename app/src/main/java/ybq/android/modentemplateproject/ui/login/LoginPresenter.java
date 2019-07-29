package ybq.android.modentemplateproject.ui.login;

import android.util.Log;

import ybq.android.modentemplateproject.base.BaseObserver;
import ybq.android.modentemplateproject.base.BasePresenter;
import ybq.android.modentemplateproject.bean.info.UserInfo;
import ybq.android.modentemplateproject.bean.response.LoginResponse;
import ybq.android.modentemplateproject.model.net.http.OnApiListener;


public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView baseView) {
        super(baseView);
    }

    public void login(LoginActivity mActivity) {

        String mobile = "18961722253";
        String password = "123456";
        String deviceType = "1";
        String deviceId = "7f696802151c47d8b72bfcffe3c267d7";
        String serverAreaId="3";

        addDisposable(apiServer.login(mobile, password), new BaseObserver(new OnApiListener<LoginResponse>() {

            @Override
            public void onSuccess(LoginResponse response) {
                UserInfo info = response.getResults();
                mView.onLoginSuccess(info);
            }

            @Override
            public void onFault(String errorMsg) {

                mView.onFault(errorMsg);

            }
        }, mView, true, mActivity, LoginResponse.class));
    }
}
