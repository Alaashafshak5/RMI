/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Salah
 */
public class ServerImp extends UnicastRemoteObject implements IServer {

    HashMap<String, IClient> listConnected = new HashMap();
    HashMap<String, String> offLineMsg = new HashMap();
    HashMap<String, IGroup> listGroups = new HashMap();
    HashMap<String, Data> chats = new HashMap();
    /////IGroup grp;
    public ServerImp() throws RemoteException {

    }

    @Override
    public void reconnect(String id, IClient client) throws RemoteException {
        listConnected.put(id, client);
        System.out.println("connect " + id);
        String msg = offLineMsg.get(id);
        if (msg != null) {
            client.notifier(msg);
            offLineMsg.put(id, null);
        }
    }

    @Override
    public void disconnect(String id) throws RemoteException {
        listConnected.remove(id);
    }

    @Override
    public void send(String msg, String id) throws RemoteException {
        IClient cl = listConnected.get(id);
        if (cl != null) {
            // client online
            cl.notifier(msg);
            System.out.println("send " + id);
        } else {
            //client offline
            String oldmsg = offLineMsg.get(id);
            if (oldmsg == null) {
                oldmsg = "";
            }
            System.out.println("list" + listConnected);
            oldmsg += "'" + msg;
            offLineMsg.put(id, oldmsg);
            System.out.println("put " + id);
        }

    }

    @Override
    public String listConnected() throws RemoteException {
        return listConnected.keySet().toString();
    }
    
    @Override
    public Object[] listGroups() throws RemoteException{
        return listGroups.keySet().toArray();
    }

    @Override
    public void createGroup(String idGroup, String idAdmin, List idclients) throws RemoteException {
        IGroup group = new GroupImp(idclients);
        //group.addClient(idAdmin);
 
        listGroups.put(idGroup, group);
       
        System.out.println("creat "+ group.getAllClient());
    }

    @Override
    public void sendToAll(String msg, String IdGroup) throws RemoteException {
        IGroup g = listGroups.get(IdGroup);
        System.out.println("send to all");
        if (g != null) {
            System.out.println("group id = " + IdGroup);
            //System.out.println("group size = " +);
            System.out.println("group=" + g.getAllClient());
            List x = g.getAllClient();
            for (int i = 0; i < x.size(); i++) {
                this.send(msg, (String) x.get(i));
                System.out.println("msg=" + msg + " idgrp=" + IdGroup);
            }
        }
    }

    @Override
    public boolean isGroup(String id) throws RemoteException {
        if (listGroups.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }
    
     @Override
    public void save(String id, Data data) throws RemoteException {
        if(chats.containsKey(id)){
            chats.remove(id);
        }
        chats.put(id, data);
        System.out.println(data + " saved");
    }

    @Override
    public Data load(String id) throws RemoteException {
               for (Map.Entry<String, Data> entrySet : chats.entrySet()) {
            String key = entrySet.getKey();
            Data value = entrySet.getValue();
            System.out.println("key=" + key + " id=" + id);
            if (key.equals(id)) {
                System.out.println("loading done");
                return value;
            }
        }
               return null;
    }
    

}
