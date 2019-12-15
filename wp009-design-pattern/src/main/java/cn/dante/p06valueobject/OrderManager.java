package cn.dante.p06valueobject;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class OrderManager extends UnicastRemoteObject implements IOrderManager {

    protected OrderManager() throws RemoteException {
    }

//    protected OrderManager(int port) throws RemoteException {
//        super(port);
//    }
//
//    protected OrderManager(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
//        super(port, csf, ssf);
//    }

    public Order getOrder(int id) throws RemoteException {
        Order order = new Order();
        order.setClientName("billy");
        order.setNumber(20);
        order.setProductName("desk");
        return order;
    }

    public String getClientName(int id) throws RemoteException {
        return "billy";
    }

    public String getProdName(int id) throws RemoteException {
        return "desk";
    }

    public int getNumber(int id) throws RemoteException {
        return 20;
    }

    public boolean checkUser(int id) {
        return true;
    }

    public void updateorder(Order order) {
        System.out.println(" in  updateorder ");
    }
}
