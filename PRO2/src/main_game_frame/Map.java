package main_game_frame;

import action_listeners.CountryButtonsListener;
import objects.Country;
import main.Visuals;
import main.Main;
import objects.Route;
import objects.TransportType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JLabel {
    public static JButton[] countriesBtns;
    private static JLabel eventPopupLbl;
    private static JLabel circle;

    public Map() {
        for (Country c : Country.values()) {
            Main.worldPopulation = Main.worldPopulation + c.population;
        }

        paintShips();
        paintPlanes();
        paintTrains();
        paintBuses();
        paintCars();

        setBackground(Visuals.getMainBackgroundColor());
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        ImageIcon mapIcon = new ImageIcon("europe_countries_s.jpg");
        setIcon(mapIcon);

        JButton polandBtn = new JButton("Poland");
        JButton germanyBtn = new JButton("Germany");
        JButton russiaBtn = new JButton("Russia");
        JButton spainBtn = new JButton("Spain");
        JButton ukraineBtn = new JButton("Ukraine");
        JButton romaniaBtn = new JButton("Romania");
        JButton franceBtn = new JButton("France");
        JButton turkeyBtn = new JButton("Turkey");
        JButton italyBtn = new JButton("Italy");
        JButton ukBtn = new JButton("<html><div style='text-align: center;'>United<br/>Kingdom</div></html>");
        JButton swedenBtn = new JButton("Sweden");
        JButton icelandBtn = new JButton("Iceland");
        JButton norwayBtn = new JButton("Norway");
        JButton greeceBtn = new JButton("Greece");
        JButton austriaBtn = new JButton("Austria");

        countriesBtns = new JButton[]{
                austriaBtn, franceBtn, germanyBtn, greeceBtn, icelandBtn, italyBtn,
                norwayBtn, polandBtn, romaniaBtn, russiaBtn, spainBtn, swedenBtn,
                turkeyBtn, ukBtn, ukraineBtn
        };

        int it = 0;
        for (Country c : Country.values()) {
            c.button = countriesBtns[it];
            it++;
        }

        turkeyBtn.setBounds(590, 495, 45, 20);
        polandBtn.setBounds(380, 315, 50, 20);
        germanyBtn.setBounds(290, 335, 60, 20);
        austriaBtn.setBounds(340, 390, 55, 20);
        greeceBtn.setBounds(440, 525, 47, 20);
        russiaBtn.setBounds(570, 220, 45, 20);
        ukraineBtn.setBounds(500, 345, 55, 20);
        norwayBtn.setBounds(295, 170, 50, 20);
        ukBtn.setBounds(180, 275, 55, 40);
        swedenBtn.setBounds(345, 200, 55, 20);
        spainBtn.setBounds(115, 470, 40, 20);
        icelandBtn.setBounds(120, 60, 50, 20);
        romaniaBtn.setBounds(450, 410, 60, 20);
        italyBtn.setBounds(330, 480, 35, 20);
        franceBtn.setBounds(208, 390, 50, 20);

        for (JButton btn : countriesBtns) {
            add(btn);
            btn.addActionListener(new CountryButtonsListener());

            updateCountryColor(btn);
            btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }

        eventPopupLbl = new JLabel("");
        eventPopupLbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        add(eventPopupLbl);

        circle = new JLabel();
        circle.setPreferredSize(new Dimension(100, 100));
        add(circle);
        paintStartInItaly();

        /*
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX() + ", " + e.getY());
            }
        });
        */

    }

    public static void updateEventPopup(String text) {
        eventPopupLbl.setBounds(5, 5, text.length() * 10, 25);
        eventPopupLbl.setText(text);

        Timer t = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventPopupLbl.setText("");
            }
        });
        t.setRepeats(false);
        t.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int thicc = 2;

        paintShipRoutes(g2, thicc);
        paintPlaneRoutes(g2, thicc);
        paintTrainRoutes(g2, thicc);
        paintBusRoutes(g2, thicc);
        paintCarRoutes(g2, thicc);

    }

    // żeby nie było, że to wszystko robiłam ręcznie - współrzędne
    // otrzymywałam dzięki MouseListenerowi

    private void paintPlaneRoutes(Graphics2D g2, int thickness) {
        g2.setColor(new Color(0, 166, 147));
        g2.setStroke(new BasicStroke(thickness));

        g2.drawLine(586, 225, 606, 510); // Russia - Turkey
        g2.drawLine(220, 395, 360, 490); // France - Italy
        g2.drawLine(133, 485, 209, 280); // Spain - UK
        g2.drawLine(465, 540, 405, 320); // Greece - Poland
        g2.drawLine(168, 62, 591, 230); // Iceland - Russia
        g2.drawLine(132, 76, 185, 280); // Iceland - UK

    }

    private void paintTrainRoutes(Graphics2D g2, int thickness) {
        g2.setColor(new Color(0x7B3F00));
        g2.setStroke(new BasicStroke(thickness));

        g2.drawLine(216, 312, 233, 392); // France - UK
        g2.drawLine(428, 320, 576, 237); // Poland - Russia
        g2.drawLine(340, 350, 350, 390); // Germany - Austria
        g2.drawLine(357, 200, 445, 52); // Sweden - Russia
        g2.drawLine(445, 52, 594, 220); // -- || --
        g2.drawLine(497, 413, 529, 360); // Romania - Ukraine

    }

    private void paintBusRoutes(Graphics2D g2, int thickness) {
        g2.setColor(new Color(0xC04000));
        g2.setStroke(new BasicStroke(thickness));

        g2.drawLine(425, 331, 481, 413); // Poland - Romania
        g2.drawLine(318, 350, 256, 394); // Germany - France
        g2.drawLine(592, 498, 503, 473); // Turkey - Greece
        g2.drawLine(503, 473, 469, 526); // -- || --
        g2.drawLine(322, 336, 382, 320); // Germany - Poland

    }

    private void paintCarRoutes(Graphics2D g2, int thickness) {
        g2.setColor(new Color(0xB803FF));
        g2.setStroke(new BasicStroke(thickness));

        g2.drawLine(427, 324, 500, 355); // Poland - Ukraine
        g2.drawLine(546, 348, 582, 237); // Ukraine - Russia
        g2.drawLine(153, 479, 225, 407); // Spain - France
        g2.drawLine(321, 352, 353, 482); // Germany - Italy
        g2.drawLine(633, 499, 698, 431); // Turkey - Russia
        g2.drawLine(698, 431, 610, 237); // -- || --
        g2.drawLine(381, 400, 395, 330); // Austria - Poland
        g2.drawLine(300, 186, 347, 214); // Norway - Sweden

        g2.drawLine(465, 528, 475, 427); // Greece - Romania

    }

    private void paintShipRoutes(Graphics2D g2, int thickness) {
        g2.setColor(new Color(0, 0, 128));
        g2.setStroke(new BasicStroke(thickness));

        g2.drawLine(175, 75, 300, 152); // Iceland - Norway
        g2.drawLine(155, 85, 200, 200); // Iceland - UK
        g2.drawLine(120, 82, 100, 420); // Iceland - Spain
        g2.drawLine(169, 529, 335, 552); // Spain - Italy
        g2.drawLine(395, 530, 447, 550); // Italy - Greece
        g2.drawLine(473, 564, 525, 545); // Greece - Turkey
        g2.drawLine(525, 430, 555, 480); // Turkey - Romania
        g2.drawLine(625, 405, 620, 458); // Turkey - Russia
        g2.drawLine(224, 220, 287, 206); // UK - Norway
        g2.drawLine(299, 296, 297, 225); // Germany - Norway
        g2.drawLine(485, 184, 380, 215); // Russia - Sweden

    }

    private void paintPlanes() {
        String path = "transport-icons\\plane-icon.png";
        TransportType t = TransportType.PLANE;

        JLabel plane1 = new JLabel(new ImageIcon(path)); // Iceland - Russia
        this.add(plane1);
        paintOne(t, Country.ICELAND, Country.RUSSIA, plane1, 528, 198, -2.5, -1, 140);

        JLabel plane2 = new JLabel(new ImageIcon(path));  // Turkey - Russia
        this.add(plane2);
        paintOne(t, Country.TURKEY, Country.RUSSIA, plane2, 597, 454, -0.1, -1, 210);

        JLabel plane3 = new JLabel(new ImageIcon(path)); // Iceland - UK
        this.add(plane3);
        paintOne(t, Country.ICELAND, Country.UK, plane3, 127, 109, 0.5, 1.5, 85);

        JLabel plane4 = new JLabel(new ImageIcon(path)); // Poland - Greece
        this.add(plane4);
        paintOne(t,Country.POLAND, Country.GREECE, plane4, 449, 497, -0.6, -1.875, 85);

        JLabel plane5 = new JLabel(new ImageIcon(path)); // France - Italy
        this.add(plane5);
        paintOne(t,Country.FRANCE, Country.ITALY, plane5, 318, 460, -0.75, -0.5, 100);

        JLabel plane6 = new JLabel(new ImageIcon(path)); // UK - Spain
        this.add(plane6);
        paintOne(t,Country.UK, Country.SPAIN, plane6, 185, 322, -1, 2.3, 55);

    }

    private void paintTrains() {
        String path = "transport-icons\\train-icon.png";
        TransportType t = TransportType.TRAIN;

        JLabel train1 = new JLabel(new ImageIcon(path)); // Russia - Poland
        this.add(train1);
        paintOne(t, Country.RUSSIA, Country.POLAND, train1, 549, 240, -1, 0.5, 120);

        JLabel train2 = new JLabel(new ImageIcon(path));  // Germany - Austria
        this.add(train2);
        paintOne(t, Country.GERMANY, Country.AUSTRIA,train2, 335, 355, 0.125, 0.5, 35);

        JLabel train3 = new JLabel(new ImageIcon(path)); // UK - France
        this.add(train3);
        paintOne(t, Country.UK, Country.FRANCE, train3, 205, 315, 0.20, 0.75, 73);

        JLabel train4 = new JLabel(new ImageIcon(path)); // Ukraine - Romania
        this.add(train4);
        paintOne(t, Country.UKRAINE, Country.ROMANIA, train4, 510, 366, -0.5, 0.6, 40);

        JLabel train5 = new JLabel(new ImageIcon(path)); // Russia - Sweden
        this.add(train5);
        paintOne(t,Country.RUSSIA, Country.SWEDEN, train5, 578, 196, -1, -1.1, 130);

        JLabel train6 = new JLabel(new ImageIcon(path)); // Sweden - Russia
        this.add(train6);
        paintOne(t, Country.SWEDEN, Country.RUSSIA, train6, 354, 175, 0.9, -1.5, 80);

    }

    private void paintBuses() {
        String path = "transport-icons\\bus-icon.png";
        TransportType t = TransportType.BUS;

        JLabel bus1 = new JLabel(new ImageIcon(path)); // France - Germany
        this.add(bus1);
        paintOne(t, Country.FRANCE, Country.GERMANY, bus1, 255, 370, 0.45, -0.25, 70);

        JLabel bus2 = new JLabel(new ImageIcon(path));  // Germany - Poland
        this.add(bus2);
        paintOne(t, Country.GERMANY, Country.POLAND, bus2, 335, 316, 0.5, -0.2, 45);

        JLabel bus3 = new JLabel(new ImageIcon(path)); // Poland - Romania
        this.add(bus3);
        paintOne(t, Country.POLAND, Country.ROMANIA, bus3, 424, 338, 0.6, 0.75, 70);

        JLabel bus4 = new JLabel(new ImageIcon(path)); // Turkey - Greece
        this.add(bus4);
        paintOne(t, Country.TURKEY, Country.GREECE, bus4, 563, 485, -0.75, -0.3, 65);

        JLabel bus5 = new JLabel(new ImageIcon(path)); // Greece - Turkey
        this.add(bus5);
        paintOne(t, Country.GREECE, Country.TURKEY, bus5, 470, 500, 0.4, -0.5, 45);

    }

    private void paintCars() {
        String path = "transport-icons\\car-icon.png";
        TransportType t = TransportType.CAR;

        JLabel car1 = new JLabel(new ImageIcon(path)); // Ukraine - Russia
        this.add(car1);
        paintOne(t, Country.UKRAINE, Country.RUSSIA, car1, 540, 320, 0.35, -1, 75);

        JLabel car2 = new JLabel(new ImageIcon(path));  // Poland - Ukraine
        this.add(car2);
        paintOne(t, Country.POLAND, Country.UKRAINE, car2, 435, 318, 0.5, 0.25, 85);

        JLabel car3 = new JLabel(new ImageIcon(path)); // Poland - Austria
        this.add(car3);
        paintOne(t, Country.POLAND, Country.AUSTRIA, car3, 383, 336, -0.25, 0.75, 45);

        JLabel car4 = new JLabel(new ImageIcon(path)); // Spain - France
        this.add(car4);
        paintOne(t, Country.SPAIN, Country.FRANCE, car4, 161, 456, 0.5, -0.6, 75);

        JLabel car5 = new JLabel(new ImageIcon(path)); // Italy - Germany
        this.add(car5);
        paintOne(t,Country.ITALY, Country.GERMANY, car5, 344, 462, -0.3, -0.9, 120);

        JLabel car6 = new JLabel(new ImageIcon(path)); // Norway - Sweden
        this.add(car6);
        paintOne(t, Country.NORWAY, Country.SWEDEN, car6, 308, 190, 0.5, 0.25, 35);

        JLabel car7 = new JLabel(new ImageIcon(path)); // Romania - Greece
        this.add(car7);
        paintOne(t, Country.ROMANIA, Country.GREECE, car7, 463, 428, -0.1, 1, 73);

        JLabel car8 = new JLabel(new ImageIcon(path)); // Russia - Turkey
        this.add(car8);
        paintOne(t, Country.RUSSIA, Country.TURKEY, car8, 610, 248, 0.45, 1, 160);

        JLabel car9 = new JLabel(new ImageIcon(path)); // Turkey - Russia
        this.add(car9);
        paintOne(t, Country.TURKEY, Country.RUSSIA, car9,640, 475, 0.5, -0.55, 78);
    }

    private void paintShips() {
        String path = "transport-icons\\ship-icon.png";
        TransportType t = TransportType.SHIP;

        JLabel ship1 = new JLabel(new ImageIcon(path)); // Iceland - Spain
        this.add(ship1);
        paintOne(t, Country.ICELAND, Country.SPAIN, ship1, 90, 395, 0.1, -1.45, 210);

        JLabel ship2 = new JLabel(new ImageIcon(path)); // Spain - Italy
        this.add(ship2);
        paintOne(t, Country.SPAIN, Country.ITALY, ship2, 178, 518, 1, 0.15, 130);

        JLabel ship3 = new JLabel(new ImageIcon(path)); // Italy - Greece
        this.add(ship3);
        paintOne(t, Country.ITALY, Country.GREECE, ship3,395, 523, 0.4, 0.15, 60);

        JLabel ship4 = new JLabel(new ImageIcon(path)); // Greece - Turkey
        this.add(ship4);
        paintOne(t, Country.GREECE, Country.TURKEY, ship4, 476, 546, 0.5, -0.2, 55);

        JLabel ship5 = new JLabel(new ImageIcon(path)); // Turkey - Romania
        this.add(ship5);
        paintOne(t, Country.TURKEY, Country.ROMANIA, ship5, 543, 460, -0.3, -0.5, 60);

        JLabel ship6 = new JLabel(new ImageIcon(path)); // Turkey - Russia
        this.add(ship6);
        paintOne(t, Country.TURKEY, Country.RUSSIA, ship6,612, 405, -0.1, 1, 30);

        JLabel ship7 = new JLabel(new ImageIcon(path)); // Iceland - UK
        this.add(ship7);
        paintOne(t, Country.ICELAND, Country.UK, ship7,148, 88, 0.45, 1, 90);

        JLabel ship8 = new JLabel(new ImageIcon(path)); //  Norway - Iceland
        this.add(ship8);
        paintOne(t, Country.NORWAY, Country.ICELAND, ship8, 280, 130, -0.9, -0.5, 115);

        JLabel ship9 = new JLabel(new ImageIcon(path)); // Sweden - Russia
        this.add(ship9);
        paintOne(t, Country.SWEDEN, Country.RUSSIA, ship9, 402, 190, 0.5, -0.15, 110);

        JLabel ship10 = new JLabel(new ImageIcon(path)); // UK - Norway
        this.add(ship10);
        paintOne(t, Country.UK, Country.NORWAY, ship10, 229, 202, 0.5, -0.1, 75);

        JLabel ship11 = new JLabel(new ImageIcon(path)); // Germany - Norway
        this.add(ship11);
        paintOne(t, Country.GERMANY, Country.NORWAY, ship11,290, 277, -0.07, -0.7, 75);

    }

    private void paintOne(TransportType type, Country cA, Country cB, JLabel label, int startX, int startY, double multiplierX, double multiplierY, int repeats) {
        final int[] n = {0};
        final int[] i = {0};
        Timer t = new Timer(125 / 3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Route.getRouteState(type, cA, cB)) {
                    label.setBounds(startX + (int) (multiplierX * n[0]), startY + (int) (multiplierY * n[0]), 21, 20);
                    n[0] += 1;
                    i[0]++;
                    if (i[0] == repeats) {
                        n[0] = 0;
                        i[0] = 0;
                    }
                }
            }
        });
        t.start();

    }

    private void paintStartInItaly() {

        final int[] i = {30};
        final int[] r = {0};
        Timer t = new Timer(150, null);
        t.setInitialDelay(1000);
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (i[0] > 100) {
                    circle.setVisible(false);
                    t.stop();
                }

                circle.setIcon(new ImageIcon("circle\\bigger-red-circle-" + i[0] + ".png"));
                circle.setBounds(332 - r[0], 476 - r[0], i[0], i[0]);

                i[0] = i[0] + 10;
                r[0] = r[0] + 5;
            }
        });
        t.start();

    }


    public static void updateCountryColor(JButton btn) {
        Country country = null;
        for (Country c : Country.values()) {
            if (c.button.equals(btn))
                country = c;
        }

        assert country != null;
        int percent = (int) Math.round((double) country.numberOfCases / country.population * 100);

        if (percent < 1) {
            Visuals.setGreenBtnLook(btn);
        } else if (percent < 5) {
            Visuals.setYellowBtnLook(btn);
        } else if (percent < 50) {
            Visuals.setRedBtnLook(btn);
        } else {
            Visuals.setBlackBtnLook(btn);
        }
    }


}
