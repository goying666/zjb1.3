package renchaigao.com.zujuba.util;

import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import renchaigao.com.zujuba.Activity.CreateStoreActivity;

/**
 * Created by Administrator on 2018/7/27/027.
 */

public class OkhttpFunc {

//
//    private void addUser(final JSONObject jsonObject, final String mode) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String path = PropertiesConfig.testServerUrl + "user/add/" + mode;
//                OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                builder.sslSocketFactory(createSSLSocketFactory());
//                builder.hostnameVerifier(new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                });
//                String jsonStr = jsonObject.toString();
//                final RequestBody body = RequestBody.create(FinalDefine.MEDIA_TYPE_JSON, jsonStr);
//                final Request request = new Request.Builder()
//                        .url(path)
//                        .header("Content-Type", "application/json")
//                        .post(body)
//                        .build();
//
//                builder.build().newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        try {
//                            JSONObject responseJson = JSONObject.parseObject(response.body().string());
//                            int code = Integer.valueOf(responseJson.get("code").toString());
//                            JSONObject responseJsonData = (JSONObject) responseJson.getJSONObject("data");
//                            switch (code) {
//                                case 1: //在数据库中更新用户数据出错；
//                                    break;
//                            }
//                        } catch (Exception e) {
//                        }
//                    }
//                });
//            }
//        }).start();
//    }
//
//    public static JSONObject getResposeJsonObject(String action1) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                            .connectTimeout(15, TimeUnit.SECONDS)
//                            .readTimeout(15, TimeUnit.SECONDS)
//                            .writeTimeout(15, TimeUnit.SECONDS)
//                            .retryOnConnectionFailure(true);
//                    builder.sslSocketFactory(createSSLSocketFactory());
//                    builder.hostnameVerifier(new HostnameVerifier() {
//                        @Override
//                        public boolean verify(String hostname, SSLSession session) {
//                            return true;
//                        }
//                    });
//                    final Request request = new Request.Builder()
//                            .url(action1)
//                            .header("Content-Type", "application/json")
//                            .get()
//                            .build();
//                    builder.build().newCall(request).enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            try {
//                                JSONObject responseJson = JSONObject.parseObject(response.body().string());
//                                int code = Integer.valueOf(responseJson.get("code").toString());
//                                JSONObject responseJsonData = (JSONObject) responseJson.getJSONObject("data");
//                                switch (code) {
//                                    case 1: //在数据库中更新用户数据出错；
//                                        break;
//                                }
//                            } catch (Exception e) {
//                            }
//                        }
//                    });
//                    return null;
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//        }).start();
//    }


    public static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new CreateStoreActivity.TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }
}
