package main;

import objects.Country;
import start_stuff.*;

import javax.swing.*;
import java.io.IOException;

/*
    Szanowny Panie Profesorze,

    zdaję sobie sprawę z tego, że mechanika ta może pozostawiać wiele do życzenia
    i jest ogólnie rzecz ujmując... biedna. Musi mi Pan jednak wybaczyć, ale
    zupełnie nie przypasował mi ten temat - ani nie jestem fanem gier w ogóle,
    ani tym bardziej stategicznych mapówek, nigdy nie grałam w Plague Inc., nie
    wiem jak działają mechaniki w takich grach i szczerze mówiąc było to dla mnie
    po prostu trudne, by wymyślić coś w tym temacie. Właściwie to błądziłam we mgle.
    Tak więc starałam się wyciągnąć z tego jak najwięcej, wyszło jak wyszło, ale
    mam nadzieję, że doceni Pan moje starania. Liczę również, że nadrabiam chociaż
    punktami stylu. :)

    Z wyrazami szacunku,
    Urszula

*/

public class Main {
    public static Level levelChosen;
    public static int worldPopulation;
    public static int dayOfEpidemic;
    public static int totalCases;
    public static int points;
    public static int cureProgress;
    public static int vaccineProgress;
    public static int healthPoints;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                new StartFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    public static String makeReadable(int number){
        String oldFormat = Integer.toString(number);
        int length = oldFormat.length();
        String newFormat = "";
        int formatCount = 0;
        for (int i = length-1; i >= 0; i--) {
            if (formatCount == 3){
                newFormat = " " + newFormat;
                formatCount = 0;
            }
            newFormat = oldFormat.charAt(i) + newFormat;
            formatCount++;
        }
        return newFormat;
    }
}
