package com.dandeeee.salt.threadsballs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

import javax.swing.JComponent;


@SuppressWarnings("serial")
class Ball extends JComponent  {

    static final int DIM_X = 17;
    static final int DIM_Y = 17;
    int x = 0;
    int y = 0;
    int dx = 2;
    int dy = 2;
    Color color;

    public int id;
    static int counter = 0;


    public Ball(){
        setOpaque(true);

        id = counter++;

        Random rand=new Random();
        color=Color.black;
        x=rand.nextInt((int)BallsPanel.bounds.getMaxX());
        y=rand.nextInt((int)BallsPanel.bounds.getMaxY());
//	setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
        setBounds(0,0, Ball.DIM_X, Ball.DIM_Y);
        setLocation(x,y);

        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("mouse_MOVED_ball " + id);
                Ball ball = (Ball) e.getSource();
                BallsWorkerThread bw = BallsPanel.ballsWorkers.get(ball.id/BallsPanel.BALLS_PER_WORKER);
                bw.toggleSuspend();


            }
        });
    }

    public void moveBall(){
        x += dx;
        y += dy;
        if (x < BallsPanel.bounds.getMinX())
        {
            x = (int) BallsPanel.bounds.getMinX();
            dx = -dx;
        }
        if (x + Ball.DIM_X >= BallsPanel.bounds.getMaxX())
        {
            x = (int) (BallsPanel.bounds.getMaxX() - Ball.DIM_X);
            dx = -dx;
        }
        if (y < BallsPanel.bounds.getMinY())
        {
            y = (int) BallsPanel.bounds.getMinY();
            dy = -dy;
        }
        if (y + Ball.DIM_Y >= BallsPanel.bounds.getMaxY())
        {
            y = (int) (BallsPanel.bounds.getMaxY() - Ball.DIM_Y);
            dy = -dy;
        }
        setLocation(x, y);
    }


    @Override
    public void paintComponent(Graphics g){
        System.out.println("paintBall");
        g.setColor(color);
        g.fillOval(0,0, Ball.DIM_X, Ball.DIM_Y);
    }

    @Override
    protected void paintChildren(Graphics arg0) {    }
    @Override
    protected void paintBorder(Graphics arg0) {    }

}



