package lex;
import java.util.LinkedList;
import java.util.Optional;

public class OperationNode extends Node {
	enum operation {
		EQ,
		NE,
		LT,
		LE,
		GT,
		GE, 
		ND,
		OR,
		NOT,
		MATCH, 
		NOTMATCH,
		DOLLAR,
        PREINC,
        POSTINC,
        PREDEC,
        POSTDEC,UNARYPOS,
        UNARYNEG,
        IN,
        EXPONENT,
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        MODULO,
        CONCATENATION
		
	}
	  
	private Node left;
	private Optional<Node> right = Optional.empty();
	private operation type;
	private Optional<Node> value = Optional.empty();
	
	
	public OperationNode(Optional<Node> value, operation type) {
		
		this.value = value;
		this.type = type;
		
	}

	public OperationNode(Node left, operation type, Optional<Node> right) {
		
		this.right = right;
		this.type = type;
		this.left = left;
		
	} 

	
	
	public  String treeWalker() {


		
		// TODO Auto-generated method stub
		return this.getLeft().getType().toString();
	}
	
	
	
	public  String getLeftEx() {
		// TODO Auto-generated method stub
		return this.getRight().get().getType().toString();
	}
	
	
	

	public Optional<Node> getVal() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public Optional<Node> getNode() {
		// TODO Auto-generated method stub
		return this.value;
	}
	
	

	@Override
	public operation getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	
	public Optional<Node> getRight(){
		return  this.right;
	}
	
	public Node getLeft(){
		return  this.left;
	}
	@Override
	public String toString() {
		return "\nThis is OperationNode:    "+" ,This is Left Node : ( "+getLeft()+"  This is the Value:  "+ getNode() +"  ,This is the type: "+getType()+" ,This is Right Node: "+getRight()+")\n" ;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		
		
		if (left == null && right == null) {
            return this.getType().toString(); // Return the variable or constant value
        }

        String leftExpr = (this.left != null) ? left.getValue() : "";
        String rightExpr = (this.right != null) ? right.get().getValue() : "";

        
  
		return "(" + leftExpr + " " + getType() + " " + rightExpr + ")";
	}
	
	
	
	
	
	
	
	
	
	
}
