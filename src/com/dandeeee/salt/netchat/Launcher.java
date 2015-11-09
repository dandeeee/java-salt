package com.dandeeee.salt.netchat;

public class Launcher {

    public static void main(String[] args) {

        Thread srv = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ChatServer.launch();
            }
        });
        srv.start();

        Thread client1 = new Thread(new Runnable() {
            public void run() {
                new ChatClientFrame();
            }
        });
        client1.start();

        Thread client2 = new Thread(new Runnable() {
            public void run() {
                new ChatClientFrame();
            }
        });
        client2.start();
    }

}