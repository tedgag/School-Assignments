/**
 * Class IncorrectPositionException
 *
 * Class to handle the case where a method wants to access a position that is not within the stack
 *
 * @author Edouard Gagné
 *
 */

public class IncorrectPositionException extends Exception {
    public IncorrectPositionException(){
        super("Incorrect Position !!!");
    }
    public IncorrectPositionException(String message){
        super(message);
    }
}
