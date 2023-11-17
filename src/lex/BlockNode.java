package lex;

import java.util.LinkedList;
import java.util.Optional;

import lex.OperationNode.operation;

public class BlockNode extends Node {
    private LinkedList<Optional<StatementNode>> statements;
    private Optional<Node> condition;

    public BlockNode(LinkedList<Optional<StatementNode>> statements, Optional<Node> condition) {
        this.statements = statements;
        this.condition = condition; 
    }
    
     
    
     
    
    public void addStatments(Optional<StatementNode> statement) {
    	
    	this.statements.add(statement);
    }
    
    
    
    public LinkedList<Optional<StatementNode>> getStatements() {
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
