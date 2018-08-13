package renchaigao.com.zujuba.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

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
    private String dataJsonString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        SharedPreferences pref = getSharedPreferences("userData", MODE_PRIVATE);
        dataJsonString = pref.getString("user", null);
        if (null != dataJsonString)
            sendAutoLogin();
        else {
            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void sendAutoLogin() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }

            @Override
            protected void onCancelled(Void aVoid) {
                super.onCancelled(aVoid);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected Void doInBackground(Void... params) {
                String path = PropertiesConfig.testServerUrl + "user/login/auto/0";
                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true);
                builder.sslSocketFactory(OkhttpFunc.createSSLSocketFactory());
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                final RequestBody body = RequestBody.create(FinalDefine.MEDIA_TYPE_JSON, dataJsonString);
                final Request request = new Request.Builder()
                        .url(path)
                        .header("Content-Type", "application/json")
                        .post(body)
                        .build();
                builder.build().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, call.request().body().toString());
                        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
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
                                case 0: //用户是存在的，更新数据成功；
                                    //将token信息保存至本地
                                    token = responseJsonData.get("token").toString();
                                    editor = getSharedPreferences("userData", MODE_PRIVATE).edit();
                                    editor.putString("token", token);
                                    editor.putString("user", responseJsonData.toJSONString());
                                    editor.apply();
                                    intent = new Intent(StartActivity.this, AdvertisingActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                case 1: //在数据库中更新用户数据出错；
                                    Toast.makeText(StartActivity.this, "在数据库中更新用户数据出错", Toast.LENGTH_LONG).show();
                                    break;
                                case -1003: //用户是存在的，本地的TOKEN超时，需要重新登录；
                                    Toast.makeText(StartActivity.this, "本地的TOKEN超时，需要重新登录", Toast.LENGTH_LONG).show();
                                    break;
                                case -1004: //用户是存在的，本地的TOKEN错误；
                                    Toast.makeText(StartActivity.this, "本地的TOKEN错误", Toast.LENGTH_LONG).show();
                                    break;
                                case -1005: //生成token错误；
                                    Toast.makeText(StartActivity.this, "生成TOKEN错误", Toast.LENGTH_LONG).show();
                                    break;
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.e(TAG, "onPostExecute");
            }
        }.execute();
    }
//    private void addUser(final String dataJsonString) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String path = PropertiesConfig.testServerUrl + "user/login/auto/0";
//                OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                OkhttpFunc okhttpFunc = new OkhttpFunc();
//                builder.sslSocketFactory(okhttpFunc.createSSLSocketFactory());
//                builder.hostnameVerifier(new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                });
//                final RequestBody body = RequestBody.create(FinalDefine.MEDIA_TYPE_JSON, dataJsonString);
//                final Request request = new Request.Builder()
//                        .url(path)
//                        .header("Content-Type", "application/json")
//                        .post(body)
//                        .build();
//                builder.build().newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.e(TAG, call.request().body().toString());
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        try {
//                            JSONObject responseJson = JSONObject.parseObject(response.body().string());
//                            int code = Integer.valueOf(responseJson.get("code").toString());
//                            JSONObject responseJsonData = (JSONObject) responseJson.getJSONObject("data");
//                            String token;
//                            SharedPreferences.Editor editor;
//                            Intent intent;
//                            switch (code) {
//                                case 1: //在数据库中更新用户数据出错；
//                                    Toast.makeText(StartActivity.this, "在数据库中更新用户数据出错", Toast.LENGTH_LONG).show();
//                                    break;
//                                case 1002: //用户是存在的，更新数据成功；
//                                    //将token信息保存至本地
//                                    token = responseJsonData.get("token").toString();
//                                    editor = getSharedPreferences("userData", MODE_PRIVATE).edit();
//                                    editor.putString("token", token);
//                                    editor.putString("responseJsonDataString", responseJsonData.toJSONString());
//                                    editor.apply();
//                                    if (!hasGo) {//程序执行到这一步说明返回的数据已经回来，
//                                        hasGo = true;
//                                        intent = new Intent(StartActivity.this, MainActivity.class);
//                                        startActivity(intent);
//                                        finish();
//                                    }
//                                    break;
//                                case -1003: //用户是存在的，本地的TOKEN超时，需要重新登录；
//                                    Toast.makeText(StartActivity.this, "本地的TOKEN超时，需要重新登录", Toast.LENGTH_LONG).show();
//                                    break;
//                                case -1004: //用户是存在的，本地的TOKEN错误；
//                                    Toast.makeText(StartActivity.this, "本地的TOKEN错误", Toast.LENGTH_LONG).show();
//                                    break;
//                            }
//                        } catch (Exception e) {
//                            Log.e(TAG, e.toString());
//                        }
//                    }
//                });
//            }
//        }).start();
//    }
}