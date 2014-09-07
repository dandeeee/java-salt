package com.dandeeee.salt.threadsballs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

@SuppressWarnings("serial")
class BallsPanel extends JPanel {

    public static final int BALLS_PER_WORKER = 3;
    static Rectangle bounds;
    BufferedImage buffer;

    static HashMap<Integer, Ball> balls;
    static HashMap<Integer, BallsWorkerThread> ballsWorkers;

    public BallsPanel(){
        setOpaque(true);
        setBackground(Color.YELLOW);
        bounds = new Rectangle();
        balls = new HashMap<Integer, Ball>();
        ballsWorkers = new HashMap<Integer,BallsWorkerThread>();
    }

    public void addNewBall() {
        Ball b = new Ball();
        add(b);
        balls.put(b.id, b);
        assignWorker(b);
        System.out.println(ballsWorkers);
    }

    void assignWorker(Ball b){

        int key = b.id/BALLS_PER_WORKER;

        BallsWorkerThread bw;
        if(ballsWorkers.containsKey(key)){
            bw = ballsWorkers.get(key);
            bw.assign(b);
        } else {
            bw = new BallsWorkerThread(b);
            ballsWorkers.put(key,bw);
            bw.assign(b);
            bw.start();
        }
        System.out.println(bw);
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("Print Panel");
        super.paintComponent(g);
        bounds = getBounds();
    }

}