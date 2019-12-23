/**
 * Class FullStackException
 *
 * Exception to handle the case where a method wants to add to a full stack.
 *
 * @author Edouard Gagné
 */


public class FullStackException extends Exception {
    public FullStackException(){
        super("Full Stack !!!");
    }
    public FullStackException(String message){
        super(message);
    }
}
