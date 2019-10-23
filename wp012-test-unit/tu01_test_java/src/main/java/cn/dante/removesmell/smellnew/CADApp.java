package cn.dante.removesmell.smellnew;


import java.awt.*;

class CADApp {
    void drawShapes(Graphics graphics, Shape shapes[]) {
        for (int i = 0; i < shapes.length; i++) {
           shapes[i].draw(graphics);

        }
    }
}