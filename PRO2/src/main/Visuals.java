package main;

import javax.swing.*;
import java.awt.*;

public class Visuals {

    public static void setButtonLook(JButton btn){
        btn.setBackground(new Color(112,0,112));
        btn.setForeground(Color.white);
        btn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    }

    public static Color getMainBackgroundColor(){
        return new Color(51,53,76);
    }

    public static void setTabBackgroundColor(JPanel tabPanel){
        tabPanel.setBackground(new Color(193, 216, 230));
    }

    public static Color getTabBackgroundColor(){
        return new Color(193, 216, 230);
    }

    public static void setGreenBtnLook(JButton btn){
        btn.setBackground(new Color(51,204,102));
        btn.setForeground(Color.black);
    }

    public static void setYellowBtnLook(JButton btn){
        btn.setBackground(new Color(244,196,48));
        btn.setForeground(Color.black);
    }

    public static void setRedBtnLook(JButton btn){
        btn.setBackground(new Color(252,3,0));
        btn.setForeground(Color.black);
    }

    public static void setBlackBtnLook(JButton btn){
        btn.setBackground(Color.black);
        btn.setForeground(Color.white);
    }

}
