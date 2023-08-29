package src.view.entity;

public class InvalidInputsException extends Exception{
    public InvalidInputsException(String msg){
        super(String.format(msg));
    }
}