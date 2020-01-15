package cn.dante.pattern.ty10_observer.v4;

//import java.util.Observer;

import java.util.Observable;
import java.util.Observer;

public class LiSi extends Observable implements Observer {
    //首先李斯是个观察者，一旦韩非子有活动，他就知道，他就要向老板汇报
    public void update(Observable observable, Object obj) {
        System.out.println("李斯：观察到李斯活动，开始向老板汇报了...");
        this.reportToQiShiHuang(obj.toString());
        System.out.println("李斯：汇报完毕，秦老板赏给他两个萝卜吃吃...\n");

        //通知所有的观察者
        super.setChanged();
        super.notifyObservers("李斯：观察汇报完毕,求关注");
    }

    //汇报给秦始皇
    private void reportToQiShiHuang(String reportContext) {
        System.out.println("李斯：报告，秦老板！韩非子有活动了--->" + reportContext);
    }

}