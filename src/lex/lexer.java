package lex;
import java.util.*;
import java.util.HashMap;

import lex.Token.tokenType;



public class lexer {
	
	
	private StringHandler handler;
	private int lineNumber;
	private int charPos;


	public lexer(String StringHandler,int line, int pos) { 
		handler = new StringHandler(StringHandler, 0);
		this.lineNumber = line;
		this.charPos = pos;
		HashMap<String, tokenType> knownWords;
		HashMap<String, tokenType> oneCharacter;
		HashMap<String, tokenType> twoCharacter;
		HashMap<String, tokenType> operator;
		helper();
		
		
	}
	
	LinkedList<Token> token = new LinkedList<Token>();
	HashMap<String, tokenType> knownWords = new HashMap<String, tokenType>();
	HashMap<String, tokenType> oneCharacter = new HashMap<String, tokenType>();
	HashMap<String, tokenType> twoCharacter = new HashMap<String, tokenType>();
	HashMap<String, tokenType> operators = new  HashMap<String, tokenType>();
	
	/**
	 * Tokenizes the input string and populates the 'token' list with tokens.
	 * @throws IllegalArgumentException if an invalid character is encountered.
	 */
	public void Lex() {
	   // int lineNumber = this.lineNumber; // Store the current line number
	    //int charPos = this.charPos;       // Store the current character position

	    while (!handler.isDone()) { // Continue processing until the input is exhausted
	        char nextChar = handler.peek(charPos); // Peek at the next character

	        if (nextChar == '\t' || nextChar == ' ') {
	            // Ignore spaces and tabs
	        } else if (nextChar == '\n'|| nextChar == ';') {
	            lineNumber++; // Increment the line number for a new line character
	            // Create a SEPARATOR token for a new line and add it to the token list
	            token.add(new Token(tokenType.SEPARATORS, lineNumber, charPos));
	        } else if (nextChar == '\r') {
	            // Ignore carriage return
	        } else if (Character.isLetter(nextChar)) {
	            // Process a word token
	        	processWord(lineNumber);
	            //System.out.print(processWord(lineNumber) + "\n");
	        } else if (Character.isDigit(nextChar)) {
	            try {
	                // Process a number token
	            	processNumber(lineNumber);
	                //System.out.print(processNumber(lineNumber) + "\n");
	            } catch (IllegalArgumentException ex) {
	                // Handle the exception or rethrow it if necessary
	                throw new IllegalArgumentException("Error processing number token at line " + lineNumber, ex);
	            }
	        } else if (nextChar == '#') {
	            // Handle comments starting with '#'
	            while (handler.peek(charPos) != '\n') {
	                handler.swallow(1);
	            }
	            if(handler.peek(charPos) == '\n') {
	            lineNumber++;}
	            
	        } else if (nextChar == '"') {
	            // Handle string literals enclosed in double quotes
	        	//processSymbol();
	            HandleStringLiteral();
	        } else if (nextChar == '`') {
	            // Handle patterns enclosed in backticks
	            HandlePattern();
	        }else {
	        	
	        	token.add(processSymbol());
	        	//handler.swallow(1);
	        	
	        }
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        

	        // Move to the next character in the input string
	        handler.swallow(1);
	    }
	}


	
	
	
	/**
	 * Processes and creates a WORD token from the input string starting at the current position.
	 *
	 * @param lineNumber The current line number.
	 * @return The created WORD token.
	 * @throws IllegalArgumentException if an invalid character is encountered.
	 */
	public Token processWord(int lineNumber) {
	    boolean stop = true;
	    int total = -1;
	    int start = 0;
	    Token currToken = null;
	    
	    
	    
	   

	    while (stop) {
	       
	    	
	        if (Character.isLetterOrDigit(handler.peek(start)) || handler.peek(start) == '_' && !handler.isDone()) {
	            // Continue accumulating characters for the word token
	            start++;
	            total++;
	            //System.out.print(" \n This is start "+start);
	        } else {
	            if (total == -1) {
	                // No valid characters were accumulated
	                throw new IllegalArgumentException("Invalid character encountered while processing a WORD token");
	            }

	            
	            if (knownWords.get(handler.peekString(total)) != null ){
	            	
	            	
	            	
	            	 currToken = new Token(knownWords.get(handler.peekString(total)), lineNumber, total);
			         token.add(currToken);
	            	
			        
	            	
	            }
	            else {
	            	
	            	// Create a WORD token with the accumulated characters and add it to the token list
		            currToken = new Token(Token.tokenType.WORDS, lineNumber, total, handler.peekString(total));
		            token.add(currToken);
	            	
	            	
	            }
	            
	            
	            
	            // Move the handler's index past the processed characters
	            handler.swallow(total);

	            // Stop processing
	            stop = false;
	        }
	    }
	    

	    return currToken;
	}

