package action_listeners;

import objects.Country;
import tabs.InfectionStatistics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountryButtonsListener implements ActionListener {
    public static Country countrySelected;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton)e.getSource();

        for (Country c : Country.values()) {
            if (c.button.equals(btnClicked))
                countrySelected = c;
        }

        InfectionStatistics.countrySelected = countrySelected;

        InfectionStatistics.showCountryStatistics();

    }
}
