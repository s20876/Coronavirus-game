package exceptions;

public class OneVaccineCountryException extends Exception {
    public OneVaccineCountryException(){
        super("Only one country can start working on a vaccine");
    }
}
