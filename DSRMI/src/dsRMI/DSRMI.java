/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsRMI;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DSRMI extends Remote {
    
    public void printInServerSide(String msg)throws RemoteException;
    public File getFile(String name)throws RemoteException;
}
