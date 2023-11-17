package lex;

import java.util.LinkedList;
import java.util.Optional;

import lex.OperationNode.operation;

public class BlockNode extends Node {
    private LinkedList<Optional<Node>> statements;
    private Optional<Node> condition;

    public BlockNode(LinkedList<Optional<Node>> statements, Optional<Node> condition) {
        this.statements = statements;
        this.condition = condition; 
    }
    
     
    
     
    
    public void addStatments(Optional<Node> statement) {
    	
    	this.statements.add(statement);
    }
    
    
    
    public LinkedList<Optional<Node>> getStatements() {
    	return this.statements;
    }
    
    
    
    public String toString() {
    	
    	
    	return "Current Block Comdtition : "+this.condition
    		  +"\nBlock Body: { "+getStatements()+" }";
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
