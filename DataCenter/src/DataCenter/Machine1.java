/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCenter;

import classes.Credentials;
import dsRMI.DSRMI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author C5220701
 */
public class Machine1 extends UnicastRemoteObject implements DSRMI {

    private static File dataDirectory;
    private static ArrayList<Credentials> clients = new ArrayList();
    private static Registry reg;

    public Machine1(File dataDirectory2) throws RemoteException {
        super();
        dataDirectory = dataDirectory2;
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

    public boolean addCredential(Credentials c) {
        return clients.add(c);
    }

    @Override
    public void printInServerSide(String msg) throws RemoteException {
        System.out.println(msg);
    }

    public static void main(String args[]) {
        try {
            dataDirectory = new File("./Data/Machine1/DFS");
            if (!dataDirectory.exists()) {
                try {
                    System.out.println("No Existe");
                    if (dataDirectory.mkdirs()) {
                        System.out.println("Success");
                    } else {
                        System.err.println("Fail");
                    }
                } catch (SecurityException se) {
                    System.err.println(se);
                }
            } else {
                System.out.println("Existe!");
            }
            reg = LocateRegistry.createRegistry(1102);
            reg.rebind("Machine1", new Machine1(dataDirectory));
            System.out.println("Machine1 started..");
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

    @Override
    public boolean createFile(String content, String name) throws RemoteException {
        PrintWriter writer = null;
        name = dataDirectory.getAbsolutePath()+"\\"+name;
        System.out.println("Nombre del Archivo: "+name);
        try {
            writer = new PrintWriter(name, "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Machine1.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Machine1.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        writer.println(content);
        writer.close();
        return true;
    }

    @Override
    public boolean deleteFile(String name) throws RemoteException {
        name = dataDirectory.getAbsolutePath()+"\\"+name;
        File file = new File(name);
        return file.delete();
    }

}
