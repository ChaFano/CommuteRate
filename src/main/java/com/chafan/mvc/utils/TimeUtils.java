package com.chafan.mvc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: 茶凡
 * @ClassName TimeUtils
 * @Description TODO
 * @date 2022/5/18 20:01
 * @Version 1.0
 */
public class TimeUtils {

    /**
     * 时间格式 xxxx_xx_xx
     * @return
     */
    public static String xxxx_xx_xx(){
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        return sdf.format(date);
    }

}
