package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI extends Remote {
    
    public String getData(String text) throws RemoteException;
    public void printInServerSide(String msg)throws RemoteException;
    public Object getNode() throws RemoteException;
}
