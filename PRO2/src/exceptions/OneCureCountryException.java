package exceptions;

public class OneCureCountryException extends Exception {
    public OneCureCountryException(){
        super("Only one country can start working on a cure");
    }
}
