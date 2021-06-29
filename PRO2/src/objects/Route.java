package objects;

import java.util.Arrays;
import java.util.List;

public class Route {
    public final static Country[] allCountries = Country.values();
    public final static Country[] trainCountries = {
            Country.AUSTRIA, Country.FRANCE, Country.GERMANY, Country.GREECE,
            Country.ITALY, Country.NORWAY, Country.POLAND, Country.ROMANIA,
            Country.RUSSIA, Country.SPAIN, Country.SWEDEN, Country.TURKEY,
            Country.UK, Country.UKRAINE
    };
    public final static Country[] busCountries = {
            Country.AUSTRIA, Country.FRANCE, Country.GERMANY, Country.GREECE,
            Country.ITALY, Country.NORWAY, Country.POLAND, Country.ROMANIA,
            Country.RUSSIA, Country.SPAIN, Country.SWEDEN, Country.TURKEY,
            Country.UKRAINE
    };

    // Austria ma dostęp do morza przez Dunaj
    // państwa wyspiarskie natomiast są osiągalne samochodowo dzięki promom
    // połączenie pociągowe między UK a Francją to oczywiście TGV przez kanał La Manche

    public static boolean[][] carRoutesOpen = new boolean[allCountries.length][allCountries.length];
    public static boolean[][] planeRoutesOpen = new boolean[allCountries.length][allCountries.length];
    public static boolean[][] trainRoutesOpen = new boolean[trainCountries.length][trainCountries.length];
    public static boolean[][] busRoutesOpen = new boolean[busCountries.length][busCountries.length];
    public static boolean[][] shipRoutesOpen = new boolean[allCountries.length][allCountries.length];

    // true for open, false for closed

    public static void openAllRoutes() {
        for (int i = 0; i < allCountries.length; i++) {
            for (int j = 0; j < allCountries.length; j++) {
                carRoutesOpen[i][j] = true;
                planeRoutesOpen[i][j] = true;
                shipRoutesOpen[i][j] = true;
            }
        }
        for (int i = 0; i < trainCountries.length; i++) {
            for (int j = 0; j < trainCountries.length; j++) {
                trainRoutesOpen[i][j] = true;
            }
        }
        for (int i = 0; i < busCountries.length; i++) {
            for (int j = 0; j < busCountries.length; j++) {
                busRoutesOpen[i][j] = true;
            }
        }
    }

    private static void changeRouteState(boolean openOrClose, TransportType transportType, Country countryA, Country countryB) {

        int indexOfA = getIndexOfCountry(countryA, transportType);
        int indexOfB = getIndexOfCountry(countryB, transportType);
        transportType.routesOpen[indexOfA][indexOfB] = openOrClose;

    }

    public static void closeRoute(TransportType transportType, Country countryA, Country countryB) {
        changeRouteState(false, transportType, countryA, countryB);
    }

    public static void openRoute(TransportType transportType, Country countryA, Country countryB) {
        changeRouteState(true, transportType, countryA, countryB);
    }

    public static void closeAllRoutesInCountry(Country country, TransportType transportType) {
        for (int i = 0; i < transportType.routesOpen.length; i++) {
            closeRoute(transportType, country, transportType.countriesTab[i]);
            closeRoute(transportType, transportType.countriesTab[i], country);
        }
    }

    public static void openAllRoutesInCountry(Country country, TransportType transportType) {
        for (int i = 0; i < transportType.routesOpen.length; i++) {
            openRoute(transportType, country, transportType.countriesTab[i]);
            openRoute(transportType, transportType.countriesTab[i], country);
        }
    }

    public static boolean areAllRoutesInCountryClosed(TransportType transportType, Country country) {
        for (Country c : transportType.countriesTab) {
            if (getRouteState(transportType, country, c))
                return false;
        }
        return true;
    }

    public static boolean isAnyRouteInCountryOpen(TransportType transportType, Country country) {
        for (Country c : transportType.countriesTab) {
            if (getRouteState(transportType, country, c))
                return true;
        }
        return false;
    }

    public static boolean getRouteState(TransportType transportType, Country countryA, Country countryB) {
        List list;
        int indexOfA;
        int indexOfB;
        switch (transportType) {
            case CAR:
                list = Arrays.asList(allCountries);
                indexOfA = list.indexOf(countryA);
                indexOfB = list.indexOf(countryB);
                return carRoutesOpen[indexOfA][indexOfB];
            case BUS:
                list = Arrays.asList(busCountries);
                indexOfA = list.indexOf(countryA);
                indexOfB = list.indexOf(countryB);
                return busRoutesOpen[indexOfA][indexOfB];
            case TRAIN:
                list = Arrays.asList(trainCountries);
                indexOfA = list.indexOf(countryA);
                indexOfB = list.indexOf(countryB);
                return trainRoutesOpen[indexOfA][indexOfB];
            case SHIP:
                list = Arrays.asList(allCountries);
                indexOfA = list.indexOf(countryA);
                indexOfB = list.indexOf(countryB);
                return shipRoutesOpen[indexOfA][indexOfB];
            case PLANE:
                list = Arrays.asList(allCountries);
                indexOfA = list.indexOf(countryA);
                indexOfB = list.indexOf(countryB);
                return planeRoutesOpen[indexOfA][indexOfB];
            default:
                return false;
        }
    }

    private static int getIndexOfCountry(Country c, TransportType t) {
        List list = Arrays.asList(t.countriesTab);
        int indexOf = list.indexOf(c);
        return indexOf;

    }
}
