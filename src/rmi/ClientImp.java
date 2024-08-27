/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author Salah
 */
public class ClientImp extends UnicastRemoteObject implements IClient {

    JTextArea area;
    JList list;
    HashMap<String, JTextArea> chat = new HashMap();

    public ClientImp() throws RemoteException {
    }

    public void setList(JList list) {
        this.list = list;
    }

    public ClientImp(JTextArea area) throws RemoteException {
        this.area = area;
    }

    @Override
    public void notifier(String msg) throws RemoteException {
        String[] tab = msg.split(":", 2);
        GuiListModels lm = new GuiListModels();
        list.setModel(lm);
        System.out.println("msg=" + msg);

        if (!lm.contains(tab[0])) {
            System.out.println("not contains client");
            lm.add(tab[0]);
        }
        if(chat.get(tab[0])==null){
            chat.put(tab[0], new JTextArea());
        }
        String[] tab2 = msg.split(":", 2);
        JTextArea a = chat.get(tab[0]);
        if (tab2.length > 1) {
            a.append("\n" + tab[1]);
            area.append("\n" + tab[1]);
        } else {
            a.append("\n" + msg);
            area.append("\n" + msg);
            System.out.println("test:" + area.getText());
        }
    }

    public void setArea(JTextArea area) {
        this.area = area;
    }

    public JList getList() {
        return list;
    }

    public HashMap<String, JTextArea> getChat() {
        return chat;
    }

    public void setChat(HashMap<String, JTextArea> chat) {
        this.chat = chat;
    }
    

}
