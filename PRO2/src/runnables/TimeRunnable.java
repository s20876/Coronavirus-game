package runnables;

import action_listeners.RoutesManagingListener;
import action_listeners.ValueCheckingListener;
import main.Main;

public class TimeRunnable implements Runnable {

    @Override
    public void run() {
        Main.dayOfEpidemic = 1;
        Thread virusThread = new Thread(new VirusRunnable());
        virusThread.start();
        new ValueCheckingListener();
        new RoutesManagingListener();
        try {
            while (!Thread.currentThread().isInterrupted()) {
                switch (Main.levelChosen) {
                    case EASY:
                        Thread.sleep(7000);
                        break;
                    case MEDIUM:
                        Thread.sleep(5000);
                        break;
                    case DIFFICULT:
                        Thread.sleep(3000);
                        break;
                    case EXTREME:
                        Thread.sleep(1000);
                        break;
                }
                Main.dayOfEpidemic++;
                countPoints();
            }
        } catch (InterruptedException ignored) { }
        virusThread.interrupt();
    }

    private void countPoints() {
        double precisionPercent = ((double) Main.totalCases / (double) Main.worldPopulation) * 100;

        int level = 0;
        switch (Main.levelChosen) {
            case EASY:
                level = 1;
                break;
            case MEDIUM:
                level = 2;
                break;
            case DIFFICULT:
                level = 3;
                break;
            case EXTREME:
                level = 4;
                break;
        }

        Main.points = (int) ((double)(Main.healthPoints * Main.dayOfEpidemic) / (precisionPercent * 1000)) * level;
    }
}
