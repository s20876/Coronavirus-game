package tabs.store;

import main.Visuals;
import objects.Country;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Store extends JPanel implements ActionListener {
    public static Country countrySelected;
    public static JLabel healthPointsLbl;
    public static JLabel notEnoughLbl;
    public static ArrayList<StoreItemWithCountry> improvementsWithCountry = new ArrayList<>();
    public static ArrayList<StoreItemWithoutCountry> improvementsWithoutCountry = new ArrayList<>();
    public static ArrayList<StoreWithCountryPanel> countryPanels = new ArrayList<>();
    private JScrollPane scrollStoreWithCountry;

    public Store() {

        Visuals.setTabBackgroundColor(this);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComboBox<Country> storeComboBox = new JComboBox<>(Country.values());
        storeComboBox.setSelectedItem(null);
        storeComboBox.addActionListener(this);

        JLabel healthIcon = new JLabel(new ImageIcon("mask-s.png"));
        healthPointsLbl = new JLabel(" " + 0 + " ");
        healthPointsLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        JPanel healthPointsPanel = new JPanel();
        Visuals.setTabBackgroundColor(healthPointsPanel);
        healthPointsPanel.add(healthIcon);
        healthPointsPanel.add(healthPointsLbl);

        notEnoughLbl = new JLabel("");
        notEnoughLbl.setForeground(new Color(0xAD1111));
        notEnoughLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        notEnoughLbl.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        JPanel upperStorePanel = new JPanel();
        upperStorePanel.setLayout(new BorderLayout());
        upperStorePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        Visuals.setTabBackgroundColor(upperStorePanel);
        upperStorePanel.add(storeComboBox, BorderLayout.LINE_START);
        upperStorePanel.add(healthPointsPanel, BorderLayout.LINE_END);
        upperStorePanel.add(notEnoughLbl, BorderLayout.CENTER);

        new StoreItemWithoutCountry( "Social campaign", 40,
                "<html>Countries will start a social<br/>campaign on hand-washing,<br/>" +
                        "coughing in oneself's elbow etc.</html>",
                "campaign");

        new StoreItemWithoutCountry( "Desinfection", 100,
                "<html>There will be intensified pressure<br/>" +
                        "on desinfecting publicly used places.</html>", "desinfection");

        new StoreItemWithoutCountry("Social distance", 180,
                "<html>People will be obligated to keep<br/>" +
                        "at least a 2m distance from each<br/>other.</html>", "distance");

        new StoreItemWithoutCountry("Mask wearing", 200,
                "<html>Wearing masks in public will<br/>be obligatory.</html>",
                "masks");

        new StoreItemWithoutCountry("Weaken the virus", 5000,
                "<html>The virus will spread slowlier.<br/>" +
                        "You can use this feature only ONCE.</html>", "virus_weak");

        StoreWithoutCountryPanel panel = new StoreWithoutCountryPanel();

        for (Country c : Country.values()) {

            new StoreItemWithCountry(c, "<html>Start working<br/>on the cure</html>", 300,
                    "<html>This country will start<br/>working on the cure.</html>",
                    "cure_start");

            new StoreItemWithCountry(c, "<html>Work faster<br/>on the cure</html>", 600,
                    "<html>You can fasten the scientists,<br/>but be careful: you can use" +
                            "<br/>this feature only ONCE.</html>", "cure_faster");

            new StoreItemWithCountry(c, "<html>Start working<br/>on the vaccine</html>", 400,
                    "<html>This country will start working<br/>on a vaccine against coronavirus.</html>",
                    "vaccine_start");

            new StoreItemWithCountry(c, "<html>Work faster<br/>on the vaccine</html>", 800,
                    "<html>You can fasten the scientists,<br/>but be careful: you can use" +
                            "<br/>this feature only ONCE.</html>", "vaccine_faster");

            new StoreWithCountryPanel(c);

        }

        JLabel tmp = new JLabel("                       (select a country)");
        tmp.setFont(new Font(Font.DIALOG, Font.ITALIC, 14));

        scrollStoreWithCountry = new JScrollPane(tmp);
        scrollStoreWithCountry.setPreferredSize(new Dimension(350,150));

        JScrollPane scrollStoreWithoutCountry = new JScrollPane(panel);
        scrollStoreWithoutCountry.setPreferredSize(new Dimension(350,290));

        JLabel independentLbl = new JLabel("Country-independent features:");
        independentLbl.setForeground(Visuals.getMainBackgroundColor());
        JPanel scrolling = new JPanel();
        scrolling.setLayout(new BoxLayout(scrolling, BoxLayout.PAGE_AXIS));
        Visuals.setTabBackgroundColor(scrolling);
        scrolling.add(scrollStoreWithCountry);
        scrolling.add(Box.createRigidArea(new Dimension(0, 5)));
        scrolling.add(independentLbl);
        scrolling.add(scrollStoreWithoutCountry);

        add(upperStorePanel, BorderLayout.PAGE_START);
        add(scrolling, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        notEnoughLbl.setText("");

        JComboBox cb = (JComboBox)e.getSource();
        countrySelected = (Country) cb.getSelectedItem();

        for (StoreWithCountryPanel panel : countryPanels) {
            if (panel.country.equals(countrySelected))
                scrollStoreWithCountry.setViewportView(panel);
        }

    }
}
