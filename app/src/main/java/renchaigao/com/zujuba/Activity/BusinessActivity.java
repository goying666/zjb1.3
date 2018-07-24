package renchaigao.com.zujuba.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import renchaigao.com.zujuba.R;
import renchaigao.com.zujuba.util.FinalDefine;
import renchaigao.com.zujuba.util.PictureRAR;

public class BusinessActivity extends AppCompatActivity {
    private String TAG = "This is BusinessActivity ";
    private TextView business_join_introduce_addres_name, business_join_introduce_time_textView_number;
    private LinearLayout linearLayout_part1, linearLayout_part2, linearLayout_part3, linearLayout_part4;
    private Button business_join_introduce_button_next, business_join_basic_button_next, business_join_detail_button_back,
            business_join_detail_button_next, business_join_map_end_back, business_join_map_end_next;
    private ScrollView business_join_NestedScrollView;
    private CheckBox business_join_introduce_time_checkBox1, business_join_introduce_time_checkBox2, business_join_introduce_time_checkBox3, business_join_introduce_time_checkBox4;
    private Integer checkBoxNum = 0;
    private ImageView business_join_map_image_store_1, business_join_map_image_store_2, business_join_map_image_store_3, business_join_map_image_store_4,
            business_join_map_image_license_1, business_join_map_image_license_2, business_join_map_image_license_3;

    public static final int TAKE_PHOTO = 1;

    public static final int PHOTO_1 = 1001;
    public static final int PHOTO_2 = 1002;
    public static final int PHOTO_3 = 1003;
    public static final int PHOTO_4 = 1004;
    public static final int PHOTO_5 = 1005;
    public static final int PHOTO_6 = 1006;
    public static final int PHOTO_7 = 1007;

    public static final int CHOOSE_PHOTO = 2;
    public static final int ADD_ADDRESS = 3;

    private Uri imageUri;

    private void initView() {
        business_join_map_image_store_1 = findViewById(R.id.business_join_map_image_store_1);
        business_join_map_image_store_2 = findViewById(R.id.business_join_map_image_store_2);
        business_join_map_image_store_3 = findViewById(R.id.business_join_map_image_store_3);
        business_join_map_image_store_4 = findViewById(R.id.business_join_map_image_store_4);
        business_join_map_image_license_1 = findViewById(R.id.business_join_map_image_license_1);
        business_join_map_image_license_2 = findViewById(R.id.business_join_map_image_license_2);
        business_join_map_image_license_3 = findViewById(R.id.business_join_map_image_license_3);
        business_join_detail_button_back = findViewById(R.id.business_join_detail_button_back);
        business_join_detail_button_next = findViewById(R.id.business_join_detail_button_next);
        business_join_basic_button_next = findViewById(R.id.business_join_basic_button_next);
        business_join_introduce_button_next = findViewById(R.id.business_join_introduce_button_next);
        business_join_map_end_back = findViewById(R.id.business_join_map_end_back);
        business_join_map_end_next = findViewById(R.id.business_join_map_end_next);
        business_join_introduce_addres_name = findViewById(R.id.business_join_introduce_addres_name);
        business_join_NestedScrollView = findViewById(R.id.business_join_NestedScrollView);
        business_join_introduce_time_textView_number = findViewById(R.id.business_join_introduce_time_textView_number);
        business_join_introduce_time_checkBox1 = findViewById(R.id.business_join_introduce_time_checkBox1);
        business_join_introduce_time_checkBox2 = findViewById(R.id.business_join_introduce_time_checkBox2);
        business_join_introduce_time_checkBox3 = findViewById(R.id.business_join_introduce_time_checkBox3);
        business_join_introduce_time_checkBox4 = findViewById(R.id.business_join_introduce_time_checkBox4);
        linearLayout_part1 = findViewById(R.id.business_join_introduce_part1);
        linearLayout_part2 = findViewById(R.id.business_join_introduce_part2);
        linearLayout_part3 = findViewById(R.id.business_join_introduce_part3);
        linearLayout_part4 = findViewById(R.id.business_join_introduce_part4);
        setLinearLayoutVisibile(1);
    }

    private void initClick() {
        business_join_introduce_addres_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessActivity.this, MapBusinessActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        business_join_introduce_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(2);
            }
        });
        business_join_introduce_time_checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) checkBoxNum++;
                else checkBoxNum--;
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_introduce_time_checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) checkBoxNum++;
                else checkBoxNum--;
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_introduce_time_checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) checkBoxNum++;
                else checkBoxNum--;
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_introduce_time_checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) checkBoxNum++;
                else checkBoxNum--;
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_basic_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(3);
            }
        });
        business_join_detail_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(4);
            }
        });
        business_join_detail_button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(2);
            }
        });
        business_join_map_end_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendStoresAddInfoTest();
            }
        });
        business_join_map_end_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendStoresAddInfo();
            }
        });
        business_join_map_image_store_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto("photo1", PHOTO_1);
            }
        });
        business_join_map_image_store_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto("photo2", PHOTO_2);
            }
        });
        business_join_map_image_store_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto("photo3", PHOTO_3);
            }
        });
        business_join_map_image_store_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto("photo4", PHOTO_4);
            }
        });
        business_join_map_image_license_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto("photo5", PHOTO_5);
            }
        });
        business_join_map_image_license_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto("photo6", PHOTO_6);
            }
        });
        business_join_map_image_license_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto("photo7", PHOTO_7);
            }
        });
    }

    private void sendStoresAddInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                String path = "https://47.106.149.105/zujuba/join/addstores";
                String path = "http://192.168.199.155:7899/join/addpic";
                OkHttpClient client = new OkHttpClient();
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.sslSocketFactory(createSSLSocketFactory());
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                RequestBody requestBody = new FormBody.Builder().add("name", "gaoyan").build();

                File file = new File(getExternalCacheDir()+"/photo1.jpg");
                File file2 = new File(getExternalCacheDir()+"/photo2.jpg");
                File file3 = new File(getExternalCacheDir()+"/photo9.jpg");

                PictureRAR.qualityCompress(getExternalCacheDir()+"/photo2.jpg",file3);

