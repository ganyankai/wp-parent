package cn.dante.removesmell.smellnew;

import lombok.Data;

import java.awt.*;
@Data
public class CircleShape implements Shape{
    Point center;
    int radius;

    @Override
    public void draw(Graphics graphics) {
//        graphics.drawCircle(getCenter(), getRadius());
    }
}
