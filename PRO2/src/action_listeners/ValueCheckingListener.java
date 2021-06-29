package action_listeners;

import main.Main;
import main_game_frame.GameInfo;
import main_game_frame.MainFrame;
import main_game_frame.Map;
import objects.Country;
import start_stuff.StartFrame;
import tabs.InfectionStatistics;
import tabs.store.Store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValueCheckingListener implements ActionListener{
    private int iter;

    public ValueCheckingListener(){
        iter = 0;

        Timer timer = new Timer(500, this);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Map
        for (JButton b : Map.countriesBtns) {
            Map.updateCountryColor(b);
        }

        // GameInfo
        Main.totalCases = 0;
        for (Country c : Country.values()) {
            Main.totalCases = Main.totalCases + c.numberOfCases;
        }

        GameInfo.timeLbl.setText("Day of epidemics: " + Main.dayOfEpidemic);
        GameInfo.worldInfectionsLbl.setText("Total cases: " + Main.makeReadable(Main.totalCases));
        GameInfo.worldPercentageLbl.setText(
                Math.round(((double) Main.totalCases / Main.worldPopulation) * 100)
                        + "% of the world infected"
        );

        GameInfo.vaccineProgressLbl.setText("Vaccine progress: "+Main.vaccineProgress+"%");
        GameInfo.cureProgressLbl.setText("Cure progress: "+Main.cureProgress+"%");

        GameInfo.pointsLbl.setText(" " +  Main.makeReadable(Main.points) + " ");
        GameInfo.healthLbl.setText(" "+  Main.makeReadable(Main.healthPoints) + " ");

        // Infection Statistics
        InfectionStatistics.showCountryStatistics();

        // Store
        Store.healthPointsLbl.setText(" " + Main.makeReadable(Main.healthPoints) + " ");

        // win or lose
        if (Main.totalCases / Main.worldPopulation * 100 == 100){
            if (iter < 1) {
                MainFrame.lostGame = true;
                iter++;
            }
        }

        if (Main.totalCases == 0){
            if (!StartFrame.isStart)
                MainFrame.wonGame = true;
        }
    }
}
