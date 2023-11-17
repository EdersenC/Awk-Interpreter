package lex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import lex.OperationNode.operation;

    class  FunctionDefinitionNode extends Node {
    private String name;
    private LinkedList<String> parameters;
    private LinkedList<Optional<Node>> body;
    

    public FunctionDefinitionNode(String name,  LinkedList<String> parameters, LinkedList<Optional<Node>> body) {

    	this.name = name;
        this.parameters = parameters;
        this.body = body;
         
    }
    
    public String execute(HashMap<String, InterpreterDataType> parameters) {
        throw new UnsupportedOperationException("Execute not implemented for the base FunctionDefinitionNode");
    }
    
   
    
    
    public String toString() {
    	return "\nFunctionName:  "+this.name
    		  +"\nFunctionParms: "+this.parameters
    		  +"\nFunctionBody: "+this.body;
    }




	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.name;
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
 
 
	@FunctionalInterface
       interface  Execute {
	    void operate();
	}


class BuiltInFunctionDefinitionNode extends FunctionDefinitionNode  {
		
	private String name;
	HashMap<String, InterpreterDataType> parameters;
	private LinkedList<StatementNode> body = new LinkedList<StatementNode>();
	private Function<HashMap<String, InterpreterDataType>, String> execute;
	private boolean veriadic;
	
	public BuiltInFunctionDefinitionNode(String name, boolean veriadic, Function<HashMap<String, InterpreterDataType>, String> execute) {
		super(name, null,null);
		this.execute = execute;
		this.veriadic = veriadic;
	}

	
	
	public String execute(HashMap<String, InterpreterDataType> parameters) {
		return execute.apply(parameters);
    }
	public boolean veriadic() {
			return this.veriadic;
		}




		 
		

		
    
 
	
}










