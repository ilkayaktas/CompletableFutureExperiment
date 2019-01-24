import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ilkayaktas on 2019-01-24 at 21:32.
 */

public class TheFuture {
    public static Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(new TheTask(completableFuture));

        return completableFuture;
    }

    public static CompletableFuture<String> supplyAsync(){
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> "Hello");

        return future;
    }

    public static void runAsync(){
        CompletableFuture<Void> future
                = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync");
        });
    }

    public static CompletableFuture<String> thenApply(){
        CompletableFuture<String> completableFuture = supplyAsync();

        return completableFuture.thenApply(s -> s + " thenApply");
    }

    public static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture
                .thenAccept(s -> System.out.println("Computation returned: " + s));

        future.get();
    }

    public static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture
                .thenRun(() -> System.out.println("Computation finished."));

        future.get();
    }

    /*
    * The best part of the CompletableFuture API is the ability
    * to combine CompletableFuture instances
    * in a chain of computation steps.
    */
    public static CompletableFuture<String> thenCompose(){
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " thenCompose"));

        // They closely relate to the map and flatMap methods of Stream and Optional classes also available in Java 8.

        return completableFuture;
    }

    public static CompletableFuture<String > thenCombine(){
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> " thenCombine"), (s1, s2) -> s1 + s2);

        return completableFuture;
    }

    public static void thenAcceptBoth(){
        CompletableFuture future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " thenAcceptBoth"),
                        (s1, s2) -> System.out.println(s1 + s2));
    }

    public static void allOfParallel() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        combinedFuture.get();

        System.out.println(future1.isDone());
        System.out.println(future2.isDone());
        System.out.println(future3.isDone());

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        System.out.println(combined);
    }
}
