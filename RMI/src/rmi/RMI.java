package rmi;


import classes.entryNode;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


public interface RMI extends Remote {
    public String getData(String text) throws RemoteException;
    public void printInServerSide(String msg)throws RemoteException;
    public Object getNode() throws RemoteException;
    public DefaultTreeModel getTreeModel() throws RemoteException;
    public boolean addDirectory(DefaultMutableTreeNode Parent, String Name)throws RemoteException;
    public boolean addFile(String Name, DefaultMutableTreeNode Parent, String Text)throws RemoteException;
    public String streamFromServer(entryNode node)throws RemoteException;
    public boolean deleteFile(DefaultMutableTreeNode nodo) throws RemoteException;
}
