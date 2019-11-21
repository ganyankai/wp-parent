package com.catt.common.util.geo;

import org.springframework.util.Assert;

/**
 * <pre>
 * Description: 地图相关工具类
 * Author: Zhang zhongtao
 * Version:
 * Since: Ver 1.1
 * Date: 2015-01-07 15:24
 * </pre>
 */
public class GisUtil {
    private static final double EARTH_RADIUS = 6378.137;
    //地球半径:6371.004千米
    public static double r = 6371004;
    //纬度每度的长度：111194.9964577288米
    public static double lon = 111194.9964577288;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 距离转换成为经纬度
     *
     * @param mapPoint 原始点
     * @param d        距离（米）
     * @param f        方向 1——x方向，2——y方向
     * @return MapPoint;
     */
    public static com.catt.common.util.geo.data.MapPoint distance2Point(com.catt.common.util.geo.data.MapPoint mapPoint, double d, int f) {
        com.catt.common.util.geo.data.MapPoint result = new com.catt.common.util.geo.data.MapPoint();
        if (f == 1) {
            //计算经线
            double lat = 2 * Math.PI * r * Math.cos(mapPoint.getY() * Math.PI / 180) / 360;
            result.setX(mapPoint.getX() + d / lat);
        } else {
            result.setY(mapPoint.getY() + d / lon);
        }

        return result;
    }

    /**
     * 计算2个坐标之间的距离
     *注：算法计算与Arcgis等专业软件计算有一定的误差，根据维度
     * @param mapPoint1 第一个点
     * @param mapPoint2 第二个点
     * @return 距离 单位 m
     */
    public static Double getDistance(com.catt.common.util.geo.data.MapPoint mapPoint1, com.catt.common.util.geo.data.MapPoint mapPoint2) {
        Assert.notNull(mapPoint1);
        Assert.notNull(mapPoint2);
        Assert.notNull(mapPoint1.getX());
        Assert.notNull(mapPoint1.getY());
        Assert.notNull(mapPoint2.getX());
        Assert.notNull(mapPoint2.getY());

        double radLng1 = rad(mapPoint1.getY());
        double radLng2 = rad(mapPoint2.getY());
        double a = radLng1 - radLng2;
        double b = rad(mapPoint1.getX()) - rad(mapPoint2.getX());
        double dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)+
                Math.cos(radLng1) * Math.cos(radLng2)* Math.pow(Math.sin(b / 2), 2)));
        dis = dis * EARTH_RADIUS;
//        dis = Math.round(dis * 10000) / 10000.0;//除数写成10000.0减小误差
        return dis * 1000;
    }

}
