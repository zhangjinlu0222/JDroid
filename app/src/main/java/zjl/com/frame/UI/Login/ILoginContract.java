package zjl.com.frame.UI.Login;

import zjl.com.frame.Base.IBasePresenter;
import zjl.com.frame.Base.IBaseView;

public interface ILoginContract {
    interface View extends IBaseView<Presenter> {

    }

    interface Presenter extends IBasePresenter {
        void login(String account,String password);
    }
}
