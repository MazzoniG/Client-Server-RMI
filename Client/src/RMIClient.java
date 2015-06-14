
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.RMI;


/**
 *
 * @author Guillermo
 */
public class RMIClient {
    public static void main(String args[]){
        
        RMIClient client = new RMIClient();
        client.connectServer();
    }

    private void connectServer() {
      try{ 
          Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
          RMI rmi = (RMI) reg.lookup("server");
          System.out.println("Connected to Server");
          String text = rmi.getData("Memo");
          System.out.println(text);
      }catch(Exception e){
          System.out.println(e);
      } 
    }
}
