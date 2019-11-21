package com.catt.common.util.geo.data;

import java.io.Serializable;

/**
 * <pre>
 * Description:
 * Author: Zhang zhongtao
 * Version:
 * Since: Ver 1.1
 * Date: 2015-01-07 15:46
 * </pre>
 */
public class MapPoint implements Serializable{
    private Double x;
    private Double y;

    public MapPoint() {
    }

    public MapPoint(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
