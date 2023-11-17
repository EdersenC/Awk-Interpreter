package lex;

public class StringHandler {

	private String AWK;
	private int index;
	
	
	public StringHandler(String AWK, int index) {
		
		this.AWK = AWK;
		this.index = index;
		
	}
	
	
	

	public char peek(int index) {
	    char character = 0;
	    int current = this.index + index; // Calculate the current index

	    // Check if the current index is within the bounds of the input string
	    if (current < this.AWK.length() && current >= 0) {
	        character = this.AWK.charAt(current);
	    }

	    return character;
	}



public String peekString(int index) {
	int i = 0;
	StringBuilder characters = new StringBuilder();
	while(i <= index) {
		
		//System.out.print(" \n at PeekString: "+i);
		characters.append(peek(i));
		i++;
		
		
	}
	characters.toString();
	
	
	return characters.toString();
}


public char getChar() {
	
	int next = this.index+1;
	char character = peek(next);
	this.index++;
	
	
	
	return character;
	
	
}

public void swallow(int index) {
	
	this.index = this.index+index;
	//System.out.print(this.index);
	
	
	//System.out.print("\n pos"+this.index);
}

public boolean isDone() {
	
	if (this.index >= this.AWK.length()) {
		
		return true;
		
		
	}else 
		
		
		return false;
	
	
	

}

public String remainder() {
	
	int remindex = this.AWK.length() - this.index;
	
	//System.out.print(remindex);
	
	
	return peekString(remindex-1);
}




























}