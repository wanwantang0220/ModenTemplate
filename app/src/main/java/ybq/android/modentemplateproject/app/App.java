package ybq.android.modentemplateproject.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

public class App extends Application {

    public static Context appContext;
    private static App mContext;
    private Set<Activity> allActivities;


    public static App getContext() {
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        mContext = this;

        initRegisterActivityLifecycleCallBacks();
    }

    /**
     * Activity生命周期管理回调方法
     */
    private void initRegisterActivityLifecycleCallBacks() {

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

                if (allActivities == null) {
                    allActivities = new HashSet<>();
                }
                allActivities.add(activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (allActivities != null) {
                    allActivities.remove(activity);
                }
            }
        });
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity activity : allActivities) {
                    activity.finish();
                }
            }
            allActivities = null;
            //绕过Activity，强制关闭
            System.exit(0);
        }
    }


}
