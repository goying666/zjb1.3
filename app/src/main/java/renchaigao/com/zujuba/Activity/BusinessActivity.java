package renchaigao.com.zujuba.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

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
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import renchaigao.com.zujuba.Json.Store;
import renchaigao.com.zujuba.R;
import renchaigao.com.zujuba.util.DataUtil;
import renchaigao.com.zujuba.util.FinalDefine;
import renchaigao.com.zujuba.util.ImgUtil;
import renchaigao.com.zujuba.util.PatternUtil;
import renchaigao.com.zujuba.util.PictureRAR;
import renchaigao.com.zujuba.util.PropertiesConfig;

public class BusinessActivity extends AppCompatActivity {
    private String TAG = "This is BusinessActivity ";
    private TextView business_join_introduce_addres_name, business_join_introduce_time_textView_number, business_join_introduce_time_textView_title_note;
    private LinearLayout linearLayout_part1, linearLayout_part2, linearLayout_part3, linearLayout_part4, business_join_introduce_part5;
    private Button business_join_introduce_button_next, business_join_basic_button_next, business_join_detail_button_back,
            business_join_detail_button_next, business_join_map_image_back, business_join_map_image_next, business_join_map_end_back, business_join_map_end_next;
    private ScrollView business_join_NestedScrollView;
    private CheckBox business_join_introduce_time_checkBox1, business_join_introduce_time_checkBox2, business_join_introduce_time_checkBox3, business_join_introduce_time_checkBox4;
    private Integer checkBoxNum = 0;
    private ImageView business_join_map_image_store_1, business_join_map_image_store_2, business_join_map_image_store_3, business_join_map_image_store_4,
            business_join_map_image_license_1, business_join_map_image_license_2, business_join_map_image_license_3, business_join_introduce_addres_image;
    private TextInputLayout business_join_name_layout, business_join_introduce_content_TextInputLayout_person,
            business_join_introduce_content_TextInputLayout_person2, business_join_introduce_content_TextInputLayout_name,
            business_join_desk_layout, business_join_maxpeople_layout, business_join_extra_storeinfo_TextInputLayout;
    private TextInputEditText business_join_name, business_join_introduce_addres_addinfo, business_telephone1, business_telephone2, business_telephone_name,
            business_join_desk_num, business_join_maxpeople_num, business_join_extra_storeinfo;
    private AppCompatSpinner business_join_class;
    private AppCompatCheckBox business_join_other_equipment_air, business_join_other_equipment_wifi,
            business_join_other_equipment_hot, business_join_other_equipment_wc;
    private AlertDialog.Builder builder, builderPhoto;
    private ProgressDialog progDialog;
    private boolean imageFinish1, imageFinish2, imageFinish3, imageFinish4, imageFinish5, imageFinish6, imageFinish7;
    private Integer imageFlag = 0;
    private Bitmap bitmapPhoto;

    private class photoImage {

    }


    public static final int PHOTO_1 = 1001;
    public static final int PHOTO_2 = 1002;
    public static final int PHOTO_3 = 1003;
    public static final int PHOTO_4 = 1004;
    public static final int PHOTO_5 = 1005;
    public static final int PHOTO_6 = 1006;
    public static final int PHOTO_7 = 1007;

    public static final int PICK_PHOTO_1 = 1011;
    public static final int PICK_PHOTO_2 = 1012;
    public static final int PICK_PHOTO_3 = 1013;
    public static final int PICK_PHOTO_4 = 1014;
    public static final int PICK_PHOTO_5 = 1015;
    public static final int PICK_PHOTO_6 = 1016;
    public static final int PICK_PHOTO_7 = 1017;

    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    public static final int ADD_ADDRESS = 3;

    private Uri imageUri;

    private Store store = new Store();

