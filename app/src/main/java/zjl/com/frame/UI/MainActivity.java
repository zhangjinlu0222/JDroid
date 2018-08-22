package zjl.com.frame.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import oa.zjl.com.frame.R;
import zjl.com.frame.Base.BaseActivity;
import zjl.com.frame.UI.Login.LoginActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView hello;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        List<Integer> ss = new ArrayList<>();
//        ss.add(1);
//        ss.add(2);
//        ss.add(3);
//        ss.add(4);
//        Toast.makeText(mContext, "1" +ss.subList(0,ss.size() / 2), Toast.LENGTH_SHORT).show();
//        Toast.makeText(mContext, "2" +ss.subList(ss.size() / 2,ss.size()), Toast.LENGTH_SHORT).show();
    }

    public void initView() {
        hello = (TextView) findViewById(R.id.hello);
        hello.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.hello){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
