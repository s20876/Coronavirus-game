package runnables;

import main.Main;
import objects.Country;
import objects.Route;
import objects.TransportType;
import start_stuff.StartFrame;

public class VirusRunnable implements Runnable {
    public static int sleep;
    public static double minInfect;
    public static double maxInfect;
    public static double minHeal;
    public static double maxHeal;
    public static double healProbability;

    @Override
    public void run() {
        try {
            Country.ITALY.isInfected = true;
            Country.ITALY.numberOfCases = 1;
            switch (Main.levelChosen) {
                case EASY:
                    sleep = 7000;
                    minInfect = 0.8;
                    maxInfect = 1.25;
                    minHeal = 0.1;
                    maxHeal = 0.3;
                    healProbability = 0.4;
                    break;
                case MEDIUM:
                    sleep = 5000;
                    minInfect = 1;
                    maxInfect = 1.45;
                    minHeal = 0.01;
                    maxHeal = 0.3;
                    healProbability = 0.3;
                    break;
                case DIFFICULT:
                    sleep = 3000;
                    minInfect = 1.2;
                    maxInfect = 1.7;
                    minHeal = 0.01;
                    maxHeal = 0.2;
                    healProbability = 0.2;
                    break;
                case EXTREME:
                    sleep = 1000;
                    minInfect = 1.5;
                    maxInfect = 2;
                    minHeal = 0.01;
                    maxHeal = 0.1;
                    healProbability = 0.1;
                    break;
            }
            StartFrame.isStart = false;
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(sleep);
                infectInsideCountry(minInfect, maxInfect);
                infectOutsideCountry();
                heal(minHeal, maxHeal, healProbability);
            }
        } catch (InterruptedException ignored) { }
    }

    private void infectInsideCountry(double min, double max) {

        for (Country c : Country.values()) {
            c.r0 = min + (Math.random() * (max - min));
            double power = 0.5 + Math.random() * (1.5 - 0.5);
            if (c.unvaccinatedPopulation != 0) {
                if (c.isInfected) {
                    if (c.numberOfCases <= (c.unvaccinatedPopulation - (int) ((c.numberOfCases / 5) * c.r0 * power))) {
                        c.numberOfCases += (int) ((c.numberOfCases / 5) * c.r0 * power);
                    } else
                        c.numberOfCases = c.unvaccinatedPopulation;
                }
            }
        }
    }

    private void infectOutsideCountry() {

        for (Country a : Country.values()) {
            if (a.isInfected) {
                for (TransportType t : TransportType.values()) {
                    double r = Math.random();
                    if (r < t.chance) {
                        if (Country.isAvailableBy(t, a)) {
                            for (Country b : t.countriesTab) {
                                if (Route.getRouteState(t, a, b)) {
                                    double p = Math.random();
                                    if (p > 0.6) {
                                        if (b.unvaccinatedPopulation > Main.dayOfEpidemic * 2) {
                                            b.isInfected = true;
                                            b.numberOfCases = b.numberOfCases + Main.dayOfEpidemic * 2;
                                        }
                                    } else if (p > 0.4) {
                                        if (b.unvaccinatedPopulation > Main.dayOfEpidemic) {
                                            b.isInfected = true;
                                            b.numberOfCases = b.numberOfCases + Main.dayOfEpidemic;
                                        }
                                    }
                                } else {
                                    double p = Math.random();
                                    if (p < 0.1) {
                                        if (b.unvaccinatedPopulation > Main.dayOfEpidemic * 0.5) {
                                            b.isInfected = true;
                                            b.numberOfCases = (int) (b.numberOfCases + Main.dayOfEpidemic * 0.5);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void heal(double min, double max, double probability) {
        if ((double)Main.totalCases / (double)Main.worldPopulation < 0.8) {
            for (Country c : Country.values()) {
                double power = min + Math.random() * (max - min);
                if (c.isInfected) {
                    double p = Math.random();
                    if (p < probability) {
                        if (c.numberOfCases > (int) (c.numberOfCases - c.numberOfCases * power)) {
                            Main.healthPoints = (int) (Main.healthPoints + (c.numberOfCases * power) / 2 * ((double) 1 / (double) Main.dayOfEpidemic));
                            c.numberOfCases = (int) (c.numberOfCases - c.numberOfCases * power);
                        } else {
                            c.numberOfCases = 0;
                            c.isInfected = false;
                        }
                    }
                }
            }
        }
    }
}