    private void initView() {
        store = new Store();
        store.setWorkingtimeid(0x0);
        store.setHardware(0x0);
        builder = new AlertDialog.Builder(this);
        business_join_introduce_button_next = findViewById(R.id.business_join_introduce_button_next);
        business_join_NestedScrollView = findViewById(R.id.business_join_NestedScrollView);

        business_join_introduce_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(2);
            }
        });
        linearLayout_part1 = findViewById(R.id.business_join_introduce_part1);

    }

    //        基础信息部分
    private void initBasicPart() {
        business_join_introduce_time_textView_number = findViewById(R.id.business_join_introduce_time_textView_number);
        business_join_basic_button_next = findViewById(R.id.business_join_basic_button_next);
        linearLayout_part2 = findViewById(R.id.business_join_introduce_part2);
        business_join_name = findViewById(R.id.business_join_name);
        business_join_class = findViewById(R.id.business_join_class);
        business_join_introduce_addres_image = findViewById(R.id.business_join_introduce_addres_image);
        business_join_introduce_addres_name = findViewById(R.id.business_join_introduce_addres_name);
        business_join_introduce_addres_addinfo = findViewById(R.id.business_join_introduce_addres_addinfo);
        business_join_introduce_content_TextInputLayout_person = findViewById(R.id.business_join_introduce_content_TextInputLayout_person);
        business_join_introduce_content_TextInputLayout_person2 = findViewById(R.id.business_join_introduce_content_TextInputLayout_person2);
        business_join_introduce_content_TextInputLayout_name = findViewById(R.id.business_join_introduce_content_TextInputLayout_name);
        business_telephone1 = findViewById(R.id.business_telephone1);
        business_telephone2 = findViewById(R.id.business_telephone2);
        business_telephone_name = findViewById(R.id.business_telephone_name);
        business_join_name_layout = findViewById(R.id.business_join_name_layout);
        business_join_introduce_time_textView_title_note = findViewById(R.id.business_join_introduce_time_textView_title_note);
        business_join_introduce_time_textView_title_note.setVisibility(View.VISIBLE);
        business_join_introduce_time_checkBox1 = findViewById(R.id.business_join_introduce_time_checkBox1);
        business_join_introduce_time_checkBox2 = findViewById(R.id.business_join_introduce_time_checkBox2);
        business_join_introduce_time_checkBox3 = findViewById(R.id.business_join_introduce_time_checkBox3);
        business_join_introduce_time_checkBox4 = findViewById(R.id.business_join_introduce_time_checkBox4);
        business_join_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                store.setStoreclass(getResources().getStringArray(R.array.business_class_array)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        business_join_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 3) {
                    business_join_name.setError("请输入大于3个字符的名字");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 3) {
                    store.setName(s.toString());
                    business_join_name.setError(null);
                } else {
                    business_join_name.setError("请输入正确的商铺名称，方便客户上门。");
                }
            }
        });
        business_join_introduce_addres_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessActivity.this, MapBusinessActivity.class);
                startActivityForResult(intent, ADD_ADDRESS);
            }
        });
        business_join_introduce_addres_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessActivity.this, MapBusinessActivity.class);
                startActivityForResult(intent, ADD_ADDRESS);
            }
        });
        business_join_introduce_addres_addinfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                store.setPlaceinfo(s.toString());
            }
        });
        business_telephone_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null != s.toString()) {
                    store.setContact(s.toString());
                } else
                    business_join_introduce_content_TextInputLayout_name.setError("请输入联系人");
            }
        });
        business_telephone1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null != s.toString()) {
                    store.setTelephonenum(s.toString());
                } else
                    business_join_introduce_content_TextInputLayout_person.setError("请输入号码");
            }
        });
        business_telephone2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null != s.toString()) {
                    store.setPhonenum(s.toString());
                }
            }
        });
        business_join_introduce_time_checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    checkBoxNum++;
                    store.setWorkingtimeid(store.getWorkingtimeid() | 0x1);
                } else {
                    store.setWorkingtimeid(store.getWorkingtimeid() & 0x1110);
                    checkBoxNum--;
                }
                Log.e(TAG, JSONObject.toJSONString(store));
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_introduce_time_checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    checkBoxNum++;
                    store.setWorkingtimeid(store.getWorkingtimeid() | 0x10);
                } else {
                    store.setWorkingtimeid(store.getWorkingtimeid() & 0x1101);
                    checkBoxNum--;
                }
                Log.e(TAG, JSONObject.toJSONString(store));
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_introduce_time_checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    checkBoxNum++;
                    store.setWorkingtimeid(store.getWorkingtimeid() | 0x100);
                } else {
                    store.setWorkingtimeid(store.getWorkingtimeid() & 0x1011);
                    checkBoxNum--;
                }
                Log.e(TAG, JSONObject.toJSONString(store));
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_introduce_time_checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    checkBoxNum++;
                    store.setWorkingtimeid(store.getWorkingtimeid() | 0x1000);
                } else {
                    store.setWorkingtimeid(store.getWorkingtimeid() & 0x111);
                    checkBoxNum--;
                }
                Log.e(TAG, JSONObject.toJSONString(store));
                business_join_introduce_time_textView_number.setText(checkBoxNum.toString());
            }
        });
        business_join_basic_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBasicPart())
                    setLinearLayoutVisibile(3);
            }
        });
    }

    //          附加信息部分
    private void initExtraPart() {
        business_join_detail_button_back = findViewById(R.id.business_join_detail_button_back);
        business_join_detail_button_next = findViewById(R.id.business_join_detail_button_next);
        linearLayout_part3 = findViewById(R.id.business_join_introduce_part3);
        business_join_desk_layout = findViewById(R.id.business_join_desk_layout);
        business_join_maxpeople_layout = findViewById(R.id.business_join_maxpeople_layout);
        business_join_desk_num = findViewById(R.id.business_join_desk_num);
        business_join_maxpeople_num = findViewById(R.id.business_join_maxpeople_num);
        business_join_other_equipment_air = findViewById(R.id.business_join_other_equipment_air);
        business_join_other_equipment_wifi = findViewById(R.id.business_join_other_equipment_wifi);
        business_join_other_equipment_hot = findViewById(R.id.business_join_other_equipment_hot);
        business_join_other_equipment_wc = findViewById(R.id.business_join_other_equipment_wc);
        business_join_extra_storeinfo_TextInputLayout = findViewById(R.id.business_join_extra_storeinfo_TextInputLayout);
        business_join_extra_storeinfo = findViewById(R.id.business_join_extra_storeinfo);

//        绑定监听
        business_join_desk_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (PatternUtil.strMatcher(s.toString(), PatternUtil.FUNC_NUMBER_1_99)) {
                    business_join_desk_layout.setError("");
                } else business_join_desk_layout.setError("请输入1到99的数字");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (PatternUtil.strMatcher(s.toString(), PatternUtil.FUNC_NUMBER_1_99)) {
                    store.setMaxdesknum(Integer.valueOf(s.toString()));
                    business_join_desk_layout.setError("");
                } else business_join_desk_layout.setError("请输入1到99的数字");
            }
        });
        business_join_maxpeople_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (PatternUtil.strMatcher(s.toString(), PatternUtil.FUNC_NUMBER_1_99)) {
                    business_join_maxpeople_layout.setError("");
                } else business_join_maxpeople_layout.setError("请输入1到99的数字");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (PatternUtil.strMatcher(s.toString(), PatternUtil.FUNC_NUMBER_1_99)) {
                    store.setMaxpeoplenum(Integer.valueOf(s.toString()));
                    business_join_maxpeople_layout.setError("");
                } else business_join_maxpeople_layout.setError("请输入1到99的数字");
            }
        });
        business_join_extra_storeinfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString() != null)
                    store.setStoreinfo(s.toString());
                else {
                }
            }
        });
        business_join_other_equipment_air.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    store.setHardware(store.getHardware() | 0x1);
                } else {
                    store.setHardware(store.getHardware() & 0x1110);
                }
            }
        });
        business_join_other_equipment_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    store.setHardware(store.getHardware() | 0x10);
                } else {
                    store.setHardware(store.getHardware() & 0x1101);
                }
            }
        });
        business_join_other_equipment_hot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    store.setHardware(store.getHardware() | 0x100);
                } else {
                    store.setHardware(store.getHardware() & 0x1011);
                }
            }
        });
        business_join_other_equipment_wc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    store.setHardware(store.getHardware() | 0x1000);
                } else {
                    store.setHardware(store.getHardware() & 0x111);
                }
            }
        });
        business_join_detail_button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(2);
            }
        });
        business_join_detail_button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkExtraPart())
                    setLinearLayoutVisibile(4);
            }
        });
    }

    //          图片信息部分
    private void initPhotoPart() {
        business_join_map_end_back = findViewById(R.id.business_join_map_end_back);
        business_join_map_end_next = findViewById(R.id.business_join_map_end_next);
        linearLayout_part4 = findViewById(R.id.business_join_introduce_part4);
        business_join_map_image_store_1 = findViewById(R.id.business_join_map_image_store_1);
        business_join_map_image_store_2 = findViewById(R.id.business_join_map_image_store_2);
        business_join_map_image_store_3 = findViewById(R.id.business_join_map_image_store_3);
        business_join_map_image_store_4 = findViewById(R.id.business_join_map_image_store_4);
        business_join_map_image_license_1 = findViewById(R.id.business_join_map_image_license_1);
        business_join_map_image_license_2 = findViewById(R.id.business_join_map_image_license_2);
        business_join_map_image_license_3 = findViewById(R.id.business_join_map_image_license_3);
        business_join_map_image_back = findViewById(R.id.business_join_map_image_back);
        business_join_map_image_next = findViewById(R.id.business_join_map_image_next);
        imageFinish1 = false;
        imageFinish2 = false;
        imageFinish3 = false;
        imageFinish4 = false;
        imageFinish5 = false;
        imageFinish6 = false;
        imageFinish7 = false;
        builderPhoto = new AlertDialog.Builder(this);
        builderPhoto.setTitle("图片来源");
        builderPhoto.setMessage("选择图片获取方式");
        builderPhoto.setNegativeButton("本地相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openAlbum();
            }
        });
        business_join_map_image_store_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFlag = PICK_PHOTO_1;
                choosePhoto(PHOTO_1);
            }
        });
        business_join_map_image_store_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFlag = PICK_PHOTO_2;
                choosePhoto(PHOTO_2);
            }
        });
        business_join_map_image_store_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFlag = PICK_PHOTO_3;
                choosePhoto(PHOTO_3);
            }
        });
        business_join_map_image_store_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFlag = PICK_PHOTO_4;
                choosePhoto(PHOTO_4);
            }
        });
        business_join_map_image_license_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFlag = PICK_PHOTO_5;
                choosePhoto(PHOTO_5);
            }
        });
        business_join_map_image_license_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFlag = PICK_PHOTO_6;
                choosePhoto(PHOTO_6);
            }
        });
        business_join_map_image_license_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFlag = PICK_PHOTO_7;
                choosePhoto(PHOTO_7);
            }
        });
        business_join_map_image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(3);
            }
        });
        business_join_map_image_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkMapPart())
                    setLinearLayoutVisibile(5);
                else
                    Toast.makeText(BusinessActivity.this, "请完善所有图片", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void choosePhoto(final Integer j) {
        builderPhoto.setPositiveButton("相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                takePhoto("photo" + imageFlag.toString(), j);
            }
        });
        builderPhoto.show();
    }

    //          所有信息展示部分
    private void initFinishPart() {
        progDialog = new ProgressDialog(BusinessActivity.this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在加载...");
        builder.setTitle("确认");
        builder.setMessage("确定提交吗？");
        builder.setNegativeButton("否", null);
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendStoresAddInfo();
                Toast.makeText(BusinessActivity.this, "你点击了确定发送", Toast.LENGTH_LONG).show();
            }
        });
        business_join_introduce_part5 = findViewById(R.id.business_join_introduce_part5);
        business_join_map_end_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLinearLayoutVisibile(4);
            }
        });
        business_join_map_end_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });
    }

    private boolean checkBasicPart() {
        if (store.getName() != null) {
            if (store.getFormataddress() != null) {
                if (store.getContact() != null) {
                    if (store.getTelephonenum() != null) {
                        if (store.getWorkingtimeid() > 0) {
                            business_join_introduce_time_textView_title_note.setVisibility(View.GONE);
                            return true;
                        } else {
                            business_join_introduce_time_textView_title_note.setVisibility(View.VISIBLE);
                            business_join_introduce_time_textView_title_note.setError("请至少选择一个营业时间段");
                            Toast.makeText(this, "请至少选择一个营业时间段", Toast.LENGTH_LONG).show();
                            return false;
                        }
                    } else {
                        business_join_introduce_content_TextInputLayout_person.setError("请完善店铺联系人电话信息");
                        Toast.makeText(this, "请完善店铺联系人电话信息", Toast.LENGTH_LONG).show();
                        return false;
                    }
                } else {
                    business_telephone_name.setError("请完善店铺联系人信息");
                    Toast.makeText(this, "请完善店铺联系人信息", Toast.LENGTH_LONG).show();
                    return false;
                }
            } else {
                business_join_name_layout.setError("请完善店铺地址信息");
                Toast.makeText(this, "请完善店铺地址信息", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            business_join_name_layout.setError("请完善店铺名称");
            Toast.makeText(this, "请完善店铺名称", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean checkExtraPart() {
        return true;
    }

    private boolean checkMapPart() {
        if (imageFinish1 & imageFinish2 & imageFinish3 & imageFinish4 & imageFinish5 & imageFinish6 & imageFinish7)
            return true;
        else return false;
    }

    private void sendStoresAddInfo() {
        /*进度条后续优化*/
        progDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                String path = "https://47.106.149.105/zujuba/join/addstores";

                String path = PropertiesConfig.serverUrl + "store/join";
                OkHttpClient client = new OkHttpClient();
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.sslSocketFactory(createSSLSocketFactory());
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                File photo1 = new File(getExternalCacheDir() + "/photo1.jpg");
                File photo2 = new File(getExternalCacheDir() + "/photo2.jpg");
                File photo3 = new File(getExternalCacheDir() + "/photo3.jpg");
                File photo4 = new File(getExternalCacheDir() + "/photo4.jpg");
                File photo5 = new File(getExternalCacheDir() + "/photo5.jpg");
                File photo6 = new File(getExternalCacheDir() + "/photo6.jpg");
                File photo7 = new File(getExternalCacheDir() + "/photo7.jpg");
                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo1.jpg", photo1);
                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo2.jpg", photo2);
                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo3.jpg", photo3);
                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo4.jpg", photo4);
                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo5.jpg", photo5);
                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo6.jpg", photo6);
                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo7.jpg", photo7);

                RequestBody fileBodyPhoto1 = RequestBody.create(FinalDefine.MEDIA_TYPE_JPG, photo1);
                RequestBody fileBodyPhoto2 = RequestBody.create(FinalDefine.MEDIA_TYPE_JPG, photo2);
                RequestBody fileBodyPhoto3 = RequestBody.create(FinalDefine.MEDIA_TYPE_JPG, photo3);
                RequestBody fileBodyPhoto4 = RequestBody.create(FinalDefine.MEDIA_TYPE_JPG, photo4);
                RequestBody fileBodyPhoto5 = RequestBody.create(FinalDefine.MEDIA_TYPE_JPG, photo5);
                RequestBody fileBodyPhoto6 = RequestBody.create(FinalDefine.MEDIA_TYPE_JPG, photo6);
                RequestBody fileBodyPhoto7 = RequestBody.create(FinalDefine.MEDIA_TYPE_JPG, photo7);

                store.setOwnerid(DataUtil.getUserData(BusinessActivity.this).getId());
                String storeString = JSONObject.toJSONString(store);
                RequestBody jsonBody = RequestBody.create(FinalDefine.MEDIA_TYPE_JSON, storeString);

                RequestBody multiBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("json", storeString)
                        .addFormDataPart("photo", photo1.getName(), fileBodyPhoto1)
                        .addFormDataPart("photo", photo2.getName(), fileBodyPhoto2)
                        .addFormDataPart("photo", photo3.getName(), fileBodyPhoto3)
                        .addFormDataPart("photo", photo4.getName(), fileBodyPhoto4)
                        .addFormDataPart("photo", photo5.getName(), fileBodyPhoto5)
                        .addFormDataPart("photo", photo6.getName(), fileBodyPhoto6)
                        .addFormDataPart("photo", photo7.getName(), fileBodyPhoto7)
                        .build();
//                File rarphoto1 = new File(getExternalCacheDir() + "/rarphoto1,jpg");
//                File rarphoto2 = new File(getExternalCacheDir() + "/rarphoto2,jpg");
//                File rarphoto3 = new File(getExternalCacheDir() + "/rarphoto3,jpg");
//                File rarphoto4 = new File(getExternalCacheDir() + "/rarphoto4,jpg");
//                File rarphoto5 = new File(getExternalCacheDir() + "/rarphoto5,jpg");
//                File rarphoto6 = new File(getExternalCacheDir() + "/rarphoto6,jpg");
//                File rarphoto7 = new File(getExternalCacheDir() + "/rarphoto7,jpg");


//                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo1.jpg", rarphoto1);
//                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo2.jpg", rarphoto2);
//                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo3.jpg", rarphoto3);
//                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo4.jpg", rarphoto4);
//                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo5.jpg", rarphoto5);
//                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo6.jpg", rarphoto6);
//                PictureRAR.qualityCompress(getExternalCacheDir() + "/photo7.jpg", rarphoto7);

//                File file = new File(Environment.getExternalStorageDirectory()+"/photo1.jpg");

//                String jsonStr = "{\"name\":\"haha11\"}";//json数据.
//
//                RequestBody body = RequestBody.create(FinalDefine.MEDIA_TYPE_JSON, jsonStr);
//                RequestBody jsonBody = RequestBody.create(FinalDefine.MEDIA_TYPE_JPG, file);
//                RequestBody jsonBody2 = RequestBody.create(FinalDefine.MEDIA_TYPE_JPG, file3);
////                RequestBody fileBody =;
//                RequestBody multiBody = new MultipartBody.Builder()
//                        .setType(MultipartBody.FORM)
//                        .addFormDataPart("file", file.getName(), jsonBody)
//                        .addFormDataPart("photo2", file3.getName(), jsonBody2)
//                        .addFormDataPart("json", jsonStr)
//                        .addPart(body)
////                        .addPart(jsonBody)
////                        .addPart(body)
//                        .build();

//                Request request = new Request.Builder()
//                        .url(PropertiesConfig.serverUrl + path)
//                        .header("Content-Type", "application/json")
//                        .post(body)
//                        .build();
                Request mulRrequest = new Request.Builder()
                        .url(path)
                        .header("Content-Type", "multipart/form-data")
                        .post(multiBody)
                        .build();

                builder.build().newCall(mulRrequest).enqueue(new Callback() {
                    //                builder.build().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        dismissDialog();
                        Log.e(TAG, call.request().body().toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
//                        确认上传成功，结束当前活动；
                        JSONObject responseJson = JSONObject.parseObject(response.body().string());
                        int code = Integer.valueOf(responseJson.get("code").toString());
                        JSONObject responseJsonData = (JSONObject) responseJson.getJSONObject("data");
                        switch (code) {
                            case 0:
                                dismissDialog();
                                finish();
                                break;
                            case 1:
                                Toast.makeText(BusinessActivity.this, "Throw an exception", Toast.LENGTH_LONG).show();
                                dismissDialog();
                                break;
                        }
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

    public void dismissDialog() {
        Log.e(TAG, "dismissDialog");
        if (progDialog != null) {
            progDialog.dismiss();
        }
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
                        bitmapPhoto = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_store_1.setAdjustViewBounds(true);
                        business_join_map_image_store_1.setImageBitmap(bitmapPhoto);
                        imageFinish1 = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_2:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        bitmapPhoto = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_store_2.setImageBitmap(bitmapPhoto);
                        imageFinish2 = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_3:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        bitmapPhoto = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_store_3.setImageBitmap(bitmapPhoto);
                        imageFinish3 = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_4:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        bitmapPhoto = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_store_4.setImageBitmap(bitmapPhoto);
                        imageFinish4 = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_5:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        bitmapPhoto = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_license_1.setImageBitmap(bitmapPhoto);
                        imageFinish5 = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_6:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        bitmapPhoto = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_license_2.setImageBitmap(bitmapPhoto);
                        imageFinish6 = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PHOTO_7:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        bitmapPhoto = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        business_join_map_image_license_3.setImageBitmap(bitmapPhoto);
                        imageFinish7 = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            case ADD_ADDRESS:
                Store storeUse = JSONObject.parseObject(data.getStringExtra("addressStoreJsonStr").toString(), Store.class);
                store.setCity(storeUse.getCity());
                store.setCitycode(storeUse.getCitycode());
                store.setDistrict(storeUse.getDistrict());
                store.setFormataddress(storeUse.getFormataddress());
                store.setNeighborhood(storeUse.getNeighborhood());
                store.setProvince(storeUse.getProvince());
                store.setTowncode(storeUse.getTowncode());
                store.setTownship(storeUse.getTownship());
                store.setLatitude(storeUse.getLatitude());
                store.setLongitude(storeUse.getLongitude());
                business_join_introduce_addres_name.setText(data.getStringExtra("addressJsonStr"));
                break;
//                通过相册选择的图片和对应的显示设置；
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmapPhoto;
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        bitmapPhoto = ImgUtil.handleImageOnKitKat(this, data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        bitmapPhoto = ImgUtil.handleImageBeforeKitKat(this, data);
                    }
                    switch (imageFlag) {
                        case PICK_PHOTO_1:
                            imageFinish1 = true;
                            String test = getExternalCacheDir() + "/photo1.jpg";
                            ImgUtil.bitmapToFile(bitmapPhoto, getExternalCacheDir() + "/photo1.jpg");
                            business_join_map_image_store_1.setImageBitmap(bitmapPhoto);
                            break;
                        case PICK_PHOTO_2:
                            imageFinish2 = true;
                            ImgUtil.bitmapToFile(bitmapPhoto, getExternalCacheDir() + "/photo2.jpg");
                            business_join_map_image_store_2.setImageBitmap(bitmapPhoto);
                            break;
                        case PICK_PHOTO_3:
                            imageFinish3 = true;
                            ImgUtil.bitmapToFile(bitmapPhoto, getExternalCacheDir() + "/photo3.jpg");
                            business_join_map_image_store_3.setImageBitmap(bitmapPhoto);
                            break;
                        case PICK_PHOTO_4:
                            imageFinish4 = true;
                            ImgUtil.bitmapToFile(bitmapPhoto, getExternalCacheDir() + "/photo4.jpg");
                            business_join_map_image_store_4.setImageBitmap(bitmapPhoto);
                            break;
                        case PICK_PHOTO_5:
                            imageFinish5 = true;
                            ImgUtil.bitmapToFile(bitmapPhoto, getExternalCacheDir() + "/photo5.jpg");
                            business_join_map_image_license_1.setImageBitmap(bitmapPhoto);
                            break;
                        case PICK_PHOTO_6:
                            imageFinish6 = true;
                            ImgUtil.bitmapToFile(bitmapPhoto, getExternalCacheDir() + "/photo6.jpg");
                            business_join_map_image_license_2.setImageBitmap(bitmapPhoto);
                            break;
                        case PICK_PHOTO_7:
                            imageFinish7 = true;
                            ImgUtil.bitmapToFile(bitmapPhoto, getExternalCacheDir() + "/photo7.jpg");
                            business_join_map_image_license_3.setImageBitmap(bitmapPhoto);
                            break;
                    }
                }
                break;
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
        business_join_introduce_part5.setVisibility(View.GONE);
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
            case 5:
                business_join_introduce_part5.setVisibility(View.VISIBLE);
                business_join_NestedScrollView.fullScroll(View.FOCUS_UP);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        setToolBar();
        initView();
        initBasicPart();
        initExtraPart();
        initPhotoPart();
        initFinishPart();
        setLinearLayoutVisibile(1);
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
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
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
