package danazone.com.vitu;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import danazone.com.vitu.api.Common;
import danazone.com.vitu.fragment.login.LoginFragment;
import io.socket.client.IO;
import io.socket.client.Socket;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    @BindView(R.id.fl_login)
    FrameLayout flLogin;

    private LoginFragment loginFragment;
    public Socket mSocket;

    public static MainActivity mainActivity;

    {
        try {
            mSocket = IO.socket(Common.URL_SOCKET);
            if (mSocket.connected()){mSocket.disconnect(); mSocket.off(); mSocket.close();}
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainActivity=this;

        mSocket.connect();
        new CountDownTimer(3000, 3000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                replaceLoginFragment();
            }
        }.start();
    }


    private void replaceLoginFragment() {

        loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_login, loginFragment)
                .commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        mSocket.disconnect();
        super.onDestroy();
    }
}