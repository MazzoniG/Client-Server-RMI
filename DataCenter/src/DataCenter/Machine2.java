/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCenter;

import classes.Credentials;
import dsRMI.DSRMI;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author C5220701
 */
public class Machine2 extends UnicastRemoteObject implements DSRMI{
    private static File dataDirectory;
    private static ArrayList<Credentials> clients = new ArrayList();;
    private static Registry reg;
    
    public Machine2(File dataDirectory2) throws RemoteException {
        super();
        dataDirectory= dataDirectory2;
    }

    public File getDataDirectory() {
        return dataDirectory;
    }

    public void setDataDirectory(File dataDirectory2) {
        dataDirectory = dataDirectory2;
    }

    public ArrayList<Credentials> getClients() {
        return clients;
    }

    
    public boolean addCredential(Credentials c){
        return clients.add(c);
    }
    
    @Override
    public void printInServerSide(String msg) throws RemoteException {
        System.out.println(msg);
    }
    
    public static void main(String args[]) {
        //loadBinaryFile();
        try {
            dataDirectory = new File("./Data/Machine2/DFS");
            if (!dataDirectory.exists()) {
                try {
                    System.out.println("No Existe");
                    if (dataDirectory.mkdirs()) {
                        System.out.println("Success");
                    }else{
                        System.err.println("Fail");
                    }
                } catch (SecurityException se) {
                    System.err.println(se);
                }
            }else{
                System.out.println("Existe!");
            }
            reg = LocateRegistry.createRegistry(1103);
            reg.rebind("Machine2", new Machine2(dataDirectory));
            System.out.println("Machine2 started..");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public File getFile(String name) throws RemoteException {
        for (final File fileEntry : dataDirectory.listFiles()) {
            if (fileEntry.getName().equals(name)) {
                return fileEntry;
            }
        }
        System.err.println("No se encontro el archivo");
        return null;
    }
}
