package lex;

import java.util.LinkedList;
import java.util.Optional;

import lex.OperationNode.operation;

public abstract class Node {


public abstract String getValue();
public abstract String toString();
public abstract operation getType();
public abstract Optional<Node> getNode();





public Node getLeft() {
	return null;
}

public Optional<Node> getRight() {
	return null;
}




}
 

class  Constant extends Node { 
	private String value;
	
	
	public Constant(String value) {
		this.value = value;
	}
	


	
	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		
		return "\nConstant Value: "+ getValue();
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

class PatternNode extends Node {
	private String value;
	public PatternNode(String value) {
		this.value = value;
		
		
		
		
	}
	@Override
	public String toString() {
		
		return "\nPattern Value: "+ this.value;
	}

	@Override
	public String getValue() {
		return value;
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




class TernaryNode extends Node{

	private Node boolExp;
	private Node trueCase;
	private Node falseCase;

	public TernaryNode(Node boolExp,Node trueCase,Node falseCase) {
		
		this.boolExp=boolExp;
		this.trueCase = trueCase;
		this.falseCase = falseCase;
		
	}
	
	 
	public Node getLeft() {
		return trueCase;
	}

	public Optional<Node> getRight() {
		return Optional.of(falseCase);
	}
	
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "This is TernayNode: BoolExpression: "+this.boolExp+",  TrueCase: "+this.trueCase+",  FalseCase: "+this.falseCase;
	}

	@Override
	public operation getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Node> getNode() {
		// TODO Auto-generated method stub
		return Optional.of(boolExp);
	}
	
	
	
	
}

 

class AssigmentNode extends StatementNode{
	private Node target;
	private Node expression;
	
	public AssigmentNode(Node target,Node expression ) {
		this.target = target;
		this.expression = expression;
	}
	
	
	
	
	
	
	public Node getLeft() {
		return target;
	}
	
	public Optional<Node> getRight() {
		return Optional.of(expression);
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "AssignmentNode";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "This is AssingmentNode:   Target: "+this.target+",  Expression: "+this.expression;
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

	/**
	 * @return
	 */
	@Override
	public BlockNode getBlock() {
		return null;
	}
}







class VariableReferenceNode extends Node {
	
	private String name = null;
	private Optional<Node> expression = Optional.empty();
	
	public VariableReferenceNode( String name, Optional<Node> expression) {
		this.name = name;
		this.expression = expression;
		
	}
	
	public VariableReferenceNode(String name) {
		this.name = name;
		
	}
	
	public Optional<Node> getRight() {
		return expression;
	}
	
	@Override
	public String toString() {
		return "\n This is VariableReferenceNode :  Varibale Name:  "+this.name+" The Expression for said index : "+this.expression;
		
	}

	@Override
	public String getValue() {
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










