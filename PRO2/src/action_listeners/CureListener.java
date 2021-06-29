package action_listeners;

import exceptions.OneCureCountryException;
import main.Main;
import main_game_frame.Map;
import objects.Country;
import runnables.VirusRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CureListener implements ActionListener {
    public static Timer timer;
    public static int pause;
    public static Country country;

    public CureListener(Country country) throws OneCureCountryException {

        if (CureListener.country != null) {
            throw new OneCureCountryException();
        } else {
            CureListener.country = country;
            Map.updateEventPopup(CureListener.country.name + " started working on a cure");

            switch (CureListener.country){
                case ROMANIA:
                case UKRAINE:
                case GREECE:
                case POLAND:
                case ITALY:
                    pause = (int)(VirusRunnable.sleep * 1.2);
                    break;
                case NORWAY:
                case ICELAND:
                case RUSSIA:
                    pause = (int)(VirusRunnable.sleep * 0.8);
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
        if (Main.cureProgress < 100)
            Main.cureProgress++;
        else {
            Map.updateEventPopup(CureListener.country.name + " finished working on the cure!");
            timer.stop();

            VirusRunnable.healProbability = 0.9;
            VirusRunnable.maxHeal = 1;
            VirusRunnable.minHeal = VirusRunnable.minHeal + 0.5;
        }
    }
}
