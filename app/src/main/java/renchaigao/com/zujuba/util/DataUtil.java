package renchaigao.com.zujuba.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.idst.nls.internal.protocol.Content;

import renchaigao.com.zujuba.Json.Store;
import renchaigao.com.zujuba.Json.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/7/27/027.
 */

public class DataUtil {
    private String userDataString;

    public static User getUserData(Context context) {
        try {
            SharedPreferences pref = context.getSharedPreferences("userData", MODE_PRIVATE);
            String dataJsonString = pref.getString("responseJsonDataString", null);
            JSONObject jsonObject = JSONObject.parseObject(dataJsonString);
            if (null != jsonObject)
                return JSONObject.parseObject(dataJsonString, User.class);
            else return null;
        } catch (Exception e) {
            return null;
        }
    }
}
