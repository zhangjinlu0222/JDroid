package zjl.com.frame.Base;


import android.content.Context;
import android.content.res.Resources;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import oa.zjl.com.frame.R;
import retrofit2.adapter.rxjava.HttpException;
import zjl.com.frame.Common.Constant;
import zjl.com.frame.Response.HttpResponse;

public abstract class BaseObserver<T> implements Observer<HttpResponse<T>> {
    /**
     * data parse error
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * net error
     */
    public static final int BAD_NETWORK = 1002;
    /**
     * connect error
     */
    public static final int CONNECT_ERROR = 1003;
    /**
     * connect time out
     */
    public static final int CONNECT_TIMEOUT = 1004;

    private Context mContext;

    private BuildBean mDialog;

    public BaseObserver(Context mContext) {
        this.mContext = mContext;
        mDialog = DialogUIUtils.showLoading(mContext,Resources.getSystem().getString(R.string.logining),true,false,false,true);
    }

    @Override
    public void onSubscribe(Disposable d) {
        addDisposable(d);

        if (mDialog != null){
            mDialog.show();
        }
    }

    @Override
    public void onNext(HttpResponse<T> tHttpResponse) {

        if (tHttpResponse.getCode() == Constant.Succeed){
            onSucceed(tHttpResponse.getResult());
        }else{
            onFailure(tHttpResponse.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {

        if (mDialog != null){
            DialogUIUtils.dismiss(mDialog);
        }

        if (e instanceof HttpException) {
            onException(BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            onException(PARSE_ERROR);
        } else {
            if (e != null) {
                onFailure(e.toString());
            } else {
                onFailure(Resources.getSystem().getString(R.string.error_unknown));
            }
        }
    }

    @Override
    public void onComplete() {
        if (mDialog != null){
            DialogUIUtils.dismiss(mDialog);
        }
    }
    private void onException(int unknownError) {
        switch (unknownError) {
            case CONNECT_ERROR:
                onFailure(Resources.getSystem().getString(R.string.error_connect_timeout));
                break;

            case CONNECT_TIMEOUT:
                onFailure(Resources.getSystem().getString(R.string.error_connect_timeout));
                break;

            case BAD_NETWORK:
                onFailure(Resources.getSystem().getString(R.string.error_bad_network));
                break;

            case PARSE_ERROR:
                onFailure(Resources.getSystem().getString(R.string.error_data_parse_exception));
                break;

            default:
                break;
        }
    }

    public abstract void onSucceed(T t);
    public abstract void onFailure(String msg);
    public abstract void addDisposable(Disposable d);
}