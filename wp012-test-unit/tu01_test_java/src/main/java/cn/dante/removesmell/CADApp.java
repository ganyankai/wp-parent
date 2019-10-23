package cn.dante.removesmell;

import java.awt.*;

class CADApp {
    void drawShapes(Graphics graphics, Shape shapes[]) {
        for (int i = 0; i < shapes.length; i++) {
            switch (shapes[i].getType()) {
                case Shape.TYPELINE:
//                    graphics.drawLine(shapes[i].getP1(), shapes[i].getP2());
                    break;
                case Shape.TYPERECTANGLE:
                    //画四条边.
//                    graphics.drawLine(...);
//                    graphics.drawLine(...);
//                    graphics.drawLine(...);
//                    graphics.drawLine(...);
                    break;
                case Shape.TYPECIRCLE:
//                    graphics.drawCircle(shapes[i].getP1(), shapes[i].getRadius());
                    break;

            }
        }
    }
}