package cn.dante.removesmell;

import lombok.Data;

import java.awt.*;
@Data
public class Shape {
    final static int TYPELINE = 0;
    final static int TYPERECTANGLE = 1;
    final static int TYPECIRCLE = 2;
    final static int TYPETRIANGLE = 3;
//    int shapeType;
    int type;
    Point p1;
    Point p2;
    //三角形的第三个点.
    Point p3;
    int radius;
}