	/**
	 * Processes and creates a NUMBERS token from the input string starting at the current position.
	 *
	 * @param lineNumber The current line number.
	 * @return The created NUMBERS token.
	 * @throws IllegalArgumentException if an invalid character is encountered.
	 */
	public Token processNumber(int lineNumber) {
	    boolean stop = true;
	    int total = -1;
	    int start = 0;
	    Token currToken = null;

	    while (!handler.isDone() && stop) {
	        char currentChar = handler.peek(start);

	        if (Character.isDigit(currentChar) || currentChar == '.') {
	            // Continue accumulating characters for the number token
	            start++;
	            total++;
	        } else {
	            if (total == -1) {
	                // No valid characters were accumulated
	                throw new IllegalArgumentException("Invalid character encountered while processing a NUMBERS token");
	            }

	            // Create a NUMBERS token with the accumulated characters and add it to the token list
	            currToken = new Token(tokenType.NUMBERS, lineNumber, total, handler.peekString(total));
	            token.add(currToken);

	            // Move the handler's index past the processed characters
	            handler.swallow(total);

	            // Stop processing
	            stop = false;
	        }
	    }

	    return currToken;
	}


	public void HandleStringLiteral() {
		int StringLiteral= 0;
		handler.swallow(1);
		boolean stop = true;
		Token currToken = null;
		while (stop) {
		    
			
			if (handler.peek(StringLiteral)=='\\') {
				
				StringLiteral++;
				
			}
			else if(handler.peek(StringLiteral)=='"') {
				
				
				stop = false;
				
			}
			
			
			StringLiteral++;
			
		
			
			
			
			
		}

		 
		currToken = new Token(tokenType.STRINGLITERAL, lineNumber, StringLiteral, handler.peekString(StringLiteral-2));
        token.add(currToken);
        handler.swallow(StringLiteral);
       // System.out.print(currToken+"\n");
		
		
	}
	
	public void HandlePattern() {
		
		
		int StringLiteral= 0;
		handler.swallow(1);
		boolean stop = true;
		Token currToken = null;
		while (stop) {
		    
			
			if (handler.peek(StringLiteral)=='\\') {
				
				StringLiteral++;
				
			}
			else if(handler.peek(StringLiteral)=='`') {
				
				
				stop = false;
				
			}
			
			
			StringLiteral++;
			
			
			
			
			
			
			
		}

		 
		currToken = new Token(tokenType.BACKTICK, lineNumber, StringLiteral, handler.peekString(StringLiteral-2));
        token.add(currToken);
        handler.swallow(StringLiteral);
       // System.out.print(currToken+"\n");
		
		
		
		
		
		
	}
	
	



	public Token processSymbol() {
		

		Token currToken = null;
		boolean isdouble = true;
		boolean issingle = false;
		
		
		
		
		
		if(isdouble & !handler.isDone()) {
			String doubleSym = handler.peekString(1);
			//System.out.print(" \n at Process Symbol: "+doubleSym);
			
		
			for(Map.Entry<String, tokenType> twosym : twoCharacter.entrySet()) {
				//System.out.print("\n I am here: "+twosym.getKey()+"  double sum val: "+doubleSym+"\n");
				
				
				if (doubleSym.compareToIgnoreCase(twosym.getKey())==0) {
					
					//System.out.print("\n They equal \n");
					
					for(Map.Entry<String, tokenType> symbol : operators.entrySet()) {
						
						
						
						if(doubleSym.compareToIgnoreCase(symbol.getKey())==0) {
							
							
							currToken = new Token(symbol.getValue(), lineNumber, 0, twosym.getKey());
							
							
						}
						
						
						
					}
					
					
					
					
					
					handler.swallow(1);
					
					
					
					return currToken;
				}else  {
					
					issingle = true;
					
				}
				
				
			}
		}
		
		
		 if(issingle){
			 String singleSym = handler.peekString(0);
			//System.out.print("\n in elese \n");
			
			for(Map.Entry<String, tokenType> onesym : oneCharacter.entrySet()) {
				
				//System.out.print("\n I am here: "+onesym.getKey()+"  single sum val: "+singleSym+" "+"\n");
				if (singleSym.compareToIgnoreCase(onesym.getKey())==0) {
					
					
					
					
					
					for(Map.Entry<String, tokenType> symbol : operators.entrySet()) {
						
						
						
						if(singleSym.compareToIgnoreCase(symbol.getKey())==0) {
							
							
							currToken = new Token(symbol.getValue(), lineNumber, 0, onesym.getKey());
							
							 
						}
						
						
						
					}
					
					
					
					
					
					
					
					
					//handler.swallow(1);
					System.out.print("\n in single if statment \n");
					return currToken;
				}
				
				
				
				
			}
			return currToken;
		 }
			
		
	
		
		
		
		
		
		
			//System.out.print("\n Done with Symbols \n");
		
	
		return null;
	}
	
