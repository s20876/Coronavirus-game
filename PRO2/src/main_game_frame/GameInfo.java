package main_game_frame;

import main.Visuals;

import javax.swing.*;
import java.awt.*;

public class GameInfo extends JPanel {
    public static JLabel timeLbl;
    public static JLabel worldInfectionsLbl;
    public static JLabel worldPercentageLbl;
    public static JLabel vaccineProgressLbl;
    public static JLabel cureProgressLbl;
    public static JLabel pointsLbl;
    public static JLabel healthLbl;

    public GameInfo() {

        setBackground(Visuals.getMainBackgroundColor());
        setLayout(new GridLayout(1, 4));

        timeLbl = new JLabel("Day of epidemics: " + 0);
        worldInfectionsLbl = new JLabel("Total cases: " + 0);
        worldPercentageLbl = new JLabel(0 + "% of the world infected");
        vaccineProgressLbl = new JLabel("Vaccine progress: "+0+"%");
        cureProgressLbl = new JLabel("Cure progress: "+0+"%");

        JLabel pointsIconLbl = new JLabel(new ImageIcon("point_star.png"));
        pointsLbl = new JLabel(" " + 0 + " ");

        JLabel healthIconLbl = new JLabel(new ImageIcon("mask-s.png"));
        healthLbl = new JLabel(" " + 0 + " ");

        timeLbl.setForeground(Color.white);
        worldInfectionsLbl.setForeground(Color.white);
        worldPercentageLbl.setForeground(Color.white);
        vaccineProgressLbl.setForeground(Color.white);
        cureProgressLbl.setForeground(Color.white);
        pointsLbl.setForeground(Color.white);
        healthLbl.setForeground(Color.white);

        timeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        worldInfectionsLbl.setHorizontalAlignment(SwingConstants.CENTER);
        worldPercentageLbl.setHorizontalAlignment(SwingConstants.CENTER);
        vaccineProgressLbl.setHorizontalAlignment(SwingConstants.CENTER);
        cureProgressLbl.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel normalPointsPanel = new JPanel();
        normalPointsPanel.setBackground(Visuals.getMainBackgroundColor());
        normalPointsPanel.add(healthIconLbl);
        normalPointsPanel.add(healthLbl);

        JPanel healthPointsPanel = new JPanel();
        healthPointsPanel.setBackground(Visuals.getMainBackgroundColor());
        healthPointsPanel.add(pointsIconLbl);
        healthPointsPanel.add(pointsLbl);

        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(Visuals.getMainBackgroundColor());
        pointsPanel.setLayout(new GridLayout(2,1));
        pointsPanel.add(normalPointsPanel);
        pointsPanel.add(healthPointsPanel);

        add(timeLbl);
        add(worldInfectionsLbl);
        add(worldPercentageLbl);
        add(vaccineProgressLbl);
        add(cureProgressLbl);
        add(pointsPanel);

    }
}
