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

	public abstract BlockNode getBlock();
}





class forNode extends StatementNode {
	private LinkedList<Optional<Node>> operation;
	private Optional<Node> statements;
	
	public forNode(LinkedList<Optional<Node>> operation,Optional<Node> statements) {
		this.operation = operation;
		this.statements = statements;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<Optional<Node>> getOperation() {
		return this.operation;
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
		return this.statements;
	}

	/**
	 * @return
	 */
	@Override
	public BlockNode getBlock() {
		return null;
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

	/**
	 * @return
	 */
	@Override
	public BlockNode getBlock() {
		return null;
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

	/**
	 * @return
	 */
	@Override
	public BlockNode getBlock() {
		return null;
	}


}


class IfNode extends StatementNode {
	private Optional<Node> operation;
	private Optional<Node> statements;
	private Optional<StatementNode> next;

	public IfNode(Optional<Node> operation,Optional<Node> statements,Optional<StatementNode> next) {
		this.next = next;
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
		return "IFNode:  "+"This Operation"+this.operation+" Staments: "+this.statements+" NextIf Node";
	}

	@Override
	public operation getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Node> getNode() {
		// TODO Auto-generated method stub
		return Optional.of(this.next.get());
	}

	/**
	 * @return
	 */
	@Override
	public BlockNode getBlock() {
		return null;
	}


}



class ContinueNode extends StatementNode {


	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "";
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

class BreakNode extends StatementNode {


	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "";
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












class DeleteNode extends StatementNode {
	private Optional<Node> param;

	public DeleteNode(Optional<Node> param) {
		this.param = param;

	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "DeleteNode:  "+this.param;
	}

	@Override
	public operation getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Node> getNode() {
		// TODO Auto-generated method stub
		return this.param;
	}

	/**
	 * @return
	 */
	@Override
	public BlockNode getBlock() {
		return null;
	}


}




class WhileNode extends StatementNode {
	private Optional<Node> condtion;
	private Optional<Node> statements;

	public WhileNode(Optional<Node> condtion, Optional<Node> statements ) {
		this.condtion = condtion;
		this.statements = statements;

	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "WhileNode:    Condition:  "+ this.condtion+" Statements: "+ this.statements;
	}

	@Override
	public operation getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Node> getNode() {
		// TODO Auto-generated method stub
		return this.statements;
	}

	/**
	 * @return
	 */
	@Override
	public BlockNode getBlock() {
		return null;
	}


}



class DoWhileNode extends StatementNode {
	private Optional<Node> condtion;
	private BlockNode statements;

	public DoWhileNode(Optional<Node> condtion, BlockNode statements ) {
		this.condtion = condtion;
		this.statements = statements;

	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "DOWhileNode:    Condition:  "+ this.condtion+" Statements: "+ this.statements;
	}

	@Override
	public operation getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Node> getNode() {
		// TODO Auto-generated method stub
		return condtion;
	}

@Override
	public BlockNode getBlock() {
		// TODO Auto-generated method stub
		return this.statements;
	}



}


class ReturnNode extends StatementNode {
	private Optional<Node> operation;

	public ReturnNode(Optional<Node> operation ) {
		this.operation = operation;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "ReturnNode:    Returing: "+this.operation;
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

	@Override
	public BlockNode getBlock() {
		return null;
	}


}


class quickStatementNode extends StatementNode {

	private Optional<Node> target;
	private operation op;

	public quickStatementNode(Optional<Node> target, operation op) {
		this.target = target;
		this.op = op;
	}


	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "This is quickStatementNode:  "+this.target+"  "+this.op;
	}

	@Override
	public operation getType() {
		// TODO Auto-generated method stub
		return op;
	}

	@Override
	public Optional<Node> getNode() {
		// TODO Auto-generated method stub
		return target;
	}

	/**
	 * @return
	 */
	@Override
	public BlockNode getBlock() {
		return null;
	}


}