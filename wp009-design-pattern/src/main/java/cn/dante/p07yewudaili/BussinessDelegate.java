package cn.dante.p07yewudaili;

import cn.dante.p06valueobject.IOrderManager;
import cn.dante.p06valueobject.Order;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class BussinessDelegate {
    IOrderManager usermanager = null;
    public BussinessDelegate(){
        try {
            IOrderManager usermanager = (IOrderManager) Naming.lookup("OrderManager");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean checkUserFromCache(int uid){
        return true;
    }

    public boolean checkUser(int uid){
        if (!checkUserFromCache(uid)){
            return usermanager.checkUser(1);
        }
        return true;
    }

    public Order getOrderFromCache(int oid){
        return null;
    }

    public Order getOrder(int oid) throws RemoteException{
        Order order = getOrderFromCache(oid);
        if (order == null){
            return usermanager.getOrder(oid);
        }
        return order;
    }

    public boolean updateOrder(Order order) throws RemoteException{
        if(checkUser(1)){
            Order o = getOrder(1);
            o.setNumber(10);
            usermanager.updateorder(o);
        }
        return true;
    }
}
