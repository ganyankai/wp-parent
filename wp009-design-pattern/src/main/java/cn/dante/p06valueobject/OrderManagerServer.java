package cn.dante.p06valueobject;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class OrderManagerServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            IOrderManager usermanager =new OrderManager();
            Naming.bind("OrderManager",usermanager);
            System.out.println("OrderManager is ready ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
