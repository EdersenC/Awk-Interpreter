package lex;

public class Token {
		
enum tokenType {
	    WORDS,
	    NUMBERS,
	    SEPARATORS,
	    WHILE,
	    IF,
	    DO,
	    FOR,
	    BREAK,
	    CONTINUE,
	    ELSE,
	    RETURN,
	    BEGIN,
	    END,
	    PRINT,
	    PRINTF,
	    NEXT,
	    IN,
	    DELETE, 
	    GETLINE,
	    EXIT,
	    NEXTFILE,
	    FUNCTION,
	    STRINGLITERAL,
	    BACKTICK,
	    TWO_CHAR_SYMBOL,
	    SINGLE_CHAR_SYMBOL,
	    LEFT_CURLY_BRACE,
	    RIGHT_CURLY_BRACE,
	    LEFT_SQUARE_BRACKET,
	    RIGHT_SQUARE_BRACKET,
	    LEFT_PARENTHESIS,
	    RIGHT_PARENTHESIS,
	    DOLLAR,
	    TILDE,
	    EQUALS,
	    LESS_THAN,
	    GREATER_THAN,
	    PLUS,
	    CARET,
	    MINUS,
	    QUESTION_MARK,
	    COLON,
	    ASTERISK,
	    SLASH,
	    PERCENT,
	    SEMICOLON,
	    NEWLINE,
	    PIPE,
	    COMMA,
	    GREATER_THAN_OR_EQUAL,
	    INCREMENT,
	    DECREMENT,
	    LESS_THAN_OR_EQUAL,
	    EQUAL_EQUAL,
	    NOT_EQUAL,
	    CARET_EQUAL,
	    PERCENT_EQUAL,
	    ASTERISK_EQUAL,
	    SLASH_EQUAL,
	    PLUS_EQUAL,
	    MINUS_EQUAL,
	    NOT_TILDE,
	    LOGICAL_AND,
	    RIGHT_SHIFT,
	    LOGICAL_OR,
	    LOGICAL_NOT
}

private tokenType type;
private String value;
private int lineNumber;
private int charPos;


public Token(tokenType type, int lineNumber, int charPos) {
	
	this.type = type;
	this.lineNumber = lineNumber;
	this.charPos = charPos;
	
}


public Token(tokenType type, int lineNumber, int charPos, String value) {
	
	this.type = type;
	this.lineNumber = lineNumber;
	this.charPos = charPos;
	this.value = value;
	
}


public tokenType gettype(){
	return type;
	
}

public String getValue() {
	return value;
}

public String toString() {
    return "Type: " + type + ", Value: " + value + ", Line: " + lineNumber + ", Char: " + charPos;
}
	

}
