package high_scores;

import main.Main;

import javax.swing.*;
import java.awt.*;

public class HsCellRenderer implements ListCellRenderer<HighScoreRecord> {
    @Override
    public Component getListCellRendererComponent(JList<? extends HighScoreRecord> list,
                                                  HighScoreRecord value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        JLabel noLbl = new JLabel(""+value.no);
        JLabel usernameLbl = new JLabel(""+value.username);
        JLabel scoreLbl = new JLabel(Main.makeReadable(value.score));

        noLbl.setForeground(new Color(51,0,204));
        usernameLbl.setForeground(new Color(0,102,51));
        scoreLbl.setForeground(new Color(173,17,17));

        JPanel oneRecord = new JPanel();
        oneRecord.setBackground(new Color(0x9F9FDF));
        oneRecord.add(noLbl);
        oneRecord.add(usernameLbl);
        oneRecord.add(scoreLbl);

        return oneRecord;
    }
}
