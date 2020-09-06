package com.ajeet.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class DeadloackExample {

    public static Object object1 = new Object();
    public static Object object2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (object1) {
                System.out.println("Thread t1 is holding lock for object1");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Wait to get lock on object2");
                synchronized (object2) {
                    System.out.println("Thread t1 is holding lock for object1 and object2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (object2) {
                System.out.println("Thread t2 is holding lock for object2");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Wait to get lock on object1");
                synchronized (object1) {
                    System.out.println("Thread t2 is holding lock for object1 and object2");
                }
            }
        });

        t1.start();
        t2.start();

        //Interrupt will not work, kill to stop the application
        interruptAfter(t1, 60000);
        interruptAfter(t2, 60000);
    }

    private static void interruptAfter(Thread thread, int delay) {
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Interrupting " + thread.getName());
                thread.interrupt();
            }
        }, delay);
    }
}
