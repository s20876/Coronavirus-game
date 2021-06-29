package high_scores;

import main.Visuals;
import start_stuff.StartFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HighScoresFrame extends JFrame {

    public HighScoresFrame() throws IOException {

        JPanel upperPartPanel = new JPanel();
        upperPartPanel.setLayout(new GridLayout(1,4));
        upperPartPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,0));
        upperPartPanel.setBackground(Visuals.getMainBackgroundColor());

        JButton backBtn = new JButton("Back to menu");
        Visuals.setButtonLook(backBtn);

        JLabel lbl1 = new JLabel();
        JLabel lbl2 = new JLabel();
        JLabel lbl3 = new JLabel();
        upperPartPanel.add(backBtn);
        upperPartPanel.add(lbl1);
        upperPartPanel.add(lbl2);
        upperPartPanel.add(lbl3);


        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new StartFrame();
                    setVisible(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JList hsJList = new JList();
        hsJList.setModel(new HsListModel());
        hsJList.setCellRenderer(new HsCellRenderer());

        JScrollPane hsScrollPane = new JScrollPane(hsJList);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Visuals.getMainBackgroundColor());
        panel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        panel.add(upperPartPanel, BorderLayout.PAGE_START);
        panel.add(hsScrollPane, BorderLayout.CENTER);

        add(panel);

        BufferedImage iconImg = ImageIO.read(new File("coronavirus-icon.png"));
        setIconImage(iconImg);
        setTitle("Koronawirus AntiPlague - High Scores");

        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
