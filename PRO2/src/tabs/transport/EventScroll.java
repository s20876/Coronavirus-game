package tabs.transport;

import main.Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventScroll extends JScrollPane {
    private static JPanel eventPanel;
    public EventScroll(){

        setPreferredSize(new Dimension(350,330));

        eventPanel = new JPanel();
        Visuals.setTabBackgroundColor(eventPanel);
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.PAGE_AXIS));


        setViewportView(eventPanel);

    }

    public static void addEvent(String text){
        JPanel event = new JPanel();
        event.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        event.setBackground(new Color(163, 186, 220));

        JLabel textLbl = new JLabel(text);
        event.add(textLbl);

        Timer t = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.setBackground(Color.white);
            }
        });
        t.setRepeats(false);
        t.start();

        eventPanel.add(event);

    }

}
