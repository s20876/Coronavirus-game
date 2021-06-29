package high_scores;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HsListModel extends AbstractListModel {
    public ArrayList<HighScoreRecord> records;

    public HsListModel() throws IOException {
        records = new ArrayList<>();

        FileInputStream fis = new FileInputStream("high_scores.txt");
        StringBuffer fileText = new StringBuffer("");
        int bajt = fis.read();
        while(bajt != -1) {
            fileText.append((char) bajt);
            bajt = fis.read();
        }
        fis.close();

        Pattern pattern = Pattern.compile("(.+)\\t(.+)\\t(.+)");
        Matcher matcher = pattern.matcher(fileText);

        while(matcher.find()){
            HighScoreRecord hscr = new HighScoreRecord(
                    matcher.group(2),
                    Integer.parseInt(matcher.group(3))
            );
            hscr.setNo(Integer.parseInt(matcher.group(1)));
            records.add(hscr);
        }

        sortScores();

    }

    public void addScore(HighScoreRecord hs){
        records.add(hs);
        fireIntervalAdded(this,records.size()-1,records.size()-1);
        sortScores();
        fireContentsChanged(this, 0, records.size()-1);
    }

    public void sortScores(){
        Collections.sort(records);
        for (int i = 0; i < records.size(); i++) {
            records.get(i).setNo(i+1);
        }
        fireContentsChanged(this, 0, records.size()-1);
    }

    @Override
    public int getSize() {
        return records.size();
    }

    @Override
    public Object getElementAt(int index) {
        return records.get(index);
    }
}
