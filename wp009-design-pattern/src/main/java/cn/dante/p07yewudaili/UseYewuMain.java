package cn.dante.p07yewudaili;

import cn.dante.p06valueobject.Order;

public class UseYewuMain {
    public static void main(String[] args) throws Exception{
        BussinessDelegate bd = new BussinessDelegate();     //可以增加缓存,减少远程方法调用次数
        Order order = bd.getOrder(1);
        order.setNumber(10);
        bd.updateOrder(order);

    }

}
