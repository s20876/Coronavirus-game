package tabs.store;

import exceptions.NotEnoughHealthPointsException;
import main.Main;
import main.Visuals;
import objects.Country;
import objects.TransportType;
import runnables.VirusRunnable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreItemWithoutCountry extends JPanel implements ActionListener {
    private int price;
    public String code;
    public JButton buyBtn;

    public StoreItemWithoutCountry(String title, int price, String description, String code){
        Store.improvementsWithoutCountry.add(this);

        this.price = price;
        this.code = code;

        Visuals.setTabBackgroundColor(this);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JPanel titlePanel = new JPanel();
        Visuals.setTabBackgroundColor(titlePanel);
        JPanel restPanel = new JPanel();
        Visuals.setTabBackgroundColor(restPanel);

        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 18));

        JLabel priceLbl = new JLabel(""+price);
        priceLbl.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 18));
        priceLbl.setForeground(new Color(14,109,57));

        JLabel maskLbl = new JLabel(new ImageIcon("mask-small.png"));

        titlePanel.add(titleLbl);
        titlePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        titlePanel.add(priceLbl);
        titlePanel.add(maskLbl);

        JLabel descriptionLbl = new JLabel(description);
        descriptionLbl.setFont(new Font("Serif", Font.ITALIC, 14));

        buyBtn = new JButton(" Buy ");
        Visuals.setButtonLook(buyBtn);
        buyBtn.addActionListener(this);

        restPanel.add(descriptionLbl);
        restPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        restPanel.add(buyBtn);

        add(titlePanel, BorderLayout.PAGE_START);
        add(restPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            buyImprovement();
        } catch (NotEnoughHealthPointsException ex) {
            Store.notEnoughLbl.setText(ex.getMessage());
        }

    }

    private void buyImprovement() throws NotEnoughHealthPointsException {
        if (Main.healthPoints >= price) {
            Store.notEnoughLbl.setText("");

            Main.healthPoints = Main.healthPoints - price;
            buyBtn.setText(" Bought ");
            buyBtn.setEnabled(false);

            executeImprovement();

        }
        else{
            throw new NotEnoughHealthPointsException();
        }
    }

    private void executeImprovement(){
        switch(code){
            case "campaign":
                VirusRunnable.minInfect = VirusRunnable.minInfect/2;
                for (TransportType t : TransportType.values()) {
                    t.chance = t.chance - t.chance/10;
                }
                for (Country c : Country.values()) {
                    c.threshold = (int)(c.threshold - (double)c.threshold/100);
                }
                break;
            case "desinfection":
                VirusRunnable.minInfect = VirusRunnable.minInfect/3;
                VirusRunnable.maxInfect = VirusRunnable.maxInfect - 0.1;
                for (TransportType t : TransportType.values()) {
                    t.chance = t.chance - t.chance/5;
                }
                for (Country c : Country.values()) {
                    c.threshold = c.threshold - c.threshold/10;
                }
                break;
            case "distance":
                VirusRunnable.minInfect = VirusRunnable.minInfect/4;
                VirusRunnable.maxInfect = VirusRunnable.maxInfect - 0.2;
                VirusRunnable.maxHeal = VirusRunnable.maxHeal + 0.1;
                VirusRunnable.healProbability = VirusRunnable.healProbability + 0.05;
                for (TransportType t : TransportType.values()) {
                    t.chance = t.chance - t.chance/2;
                }
                for (Country c : Country.values()) {
                    c.threshold = c.threshold - c.threshold/2;
                }
                break;
            case "masks":
                VirusRunnable.minInfect = VirusRunnable.minInfect/5;
                VirusRunnable.maxInfect = VirusRunnable.maxInfect - 0.4;
                VirusRunnable.maxHeal = VirusRunnable.maxHeal + 0.1;
                VirusRunnable.healProbability = VirusRunnable.healProbability + 0.1;
                for (TransportType t : TransportType.values()) {
                    t.chance = t.chance - t.chance/2;
                }
                for (Country c : Country.values()) {
                    c.threshold = c.threshold - c.threshold/2;
                }
                break;
            case "virus_weak":
                VirusRunnable.minInfect = VirusRunnable.minInfect/10;
                VirusRunnable.maxInfect = VirusRunnable.maxInfect/2;
                break;
        }
    }
}
