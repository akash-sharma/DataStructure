package com.akash.threading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintOddEven {

    private Lock lock;
    private Condition evenCondition;
    private Condition oddCondition;

    public PrintOddEven(Lock lock, Condition evenCondition, Condition oddCondition) {
        this.lock = lock;
        this.evenCondition = evenCondition;
        this.oddCondition = oddCondition;
    }

    public Lock getLock() {
        return lock;
    }

    public Condition getEvenCondition() {
        return evenCondition;
    }

    public Condition getOddCondition() {
        return oddCondition;
    }

    public static void main(String args[]) {
        Lock lock = new ReentrantLock();
        Condition evenCondition = lock.newCondition();
        Condition oddCondition = lock.newCondition();
        PrintOddEven printOddEven = new PrintOddEven(lock, evenCondition, oddCondition);

        EvenOddNumber number = new EvenOddNumber(15);
        PrintEven printEven = new PrintEven(number, printOddEven);
        PrintOdd printOdd = new PrintOdd(number, printOddEven);
        Thread evenThread = new Thread(printEven);
        Thread oddThread = new Thread(printOdd);
        evenThread.start();
        oddThread.start();
    }
}

class PrintEven implements Runnable {

    private EvenOddNumber number;
    private PrintOddEven printOddEven;

    public PrintEven(EvenOddNumber number, PrintOddEven printOddEven) {
        this.number = number;
        this.printOddEven = printOddEven;
    }

    public void run() {
        printOddEven.getLock().lock();
        try {
            while (number.isLimitInRange()) {
                if(number.isOdd()) {
                    // even condition await
                    printOddEven.getEvenCondition().await();
                } else {
                    int value = number.getAndIncrement();
                    System.out.println("PrintEven : " + value);
                    // odd condition signal
                    printOddEven.getOddCondition().signal();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("thread interrupted");
            e.printStackTrace();
        } finally {
            printOddEven.getLock().unlock();
        }
    }
}

class PrintOdd implements Runnable {

    private EvenOddNumber number;
    private PrintOddEven printOddEven;

    public PrintOdd(EvenOddNumber number, PrintOddEven printOddEven) {
        this.number = number;
        this.printOddEven = printOddEven;
    }

    public void run() {
        printOddEven.getLock().lock();
        try {
            while (number.isLimitInRange()) {
                if(!number.isOdd()) {
                    // odd condition await
                    printOddEven.getOddCondition().await();
                } else {
                    int value = number.getAndIncrement();
                    System.out.println("PrintOdd : " + value);
                    // even condition signal
                    printOddEven.getEvenCondition().signal();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("thread interrupted");
            e.printStackTrace();
        } finally {
            printOddEven.getLock().unlock();
        }
    }
}

class EvenOddNumber {

    private AtomicInteger value;
    private int limit;

    EvenOddNumber(int limit) {
        this.limit = limit;
        value = new AtomicInteger(1);
    }

    public int getAndIncrement() {
        return value.getAndIncrement();
    }

    public boolean isOdd() {
        return value.get() % 2 != 0;
    }

    public boolean isLimitInRange() {
        return limit >= value.get();
    }
}