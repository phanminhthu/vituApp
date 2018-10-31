package danazone.com.vitu.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import danazone.com.vitu.MainActivity;
import danazone.com.vitu.R;
import danazone.com.vitu.api.Common;
import danazone.com.vitu.model.InfoUser;
import danazone.com.vitu.sqlite.DBManager;
import danazone.com.vitu.util.Utils;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class LoginFragment extends Fragment {

    public static String TAG = LoginFragment.class.getSimpleName();

    Unbinder unbinder;
    @BindView(R.id.ln_header)
    LinearLayout lnHeader;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.tv_login_gg)
    TextView tvLoginGg;
    @BindView(R.id.fl_btn_login_gg)
    LinearLayout flBtnLoginGg;
    @BindView(R.id.tv_login_fb)
    TextView tvLoginFb;
    @BindView(R.id.fl_btn_login_fb)
    LinearLayout flBtnLoginFb;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    private Socket mSocket;
    private DBManager dbManager;
    private MainActivity mainActivity;

    private Emitter.Listener onLogin;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        onClickButton(view);
        mainActivity=MainActivity.mainActivity;
        mSocket=mainActivity.mSocket;

//        try {
//            mSocket = IO.socket(Common.URL_SOCKET);
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }


//        onLogin  = new Emitter.Listener() {
//            @Override
//            public void call(final Object... args) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.e("Rio event login",args[0].toString());
//                        Log.e("Rio event login",args[1].toString());
//                        if (args[0].toString().matches("true")) {
//                            Toast.makeText(getContext(), "" + args[1].toString(), Toast.LENGTH_SHORT).show();
////                        System.out.println("999999999999999999999" + args[1].toString());
////                        if (SessionManager.getInstance().getKeySavePhone().equals("") && SessionManager.getInstance().getKeySavePass().equals("")) {
////                            SessionManager.getInstance().setKeySavePhone(mEdtPhone.getText().toString().trim());
////                            SessionManager.getInstance().setKeySavePass(mEdtPassWord.getText().toString().trim());
////                        }
//                            //   System.out.println("11111111111111111111111111: " + homeTowm + degree);
//                            JSONObject jsonObject = (JSONObject) args[1];
//                            try {
//                                String name = jsonObject.getString("name");
//                                String phone = jsonObject.getString("phoneNumber");
//                                String email = jsonObject.getString("email");
//                                String gender = jsonObject.getString("sex");
//                                String school = jsonObject.getString("school");
//                                String birthday = jsonObject.getString("yearOfBirth");
//                                String homeTowm = jsonObject.getString("homeTown");
//                                String ex = jsonObject.getString("experience");
//                                String type = jsonObject.getString("type");
//                                String avatar = jsonObject.getString("avatar");
//                                String verify = jsonObject.getString("verified");
//                                String degree = jsonObject.getString("degree");
//                                String coin = jsonObject.getString("coin");
//
////                            System.out.println("1111111111111111111111111111111111111222: " + avatar);
//
//                                InfoUser infoUser = new InfoUser();
//                                infoUser.setName(name);
//                                infoUser.setPhone(phone);
//                                infoUser.setEmail(email);
//                                infoUser.setGender(gender);
//                                infoUser.setSchool(school);
//                                infoUser.setBirthday(birthday);
//                                infoUser.setHomeTown(homeTowm);
//                                infoUser.setExperience(Integer.valueOf(ex));
//                                infoUser.setType(Integer.valueOf(type));
//                                infoUser.setAvatar(avatar);
//                                infoUser.setVerify(Integer.valueOf(verify));
//                                infoUser.setDegree(degree);
//                                infoUser.setCoin(Integer.valueOf(coin));
//                                dbManager.addIfoUser(infoUser);
//
////                            MainActivity_.intent(LoginActivity.this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP
////                                    | Intent.FLAG_ACTIVITY_CLEAR_TASK
////                                    | Intent.FLAG_ACTIVITY_NEW_TASK).start();
////                            finish();
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        } else {
//                            Utils.showAlertDialog(getContext(),"" + args[1].toString());
//                        }
//                    }
//                });
//            }
//        };

onLogin = new Emitter.Listener() {
    @Override
    public void call(Object... args) {
        Log.e("Rio event login",args[0].toString());
        Log.e("Rio event login",args[1].toString());
    }
};
        mSocket.on("login", onLogin);
        return view;
    }

    @OnClick({R.id.fl_btn_login_gg, R.id.fl_btn_login_fb, R.id.tv_login})
    void onClickButton(View view) {
        switch (view.getId()) {

            case R.id.fl_btn_login_gg:

                break;

            case R.id.fl_btn_login_fb:

                break;

            case R.id.tv_login:

                Log.e("Rio event login","onClickButton");
                JSONObject object = new JSONObject();
                try {
                    object.put("phoneNumber", "01234567999");
                    object.put("pass", "111111");
                    object.put("species", 0);
                    object.put("type", 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mSocket.emit("login", object);
                break;
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