//                File file = new File(Environment.getExternalStorageDirectory()+"/photo1.jpg");

                Log.e(TAG,Environment.getExternalStorageDirectory().toString());

                String jsonStr = "{\"name\":\"haha11\"}";//json数据.
                RequestBody body = RequestBody.create(FinalDefine.MEDIA_TYPE_JSON, jsonStr);
                RequestBody jsonBody = RequestBody.create(FinalDefine.MEDIA_TYPE_JPG,file);
                RequestBody jsonBody2 = RequestBody.create(FinalDefine.MEDIA_TYPE_JPG,file3);
//                RequestBody fileBody =;
                RequestBody multiBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file",file.getName(),jsonBody)
                        .addFormDataPart("photo2",file3.getName(),jsonBody2)
//                        .addPart(jsonBody)
//                        .addPart(body)
                        .build();

                Request request = new Request.Builder()
                        .url(path)
                        .header("Content-Type", "application/json")
                        .post(body)
                        .build();


                Request mulRrequest = new Request.Builder()
                        .url(path)
                        .header("Content-Type", "multipart/form-data")
                        .post(multiBody)
                        .build();

                builder.build().newCall(mulRrequest).enqueue(new Callback() {
//                builder.build().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        Log.e(TAG, call.request().body().toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        Log.e(TAG, response.body().string());
                    }
                });
//                    Response response = client.newCall(request).execute();
//                    Log.e(TAG, response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                        Log.e(TAG, call.request().body().toString());
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
//                        Log.e(TAG, response.body().string());
//                    }
//                });
////                    Response response = client.newCall(request).execute();
////                    Log.e(TAG, response.body().string());
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
            }
        }).start();
    }
    private void sendStoresAddInfoTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://192.168.199.155:7899/join/addstores";
                OkHttpClient client = new OkHttpClient();
                String jsonStr = "{\"name\":\"sendStoresAddInfoTest\"}";//json数据.
                RequestBody body = RequestBody.create(FinalDefine.MEDIA_TYPE_JSON, jsonStr);
                Request request = new Request.Builder()
                        .url(path)
                        .header("Content-Type", "application/json")
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        Log.e(TAG, call.request().body().toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        Log.e(TAG, response.body().string());
                    }
                });
//                    Response response = client.newCall(request).execute();
//                    Log.e(TAG, response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                        Log.e(TAG, call.request().body().toString());
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
//                        Log.e(TAG, response.body().string());
//                    }
//                });
////                    Response response = client.newCall(request).execute();
////                    Log.e(TAG, response.body().string());
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
            }
        }).start();
    }

    private void takePhoto(String photoName, int requestCode) {
        File outputImage = new File(getExternalCacheDir(), photoName + ".jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 24) {
            imageUri = Uri.fromFile(outputImage);
        } else {
            imageUri = FileProvider.getUriForFile(BusinessActivity.this, "com.example.cameraalbumtest.fileprovider", outputImage);
        }
        // 启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_1:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_store_1.setAdjustViewBounds(true);
                        business_join_map_image_store_1.setImageBitmap(bitmap);
                        business_join_map_image_store_1.setMaxWidth(600);
                        business_join_map_image_store_1.setMaxHeight(600);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_2:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_store_2.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_3:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_store_3.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_4:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_store_4.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_5:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_license_1.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_6:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_license_2.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_7:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_license_3.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            case ADD_ADDRESS:
                business_join_introduce_addres_name.setText(data.getStringExtra("addressJsonStr"));
                break;
//            case CHOOSE_PHOTO:
//                if (resultCode == RESULT_OK) {
//                    // 判断手机系统版本号
//                    if (Build.VERSION.SDK_INT >= 19) {
//                        // 4.4及以上系统使用这个方法处理图片
//                        handleImageOnKitKat(data);
//                    } else {
//                        // 4.4以下系统使用这个方法处理图片
//                        handleImageBeforeKitKat(data);
//                    }
//                }
//                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }

    private void setLinearLayoutVisibile(int whichOne) {
        linearLayout_part1.setVisibility(View.GONE);
        linearLayout_part2.setVisibility(View.GONE);
        linearLayout_part3.setVisibility(View.GONE);
        linearLayout_part4.setVisibility(View.GONE);
        switch (whichOne) {
            case 1:
                linearLayout_part1.setVisibility(View.VISIBLE);
                business_join_NestedScrollView.fullScroll(View.FOCUS_UP);
                break;
            case 2:
                linearLayout_part2.setVisibility(View.VISIBLE);
                business_join_NestedScrollView.fullScroll(View.FOCUS_UP);
                break;
            case 3:
                linearLayout_part3.setVisibility(View.VISIBLE);
                business_join_NestedScrollView.fullScroll(View.FOCUS_UP);
                break;
            case 4:
                linearLayout_part4.setVisibility(View.VISIBLE);
                business_join_NestedScrollView.fullScroll(View.FOCUS_UP);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar();
        setContentView(R.layout.activity_business);
        initView();
        initClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void setToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private long time = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - time > 1000)) {
                Toast.makeText(this, "再按一次取消入驻", Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    public static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
    }
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }
}
