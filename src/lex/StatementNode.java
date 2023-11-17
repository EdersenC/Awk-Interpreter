package lex;

import java.util.LinkedList;
import java.util.Optional;

import lex.OperationNode.operation;

public abstract class StatementNode extends Node {
	private Optional<Node> statement;
	//Optional<Token> statement;
	

 
	
	 
	
	@Override
	public String toString() {

		return "\nthis is the statement: "+this.statement;
		
		
		
	}




	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public operation getType() {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public Optional<Node> getNode() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}





class forNode extends StatementNode {
	private Optional<Node> operation;
	private Optional<Node> statements;	
	
	public forNode(Optional<Node> operation,Optional<Node> statements) {
		this.operation = operation;
		this.statements = statements;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "forNode:  "+"This Operation"+this.operation+" Staments: "+this.statements+" Next forNode";
	}

	@Override
	public operation getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Node> getNode() {
		// TODO Auto-generated method stub
		return this.operation;
	}

	
	
	
}


class forEachNode extends StatementNode {
	private Optional<Node> operation;
	private Optional<Node> statements;		
	
	public forEachNode(Optional<Node> operation,Optional<Node> statements) {
		this.operation = operation;
		this.statements = statements;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "ForEachNode:  "+"This Operation"+this.operation+" Staments: "+this.statements+" Next ForEachNode";
	}

	@Override
	public operation getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Node> getNode() {
		// TODO Auto-generated method stub
		return this.operation;
	}

	
	
	
}






class FunctionCallNode extends StatementNode {
	private Optional<Token> function;
	private LinkedList<Optional<Node>> params; 
	public FunctionCallNode( Optional<Token> function, LinkedList<Optional<Node>> params) {
		
		this.params  =params;
		this.function  =function;
	}
	


	public String toString() {
		return "FunctionCallNode:  "+"Function Name: "+this.function+"   Function Parameters:  "+this.params;
	}

	
}