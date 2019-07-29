package ybq.android.modentemplateproject.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ybq.android.modentemplateproject.R;
import ybq.android.modentemplateproject.base.SimpleActivity;
import ybq.android.modentemplateproject.ui.login.LoginActivity;

public class MainActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventData() {

    }


    public void btnClick(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
