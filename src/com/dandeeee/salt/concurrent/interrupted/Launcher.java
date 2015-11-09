package com.dandeeee.salt.concurrent.interrupted;

/**
 * This program demonstrates how to interrupt a running
 * Java thread that computes the greatest common divisor (GCD).
 */
public class Launcher {

    public static void main(String[] args) {
        System.out.println("Entering main()");

        // Create the GCD Runnable, passing in the type of thread it
        // runs in (i.e., "user" or "daemon").
        GCDRunnable runnableCommand = new GCDRunnable();

        // Create a new Thread that's will execute the runnableCommand concurrently.
        Thread thr = new Thread(runnableCommand);
        thr.start();

        try {
            Thread.sleep(2000);
            System.out.println("interrupting thread " + thr.getName() + " from " + Thread.currentThread().getName());
            thr.interrupt();
//            Thread.sleep(1000);
        } catch (InterruptedException x) {
            x.printStackTrace();
        }

        System.out.println("Leaving main()");
    }
}

