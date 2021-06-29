package exceptions;

public class NotEnoughHealthPointsException extends Exception {
    public NotEnoughHealthPointsException(){
        super("<html>You don't have enough<br/>health points</html>");
    }
}