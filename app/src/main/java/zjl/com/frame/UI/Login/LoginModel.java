package zjl.com.frame.UI.Login;

import com.google.gson.Gson;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import zjl.com.frame.API.ApiWrapper;
import zjl.com.frame.Response.HttpResponse;
import zjl.com.frame.Response.LoginResult;

import static zjl.com.frame.Utils.MD5.md5twice;

public class LoginModel{

    private static final String TAG = "LoginModel";

    public Observable<HttpResponse<LoginResult>> login(String account, String password){
        HashMap<String,Object> map = new HashMap<>();
        map.put("phone",account);
        map.put("pwd",md5twice(password));
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), new Gson().toJson(map));
        return new ApiWrapper().login(body);
    }
}
