package tabs.transport;

import main.Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class TransportInformation extends JPanel {
    private static EventScroll eventScroll;

    public TransportInformation(){

        Visuals.setTabBackgroundColor(this);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        ChooseTransportTypePanel chooseTransportTypePanel = new ChooseTransportTypePanel();

        ChooseRoutePanel chooseRoutePanel = new ChooseRoutePanel();

        JPanel upperPanel = new JPanel();
        Visuals.setTabBackgroundColor(upperPanel);
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(chooseTransportTypePanel, BorderLayout.PAGE_START);
        upperPanel.add(chooseRoutePanel, BorderLayout.CENTER);

        eventScroll = new EventScroll();

        add(upperPanel,BorderLayout.PAGE_START);
        add(eventScroll,BorderLayout.CENTER);

    }

    /*
    Ponownie chciałabym zgłosić, że innego rozwiązania nie udało mi się zaimplementować tak, aby działało,
    więc i tej metody użyłam z poniższego linka:
    https://stackoverflow.com/a/31317110
    */

    public static void scrollToBottom(){
        JScrollBar verticalBar = eventScroll.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

}
