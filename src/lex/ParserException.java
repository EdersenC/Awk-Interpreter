package lex;


public class ParserException extends Exception {
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
		super("Failed to Convert: "+message); 
		// TODO Auto-generated constructor stub
	}
		
	
}


class NotFound extends ParserException {

	public NotFound (String message) {
		super("Item is not Found: "+message); 
		// TODO Auto-generated constructor stub
	}
		
	
}