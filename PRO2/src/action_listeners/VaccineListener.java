package action_listeners;

import exceptions.OneVaccineCountryException;
import main.Main;
import main_game_frame.Map;
import objects.Country;
import runnables.VirusRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VaccineListener implements ActionListener {
    public static Timer timer;
    public static int pause;
    public static Country country;

    public VaccineListener(Country country) throws OneVaccineCountryException {

        if (VaccineListener.country != null) {
            throw new OneVaccineCountryException();
        } else {
            VaccineListener.country = country;
            Map.updateEventPopup(VaccineListener.country.name + " started working on a vaccine");

            switch (VaccineListener.country) {
                case ROMANIA:
                case UKRAINE:
                case GREECE:
                case POLAND:
                case ITALY:
                    pause = (int) (VirusRunnable.sleep * 1.3);
                    break;
                case NORWAY:
                case ICELAND:
                case RUSSIA:
                    pause = (int) (VirusRunnable.sleep * 0.7);
                    break;
                default:
                    pause = VirusRunnable.sleep;
            }

            timer = new Timer(pause, this);
            timer.start();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Main.vaccineProgress < 100)
            Main.vaccineProgress++;
        else {
            Map.updateEventPopup(VaccineListener.country.name + " finished working on the vaccine!");
            timer.stop();

            VirusRunnable.healProbability = 0.95;
            VirusRunnable.maxHeal = 1;
            VirusRunnable.minHeal = VirusRunnable.minHeal + 0.2;

            Timer t = new Timer(VirusRunnable.sleep, null);
            t.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (Country c : Country.values()) {
                        int healthyPopulation = c.population - c.numberOfCases;
                        if (c.unvaccinatedPopulation - (int)(0.3 * healthyPopulation) > 0) {
                            c.unvaccinatedPopulation = c.unvaccinatedPopulation - (int) (0.5 * healthyPopulation);
                        }else {
                            c.unvaccinatedPopulation = 0;
                            t.stop();
                        }
                    }
                }
            });
            t.start();

        }
    }
}

