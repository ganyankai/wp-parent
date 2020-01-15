package cn.dante.pattern.ty10_observer.v4;

import java.util.Observable;
import java.util.Observer;

public class Dalisi implements Observer {

    public void update(Observable o, Object arg) {
        System.out.println("检测到李斯了,happy ");
    }

}
