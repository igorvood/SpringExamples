package ru.vood.threads;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class GitHubLookupService implements Callable<User>, Runnable {
    public CompletableFuture<User> findUser(String pivotalSoftware) {
        final Random random = new Random();
        final int anInt = random.nextInt(10000);
        System.out.println("Thread name " + Thread.currentThread().getName() + " time out " + anInt);
        try {
            Thread.sleep(anInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(new User(pivotalSoftware));
    }

    public User findUser1(String pivotalSoftware) {
        final Random random = new Random();
        final int anInt = random.nextInt(10000);
        System.out.println("Thread name " + Thread.currentThread().getName() + " time out " + anInt);
        try {
            Thread.sleep(anInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new User(pivotalSoftware);
    }

    @Override
    public User call() {
        return findUser1("qqqq");
    }

    @Override
    public void run() {
        findUser1("qqqq");
    }
}
