package ybq.android.modentemplateproject.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import ybq.android.modentemplateproject.R;
import ybq.android.modentemplateproject.base.BaseActivity;
import ybq.android.modentemplateproject.bean.info.UserInfo;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPresenter(Context mContext) {
        return new LoginPresenter(this);
    }

    @Override
    protected void initEventData() {
        btnLogin.setOnClickListener(v -> mPresenter.login(LoginActivity.this));

    }


    @Override
    public void onLoginSuccess(UserInfo info) {
        Log.i("TAG", "mobile = " + info.getMobile());
    }


    @Override
    public void onFault(String msg) {
        Log.i("TAG", "msg = " + msg);
    }
}
