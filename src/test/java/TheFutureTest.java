import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

/**
 * Created by ilkayaktas on 2019-01-24 at 21:33.
 */
public class TheFutureTest {

    @Test
    public void calculateAsync() throws ExecutionException, InterruptedException {
        Future<String> completableFuture = TheFuture.calculateAsync();


        String result = completableFuture.get();
        assertEquals("Hello", result);
    }

    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = TheFuture.supplyAsync();

        String result = completableFuture.get();
        assertEquals("Hello", result);
    }

    @Test
    public void runAsync() {
        TheFuture.runAsync();

    }

    @Test
    public void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = TheFuture.thenApply();

        String result = completableFuture.get();
        assertEquals("Hello thenApply", result);
    }

    @Test
    public void thenAccept() throws ExecutionException, InterruptedException {
        TheFuture.thenAccept();
    }

    @Test
    public void thenRun() throws ExecutionException, InterruptedException {
        TheFuture.thenRun();
    }

    @Test
    public void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = TheFuture.thenCompose();

        String result = completableFuture.get();
        assertEquals("Hello thenCompose", result);
    }

    @Test
    public void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = TheFuture.thenCombine();

        String result = completableFuture.get();
        assertEquals("Hello thenCombine", result);
    }

    @Test
    public void thenAcceptBoth(){
        TheFuture.thenAcceptBoth();
    }

    @Test
    public void allOfParallel() throws ExecutionException, InterruptedException {
        TheFuture.allOfParallel();
    }
}