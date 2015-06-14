import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import rmi.RMI;


/**
 *
 * @author Guillermo
 */
public class RMIServer extends UnicastRemoteObject implements RMI {
  
    public RMIServer() throws RemoteException{
        super();
    }

    @Override
    public String getData(String text) throws RemoteException {
        text = "Hi " + text;
        return text;
    }
    
    public static void main(String args[]){
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("server", new RMIServer());
            System.out.println("Server started..");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
