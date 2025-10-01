package util;

import java.util.concurrent.Callable;

/**
 * Generic retry helper.
 * Example usage:
 *   String res = RetryUtil.retry(() -> doNetworkCall(), 3, 200);
 */
public final class RetryUtil {
    private RetryUtil() {}

    public static <T> T retry(Callable<T> task, int maxAttempts, long initialDelayMs) throws Exception {
        if (task == null) throw new IllegalArgumentException("task cannot be null");
        if (maxAttempts < 1) throw new IllegalArgumentException("maxAttempts >= 1 required");
        long delay = Math.max(1, initialDelayMs);
        int attempt = 1;
        while (true) {
            try {
                return task.call();
            } catch (Exception ex) {
                if (attempt >= maxAttempts) throw ex;
                Thread.sleep(delay);
                delay = Math.min(delay * 2, 10000); // exponential backoff cap 10s
                attempt++;
            }
        }
    }
}
