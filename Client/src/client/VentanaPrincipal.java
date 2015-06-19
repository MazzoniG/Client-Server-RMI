package client;

import classes.entryNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class VentanaPrincipal extends JFrame {

    JPanel Panel = new JPanel();
    JTextArea TextArea = new JTextArea(25, 25);
    JTree Tree = new JTree();
    JScrollPane ScrollPane;
    

    public VentanaPrincipal() {
        super("Ventana Principal");
        this.dispose();
        this.setLocationRelativeTo(null);
        this.setSize(615, 480);

        Panel.setBackground(Color.GRAY);

        Tree.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    TreePath path = Tree.getPathForLocation(e.getX(), e.getY());
                    Rectangle pathBounds = Tree.getUI().getPathBounds(Tree, path);
                    if (pathBounds != null && pathBounds.contains(e.getX(), e.getY())) {
                        
                        JPopupMenu menu = new JPopupMenu();
                        JMenuItem MenuItemFile = new JMenuItem("Crear Archivo");
                        menu.add(MenuItemFile);
                        JMenuItem MenuItemDirectory = new JMenuItem("Crear Directorio");
                        menu.add(MenuItemDirectory);
                        menu.show(Tree, pathBounds.x, pathBounds.y + pathBounds.height);
                        
                        MenuItemFile.addActionListener(
                        new ActionListener(){
                            public void actionPerformed(ActionEvent Event){
                                System.out.println("Archivo");
                            }
                        });
                        
                        MenuItemDirectory.addActionListener(
                        new ActionListener(){
                            public void actionPerformed(ActionEvent Event){                                
                                System.out.println("Directorio");
                                TreePath tp = Tree.getSelectionPath();
                                DefaultMutableTreeNode insertNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
                                DefaultMutableTreeNode node = new DefaultMutableTreeNode("Aqui va el textField");
                                addDirectory(insertNode,"texto");
                            }
                        });
                    }
                }
            }
        });

        ScrollPane = new JScrollPane(Tree);
        Dimension size = new Dimension(260, 400);
        ScrollPane.setPreferredSize(size);
        ScrollPane.setMinimumSize(size);
        Panel.add(ScrollPane);
        Panel.add(TextArea);

        add(Panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
    
    
        private  void addDirectory(DefaultMutableTreeNode Father, String Name) {
        DefaultTreeModel model = (DefaultTreeModel) Tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
//        entryNode novo = new entryNode(Name, (entryNode) Father.getUserObject(), -1, true);
        model.insertNodeInto(new DefaultMutableTreeNode(Name), Father,0);
        }

    private void addTextFile(DefaultMutableTreeNode Father, String Name, String Content){
        
       // entryNode hijo = new entryNode(Name, )
        
    }
    
}