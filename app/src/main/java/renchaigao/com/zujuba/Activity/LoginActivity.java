package renchaigao.com.zujuba.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import renchaigao.com.zujuba.Json.User;
import renchaigao.com.zujuba.Json.UserLogin;
import renchaigao.com.zujuba.R;
import renchaigao.com.zujuba.util.FinalDefine;
import renchaigao.com.zujuba.util.PictureRAR;
import renchaigao.com.zujuba.util.PropertiesConfig;
import renchaigao.com.zujuba.util.RespCode;
import renchaigao.com.zujuba.util.SecurityFunc;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private final String TAG = "LoginActivity";


    private TextInputEditText login_telephone_TextInputEditText, login_pwd_TextInputEditText;
    private TextInputLayout login_telephone_TextInputLayout, login_pwd_TextInputLayout;
    private Button login_enter_button;
    private UserLogin userLogin;
    private boolean telephoneChangeFlag, pwdChangeFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setToolBar();
        initView();
        initData();
        initClick();
    }

    private void initData() {
        userLogin = new UserLogin();
    }

    private void initView() {
        login_enter_button = findViewById(R.id.login_enter_button);
        login_telephone_TextInputLayout = findViewById(R.id.login_telephone_TextInputLayout);
        login_pwd_TextInputLayout = findViewById(R.id.login_pwd_TextInputLayout);
        login_pwd_TextInputLayout.setPasswordVisibilityToggleEnabled(true);
        login_telephone_TextInputEditText = findViewById(R.id.login_telephone_TextInputEditText);
        login_pwd_TextInputEditText = findViewById(R.id.login_pwd_TextInputEditText);

        login_pwd_TextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 6) {
                    login_pwd_TextInputLayout.setError("请输入大于6位的密码");
                    pwdChangeFlag = false;
                } else {
                    login_pwd_TextInputLayout.setError(null);
                    pwdChangeFlag = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (SecurityFunc.checkPWDisOk(s.toString()) && pwdChangeFlag)
                    userLogin.setUserpwd(s.toString());
                else
                    login_pwd_TextInputLayout.setError("密码格式不对");
            }
        });

//        电话号码监听部分
        login_telephone_TextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e(TAG, start + " _ " + before + " _ " + count + " _ " + s.toString());
                if (s.length() < 11) {
                    login_telephone_TextInputLayout.setError("请输入正确的11位号码");
                    telephoneChangeFlag = false;
                } else if (s.length() > 11) {
                    login_telephone_TextInputLayout.setError("号码已超过11位，请修改");
                    telephoneChangeFlag = false;
                } else {
                    telephoneChangeFlag = true;
                    login_telephone_TextInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (telephoneChangeFlag)
                    userLogin.setTelephone(s.toString());
            }
        });
    }

    private void initClick() {
        login_enter_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser(JSONObject.parseObject(userLogin.toString()));
            }
        });
    }

    private boolean checkUserSendInfo() {
        String userTelephone = login_telephone_TextInputEditText.getText().toString();
        String userPwd = login_pwd_TextInputEditText.getText().toString();
        if (null != userTelephone && null != userPwd) {
            userLogin.setUserpwd(userPwd);
            userLogin.setUserpwd(userTelephone);
            return true;
        } else {
            Log.e(TAG, "checkUserSendInfo : wrong telephone or password");
            return false;
        }
    }

    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new BusinessActivity.TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    private void addUser(final JSONObject jsonObject) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = PropertiesConfig.testServerUrl + "user/add";
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.sslSocketFactory(createSSLSocketFactory());
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                String jsonStr = jsonObject.toString();
                final RequestBody body = RequestBody.create(FinalDefine.MEDIA_TYPE_JSON, jsonStr);
                final Request request = new Request.Builder()
                        .url(path)
                        .header("Content-Type", "application/json")
                        .post(body)
                        .build();

                builder.build().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, call.request().body().toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            JSONObject responseJson = JSONObject.parseObject(response.body().string());
                            JSONObject responseJsonData = (JSONObject) responseJson.getJSONObject("data");
                            int code = Integer.valueOf(responseJson.get("code").toString());
                            switch (code){
                                case 0:
                                    String token = responseJsonData.get("token").toString();
                                    SharedPreferences.Editor editor = getSharedPreferences("userData",MODE_PRIVATE).edit();
                                    editor.putString("token",token);
                                    editor.apply();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                            }
//                        switch (jsonObject1)
                            Log.e(TAG, response.body().string());

                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                    }
                });
            }
        }).start();
    }

}

