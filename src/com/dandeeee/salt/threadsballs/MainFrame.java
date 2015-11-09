package com.dandeeee.salt.threadsballs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
class MainFrame extends JFrame {

    JButton 	jbtnNew;
    JButton 	jbtnDone;
    JFrame  	jfrmMainFrame;
    BallsPanel ballsPanel;
    JPanel  	jpnlBtn;

    class ListenerBtnNew implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//	    System.out.println("NEW");
            ballsPanel.addNewBall();
        }
    }

    class ListenerBtnDone implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
//	    System.out.println("DONE");
            System.exit(0);
        }
    }

    public MainFrame() {
        jfrmMainFrame = new JFrame();
        jfrmMainFrame.setSize(500, 300);
        jfrmMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jbtnNew = new JButton("New");
        jbtnNew.addActionListener(new ListenerBtnNew());

        jbtnDone = new JButton("Done");
        jbtnDone.addActionListener(new ListenerBtnDone());

        ballsPanel =new BallsPanel();
        ballsPanel.setLayout(new FlowLayout());

        jpnlBtn = new JPanel();
        jpnlBtn.setLayout(new FlowLayout());
        jpnlBtn.setBackground(Color.black);
        jpnlBtn.add(jbtnNew);
        jpnlBtn.add(jbtnDone);

        jfrmMainFrame.add(ballsPanel,BorderLayout.CENTER);
        jfrmMainFrame.add(jpnlBtn,BorderLayout.SOUTH);

        jfrmMainFrame.setVisible(true);
    }
}