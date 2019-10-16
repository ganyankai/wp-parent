package com.zrytech.framework.app.utils;

import org.junit.jupiter.api.Test;

/**
 * @Desinition:计算两者之间的距离
 * @author:jxx
 * @create:2018-08-23
 */
public class DistanceUtils {

    @Test
    public void demo() {
        double s = algorithm(103.275158, 25.586327, 102.852451, 24.873998);
        System.out.println("距离:" + s + "公里");
        int instance=  Integer.parseInt(new java.text.DecimalFormat("0").format(s));
        System.out.println("距离:" + instance + "公里");
    }

    /**
     * @Desinition:根据两者的之间的经纬度计算出距离
     */
    public static double algorithm(double longitude1, double latitude1, double longitude2, double latitude2) {
        double Lat1 = rad(latitude1); // 纬度
        double Lat2 = rad(latitude2);//纬度
        double a = Lat1 - Lat2;//两点纬度之差
        double b = rad(longitude1) - rad(longitude2); //经度之差
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式
        s = s * 6378137.0;//弧长乘地球半径（半径为米）
        s = Math.round(s * 10000d) / 10000d;//精确距离的数值
        return s / 1000;
    }

    /**
     * 角度转弧度计算
     */
    private static double rad(double d) {
        return d * Math.PI / 180.00; //角度转换成弧度
    }
}
