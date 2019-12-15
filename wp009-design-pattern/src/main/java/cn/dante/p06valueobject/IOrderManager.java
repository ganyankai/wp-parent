package cn.dante.p06valueobject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOrderManager extends Remote {

    public Order getOrder(int id) throws RemoteException;   //vo
    public String getClientName(int id) throws RemoteException;
    public String getProdName(int id) throws RemoteException;
    public int getNumber(int id) throws RemoteException;

    public boolean checkUser(int id);
    public void updateorder(Order order);

}
