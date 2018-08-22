package zjl.com.frame.UI.Login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import oa.zjl.com.frame.R;
import zjl.com.frame.Base.BaseActivity;
import zjl.com.frame.Schedulers.SchedulerProvider;
import zjl.com.frame.UI.Login.LoginPresenter;
import zjl.com.frame.UI.Login.ILoginContract;

public class LoginActivity extends BaseActivity<ILoginContract.Presenter> implements ILoginContract.View{


    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.project_name)
    TextView projectName;
    @BindView(R.id.login_account)
    EditText loginAccount;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this,
                new LoginModel(),this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        mPresenter.login("13087516128","123123");
//        mPresenter.login("13087516128","123456");
        mPresenter.login("13087516128","123456");
    }

    @Override
    public void setPresenter(ILoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void succeed() {
        Toast.makeText(mContext, "aaaaaaaaaaa", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void fail() {}

    @Override
    public void fail(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
