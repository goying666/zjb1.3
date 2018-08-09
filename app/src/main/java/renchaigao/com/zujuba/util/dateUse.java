package renchaigao.com.zujuba.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateUse {

    public static String getTodayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(new Date()));
        return formatter.format(new Date());
    }

    public static String DateToString(Date dateValue) {
        if (dateValue != null) {
            DateFormat mediumDateFormat = DateFormat.getDateTimeInstance
                    (DateFormat.MEDIUM, DateFormat.MEDIUM);
            return mediumDateFormat.format(dateValue);
        } else {
            return null;
        }
    }

    public static Date StringToDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateret = formatter.parse(dateStr);
            return dateret;
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String GetStringDateNow() {
        return DateToString(new Date());
    }

    //    public static Date GetFormatDateNow(){
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    }
    /* 功能：比较token创建时间是否超时
     *  参数：默认超过2天不登录就算超时 */
    public static boolean tokenDateCompare(Date date) {
        Calendar calendarToken = Calendar.getInstance();
        Calendar calendarNow = Calendar.getInstance();
        calendarToken.setTime(date);
        calendarNow.setTime(new Date());
        if ((calendarNow.getTimeInMillis() - calendarToken.getTimeInMillis()) / (1000 * 60 * 60 * 24) < 2)
            return true;
        else
            return false;
    }
}
