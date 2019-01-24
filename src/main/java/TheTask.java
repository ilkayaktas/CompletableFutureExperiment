import java.util.concurrent.CompletableFuture;

/**
 * Created by ilkayaktas on 2019-01-24 at 12:46.
 */

public class TheTask implements Runnable {
    CompletableFuture<String> completableFuture;

    public TheTask(CompletableFuture<String> completableFuture) {
        this.completableFuture = completableFuture;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            complete();
//            cancel(); // throws CancellationException
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void cancel(){
        completableFuture.cancel(false);
    }

    private void complete(){
        completableFuture.complete("Hello");
    }
}
