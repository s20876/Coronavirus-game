package tabs.store;

import main.Visuals;
import objects.Country;

import javax.swing.*;

public class StoreWithCountryPanel extends JPanel {
    public Country country;

    public StoreWithCountryPanel(Country country){
        this.country = country;
        Store.countryPanels.add(this);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        Visuals.setTabBackgroundColor(this);

        for (StoreItemWithCountry item : Store.improvementsWithCountry) {
            if (item.country.equals(this.country))
                add(item);
        }

    }
}
