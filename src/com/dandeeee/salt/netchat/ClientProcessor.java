package com.dandeeee.salt.netchat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;


public class ClientProcessor{
    static int id = 1;

    ChatServer server;

    Socket socket;

    ObjectInputStream socketReader;
    ObjectOutputStream socketWriter;

    User user;

    Thread messager;

    public ClientProcessor(ChatServer server_){
        server = server_;

        try{
            System.out.println("ClientProcessor.c-tor: Try to get a new connection.");
            socket = server.serverSocket.accept();
            socketReader = new ObjectInputStream(socket.getInputStream());
            socketWriter = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("ClientProcessor.c-tor: Client Connected to the Server.");

            readUserData();
            sendGreeting();

            messager = new Thread(new Runnable(){
                public void run() {
                    while(socket.isConnected()){
                        System.out.println("ClientProcessor.MESSAGER");
                        String clientMsg = recieveMessage();
                        // msg_out = server.getActiveUsers();
                        sendBroadCastMessage(clientMsg);
                    }
                    System.out.println("ClientProcessor.MESSAGER DIES");
                }
            });
            messager.start();

        } catch (Exception e) {
            System.out.println("ClientProcessor.c-tor: ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    void sendGreeting(){
        String msg_out = "Hello " + user.name + "!";
        sendBroadCastMessage(msg_out);
        sendMessage(msg_out);
    }

    void readUserData(){
        try {
            user = (User)socketReader.readObject();
            System.out.println("ClientProcessor.readUserData(): " + user);
            user.name = user.name + " " + id++;
        } catch (Exception e) {
            System.out.println("ClientProcessor.readUserData(): ERROR " + e.getMessage());
        }

    }

    void closeConnection(){
        if(socket.isConnected()){
            try {
                sendBroadCastMessage(user.name + " left the room.");
                server.clients.remove(user.name);
                socket.close();
                messager.join();
                messager.interrupt();
                this.finalize();
            } catch (IOException e) {
                System.out.println("ClientProcessor.closeConnection(): ERROR " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("ClientProcessor.closeConnection(): ERROR " + e.getMessage());
            }catch (Throwable e) {
                System.out.println("ClientProcessor.closeConnection(): ERROR " + e.getMessage());
            }
        }
    }

    String recieveMessage(){
        String msg_in = null;
        try {
            System.out.println("ClientProcessor.recieveMessage: tries to recieve a message.");
            msg_in = socketReader.readUTF();
            msg_in = user.name + "> " + msg_in;
        } catch (IOException e) {
            System.out.println("ClientProcessor.recieveMessage: ERROR " + e.getMessage());
            closeConnection();
        }finally{
            if(msg_in!=null){
                System.out.println("ClientProcessor.recieveMessage: Message: " + msg_in);
            }
        }
        return msg_in;
    }

    void sendBroadCastMessage(String msg_out){
        if(msg_out!=null){
            System.out.println("ClientProcessor.sendMessage: tries to send: " + msg_out);
            Iterator<ClientProcessor> iter = server.clients.values().iterator();
            while(iter.hasNext()){
                iter.next().sendMessage(msg_out);
            }
        }
    }


    void sendMessage(String msg_out){
        if(msg_out!=null){
            System.out.println("ClientProcessor.sendMessage: tries to send: " + msg_out);
            try {
                socketWriter.writeUTF(msg_out);
                socketWriter.flush();
            } catch (IOException e) {
                System.out.println("ClientProcessor.sendMessage: ERROR " + e.getMessage());
            }
        }
    }

}