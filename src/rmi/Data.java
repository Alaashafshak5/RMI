/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.io.Serializable;
import java.util.HashMap;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author Alaa Shafshak
 */
public class Data implements Serializable{
    
    JList list;
    HashMap<String, JTextArea> areamap;

    public Data(JList list, HashMap<String, JTextArea> areamap) {
        this.list = list;
        this.areamap = areamap;
    }

    public JList getList() {
        return list;
    }

    public void setList(JList list) {
        this.list = list;
    }

    public HashMap<String, JTextArea> getAreamap() {
        return areamap;
    }

    public void setAreamap(HashMap<String, JTextArea> areamap) {
        this.areamap = areamap;
    }

   
    
    
}
