package main_game_frame;

import tabs.InfectionStatistics;
import tabs.store.Store;
import tabs.transport.TransportInformation;

import javax.swing.*;
import java.awt.*;

public class TabPanel extends JTabbedPane {
    public TabPanel(){

        setBackground(new Color(48,	233	,180));

        InfectionStatistics infectionPanel = new InfectionStatistics();
        TransportInformation transportPanel = new TransportInformation();
        Store storePanel = new Store();

        addTab("Infections", infectionPanel);
        addTab("Transport", transportPanel);
        addTab("Store", storePanel);
    }

}
