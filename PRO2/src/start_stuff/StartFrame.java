package start_stuff;

import high_scores.HighScoresFrame;
import main_game_frame.MainFrame;
import main.Visuals;
import main.Main;
import objects.Country;
import tabs.store.Store;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StartFrame extends JFrame {
    public static boolean isStart;

    public StartFrame() throws IOException {

        restartData();

        JLabel welcomeLbl = new JLabel("Koronawirus AntiPlague");
        welcomeLbl.setForeground(new Color(255,	135,	20));
        welcomeLbl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        welcomeLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JButton newGameBtn = new JButton(" New Game ");
        JButton highScoresBtn = new JButton(" High Scores ");
        JButton exitBtn = new JButton(" Exit ");
        JButton[] startBtns = new JButton[]{newGameBtn, highScoresBtn, exitBtn};

        for (JButton btn : startBtns) {
            Visuals.setButtonLook(btn);
            btn.setAlignmentX((Component.CENTER_ALIGNMENT));
        }

        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] levels = Level.values();

                int lvl = JOptionPane.showOptionDialog(StartFrame.this,
                        "Choose your difficulty level",
                        "Level choosing", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null,
                        levels, levels[0]);

                switch (lvl) {
                    case 0:
                        Main.levelChosen = (Level) levels[0];
                        break;
                    case 1:
                        Main.levelChosen = (Level) levels[1];
                        break;
                    case 2:
                        Main.levelChosen = (Level) levels[2];
                        break;
                    case 3:
                        Main.levelChosen = (Level) levels[3];
                        break;
                    default:
                        Main.levelChosen = null;
                }

                if (Main.levelChosen != null) {
                    try {
                        new MainFrame();
                        setVisible(false);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        highScoresBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new HighScoresFrame();
                    setVisible(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel btnsPanel = new JPanel();
        btnsPanel.setBackground(Visuals.getMainBackgroundColor());
        btnsPanel.setLayout(new BoxLayout(btnsPanel, BoxLayout.PAGE_AXIS));

        btnsPanel.add(newGameBtn);
        btnsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        btnsPanel.add(highScoresBtn);
        btnsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        btnsPanel.add(exitBtn);

        JPanel panel = new JPanel();
        panel.setBackground(Visuals.getMainBackgroundColor());
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        panel.add(welcomeLbl, BorderLayout.PAGE_START);
        panel.add(btnsPanel, BorderLayout.CENTER);

        add(panel);

        BufferedImage iconImg = ImageIO.read(new File("coronavirus-icon.png"));
        setIconImage(iconImg);
        setTitle("Koronawirus AntiPlague - Start Menu");

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void restartData(){
        isStart = true;
        Main.levelChosen = null;
        Main.dayOfEpidemic = 0;
        Main.points = 0;
        Main.healthPoints = 0;
        Main.cureProgress = 0;
        Main.vaccineProgress = 0;
        for (Country c : Country.values()) {
            c.numberOfCases = 0;
            c.unvaccinatedPopulation = c.population;
            c.r0 = 0;
            c.isInfected = false;
            c.threshold = c.constThreshold;
        }
        Store.improvementsWithCountry = new ArrayList<>();
        Store.improvementsWithoutCountry = new ArrayList<>();
        Store.countryPanels = new ArrayList<>();
    }

}
