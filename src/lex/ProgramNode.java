package lex;

import java.util.LinkedList;
import java.util.Optional;

import lex.OperationNode.operation;

public class ProgramNode extends Node {
    LinkedList<BlockNode> beginBlocks = new LinkedList<BlockNode>();
    LinkedList<FunctionDefinitionNode> functionDefinitions = new LinkedList<FunctionDefinitionNode>();
    LinkedList<BlockNode> actionBlocks = new LinkedList<BlockNode>();	
    LinkedList<BlockNode> endBlocks = new LinkedList<BlockNode>();
    
   
     
    
   
    
    
    
    
    
    
    
    
    
    
    public String toString() {
    	
    	
    	
    	return "\nThis Begin Block: "+this.beginBlocks.toString()
    		  +"\nThese are Functions: "+this.functionDefinitions.toString()
    	      +"\nThese are the Action blocks: "+ this.actionBlocks.toString()
    	      +"\nThis is the End Block: "+this.endBlocks.toString();
    	
    	
    	
    	
    	
    	
    	
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