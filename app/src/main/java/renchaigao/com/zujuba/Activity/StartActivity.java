package renchaigao.com.zujuba.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import renchaigao.com.zujuba.R;
import renchaigao.com.zujuba.util.FinalDefine;
import renchaigao.com.zujuba.util.OkhttpFunc;
import renchaigao.com.zujuba.util.PropertiesConfig;

/**
 * Created by Administrator on 2018/7/27/027.
 */


public class StartActivity extends AppCompatActivity {

    private final String TAG = "StartActivity";
    private boolean hasGo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        autoLogin();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);       //此界面沉睡5秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!hasGo) {//hasGo为false说明还没有离开当前界面，说明后面的登录请求还没有返回响应；这种情况下就通过条件内代码启动login活动；
                    hasGo = true;
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }).start();
    }

    public void autoLogin() {
        try {
            SharedPreferences pref = getSharedPreferences("userData", MODE_PRIVATE);
            String dataJsonString = pref.getString("responseJsonDataString", null);
            JSONObject jsonObject = JSONObject.parseObject(dataJsonString);
            if (null != jsonObject)
                addUser(jsonObject, "auto");
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    private void addUser(final JSONObject jsonObject, final String mode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = PropertiesConfig.testServerUrl + "user/add/" + mode;
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                OkhttpFunc okhttpFunc = new OkhttpFunc();
                builder.sslSocketFactory(okhttpFunc.createSSLSocketFactory());
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
                            int code = Integer.valueOf(responseJson.get("code").toString());
                            JSONObject responseJsonData = (JSONObject) responseJson.getJSONObject("data");
                            String token;
                            SharedPreferences.Editor editor;
                            Intent intent;
                            switch (code) {
                                case 1: //在数据库中更新用户数据出错；
                                    Toast.makeText(StartActivity.this, "在数据库中更新用户数据出错", Toast.LENGTH_LONG).show();
                                    break;
                                case 1002: //用户是存在的，更新数据成功；
                                    //将token信息保存至本地
                                    token = responseJsonData.get("token").toString();
                                    editor = getSharedPreferences("userData", MODE_PRIVATE).edit();
                                    editor.putString("token", token);
                                    editor.putString("responseJsonDataString", responseJsonData.toJSONString());
                                    editor.apply();
                                    if (!hasGo) {//程序执行到这一步说明返回的数据已经回来，
                                        hasGo = true;
                                        intent = new Intent(StartActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    break;
                                case -1003: //用户是存在的，本地的TOKEN超时，需要重新登录；
                                    Toast.makeText(StartActivity.this, "本地的TOKEN超时，需要重新登录", Toast.LENGTH_LONG).show();
                                    break;
                                case -1004: //用户是存在的，本地的TOKEN错误；
                                    Toast.makeText(StartActivity.this, "本地的TOKEN错误", Toast.LENGTH_LONG).show();
                                    break;
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                    }
                });
            }
        }).start();
    }
}