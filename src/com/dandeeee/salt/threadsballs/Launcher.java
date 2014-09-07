package com.dandeeee.salt.threadsballs;

import javax.swing.SwingUtilities;

class Launcher {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new MainFrame();
            }
        });
    }
}