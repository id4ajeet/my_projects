package com.ajeet.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BlockingThreadExample {
    public Object object1 = new Object();

    public static void main(String[] args) {
        BlockingThreadExample ex = new BlockingThreadExample();
        ex.execute();
    }

    private void execute() {

        Thread t1 = new CustomThread();
        t1.start();

        Thread t2 = new CustomThread();
        t2.start();

        interruptAfter(t1, 60000);
        interruptAfter(t2, 20000);
    }

    class CustomThread extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("Starting Blocked " + getName());
                synchronized (this) {
                    synchronized (object1) {
                        System.out.println("Blocked " + getName());
                        Thread.currentThread().wait();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted " + getName());
            }
        }
    }

    private void interruptAfter(Thread thread, int delay) {
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Interrupting " + thread.getName());
                thread.interrupt();
            }
        }, delay);
    }
}
