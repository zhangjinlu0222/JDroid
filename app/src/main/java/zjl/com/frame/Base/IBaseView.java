package zjl.com.frame.Base;

public interface IBaseView<T>{
    void setPresenter( T presenter);
    void succeed();
    void fail();
    void fail(String msg);
}
