/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author OWNER
 */
public class entryNode implements Serializable{
    private String name;
    private String Parent;
    private int DataNode;
    private boolean isFile;

    public entryNode(String name, String Parent, int DataNode, boolean isFile) {
        this.name = name;
        this.Parent = Parent;
        this.DataNode = DataNode;
        this.isFile = isFile;
    }

    public boolean isIsFile() {
        return isFile;
    }

    public void setIsFile(boolean isFile) {
        this.isFile = isFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return Parent;
    }

    public void setParent(String Parent) {
        this.Parent = Parent;
    }

    public int getDataNode() {
        return DataNode;
    }

    public void setDataNode(int DataNode) {
        this.DataNode = DataNode;
    }
    
    
}
