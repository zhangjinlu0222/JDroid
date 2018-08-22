package zjl.com.frame.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;
import zjl.com.frame.API.ApiWrapper;
import zjl.com.frame.Common.ActivityManager;


public abstract class BaseActivity<P extends  IBasePresenter> extends Activity {
    protected Context mContext;

    public static final String TAG = "BaseActivity";

    public P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        //Activity stack manager
        ActivityManager.getsInstance().addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID,null);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity stack manager
        ActivityManager.getsInstance().removeActivity(this);

        // unsubscribe after activity destory
        if (mPresenter != null){
            mPresenter.unsubscribe();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // subscribe after activity resume
        if (mPresenter != null){
            mPresenter.subscribe();
        }
    }
}
