package com.dandeeee.salt.netchat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ClientConnection{
    User user;

    Socket socket;

    ObjectInputStream socketReader;
    ObjectOutputStream socketWriter;

    int port;

    Thread messager;

    ChatClientFrame form;

    public ClientConnection(User user_, int port_, ChatClientFrame form_) {
        user = user_;
        port = port_;
        form = form_;

        socket = new Socket();

        while(!(socket.isConnected())){
            try {
                tryConnect();
                Thread.currentThread();
                Thread.sleep(1000);
            } catch (InterruptedException e) {    }
        }

        sendUserData();

        messager = new Thread(new Runnable(){
            public void run() {
                while(socket.isConnected()){
                    System.out.println("ClientConnection.MESSAGER");
                    String msg_in = recieveMessage();
                    form.updateUI(msg_in);
                }
                System.out.println("ClientConnection.MESSAGER DIES");
            }
        });
        messager.start();
    }

    void tryConnect(){
        SocketAddress serverAddress = new InetSocketAddress("127.0.0.1", port);
        try {
            socket.connect(serverAddress);

            socketWriter = new ObjectOutputStream(socket.getOutputStream());
            socketReader = new ObjectInputStream(socket.getInputStream());

            System.out.println("ClientConnection.tryConnect: Connected to the Server.");


        }catch(ConnectException e){

            System.out.println("ClientConnection.tryConnect: ERROR ConnectionException: " + e.getMessage());
            socket = new Socket();

        }catch (Exception e) {
            System.out.println("ClientConnection.tryConnect: ERROR : " + e.getMessage());
            socket = null;
            e.printStackTrace();
        }

    }

    void sendUserData(){
        if(isConnected()){
            try {
                socketWriter.writeObject(user);
            } catch (IOException e) {
                System.out.println("ClientConnection.sendUserData: ERROR : " + e.getMessage());
            }
        }
    }

    public boolean isConnected(){
        return socket.isConnected();
    }

    public boolean isClosed(){
        return socket.isClosed();
    }


    public void sendMessage(String msg) {
        System.out.println("ClientConnection.sendMessage: sending " + msg);
        try {
            socketWriter.writeUTF(msg);
            socketWriter.flush();
        } catch (IOException e) {
            System.out.println("ClientConnection.sendMessage: ERROR " + e.getMessage());
            e.printStackTrace();
        }
    }


    String recieveMessage(){
        String serverMsg = null;
        System.out.println("ClientConnection.recieveMessage: : tries to recieve a message. ");
        if(socket.isConnected()){
            try {
                serverMsg = socketReader.readUTF();
            } catch (IOException e) {
                System.out.println("ClientConnection.recieveMessage: ERROR " + e.getMessage());
                closeConnection();
            }finally{
                if(serverMsg!=null){
                    System.out.println("ClientConnection.recieveMessage: Message from server recieved " + serverMsg);
                }
            }
            return serverMsg;
        }
        return null;
    }


    void closeConnection(){
        if(socket.isConnected()){
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("ClientConnection.closeConnection(): ERROR " + e.getMessage());
            }finally{
                System.out.println("ClientConnection.shutdown: closed the connection.");
            }
        }

    }

    String request(String cmd){
        System.out.println("REQUEST!!!");
        String out = "";
        sendMessage(cmd);
        try {
            out = socketReader.readUTF();
        } catch (IOException e) {
            System.out.println("ClientConnection.requestActiveUsers: ERROR " + e.getMessage());
        }
        return out;
    }
}