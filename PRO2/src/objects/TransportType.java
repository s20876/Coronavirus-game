package objects;

public enum TransportType {
    CAR ("Car", "car", Route.allCountries, Route.carRoutesOpen, 0.3),
    BUS ("Bus", "bus", Route.busCountries, Route.busRoutesOpen, 0.6),
    TRAIN ("Train", "train", Route.trainCountries, Route.trainRoutesOpen, 0.7),
    PLANE ("Plane", "plane", Route.allCountries, Route.planeRoutesOpen, 0.9),
    SHIP ("Ship", "ship", Route.allCountries, Route.shipRoutesOpen, 0.1);

    public final String nameB;
    public final String nameS;
    public Country[] countriesTab;
    public boolean[][] routesOpen;
    public double chance; // chance to give further the infection by this transport

    TransportType(String nameB, String nameS, Country[] countriesTab, boolean[][] routesOpen, double chance){
        this.nameB = nameB;
        this.nameS = nameS;
        this.countriesTab = countriesTab;
        this.routesOpen = routesOpen;
        this.chance = chance;
    }

}
