package lex;

public class ReturnType {
	
	enum RType {
		NORMAL,
		BREAK,
		DELETE,
		CONTINUE,
		RETURN,
		TEST,
		NONE
	}
	
	
	
	
	
	private String value;
	private RType type;
	
	
	
	
	public ReturnType(String value,RType type ) {
	this.value = value;
	this.type = type;
	}
	public ReturnType(RType type) {
	this.type = type;
	}
	
	public String toString() {
		return ("This is ReturnType:   Value: %s  Type %s" ).formatted(value, type);
	}
	public String getValue() {
		return value; 
	}
	public RType getRType() {
		return type;
	}
	
	
	
}
