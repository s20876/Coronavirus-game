package tabs;

import main.Main;
import objects.Country;
import main.Visuals;

import javax.swing.*;
import java.awt.*;

public class InfectionStatistics extends JPanel {
    public static Country countrySelected;

    private static JLabel countryNameLbl;
    private static JLabel countryFlagLbl;
    private static JLabel populationLbl;
    private static JLabel infectionsLbl;
    private static JLabel percentageLbl;
    private static JLabel r0Lbl; // basic reproductive ratio
    private static JLabel countryMapLbl;

    private static int percent;
    private static JButton percentBtn;
    private static JButton messageBtn;

    public InfectionStatistics() {

        Visuals.setTabBackgroundColor(this);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        Visuals.setTabBackgroundColor(textPanel);

        countryNameLbl = new JLabel("(select a country)");
        countryFlagLbl = new JLabel("");

        countryNameLbl.setFont(new Font(Font.DIALOG, Font.ITALIC, 14));

        populationLbl = new JLabel("");
        infectionsLbl = new JLabel("");
        percentageLbl = new JLabel("");
        r0Lbl = new JLabel("");

        countryMapLbl = new JLabel("");

        textPanel.add(countryNameLbl);
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        textPanel.add(countryFlagLbl);
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        textPanel.add(populationLbl);
        textPanel.add(infectionsLbl);
        textPanel.add(percentageLbl);
        textPanel.add(r0Lbl);
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        add(textPanel, BorderLayout.PAGE_START);
        add(countryMapLbl, BorderLayout.CENTER);

        percentBtn = new JButton("");
        countryMapLbl.add(percentBtn);
        percentBtn.setVisible(false);
        messageBtn = new JButton("");
        countryMapLbl.add(messageBtn);
        messageBtn.setVisible(false);

        countrySelected = Country.AUSTRIA;


    }

    public static void showCountryStatistics() {
        percent = (int) Math.round((double) countrySelected.numberOfCases / countrySelected.population * 100);

        countryNameLbl.setText(countrySelected.toString());
        countryNameLbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

        countryFlagLbl.setIcon(countrySelected.flag);

        populationLbl.setText("Population: " + countrySelected.populationToString);
        infectionsLbl.setText("Cases: " + Main.makeReadable(countrySelected.numberOfCases));
        percentageLbl.setText("% infected: " + percent + "%");
        r0Lbl.setText("Basic reproduction ratio R0 = " + countrySelected.r0);

        createCountryMap();

    }

    private static void createCountryMap() {

        int width = 300;
        int height = 370;

        countryMapLbl.setIcon(countrySelected.map);

        percentBtn.setVisible(true);
        percentBtn.setText(percent + "%");
        percentBtn.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        percentBtn.setBounds(width / 2 - 33, height / 2 - 34, 66, 22);

        messageBtn.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        messageBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        messageBtn.setBounds(width / 2 - 65, height / 2 + 34 - 26, 130, 26);

        if (percent < 1){
            Visuals.setGreenBtnLook(percentBtn);

            messageBtn.setVisible(false);

        }else if(percent < 5){
            Visuals.setYellowBtnLook(percentBtn);

            messageBtn.setVisible(true);
            messageBtn.setText("WARNING!");
            Visuals.setYellowBtnLook(messageBtn);

        }else if(percent < 50){
            Visuals.setRedBtnLook(percentBtn);

            messageBtn.setVisible(true);
            messageBtn.setText("DANGER!");
            Visuals.setRedBtnLook(messageBtn);

        }else{
            Visuals.setBlackBtnLook(percentBtn);

            messageBtn.setVisible(true);
            messageBtn.setText("TOO LATE X__X");
            Visuals.setBlackBtnLook(messageBtn);
        }

    }


}
