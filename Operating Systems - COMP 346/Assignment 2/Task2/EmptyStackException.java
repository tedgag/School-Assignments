/**
 * Class EmptyStackException
 *
 * Exception to handle the case where a method wants to retrieve from an empty stack.
 *
 * @author Edouard Gagné
 *
 */
public class EmptyStackException extends Exception {
    public EmptyStackException(){
        super("Empty Stack !!!");
    }
    public EmptyStackException(String message){
        super(message);
    }
}
