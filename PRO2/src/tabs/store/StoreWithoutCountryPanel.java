package tabs.store;

import main.Visuals;

import javax.swing.*;

public class StoreWithoutCountryPanel extends JPanel {

    public StoreWithoutCountryPanel(){

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        Visuals.setTabBackgroundColor(this);

        for (StoreItemWithoutCountry item : Store.improvementsWithoutCountry) {
            add(item);
        }

    }
}

