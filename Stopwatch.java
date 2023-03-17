import java.util.Scanner;

public class Stopwatch {
    private long startTime;
    private long stopTime;
    private boolean isRunning;

    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
            System.out.println("Stopwatch started");
        } else {
            System.out.println("Stopwatch is already running");
        }
    }

    public void stop() {
        if (isRunning) {
            stopTime = System.currentTimeMillis();
            isRunning = false;
            System.out.println("Stopwatch stopped");
        } else {
            System.out.println("Stopwatch is not running");
        }
    }

    public long getElapsedTime() {
        if (!isRunning) {
            return stopTime - startTime;
        } else {
            return System.currentTimeMillis() - startTime;
        }
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Press enter to start the stopwatch");
        scanner.nextLine();
        stopwatch.start();

        System.out.println("Press enter to stop the stopwatch");
        scanner.nextLine();
        stopwatch.stop();

        System.out.println("Elapsed time: " + stopwatch.getElapsedTime() + " milliseconds");
    }
}
