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
 * @author Alaa Shafshak
 */
public interface IGroup extends Remote {

    public void addClient(String id) throws RemoteException;

    public void removeClient(String id) throws RemoteException;

    public List getAllClient() throws RemoteException;
}
