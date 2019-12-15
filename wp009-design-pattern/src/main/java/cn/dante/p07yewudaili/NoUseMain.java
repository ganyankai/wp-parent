package cn.dante.p07yewudaili;

import cn.dante.p06valueobject.IOrderManager;
import cn.dante.p06valueobject.Order;

import java.rmi.Naming;

public class NoUseMain {
    public static void main(String[] args) {
        try{
            IOrderManager usermanager = (IOrderManager) Naming.lookup("OrderManager");

            if(usermanager.checkUser(1)){
                Order order = usermanager.getOrder(1);
                order.setNumber(10);
                usermanager.updateorder(order);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
