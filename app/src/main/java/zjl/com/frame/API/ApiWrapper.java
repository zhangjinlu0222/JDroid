package zjl.com.frame.API;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import zjl.com.frame.Response.HttpResponse;
import zjl.com.frame.Response.LoginResult;

public class ApiWrapper extends Api{

    public Observable<HttpResponse<LoginResult>> login(RequestBody body) {
        return getService().login(body);
    }
}
