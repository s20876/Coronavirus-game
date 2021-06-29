package tabs.store;

import action_listeners.CureListener;
import action_listeners.VaccineListener;
import exceptions.NotEnoughHealthPointsException;
import exceptions.OneCureCountryException;
import exceptions.OneVaccineCountryException;
import main.Main;
import main.Visuals;
import objects.Country;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreItemWithCountry extends JPanel implements ActionListener {
    private int price;
    private String code;
    public Country country;
    private JButton buyBtn;

    public StoreItemWithCountry(Country country, String title, int price, String description, String code){
        Store.improvementsWithCountry.add(this);

        this.price = price;
        this.country = country;
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

        if (this.code.equals("cure_faster") || this.code.equals("vaccine_faster")){
            buyBtn.setEnabled(false);
        }

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

            boolean cond = CureListener.country == null;
            enableOnCondition("cure_start", "cure_faster", cond);
            cond = VaccineListener.country == null;
            enableOnCondition("vaccine_start", "vaccine_faster", cond);

            executeImprovement();

        }
        else{
            throw new NotEnoughHealthPointsException();
        }
    }

    private void enableOnCondition(String itemClickedCode, String itemEnablingCode, boolean condition){
        if(code.equals(itemClickedCode)){
            for (StoreItemWithCountry item : Store.improvementsWithCountry) {
                if (this.country.equals(item.country) && item.code.equals(itemEnablingCode) && condition){
                    item.buyBtn.setEnabled(true);
                }
            }
        }
    }

    private void executeImprovement(){
        switch(code){
            case "cure_start":
                try {
                    new CureListener(country);
                } catch (OneCureCountryException e){
                    Main.healthPoints = Main.healthPoints + price;
                    buyBtn.setText(" Buy ");
                    buyBtn.setEnabled(true);
                    JOptionPane.showMessageDialog(this, e.getMessage(),
                            "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "cure_faster":
                CureListener.timer.setDelay(CureListener.pause/2);
                break;
            case "vaccine_start":
                try {
                    new VaccineListener(country);
                } catch (OneVaccineCountryException e){
                    Main.healthPoints = Main.healthPoints + price;
                    buyBtn.setText(" Buy ");
                    buyBtn.setEnabled(true);
                    JOptionPane.showMessageDialog(this, e.getMessage(),
                            "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "vaccine_faster":
                VaccineListener.timer.setDelay(VaccineListener.pause/2);
                break;
        }
    }
}
