package cn.dante.pattern.ty10_observer.v4;

import java.util.Observable;

//import javafx.beans.Observable;

public class HanFeiZi extends Observable {
    //韩非子要吃饭了
    public void haveBreakfast() {
        System.out.println("韩非子:开始吃饭了...");
    //通知所有的观察者
        super.setChanged();
        super.notifyObservers("韩非子在吃饭");
    }

    //韩非子开始娱乐了,古代人没啥娱乐，你能想到的就那么多
    public void haveFun() {
        System.out.println("韩非子:开始娱乐了...");
        super.setChanged();
        this.notifyObservers("韩非子在娱乐");
    }
}