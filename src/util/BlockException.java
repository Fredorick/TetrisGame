package util;

@SuppressWarnings("serial")
public class BlockException extends Exception {

	private int number;
    public int getNumber(){return number;}
    public BlockException(String message, int num){  
        super(message);
        number=num;
    }

}
