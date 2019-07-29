package ybq.android.modentemplateproject.bean.response;

import ybq.android.modentemplateproject.bean.info.UserInfo;

public class LoginResponse extends BaseResponse {

    private UserInfo results;

    public LoginResponse() {
    }

    public UserInfo getResults() {
        return results;
    }

    public void setResults(UserInfo results) {
        this.results = results;
    }
}
