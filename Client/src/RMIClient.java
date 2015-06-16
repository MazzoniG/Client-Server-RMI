


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.RMI;
import server.entryNode;
//import java.util.Scanner;

/**
 *
 * @author Guillermo
 */
public class RMIClient {

    public static void main(String args[]) {

        RMIClient client = new RMIClient();
        client.connectServer();
    }

    private void connectServer() {
        try {
//            Scanner in = new Scanner(System.in);
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",1101);
            
            RMI rmi = (RMI) reg.lookup("server");
            System.out.println("Connected to Server");
            String text = rmi.getData("Memo");
            entryNode novo = (entryNode)rmi.mazzoniSeLaCome();
            System.out.println("Test\n"+novo.getName());
            rmi.printInServerSide("This text has been printed in the side server ;)");
//            for (int i = 0; i < 10; i++) {
//                String msg = in.nextLine();
//                rmi.printInServerSide(msg);
//            }
            System.out.println(text);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
