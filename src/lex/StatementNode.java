package lex;

import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Function;

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





class ForNode extends StatementNode {
	private Optional<StatementNode> var;
	private Optional<Node> bool;
	private Optional<StatementNode> inc;
	private BlockNode statements;
	
	public ForNode(Optional<StatementNode> var,Optional<Node> bool,Optional<StatementNode> inc, BlockNode statements) {
		this.var = var;
		this.bool = bool;
		this.inc = inc;
		this.statements = statements;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<StatementNode> getinit() {
		return var;
	}
	public Optional<Node> getbool() {
		return bool;
	}
	public Optional<StatementNode> getinc() {
		return inc;
	}
	@Override
	public String toString() {
		return ("This is ForNode:  Init: %s  BoolCon: %s Inc: %s Statements: %s").formatted(var,bool,inc,statements);
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
		return statements;
	}


}


class forEachNode extends StatementNode {
	private Optional<Node> operation;
	private BlockNode statements;
	
	public forEachNode(Optional<Node> operation,BlockNode statements) {
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
		return statements;
	}


}






class FunctionCallNode extends StatementNode {
	private Optional<Token> function;
	private LinkedList<Optional<Node>> params; 
	public FunctionCallNode( Optional<Token> function, LinkedList<Optional<Node>> params) {
		
		this.params  =params;
		this.function  =function;
	}

	public String getFunction() {
		if(function.isPresent()) {
			return function.get().gettype().toString().toLowerCase();
		}
		return "none";
	}

	public LinkedList<Optional<Node>> getParams() {
		return params;
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
	private Optional<Node> condition;
	private BlockNode statements;
	private Optional<StatementNode> next;
	private IfNode  pre;

	public IfNode(Optional<Node> condition,BlockNode statements,IfNode pre, Optional<StatementNode> next) {
		this.next = next;
		this.condition = condition;
		this.statements = statements;
		this.pre = pre;
	}


	public Optional<StatementNode> getNext() {
			return next;
	}
	public IfNode getPre() {
		return pre;
	}

	public Optional<Node> getCondition() {
		return condition;
	}
	public boolean hasNext() {
        return next.isPresent();
    }
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return ("IFNode:  Condition: %s Statements: %s Previous IFNode:%s  NextIFNode: %s  ").formatted(condition,statements,getPre(),next);
	}

	@Override
	public operation getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Node> getNode() {
		return Optional.empty();
	}

	/**
	 * @return
	 */
	@Override
	public BlockNode getBlock() {
		return statements;
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
	private BlockNode statements;

	public WhileNode(Optional<Node> condtion, BlockNode statements ) {
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
		return condtion;
	}

	/**
	 * @return
	 */
	@Override
	public BlockNode getBlock() {
		return statements;
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
		return statements;
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