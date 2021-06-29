package tabs.transport;

import objects.TransportType;
import main.Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseTransportTypePanel extends JPanel {

    static TransportType selectedType;

    public ChooseTransportTypePanel(){

        JLabel chooseTransportLbl = new JLabel("Select type of communication: ");

        JButton showBtn = new JButton(" Show map ");
        JButton defaultViewBtn = new JButton(" Back to normal map ");

        JPanel confirmViewBtns = new JPanel();
        confirmViewBtns.setLayout(new BoxLayout(confirmViewBtns, BoxLayout.LINE_AXIS));
        Visuals.setTabBackgroundColor(confirmViewBtns);
        confirmViewBtns.add(showBtn);
        confirmViewBtns.add(Box.createRigidArea(new Dimension(10, 0)));
        confirmViewBtns.add(defaultViewBtn);

        Visuals.setButtonLook(showBtn);
        Visuals.setButtonLook(defaultViewBtn);

        JRadioButton carsBtn = new JRadioButton("cars");
        JRadioButton trainsBtn = new JRadioButton("trains");
        JRadioButton busesBtn = new JRadioButton("buses");
        JRadioButton planesBtn = new JRadioButton("planes");
        JRadioButton shipsBtn = new JRadioButton("ships");

        JRadioButton[] radioBtnTab = new JRadioButton[]{carsBtn, trainsBtn, busesBtn, planesBtn, shipsBtn};

        ButtonGroup transportBtnGroup = new ButtonGroup();

        ActionListener radioBtnAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String btnText = e.getActionCommand();

                switch(btnText){
                    case "cars":
                        selectedType = TransportType.CAR;
                        break;
                    case "trains":
                        selectedType = TransportType.TRAIN;
                        break;
                    case "buses":
                        selectedType = TransportType.BUS;
                        break;
                    case "planes":
                        selectedType = TransportType.PLANE;
                        break;
                    case "ships":
                        selectedType = TransportType.SHIP;
                        break;
                    default:
                        selectedType = null;
                }
                ChooseRoutePanel.setComboBoxes();
            }
        };

        for (JRadioButton rbtn : radioBtnTab) {
            transportBtnGroup.add(rbtn);
            rbtn.setBackground(Visuals.getTabBackgroundColor());
            rbtn.addActionListener(radioBtnAL);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2));
        panel.add(carsBtn);
        panel.add(planesBtn);
        panel.add(busesBtn);
        panel.add(shipsBtn);

        JPanel radioBtnPanel = new JPanel();
        radioBtnPanel.setLayout(new BorderLayout());
        radioBtnPanel.add(panel, BorderLayout.CENTER);
        radioBtnPanel.add(trainsBtn, BorderLayout.PAGE_END);

        chooseTransportLbl.setAlignmentX(Component.RIGHT_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        Visuals.setTabBackgroundColor(this);
        add(chooseTransportLbl);
        add(radioBtnPanel);


    }
}