	public void helper() {
		
		knownWords.put("while",    tokenType.WHILE);
        knownWords.put("if",       tokenType.IF);
        knownWords.put("do",       tokenType.DO);
        knownWords.put("for",      tokenType.FOR);
        knownWords.put("break",    tokenType.BREAK);
        knownWords.put("continue", tokenType.CONTINUE);
        knownWords.put("else",     tokenType.ELSE);
        knownWords.put("return",   tokenType.RETURN);
        knownWords.put("BEGIN",    tokenType.BEGIN);
        knownWords.put("END",      tokenType.END);
        knownWords.put("print",    tokenType.PRINT);
        knownWords.put("printf",   tokenType.PRINTF);
        knownWords.put("next",     tokenType.NEXT);
        knownWords.put("in",       tokenType.IN);
        knownWords.put("delete",   tokenType.DELETE);
        knownWords.put("getline",  tokenType.GETLINE);
        knownWords.put("exit", 	   tokenType.EXIT);
        knownWords.put("nextfile", tokenType.NEXTFILE);
        knownWords.put("function", tokenType.FUNCTION);
        
        
        oneCharacter.put("{", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("}", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("[", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("]", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("(", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put(")", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("$", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("~", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("=", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("<", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put(">", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("!", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("+", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("^", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("-", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("?", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put(":", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("*", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("/", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("%", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put(";", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("\n",tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put("|", tokenType.SINGLE_CHAR_SYMBOL);
        oneCharacter.put(",", tokenType.SINGLE_CHAR_SYMBOL);

        // Initialize the two-character symbol hash map
        twoCharacter.put(">=", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("++", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("--", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("<=", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("==", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("!=", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("^=", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("%=", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("*=", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("/=", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("+=", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("-=", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("!~", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("&&", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put(">>", tokenType.TWO_CHAR_SYMBOL);
        twoCharacter.put("||", tokenType.TWO_CHAR_SYMBOL);
        
        
        
        
        
        
        
        operators.put("{", tokenType.LEFT_CURLY_BRACE);
        operators.put("}", tokenType.RIGHT_CURLY_BRACE);
        operators.put("[", tokenType.LEFT_SQUARE_BRACKET);
        operators.put("]", tokenType.RIGHT_SQUARE_BRACKET);
        operators.put("(", tokenType.LEFT_PARENTHESIS);
        operators.put(")", tokenType.RIGHT_PARENTHESIS);
        operators.put("$", tokenType.DOLLAR);
        operators.put("~", tokenType.TILDE);
        operators.put("=", tokenType.EQUALS);
        operators.put("<", tokenType.LESS_THAN);
        operators.put(">", tokenType.GREATER_THAN);
        operators.put("!", tokenType.LOGICAL_NOT);
        operators.put("+", tokenType.PLUS);
        operators.put("^", tokenType.CARET);
        operators.put("-", tokenType.MINUS);
        operators.put("?", tokenType.QUESTION_MARK);
        operators.put(":", tokenType.COLON);
        operators.put("*", tokenType.ASTERISK);
        operators.put("/", tokenType.SLASH);
        operators.put("%", tokenType.PERCENT);
        operators.put(";", tokenType.SEMICOLON);
        operators.put("\n", tokenType.NEWLINE);
        operators.put("|", tokenType.PIPE);
        operators.put(",", tokenType.COMMA);
        operators.put(">=", tokenType.GREATER_THAN_OR_EQUAL); 
        operators.put("++", tokenType.INCREMENT);
        operators.put("--", tokenType.DECREMENT);
        operators.put("<=", tokenType.LESS_THAN_OR_EQUAL);
        operators.put("==", tokenType.EQUAL_EQUAL);
        operators.put("!=", tokenType.NOT_EQUAL);
        operators.put("^=", tokenType.CARET_EQUAL);
        operators.put("%=", tokenType.PERCENT_EQUAL); 
        operators.put("*=", tokenType.ASTERISK_EQUAL);
        operators.put("/=", tokenType.SLASH_EQUAL);
        operators.put("+=", tokenType.PLUS_EQUAL);
        operators.put("-=", tokenType.MINUS_EQUAL);
        operators.put("!~", tokenType.NOT_TILDE);
        operators.put("&&", tokenType.LOGICAL_AND);
        operators.put(">>", tokenType.RIGHT_SHIFT);
        operators.put("||", tokenType.LOGICAL_OR);
        
        
        
        
		System.out.println("These are the know words hashmapped"+knownWords+"\n");
		System.out.println("These are the know words hashmapped"+oneCharacter+"\n");
		System.out.println("These are the know words hashmapped"+twoCharacter+"\n");
		
		
	}
	
	
	

}
