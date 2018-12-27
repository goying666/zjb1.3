package renchaigao.com.zujuba.Activity.Normal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import renchaigao.com.zujuba.Activity.AdvertisingActivity;
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
    // Handler内部类，它的引用在子线程中被使用，发送mesage，被handlerMesage方法接收
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            String str = (String) msg.obj;
            Toast.makeText(StartActivity.this, str, Toast.LENGTH_SHORT).show();
        }

        ;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        LitePal.initialize(this);
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
                String path = PropertiesConfig.userServerUrl + "login/auto/0";
                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .connectTimeout(3, TimeUnit.SECONDS)
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
                String str = dataJsonString;
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
                            Message msg = new Message();
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
                                    msg.obj = "登录成功";
                                    // 把消息发送到主线程，在主线程里现实Toast
                                    handler.sendMessage(msg);
                                    finish();
                                    break;
                                case 1: //在数据库中更新用户数据出错；
                                    intent = new Intent(StartActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                case -1003: //用户是存在的，本地的TOKEN超时，需要重新登录；
                                    intent = new Intent(StartActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    msg.obj = "token过期";
                                    // 把消息发送到主线程，在主线程里现实Toast
                                    handler.sendMessage(msg);
                                    finish();
                                    break;
                                case -1004: //用户是存在的，本地的TOKEN错误；
                                    intent = new Intent(StartActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    msg.obj = "token错误";
                                    // 把消息发送到主线程，在主线程里现实Toast
                                    handler.sendMessage(msg);
                                    finish();
                                    break;
                                case -1005: //生成token错误；
                                    intent = new Intent(StartActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    msg.obj = "生成token错误";
                                    // 把消息发送到主线程，在主线程里现实Toast
                                    handler.sendMessage(msg);
                                    finish();
                                    break;

                                case -1010: //没有找到用户；
                                    intent = new Intent(StartActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    msg.obj = "用户不存在，请确认手机号正确";
                                    // 把消息发送到主线程，在主线程里现实Toast
                                    handler.sendMessage(msg);
                                    finish();
                                    break;

                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
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