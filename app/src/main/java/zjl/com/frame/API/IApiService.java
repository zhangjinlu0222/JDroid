package zjl.com.frame.API;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import zjl.com.frame.Response.HttpResponse;
import zjl.com.frame.Response.LoginResult;

public interface IApiService {

    @POST("WebApi/V1/User/Login")
    Observable<HttpResponse<LoginResult>> login(@Body RequestBody body);

}
