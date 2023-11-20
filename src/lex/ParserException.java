package lex;


import java.lang.ref.Reference;

public class ParserException extends Exception {

	enum ErrorType {
		VARIABLEREFERENCE,
		PARAMAS
	}





    public ParserException(String message) {
        super(message);
    }
    
}

  
class unExpectedElement extends ParserException {

	public unExpectedElement (String message) {
		super(message); 
		// TODO Auto-generated constructor stub
	}
	
	 
	 
	
	public void trackError() {
	
		System.err.println("UnexpectedElemnt E : " + getMessage());
	}
	
	
	
}



class ConversionError extends ParserException {

	public ConversionError (String message) {
		super("\n Failed to Convert: "+message);
		// TODO Auto-generated constructor stub
	}
		
	
}


class NotFound extends ParserException {
	public NotFound ( int params, String message , String line) {
		super(("\n EXPECTED %s Parameters For This Function: { %s } LINE: %s").formatted(params,message,line));
	}
	public NotFound (String message, String line) {
		super(("\n No Reference To: { %s } LINE: %s").formatted(message,line));
	}


}