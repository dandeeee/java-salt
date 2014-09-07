package com.dandeeee.salt.threadsballs;

import java.awt.*;
import java.util.HashMap;

class BallsWorkerThread extends Thread {
    public int id;
    boolean suspended = false;
    HashMap<Integer,Ball> ballsProcessed;
    Color[] cols = { Color.BLACK,Color.BLUE,Color.CYAN,Color.GRAY,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.RED,Color.WHITE };

    BallsWorkerThread(Ball b){
        super();
        id = b.id/BallsPanel.BALLS_PER_WORKER;
        ballsProcessed = new HashMap<Integer,Ball>();
        setName(Integer.toString(id));
    }

    void assign(Ball b) {
        Color c=cols[id%9];
        b.color = c;
        ballsProcessed.put(b.id, b);
    }

    @SuppressWarnings("deprecation")
    public void toggleSuspend(){
        if(suspended == false){
            suspended = true;
            suspend();
        } else {
            suspended = false;
            resume();
        }
    }

    @Override
    public void run() {
        try {
            while(true){
                for(Integer key : ballsProcessed.keySet()){
                    ballsProcessed.get(key).moveBall();
                }
                sleep(20);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}