package ybq.android.modentemplateproject.ui.login;

import ybq.android.modentemplateproject.base.BaseView;
import ybq.android.modentemplateproject.bean.info.UserInfo;

public interface LoginView extends BaseView {
    void onLoginSuccess(UserInfo info);
}
