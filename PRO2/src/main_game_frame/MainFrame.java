package main_game_frame;

import high_scores.HighScoreRecord;
import high_scores.HsListModel;
import objects.Country;
import objects.Route;
import main.Visuals;
import main.Main;
import runnables.TimeRunnable;
import start_stuff.StartFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainFrame extends JFrame implements KeyListener {
    public static Map map;
    public static boolean lostGame;
    public static boolean wonGame;

    private Thread timeThread = new Thread(new TimeRunnable());

    public MainFrame() throws IOException {

        timeThread.start();
        Route.openAllRoutes();

        lostGame = false;
        wonGame = false;

        lostOrWonGame();

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Visuals.getMainBackgroundColor());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GameInfo gameInfoPanel = new GameInfo();

        JButton backBtn = new JButton(" Back to menu ");
        Visuals.setButtonLook(backBtn);

        backBtn.addActionListener(e -> {
            try {
                backToMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());

        map = new Map();

        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(Visuals.getMainBackgroundColor());
        upperPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(backBtn, BorderLayout.LINE_START);
        upperPanel.add(gameInfoPanel, BorderLayout.CENTER);

        TabPanel tabs = new TabPanel();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(upperPanel, BorderLayout.PAGE_START);
        mainPanel.add(map, BorderLayout.CENTER);
        mainPanel.add(tabs, BorderLayout.LINE_END);

        add(mainPanel);

        BufferedImage iconImg = ImageIO.read(new File("coronavirus-icon.png"));
        setIconImage(iconImg);
        setTitle("Koronawirus AntiPlague");

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /*
     Nie mogłam sobie poradzić z niereagującym na moje polecenia KeyListenerem,
     więc pozwoliłam sobie wykorzystać rozwiązanie z następującego linku:
     https://stackoverflow.com/a/1379517
    */

    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            switch (e.getID()) {
                case KeyEvent.KEY_PRESSED:
                    keyPressed(e);
                    break;
                case KeyEvent.KEY_RELEASED:
                    keyReleased(e);
                    break;
                case KeyEvent.KEY_TYPED:
                    keyTyped(e);
                    break;
            }
            return false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private boolean isCtrlPressed = false;
    private boolean isShiftPressed = false;
    private boolean isQPressed = false;

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                isShiftPressed = true;
                break;
            case KeyEvent.VK_CONTROL:
                isCtrlPressed = true;
                break;
            case KeyEvent.VK_Q:
                isQPressed = true;
                break;
        }
        if (isQPressed && isCtrlPressed && isShiftPressed) {
            try {
                backToMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                isShiftPressed = false;
                break;
            case KeyEvent.VK_CONTROL:
                isCtrlPressed = false;
                break;
            case KeyEvent.VK_Q:
                isQPressed = false;
                break;
        }
    }

    public void lostOrWonGame(){
        Timer t = new Timer(500, null);
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (lostGame){
                    timeThread.interrupt();
                    ImageIcon icon = new ImageIcon("plague1.png");
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "You lost the game. All people\nof the world got infected.",
                            "GAME OVER", JOptionPane.ERROR_MESSAGE, icon);
                    try {
                        endGame();
                    } catch (IOException ignored) {}

                    t.stop();
                }
                if (wonGame){
                    timeThread.interrupt();
                    ImageIcon icon = new ImageIcon("earth.png");
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "You won the game!\nThe world is safe :)",
                            "SUCCESS", JOptionPane.INFORMATION_MESSAGE, icon);
                    try {
                        endGame();
                    } catch (IOException ignored) {}

                    t.stop();
                }
            }
        });
        t.start();
    }


    public void endGame() throws IOException{
        String username = JOptionPane.showInputDialog(this,
                "Enter your username, so that your score can be saved",
                "username", JOptionPane.PLAIN_MESSAGE);
        saveScore(new HighScoreRecord(username, Main.points));

        new StartFrame();

        Main.levelChosen = null;
        Main.dayOfEpidemic = 0;
        Main.points = 0;
        Main.healthPoints = 0;
        Main.cureProgress = 0;
        Main.vaccineProgress = 0;
        for (Country c : Country.values()) {
            c.numberOfCases = 0;
            c.r0 = 0;
            c.isInfected = false;
        }

        dispose();

    }

    public void backToMenu() throws IOException {
        int option = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to end this game?", "End game",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            timeThread.interrupt();
            endGame();
        }
    }

    public static void saveScore(HighScoreRecord hs) throws IOException {

        HsListModel hsm = new HsListModel();
        hsm.addScore(hs);

        FileWriter fw = null;
        try {
            fw = new FileWriter("high_scores.txt");

            for (HighScoreRecord hsr : hsm.records) {
                fw.write(hsr.no + "\t" + hsr.username + "\t" + hsr.score+"\n");
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
