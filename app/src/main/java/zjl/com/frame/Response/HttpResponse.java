package zjl.com.frame.Response;

public class HttpResponse<T> {
    private int Code;
    private String Message;
    private String Time;
    private T Result;

    public int getCode() {
        return Code;
    }

    public String getMessage() {
        return Message;
    }

    public String getTime() {
        return Time;
    }

    public T getResult() {
        return Result;
    }

    public void setResult(T result) {
        Result = result;
    }
}

