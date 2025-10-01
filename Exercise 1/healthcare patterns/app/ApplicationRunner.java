package app;

import util.LoggingUtil;
import util.RetryUtil;

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Long-running application runner with a CLI menu.
 * - Uses AtomicBoolean for loop control (no while(true))
 * - Has a background scheduler demonstrating long-running jobs
 * - Accepts user commands to run pattern demos on demand
 */
public class ApplicationRunner {
    private static final Logger logger = LoggingUtil.getLogger(ApplicationRunner.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final AtomicBoolean running = new AtomicBoolean(true);

    public void start() {
        logger.info("Application starting...");

        // Example background job: health summary every 30 seconds (non-blocking)
        scheduler.scheduleAtFixedRate(() -> {
            logger.info("[BackgroundTask] Periodic health-check tick.");
            // Place background periodic tasks here (monitoring, cleanup, analytics)
        }, 30, 30, TimeUnit.SECONDS);

        // CLI
        try (Scanner sc = new Scanner(System.in, "UTF-8")) {
            printHelp();

            while (running.get()) {
                System.out.print("\n> ");
                String line = sc.nextLine();
                if (line == null) {
                    // EOF — treat as exit
                    shutdown();
                    break;
                }
                String cmd = line.trim();
                if (cmd.isEmpty()) continue;

                try {
                    handleCommand(cmd);
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Error handling command: " + cmd, ex);
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "CLI loop terminated unexpectedly", e);
        } finally {
            shutdown();
        }
    }

    private void printHelp() {
        System.out.println("HealthcarePatterns - Demo Runner");
        System.out.println("Type a command to run a demo or control the app:");
        System.out.println("  help               - show this help");
        System.out.println("  list               - list available demos");
        System.out.println("  run <n>            - run demo number n (1..7)");
        System.out.println("                       1: Observer  2: Strategy  3: Singleton");
        System.out.println("                       4: Factory   5: Adapter   6: Decorator");
        System.out.println("                       7: RunAll");
        System.out.println("  retry-demo         - runs a sample transient operation with retry");
        System.out.println("  exit               - exit the application");
    }

    private void handleCommand(String cmd) throws Exception {
        String lower = cmd.toLowerCase();
        if (lower.equals("help")) {
            printHelp();
        } else if (lower.equals("list")) {
            printHelp();
        } else if (lower.startsWith("run ")) {
            String[] parts = cmd.split("\\s+");
            if (parts.length >= 2) {
                runDemoByNumber(parts[1]);
            } else {
                System.out.println("Usage: run <n>");
            }
        } else if (lower.equals("runall") || lower.equals("run 7") || lower.equals("7")) {
            runAllDemos();
        } else if (lower.equals("retry-demo")) {
            runRetryDemo();
        } else if (lower.equals("exit")) {
            shutdown();
        } else {
            System.out.println("Unknown command. Type 'help' for options.");
        }
    }

    private void runDemoByNumber(String nStr) {
        int n;
        try {
            n = Integer.parseInt(nStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid demo number.");
            return;
        }
        switch (n) {
            case 1:
                logger.info("Running Observer Demo");
                observer.ObserverDemo.run();
                break;
            case 2:
                logger.info("Running Strategy Demo");
                strategy.StrategyDemo.run();
                break;
            case 3:
                logger.info("Running Singleton Demo");
                singleton.SingletonDemo.run();
                break;
            case 4:
                logger.info("Running Factory Demo");
                factory.FactoryDemo.run();
                break;
            case 5:
                logger.info("Running Adapter Demo");
                adapter.AdapterDemo.run();
                break;
            case 6:
                logger.info("Running Decorator Demo");
                decorator.DecoratorDemo.run();
                break;
            case 7:
                logger.info("Running ALL demos");
                runAllDemos();
                break;
            default:
                System.out.println("Demo number out of range (1..7).");
        }
    }

    private void runAllDemos() {
        observer.ObserverDemo.run();
        strategy.StrategyDemo.run();
        singleton.SingletonDemo.run();
        factory.FactoryDemo.run();
        adapter.AdapterDemo.run();
        decorator.DecoratorDemo.run();
    }

    /**
     * Demonstration of transient retry using util.RetryUtil
     */
    private void runRetryDemo() {
        logger.info("Starting retry-demo (simulated transient operation).");
        try {
            String result = RetryUtil.retry(() -> {
                // Simulate transient operation that fails randomly
                double r = Math.random();
                if (r < 0.7) { // 70% chance to fail
                    throw new RuntimeException("Simulated transient failure (r=" + r + ")");
                }
                return "Success! r=" + r;
            }, 5, 200);
            System.out.println("Retry-demo result: " + result);
            logger.info("Retry-demo succeeded.");
        } catch (Exception e) {
            System.out.println("Retry-demo failed after retries: " + e.getMessage());
            logger.log(Level.WARNING, "Retry-demo final failure", e);
        }
    }

    public void shutdown() {
        if (!running.getAndSet(false)) return;
        logger.info("Application shutting down...");
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
        logger.info("Shutdown complete.");
    }
}
