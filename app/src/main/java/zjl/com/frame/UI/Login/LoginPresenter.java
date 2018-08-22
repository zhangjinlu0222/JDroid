package zjl.com.frame.UI.Login;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zjl.com.frame.Base.BaseObserver;
import zjl.com.frame.Common.MyApplication;
import zjl.com.frame.Response.HttpResponse;
import zjl.com.frame.Response.LoginResult;
import zjl.com.frame.Schedulers.BaseSchedulerProvider;

public class LoginPresenter implements ILoginContract.Presenter {

    @NonNull
    private final LoginModel mLoginModel;

    @NonNull
    private final ILoginContract.View mLoginView;

    @NonNull
    private final CompositeDisposable mCompositeDisposable;

//    @NonNull
//    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final Context mContext;

    private final static String TAG = "LoginPresenter";


    public LoginPresenter(Context context,LoginModel loginModel, ILoginContract.View mLoginView) {
        this.mContext = context;
        this.mLoginModel = loginModel;
        this.mLoginView = mLoginView;
//        this.mSchedulerProvider = mSchedulerProvider;

        mCompositeDisposable = new CompositeDisposable();
        mLoginView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void login(String account, String password) {
                mLoginModel.login(account,password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LoginResult>(mContext) {

                    @Override
                    public void onSucceed(LoginResult loginResult) {
                        mLoginView.succeed();
                    }

                    @Override
                    public void onFailure(String msg) {
                        mLoginView.fail(msg);
                    }

                    @Override
                    public void addDisposable(Disposable d) {
                        mCompositeDisposable.add(d);
                    }
                });

    }
}
