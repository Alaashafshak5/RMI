/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Salah
 */
public interface IServer extends Remote {

    public void reconnect(String id, IClient client) throws RemoteException;

    public void disconnect(String id) throws RemoteException;

    public void send(String msg, String id) throws RemoteException;

    public String listConnected() throws RemoteException;
    
    public void createGroup(String idGroup, String idAdmin, List idclients) throws RemoteException;
    
    public void sendToAll(String msg, String idGroup) throws RemoteException;
    
    public boolean isGroup(String id) throws RemoteException;

    public Object[] listGroups() throws RemoteException;
    
    public void save(String id, Data data) throws RemoteException;
    
    public Data load(String id)  throws RemoteException;
}
