package tabs.transport;

import main.Visuals;
import objects.Country;
import objects.Route;
import objects.TransportType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseRoutePanel extends JPanel implements ActionListener {

    private static JComboBox<Country> countryA;
    private static JComboBox<Country> countryB;

    private static Country selectedCountryA;
    private static Country selectedCountryB;


    public ChooseRoutePanel(){

        Visuals.setTabBackgroundColor(this);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15,0,0,0));

        JPanel fromAtoBPanel = new JPanel();
        Visuals.setTabBackgroundColor(fromAtoBPanel);
        fromAtoBPanel.setLayout(new BoxLayout(fromAtoBPanel, BoxLayout.LINE_AXIS));
        fromAtoBPanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        countryA = new JComboBox<>();
        countryB = new JComboBox<>();

        selectedCountryA = Country.AUSTRIA;
        selectedCountryB = Country.AUSTRIA;

        countryA.setActionCommand("A");
        countryB.setActionCommand("B");

        countryA.addActionListener(this);
        countryB.addActionListener(this);

        JLabel arrow = new JLabel(new ImageIcon("grunge-arrow.png"));

        fromAtoBPanel.add(countryA);
        fromAtoBPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        fromAtoBPanel.add(arrow);
        fromAtoBPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        fromAtoBPanel.add(countryB);

        JButton showBtn = new JButton(" Show route ");
        Visuals.setButtonLook(showBtn);

        JPanel btnPanel = new JPanel();
        Visuals.setTabBackgroundColor(btnPanel);
        btnPanel.setLayout(new GridLayout(1,3));
        JLabel tmp1 = new JLabel("");
        JLabel tmp2 = new JLabel("");
        btnPanel.add(tmp1);
        btnPanel.add(showBtn);
        btnPanel.add(tmp2);

        JPanel routeInfoPanel = new JPanel();
        Visuals.setTabBackgroundColor(routeInfoPanel);
        JLabel routeTextLbl = new JLabel("");
        routeTextLbl.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        JLabel routeOpenLbl = new JLabel("");
        routeOpenLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        routeOpenLbl.setForeground(new Color(102,0,102));
        routeInfoPanel.add(routeTextLbl);
        routeInfoPanel.add(routeOpenLbl);

        showBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (countryA != null && countryB != null && ChooseTransportTypePanel.selectedType != null){
                    routeTextLbl.setText(
                            ChooseTransportTypePanel.selectedType.nameB + " route from "
                                    + selectedCountryA.name + " to " + selectedCountryB.name + " is "
                    );
                    routeOpenLbl.setText(Route.getRouteState(ChooseTransportTypePanel.selectedType,
                            selectedCountryA, selectedCountryB) ? "OPEN" : "CLOSED"
                    );
                }

            }
        });

        JPanel tmp = new JPanel();
        tmp.setLayout(new GridLayout(3,1));
        Visuals.setTabBackgroundColor(tmp);
        tmp.add(fromAtoBPanel);
        tmp.add(btnPanel);
        tmp.add(routeInfoPanel);

        add(tmp, BorderLayout.PAGE_START);

    }

    public static void setComboBoxes(){
        DefaultComboBoxModel<Country> defaultModelA = new DefaultComboBoxModel<>(Route.allCountries);
        DefaultComboBoxModel<Country> trainModelA = new DefaultComboBoxModel<>(Route.trainCountries);
        DefaultComboBoxModel<Country> busModelA = new DefaultComboBoxModel<>(Route.busCountries);
        DefaultComboBoxModel<Country> defaultModelB = new DefaultComboBoxModel<>(Route.allCountries);
        DefaultComboBoxModel<Country> trainModelB = new DefaultComboBoxModel<>(Route.trainCountries);
        DefaultComboBoxModel<Country> busModelB = new DefaultComboBoxModel<>(Route.busCountries);

        if (ChooseTransportTypePanel.selectedType == TransportType.TRAIN){
            countryA.setModel(trainModelA);
            countryB.setModel(trainModelB);
        }else if(ChooseTransportTypePanel.selectedType == TransportType.BUS){
            countryA.setModel(busModelA);
            countryB.setModel(busModelB);
        }else if(ChooseTransportTypePanel.selectedType != null) {
            countryA.setModel(defaultModelA);
            countryB.setModel(defaultModelB);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox comboBox = (JComboBox)e.getSource();
        Country country = (Country)comboBox.getSelectedItem();

        if (comboBox.getActionCommand().equals("A"))
            selectedCountryA = country;
        else
            selectedCountryB = country;

    }
}
