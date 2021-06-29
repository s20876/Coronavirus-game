package objects;
import main.Main;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public enum Country {

    AUSTRIA ("Austria", 8858775,300, false, new ImageIcon("flags\\austria.jpg"), new ImageIcon("countries_maps\\austria.png")),
    FRANCE ("France", 67012883, 600,false, new ImageIcon("flags\\france.jpg"), new ImageIcon("countries_maps\\france.png")),
    GERMANY ("Germany", 83019213,500, false, new ImageIcon("flags\\germany.jpg"), new ImageIcon("countries_maps\\germany.png")),
    GREECE ("Greece", 10724599, 2000, false, new ImageIcon("flags\\greece.jpg"), new ImageIcon("countries_maps\\greece.png")),
    ICELAND ("Iceland", 366130, 50, false, new ImageIcon("flags\\iceland.jpg"), new ImageIcon("countries_maps\\iceland.png")),
    ITALY ("Italy", 60359546, 1000, true, new ImageIcon("flags\\italy.jpg"), new ImageIcon("countries_maps\\italy.png")),
    NORWAY ("Norway", 5058007,100, false, new ImageIcon("flags\\norway.png"), new ImageIcon("countries_maps\\norway.png")),
    POLAND ("Poland", 37972812,150, false, new ImageIcon("flags\\poland.jpg"), new ImageIcon("countries_maps\\poland.png")),
    ROMANIA ("Romania", 19414458, 2500, false, new ImageIcon("flags\\romania.jpg"), new ImageIcon("countries_maps\\romania.png")),
    RUSSIA ("Russia", 146745098, 10000, false, new ImageIcon("flags\\russia.jpg"), new ImageIcon("countries_maps\\russia.png")),
    SPAIN ("Spain", 46937060, 800, false, new ImageIcon("flags\\spain.jpg"), new ImageIcon("countries_maps\\spain.png")),
    SWEDEN ("Sweden", 10230185,10000, false, new ImageIcon("flags\\sweden.png"), new ImageIcon("countries_maps\\sweden.png")),
    TURKEY ("Turkey", 83154997, 5000, false, new ImageIcon("flags\\turkey.jpg"), new ImageIcon("countries_maps\\turkey.png")),
    UK ("United Kingdom", 66647112, 700, false, new ImageIcon("flags\\uk.png"), new ImageIcon("countries_maps\\uk.png")),
    UKRAINE ("Ukraine", 41858119,2000, false, new ImageIcon("flags\\ukraine.jpg"), new ImageIcon("countries_maps\\ukraine.png"));

    public final String name;
    public final int population;
    public final ImageIcon flag;
    public final ImageIcon map;
    public JButton button;

    public int constThreshold;
    public int threshold; // podstawowy próg zamykania dróg
    public boolean isInfected;
    public int numberOfCases;
    public double r0; // współczynnik reprodukcji wirusa tzn. ile osób zarazi jedna osoba
    public int unvaccinatedPopulation;

    public final String populationToString;

    Country(String name, int population, int threshold, boolean isInfected, ImageIcon flag, ImageIcon map){
        this.name = name;
        this.population = population;
        this.flag = flag;
        this.map = map;
        this.isInfected = isInfected;
        this.threshold = threshold;
        constThreshold = threshold;
        numberOfCases = 0;
        r0 = 0;
        unvaccinatedPopulation = population;

        populationToString = Main.makeReadable(population);
    }

    public static boolean isAvailableBy(TransportType t, Country c){
        List list;
        if (t.equals(TransportType.BUS)) {
            list = Arrays.asList(Route.busCountries);
        }else if(t.equals(TransportType.TRAIN)){
            list = Arrays.asList(Route.trainCountries);
        }else{
            list = Arrays.asList(Route.allCountries);
        }
        return list.contains(c);
    }

}
