package server;

import classes.Credentials;
import classes.entryNode;
import dsRMI.DSRMI;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import rmi.RMI;

/**
 *
 * @author Guillermo
 */
public class RMIServer extends UnicastRemoteObject implements RMI {
    
    private static HashMap<String,Credentials> connections = new HashMap();
    
    public RMIServer() throws RemoteException {
        super();
    }

    @Override
    public Object getNode() throws RemoteException {
        return new entryNode();
    }

    @Override
    public String getData(String text) throws RemoteException {
        text = "Hello " + text;
        return text;
    }

    @Override
    public void printInServerSide(String msg) throws RemoteException {
        System.out.println(msg);
    }

    static DefaultTreeModel archiveStructure = null;

    public static void main(String args[]) {
        
        //loadBinaryFile();
        try {
            connections.put("Server", new Credentials(1101));
            connections.put("Machine1", new Credentials(1102));
            connections.put("Machine2", new Credentials(1103));
            connections.put("Machine3", new Credentials(1104));
            
            Registry reg = LocateRegistry.createRegistry(connections.get("Server").getPort());
            reg.rebind("server", new RMIServer());
            System.out.println("Server started..");
            
            Registry reg1 = LocateRegistry.getRegistry("127.0.0.1",connections.get("Machine1").getPort());
            DSRMI rmi1 = (DSRMI) reg1.lookup("Machine1");
            System.out.println("Connected to Machine1");
            rmi1.printInServerSide("Popeye, Why you do this?");

            Registry reg2 = LocateRegistry.getRegistry("127.0.0.1",connections.get("Machine2").getPort());
            DSRMI rmi2 = (DSRMI) reg2.lookup("Machine2");
            System.out.println("Connected to Machine2");
            rmi2.printInServerSide("Popeye, Why you do this? Again?");
            
            Registry reg3 = LocateRegistry.getRegistry("127.0.0.1",connections.get("Machine3").getPort());
            DSRMI rmi3 = (DSRMI) reg3.lookup("Machine3");
            System.out.println("Connected to Machine3");
            rmi3.printInServerSide("Popeye, Why you do this? Over and Over Again?");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void loadBinaryFile() {
        File file = null;
        try {
            file = new File("./archiveStructure.bin");
            //If file does not exist, it creates a new archiveStructure
            //which is the structure of the file in the system
            if (!file.exists()) {
                entryNode rootNode = new entryNode();
                DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootNode);
                archiveStructure = new DefaultTreeModel(root);
            } else {
                //If file exists, it reads the file and cast
                //that file in order to load it in archiveStructure
                FileInputStream entry = new FileInputStream(file);
                ObjectInputStream object = new ObjectInputStream(entry);
                try {
                    archiveStructure = (DefaultTreeModel) object.readObject();
                } catch (EOFException ex) {

                } finally {
                    //Always close the FileInputStream and the ObjectInputStream
                    //to avoid errors
                    object.close();
                    entry.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveToBinaryFile() {
        File file = null;
        try {
            //Save the archiveStructure into a binaryFile
            //this method should be executed before the program
            //finishes its execution
            file = new File("./archiveStructure.bin");
            FileOutputStream exit = new FileOutputStream(file);
            ObjectOutputStream object = new ObjectOutputStream(exit);
            object.writeObject(archiveStructure);
            object.flush();
            object.close();
            exit.close();
        } catch (Exception ext) {
            ext.printStackTrace();
        }
    }

}
