package zjl.com.frame.Common;

import android.app.Application;

import com.dou361.dialogui.DialogUIUtils;

public class MyApplication extends Application {
    private static MyApplication sInstance;

    public static MyApplication getsInstance(){
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);

        DialogUIUtils.init(getsInstance().getApplicationContext());
    }

    private void exit(){
        ActivityManager.getsInstance().exit(this);
    }
}
