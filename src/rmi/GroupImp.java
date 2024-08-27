/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;

/**
 *
 * @author Alaa Shafshak
 */
public class GroupImp extends UnicastRemoteObject implements IGroup {

    String idGroup;
    List allClients= new ArrayList();
    JList list;
    public JList getList() {
        return list;
    }

    public void setList(JList list) {
        this.list = list;
    }
   
    
    public GroupImp(List allClients) throws RemoteException{
        
        this.allClients = allClients;
    }
    public GroupImp() throws RemoteException {
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public void addClient(String id) throws RemoteException {
        allClients.add(id);
        System.out.println(id+"added");
    }

    public void setAllClients(List allClients) {
        this.allClients = allClients;
    }

    @Override
    public void removeClient(String id) throws RemoteException {
        allClients.remove(id);
    }

    @Override
    public List getAllClient() throws RemoteException {
        return allClients;
    }

}
