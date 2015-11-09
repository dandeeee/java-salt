package com.dandeeee.salt.netchat;

import java.net.ServerSocket;
import java.util.HashMap;

public class ChatServer{
    
    ServerSocket serverSocket;
    ClientProcessor clientProcessor;
    
    HashMap<User,ClientProcessor> clients = new HashMap<User,ClientProcessor>();
    
    private int port;
    
    public ChatServer(int port_) {
        port = port_;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("ChatServer.c-tor: Server Started...");
        } catch (Exception e) {
            System.out.println("ChatServer.c-tor: ERROR " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    void getNewConnection() {
        clientProcessor = new ClientProcessor(this);
        clients.put(clientProcessor.user, clientProcessor);
        getNewConnection();
    }
    
    String getActiveUsers() {
        String msg = "";
        for (User u : clients.keySet())
            msg = msg + ", " + u.name;
        return msg;
    }
    
    public static void launch(){
        ChatServer server = new ChatServer(55555);

        server.getNewConnection();
    }
}
