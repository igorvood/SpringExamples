package ru.vood.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class Application {

    private final GitHubLookupService gitHubLookupService = new GitHubLookupService();


    private static void test1() {
        int i = 0;
        final Application application = new Application();

        List<Future<User>> futureList = new ArrayList<>();
        futureList.add(application.gitHubLookupService.findUser("PivotalSoftware"));
        futureList.add(application.gitHubLookupService.findUser("CloudFoundry"));
        futureList.add(application.gitHubLookupService.findUser("Spring-Projects"));

        while (true) {
            final Optional<Future<User>> first = futureList.stream().filter(userFuture -> !userFuture.isDone()).findFirst();

            if (!first.isPresent()) {
                break;
            }
            i++;
        }

        System.out.println(i);
    }

    private static void test2() {
        int i = 0;
        final Application application = new Application();

        List<Future<User>> futureList = new ArrayList<>();

        futureList.add(CompletableFuture.completedFuture(application.gitHubLookupService.findUser1("PivotalSoftware")));
        futureList.add(CompletableFuture.completedFuture(application.gitHubLookupService.findUser1("CloudFoundry")));
        futureList.add(CompletableFuture.completedFuture(application.gitHubLookupService.findUser1("Spring-Projects")));


        final CompletableFuture<User> completableFuture = new CompletableFuture();

        while (true) {
            final Optional<Future<User>> first = futureList.stream().filter(userFuture -> !userFuture.isDone()).findFirst();

            if (!first.isPresent()) {
                break;
            }
            i++;
        }

        System.out.println(i);
    }

    private static void test3() {
        int i = 0;
        final Application application = new Application();

        List<CompletableFuture<Void>> futureList = new ArrayList<>();

        for (i = 0; i < 20; i++) {
            futureList.add(CompletableFuture.runAsync(application.gitHubLookupService));
        }

        while (true) {
            final Optional<CompletableFuture<Void>> first = futureList.stream().filter(userFuture -> !userFuture.isDone()).findFirst();

            if (!first.isPresent()) {
                break;
            }
            if (i % 10000000 == 0) {
                final long count = futureList.stream()
                        .filter(userFuture -> !userFuture.isDone())
                        .count();
                System.out.println(("осталось отраотать " + count));
                final Optional<CompletableFuture<Void>> first1 = futureList.stream()
                        .filter(userFuture -> userFuture.isDone())
                        .findFirst();
//                System.out.println(first1.get().get().getClass().toString());
                i = 0;
            }
            i++;
        }

        System.out.println(i);
    }


    private static void test4() throws ExecutionException, InterruptedException {
        int i = 0;
//      final Application application = new Application();

        //      CompletableFuture<User> future = new CompletableFuture<>();

        List<Future<User>> futureList = new ArrayList<>();

        final ForkJoinPool forkJoinPool = new ForkJoinPool();

        for (i = 0; i < 20; i++) {
            //FutureTask<User> futureTask = new FutureTask(application.gitHubLookupService);
            FutureTask<User> futureTask = new FutureTask(new GitHubLookupService());
            futureList.add(futureTask);

            forkJoinPool.submit(futureTask);
            //new Thread(futureTask).start();
        }

        while (true) {
            final Optional<Future<User>> first = futureList.stream().filter(userFuture -> !userFuture.isDone()).findFirst();

            if (!first.isPresent()) {
                break;
            }
            if (i % 10000000 == 0) {
                final long count = futureList.stream()
                        .filter(userFuture -> !userFuture.isDone())
                        .count();
                System.out.println(("осталось отработать " + count));
                i = 0;
                final Optional<Future<User>> first1 = futureList.stream()
                        .filter(userFuture -> userFuture.isDone())
                        .findFirst();
                System.out.println(first1.get().get().toString());
            }
            //System.out.println(("осталось отраотать " + i));
            i++;
        }
        System.out.println(i);
    }

    public static void main(String... args) throws Exception {
        test4();
    }
}
