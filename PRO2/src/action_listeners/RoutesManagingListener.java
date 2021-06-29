package action_listeners;

import main.Main;
import objects.Country;
import objects.Route;
import objects.TransportType;
import tabs.transport.TransportInformation;
import tabs.transport.EventScroll;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoutesManagingListener implements ActionListener {

    public RoutesManagingListener() {
        Timer timer = new Timer(500, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (TransportType t : TransportType.values()) {
            doRouteChecking(t);
        }

    }

    private void doRouteChecking(TransportType type) {

        for (Country c : Country.values()) {
            if (Country.isAvailableBy(type, c)) {

                int threshold;

                switch (type) {
                    case CAR:
                        threshold = c.threshold * 10;
                        break;
                    case BUS:
                        threshold = (int) (c.threshold * 1.5);
                        break;
                    case TRAIN:
                        threshold = c.threshold * 2;
                        break;
                    case PLANE:
                        threshold = c.threshold;
                        break;
                    case SHIP:
                        threshold = c.threshold * 11;
                        break;
                    default:
                        threshold = 0;
                }

                if (c.numberOfCases >= threshold) {
                    if (Route.isAnyRouteInCountryOpen(type, c)) {
                        EventScroll.addEvent("Day " + Main.dayOfEpidemic + ": " + c.name + " closed all their " + type.nameS + " routes");
                        TransportInformation.scrollToBottom();
                    }
                    Route.closeAllRoutesInCountry(c, type);
                } else if (Route.areAllRoutesInCountryClosed(type, c)) {
                    Route.openAllRoutesInCountry(c, type);
                    EventScroll.addEvent("Day " + Main.dayOfEpidemic + ": " + c.name + " opened all their " + type.nameS + " routes");
                    TransportInformation.scrollToBottom();
                }
            }
        }
    }
}
