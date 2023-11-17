package lex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.OptionalLong;

import lex.OperationNode.operation;
import lex.Token.tokenType;

import javax.swing.text.html.Option;

public class Parser {
	
	
	private TokenManaglinked handler;
	
public Parser(LinkedList<Token> Tokens) {
		
		this.handler = new TokenManaglinked(Tokens, 0);
		HashMap<String, tokenType> knownWords;
		HashMap<String, tokenType> oneCharacter;
		HashMap<String, tokenType> twoCharacter;
		
		
		 
	
		
		
		
		
	}
	
public void printT(Token.tokenType t){
	
	for(int i =0; i< this.handler.getSize();i++) {
		
		if(this.handler.peek(i).get().gettype()== t) {
			
			System.out.print(this.handler.peek(i));
			
		}
		
		
	}
	 
	
	
	
}

	
	
	
	
	public boolean acceptSeparators() {
		
		if(this.handler.MatchAndRemove(Token.tokenType.SEPARATORS).isEmpty()== false) {
			
			return true;
		}
		return false;
	}
	
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	
	
		
	public ProgramNode parse() throws Exception {
	    System.out.print("\n Here at parse \n");
	    ProgramNode funcDef = new ProgramNode();
	    int i = 0;

	    while (this.handler.MoreTokens()) {
	        while (acceptSeparators());
	        if (!parseFunction(funcDef) && !parseAction(funcDef)) {
	            // Both parseFunction and parseAction returned false, so throw an exception.
	            //throw new Exception("Both parseFunction and parseAction returned false.");
	        }

	        i++;
	    }

	    //System.out.print("\n this is handler. tokens to string: "+this.handler.toString());

	    //System.out.print("\n this is program node: "+funcDef.toString());
	    return funcDef;
	}

	
	
	public boolean parseFunction(ProgramNode programNode) throws ParserException {
		Optional<Node> empty = Optional.empty();
		FunctionDefinitionNode funcDefNode;
		TokenManaglinked funcHandler = this.handler;
		LinkedList<String>  params = new LinkedList<String> ();
		Optional<Token> parm;
		LinkedList<Optional<Token>> funcholder = new LinkedList<Optional<Token>>();
		LinkedList<StatementNode> body = new LinkedList<StatementNode>();
		BlockNode block = null;
		LinkedList<Optional<Token>> cummlate = new LinkedList<Optional<Token>>();
		Optional<Token> funcName = Optional.empty();
		Token.tokenType function = Token.tokenType.FUNCTION;
		Token.tokenType words = Token.tokenType.WORDS;
		Token.tokenType leftPar = Token.tokenType.LEFT_PARENTHESIS;
		Token.tokenType rightPar = Token.tokenType.RIGHT_PARENTHESIS;
		Token.tokenType coma = Token.tokenType.COMMA;
		;
//		System.out.print("\n this is is func: \n"+isFunc);
		System.out.print("\nthis is nextType sir: \n"+ get_NextType(0)+" this is the value: "+getVal());
		
	//c//c//c///c///c///c///c///c///c///c///c///c///c///c///c//c///c//c///c//c////c//c///c//c//c//c//c//c//c//c//c//c//c//c///c//c//c//c//c//c//c//c//c//c//c//c/c/c//c/c
		if(handler.MatchAndRemove(function).isPresent()) {
			while(acceptSeparators());
			funcName = handler.MatchAndRemove(words);
			if(funcName.isEmpty())
				throw new ParserException("Missing Function Name		Current handler Item: "+this.handler);
			while(acceptSeparators());
			if(handler.MatchAndRemove(leftPar).isEmpty())
				throw new unExpectedElement("\n Error Occurred During Parsing.    \nExpected:  "+leftPar+" Current handler Item: "+handler.peek(0));
			while(acceptSeparators());
			
			
			
			boolean stop = false;
			while(handler.MatchAndRemove(rightPar).isEmpty()&&!stop) {
				
				
			
				
				parm = handler.MatchAndRemove(words);
				while(acceptSeparators());
				if(parm.isEmpty()) {
					if(handler.MatchAndRemove(rightPar).isEmpty())
						throw new unExpectedElement("\n Error Occurred During Parsing.    Expected:  "+rightPar+" Current handler Item: "+handler.peek(0));}
				if(handler.MatchAndRemove(coma).isEmpty()) {
					while(acceptSeparators());
					if(handler.MatchAndRemove(rightPar).isEmpty())
						throw new unExpectedElement("\n Error Occurred During Parsing.    Expected:  "+coma+" Current handler Item: "+handler.peek(0));
					stop = true;
				}
				params.add(parm.get().getValue());
				while(acceptSeparators());
				if(get_NextType(0)!=words) {
					stop = true;
				} 
				


				
//				parm = handler.MatchAndRemove(words);
//				if(parm.isEmpty()&&!stop)
//					throw new ParserException("Missing Function Paramater after the Coma		Current handler Item: "+this.handler);
//				params.add(parm.get().getValue());
//	
			}
			

			block = parseBlock(empty);
			
			programNode.functionDefinitions.add(new FunctionDefinitionNode(funcName.get().getValue(), params,block.getStatements()));
			System.out.print("\n\n this is ProgramNode: "+programNode.toString());
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean parseAction(ProgramNode programNode) throws ParserException {
		
		TokenManaglinked funcHandler = this.handler;
		int enoughBrackets = 0;
		
		
		if(get_NextType(0)== Token.tokenType.BEGIN||get_NextType(0)== Token.tokenType.END||getVal()=="{"||getVal()=="(") {
			System.out.print("\n At PArseAction line 189 before matach and remove: here is the current type:  "+get_NextType(0)+"\n");
			

			if(get_NextType(0)== Token.tokenType.BEGIN) {
				while(acceptSeparators());
				funcHandler.MatchAndRemove(get_NextType(0));
				programNode.beginBlocks.add(parseBlock(null));
			}else if(get_NextType(0)== Token.tokenType.END) {
				while(acceptSeparators());
				funcHandler.MatchAndRemove(get_NextType(0));
				programNode.endBlocks.add(parseBlock(null));
			}else if(getVal()=="(") {
				while(acceptSeparators());
				funcHandler.MatchAndRemove(get_NextType(0));
			}
			
			else {
				
				
				programNode.actionBlocks.add(parseBlock(null));
				
			}
			System.out.print("\n\n this is ProgramNode: "+programNode.toString());
			return true;
		}
		
		
	
		
		return false;
		
	}
	
	
	
	public Token.tokenType get_NextType(int x) {
		Optional<Token> curr = this.handler.peek(x);
		if(curr.isPresent()) {
			return curr.get().gettype();
		}
		return null;
	}


	public boolean isStatementNode(Optional<Node> node){
        return node.get() instanceof StatementNode;
    }

	public Optional<StatementNode> parseStatement() throws ParserException {
		Token.tokenType IF = Token.tokenType.IF;
		Token.tokenType Continue = Token.tokenType.CONTINUE;
		Token.tokenType Break = Token.tokenType.BREAK;
		Token.tokenType For = Token.tokenType.FOR;
		Token.tokenType Delete = Token.tokenType.DELETE;
		Token.tokenType While = Token.tokenType.WHILE;
		Token.tokenType doWhile = Token.tokenType.DO;
		Token.tokenType Return = Token.tokenType.RETURN;
		Optional<Node> empty = Optional.empty();
		TokenManaglinked blockHandler = this.handler;
		
		
		if(blockHandler.MatchAndRemove(Continue).isPresent()) {
			return parseContinue();
		}
		if(blockHandler.MatchAndRemove(Break).isPresent()) {
			return parseBreak();
		}
		if(blockHandler.MatchAndRemove(IF).isPresent()) {
			return parseIF(Optional.empty());
		}
		if(blockHandler.MatchAndRemove(For).isPresent()) {
			return parseFor();
		}
		if(blockHandler.MatchAndRemove(Delete).isPresent()) {
			return parseDelete();
		}
		if(blockHandler.MatchAndRemove(While).isPresent()) {
			return parseWhile(0);
		}
		if(blockHandler.MatchAndRemove(doWhile).isPresent()) {
			return parseWhile(1);
		}
		if(blockHandler.MatchAndRemove(Return).isPresent()) {
			return parseReturn();
		}

		Optional<Node> op = parseOperation();
		if(op.isPresent()) {
			if (op.get().getType() == operation.POSTINC)
				return Optional.of(new quickStatementNode(op.get().getNode(), op.get().getType()));
			if (op.get() instanceof StatementNode)
				return op.map(node -> (StatementNode) node);
			throw new ParserException("Expected Statement Node but got: " + op.get().getValue());
		}
		return Optional.empty();
    }

	
		//48 min(with Redundancy) 
	public BlockNode parseBlock(Optional<Node> condition) throws ParserException {
		TokenManaglinked blockHandler = this.handler;
		LinkedList<Optional<StatementNode>> stat = new LinkedList<Optional<StatementNode>>();
		Token.tokenType leftC = Token.tokenType.LEFT_CURLY_BRACE;
		Token.tokenType rightC = Token.tokenType.RIGHT_CURLY_BRACE;
		Optional<StatementNode> statements;
		//look for open if not their throw Exception: 
		if(blockHandler.MatchAndRemove(leftC).isPresent()) {
		while(acceptSeparators());
		statements = parseStatement();
		// loop through block and continue to add statements
		while(statements.isPresent()) {
			stat.add(statements);
			while(acceptSeparators());
			statements = parseStatement();
		}
		
		
		//// should have gotten Statements { Not getting LAST BRACKET.
		if(blockHandler.MatchAndRemove(rightC).isEmpty())
			throw new unExpectedElement("\n Error Occurred During Parsing.    Expected:  {"+rightC+"} But the Current Item is:  "+handler.peek(0));
		return new BlockNode(stat, condition);
		}
		
		
		
		//stat.add(parseStatement());
		
		//looking  for if , do, while calls once  no brackets 
		while(acceptSeparators());
		statements = parseStatement();
		while(acceptSeparators());
		if(statements.isEmpty())
			throw new unExpectedElement("NO Statements Found Current Handler Item: "+blockHandler.peek(0));
		System.out.print("\n This is BlockHandler to string : \n "+stat);
		stat.add(statements);
		return new BlockNode(stat, condition);
	}
	
	
	public Optional<StatementNode> parseIF(Optional<StatementNode> nextIF) throws ParserException {
		TokenManaglinked IF = this.handler;
		//LinkedList<Optional<Token>> Operation;
		Optional<Node> operation;
		Optional<Node> blocked;
		Optional<Node> statements;
		Token.tokenType leftPar = Token.tokenType.LEFT_PARENTHESIS;
		Token.tokenType rightPar = Token.tokenType.RIGHT_PARENTHESIS;
		Token.tokenType iff = Token.tokenType.IF;
		Token.tokenType Else = Token.tokenType.ELSE;
		Optional<StatementNode> next = nextIF;
			while(acceptSeparators());
			//Look for open parent get Operation
			if(IF.MatchAndRemove(leftPar).isPresent()) {
				operation = parseOperation();
				while(acceptSeparators());
				if(IF.MatchAndRemove(rightPar).isPresent()) {
					while(acceptSeparators());
					// blocked gets everything including closing brackets if it has
						blocked = Optional.of(parseBlock(Optional.empty()));
						next = Optional.of(new IfNode(operation,blocked,next));
						if(IF.MatchAndRemove(Else).isPresent()) {
							while(acceptSeparators());
							if(IF.MatchAndRemove(iff).isPresent()) {
								System.out.print("This is Left At ParseIF():"+ nextIF );
								return parseIF(next);
							}
						}
				}
				
			}
			System.out.print("This is Left At ParseIF():"+ nextIF );
			return next;
		}
	
	

	//47 min.
	public Optional<StatementNode> parseFor() throws ParserException{
		Token.tokenType leftPar = Token.tokenType.LEFT_PARENTHESIS;
		Token.tokenType rightPar = Token.tokenType.RIGHT_PARENTHESIS;
		Token.tokenType in = Token.tokenType.IN;
		Optional<Node> operation;
		Optional<Node> blocked;
		Optional<Node> statements;
		int counter = 0;
		Optional empty = Optional.empty();
		LinkedList<Optional<Node>> operations = new LinkedList<Optional<Node>>();










		if(handler.MatchAndRemove(leftPar).isPresent()) {
			while(acceptSeparators());
		while(get_NextType(counter)!= in) {

			if(get_NextType(counter)==rightPar) {
				// if inside means its a ;; for
				for(int i = 0; i<3; i++){
					while(acceptSeparators());
					operations.add(parseOperation());
				}
				if(handler.MatchAndRemove(rightPar).isPresent()) {
					blocked = Optional.of(parseBlock(empty));
					while(acceptSeparators());
					return Optional.of(new forNode(operations,blocked));
				}
			}

			counter++;
		}



		operation = parseOperation();
		// if outside means its a foreach
		blocked = Optional.of(parseBlock(empty));
		return Optional.of(new forEachNode(operation,blocked));
		}



		return empty;
	}
	
	
	

	//20 min.
	public Optional<StatementNode> parseDelete() throws ParserException{
		Token.tokenType leftB = Token.tokenType.LEFT_SQUARE_BRACKET;
		Token.tokenType rightB = Token.tokenType.RIGHT_SQUARE_BRACKET;
		Token.tokenType word = Token.tokenType.WORDS;
		Optional empty = Optional.empty();

		Optional<Node> operation = parseOperation();
		if(operation.isPresent())
			return Optional.of(new DeleteNode(operation));
		return empty;
	}
	
	//20min
	public Optional<StatementNode> parseWhile(int type) throws ParserException{
		Optional<Node> empty = Optional.empty();
		Token.tokenType leftPar = Token.tokenType.LEFT_PARENTHESIS;
		Token.tokenType rightPar = Token.tokenType.RIGHT_PARENTHESIS;
		Token.tokenType While = Token.tokenType.WHILE;
		LinkedList<Optional<Node>> params = new LinkedList<Optional<Node>>();
		Optional<Node> operation;
		BlockNode blocked;
		if(type == 0) {
			operation = parseParenthesis(0).getFirst();
			return Optional.of(new WhileNode(operation,Optional.of(parseBlock(empty))));
		}
			// in this section means DoWhile
			blocked = parseBlock(empty);
			if(handler.MatchAndRemove(While).isEmpty())
				throw new unExpectedElement("\n Error Occurred During Parsing.    Expected:  {"+While+"} But the Current Item is:  "+handler.peek(0)); 
			while(acceptSeparators());
			operation = parseParenthesis(0).getFirst();
			return Optional.of(new DoWhileNode(operation,blocked));
	}
	
	
	
	
	
	public LinkedList<Optional<Node>> parseParenthesis(int type) throws ParserException{
		Optional<Node> empty = Optional.empty();
		Token.tokenType word= Token.tokenType.WORDS;
		Token.tokenType coma = Token.tokenType.COMMA;
		Token.tokenType equal = Token.tokenType.EQUALS;
		Token.tokenType leftPar = Token.tokenType.LEFT_PARENTHESIS;
		Token.tokenType rightPar = Token.tokenType.RIGHT_PARENTHESIS;
		LinkedList<Optional<Node>> params = new LinkedList<Optional<Node>>();
		Optional<Node> operation;
		
		// Basic call ParseOp close paren, return op
		if(type == 0) {
			while(acceptSeparators());
			if(handler.MatchAndRemove(leftPar).isEmpty())
				throw new unExpectedElement("\n Error Occurred During Parsing.    \nExpected:  "+leftPar+" Current handler Item: "+handler.peek(0));
			while(acceptSeparators());
			params.add(parseOperation());
			while(acceptSeparators());
			if(handler.MatchAndRemove(rightPar).isEmpty())
				throw new unExpectedElement("\n Error Occurred During Parsing.    \nExpected:  "+rightPar+" Current handler Item: "+handler.peek(0));
			return params;
		}
		// Second Call handles Parameters separated by commas
		else if(type == 1) {
			// now need to handle differce with comas ArraymemberShip & FucntionCall
			if(handler.MatchAndRemove(leftPar).isEmpty()) // removed leftPar
				throw new unExpectedElement("\n Error Occurred During Parsing.    \nExpected:  "+leftPar+" Current handler Item: "+handler.peek(0));
			while(handler.MatchAndRemove(rightPar).isEmpty()) { // in loop will call operation repeatedly
				while(acceptSeparators());
				handler.MatchAndRemove(coma);
				params.add(parseOperation());
				while(acceptSeparators());
				
			}
			System.out.print("\n At parsePar() Current handler Item: "+handler.peek(0));
			return params;
		} // is ParseMember need to find out what so it knows not to do in,in 

		return params;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Optional<StatementNode> parseReturn()throws ParserException{
		return Optional.of(new ReturnNode(parseOperation()));
	}
	
	public Optional<StatementNode> parseContinue(){
		return Optional.of(new ContinueNode());
	}
	
	public Optional<StatementNode> parseBreak(){
		return Optional.of(new BreakNode());
	}
	
	
	public Optional<Node> parseFunctionCall() throws ParserException{
		Optional<Node> empty = Optional.empty();
		Token.tokenType word= Token.tokenType.WORDS;
		Token.tokenType coma = Token.tokenType.COMMA; 
		Token.tokenType equal = Token.tokenType.EQUALS;
		Token.tokenType leftPar = Token.tokenType.LEFT_PARENTHESIS;
		Token.tokenType rightPar = Token.tokenType.RIGHT_PARENTHESIS;
		
		
		Token.tokenType getLine = Token.tokenType.GETLINE;
		Token.tokenType print = Token.tokenType.PRINT;
		Token.tokenType printF = Token.tokenType.PRINTF;
		Token.tokenType nextFile = Token.tokenType.NEXTFILE;
		Token.tokenType next = Token.tokenType.NEXT;
		Token.tokenType exit = Token.tokenType.EXIT;
		
		LinkedList<Optional<Node>> params = new LinkedList<Optional<Node>>();
		Optional<Node> operation;
		int counter= 0;

		// Gets Function call Reference. Pattern = "	Word = myFunc( 	"
		
		if(get_NextType(counter)== word &&get_NextType(counter+1)==equal);{
			if(get_NextType(counter+2)== word &&get_NextType(counter+3)==leftPar) {
				Optional<Token> assingment = handler.MatchAndRemove(word); // gets first word
				handler.MatchAndRemove(equal); // gets equal 
				while(acceptSeparators());
				Optional<Token> funcName = handler.MatchAndRemove(word);/// gets function name
				params.addAll(parseParenthesis(1)); // gets Parameters Parsed in a linkedList of Optional's.
				return Optional.of(new AssigmentNode(new VariableReferenceNode(assingment.get().getValue()),new FunctionCallNode(funcName,params)));
			}
		}
		
		
		
			// if here we know its a function call. because pattern = " Word+( " Ex: myfunc(
		if(get_NextType(counter)== word &&get_NextType(counter+1)==leftPar) {
			Optional<Token> funcName = handler.MatchAndRemove(word);
			params.addAll(parseParenthesis(1)); // gets Parameters Parsed in a linkedList of Optional's.
			// what will it call too it will find a func def node but if like user defined, goes to lambada,
			
			return Optional.of(new FunctionCallNode(funcName,params));
		}
		
		
		// getLine
		Optional<Token> funcName = handler.MatchAndRemove(getLine);
		if(funcName.isPresent()) {
			while(acceptSeparators());
			if(get_NextType(0)==leftPar) {
				params.addAll(parseParenthesis(1)); // gets Parameters Parsed in a linkedList of Optional's.
				return Optional.of(new FunctionCallNode(funcName,params));		
			}
			params.add(parseOperation());
			return Optional.of(new FunctionCallNode(funcName,params));
		}

		// print
		funcName = handler.MatchAndRemove(print);
		if(funcName.isPresent()) {
			if(get_NextType(0)==leftPar) {
				params.addAll(parseParenthesis(1)); // gets Parameters Parsed in a linkedList of Optional's.

				return Optional.of(new FunctionCallNode(funcName,params));		
			}
			
			params.add(parseOperation());
			return Optional.of(new FunctionCallNode(funcName,params));
			
		}
		//printF
		funcName = handler.MatchAndRemove(printF);
		if(funcName.isPresent()) {
			if(get_NextType(0)==leftPar) {
				params.addAll(parseParenthesis(1)); // gets Parameters Parsed in a linkedList of Optional's.
				return Optional.of(new FunctionCallNode(funcName,params));		
			}
			
			params.add(parseOperation());
			return Optional.of(new FunctionCallNode(funcName,params));
		}
		// exit
		funcName = handler.MatchAndRemove(exit);
		if(funcName.isPresent()) {
			if(get_NextType(0)==leftPar) {
				params.addAll(parseParenthesis(1)); // gets Parameters Parsed in a linkedList of Optional's.
				return Optional.of(new FunctionCallNode(funcName,params));	
			}
			
			params.add(parseOperation());
			return Optional.of(new FunctionCallNode(funcName,params));
		}
		// NextFile 
		funcName = handler.MatchAndRemove(nextFile);
		if(funcName.isPresent()) {
			if(get_NextType(0)==leftPar) {
				params.addAll(parseParenthesis(1)); // gets Parameters Parsed in a linkedList of Optional's.
				return Optional.of(new FunctionCallNode(funcName,params));		
			}
			
			params.add(parseOperation());
			return Optional.of(new FunctionCallNode(funcName,params));
		}
		// next
		funcName = handler.MatchAndRemove(next);
		if(funcName.isPresent()) {
			if(get_NextType(0)==leftPar) {
				params.addAll(parseParenthesis(1)); // gets Parameters Parsed in a linkedList of Optional's.
				return Optional.of(new FunctionCallNode(funcName,params));		
			}
			
			params.add(parseOperation());
			return Optional.of(new FunctionCallNode(funcName,params));
		}
		
		
		
		return empty;
	}


	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getVal() {
		Optional<Token> curr = this.handler.peek(0);
		
		if(curr.isPresent()) {
			String nextValue = curr.get().getValue();
			return nextValue;
		}else return null;
		
	}
	
	
	
// 	Parser 2
	
	
	
	public Optional<Node> parseLValue() throws ParserException{
		System.out.print(" \n \n Start of parseLvaule() ");
		Token.tokenType dollar = Token.tokenType.DOLLAR;
		Token.tokenType word = Token.tokenType.WORDS;
		Token.tokenType openArray = Token.tokenType.LEFT_SQUARE_BRACKET;
		Token.tokenType closeArray = Token.tokenType.RIGHT_SQUARE_BRACKET;
		
		if(this.handler.MatchAndRemove(dollar).isPresent()) {
			
			return Optional.of(new OperationNode(parseBottomLevel(), operation.DOLLAR));
		}
		
			Optional<Token> varName = this.handler.MatchAndRemove(word);
		if(varName.isPresent()) {
			System.out.print(" \n \n This is varName at ParseLValue():  "+ varName);
			
			if(this.handler.MoreTokens()) {
				//Optional<Node> oper = parseOperation();
				//System.out.print("\nthis opper bedore openarry"+ oper);
				if(this.handler.MatchAndRemove(openArray).isPresent()) {
					System.out.print("\n\nParseValLevel() Entere array");
					Optional<Node> oper =parseOperation();
					System.out.print("\n\nParseValLevel() Operation is done witinh array:  "+oper);
					if(this.handler.MatchAndRemove(closeArray).isPresent()){
						System.out.print("ParseValLevel() Closed Array returning val");
						return Optional.of(new VariableReferenceNode(varName.get().getValue(),oper));
					}
					
				}
			}
			System.out.print(" \n \n parseLvalue() : Returing VariableNode: and this is current Tokens:   "+ this.handler);
			return Optional.of(new VariableReferenceNode(varName.get().getValue()));
		}
		 return Optional.empty();
		//System.out.print(" \n \n yo at bottom of parseLvaule() "+ varName)
	}
	
	
	public Optional<Node> parseBottomLevel() throws ParserException{
		
		System.out.print(" \n \n Currently at bottomlevel() ");
		System.out.print("\nthis is the pyaing feild: "+ handler);
		
		Token.tokenType stringL = Token.tokenType.STRINGLITERAL;
		Token.tokenType Number = Token.tokenType.NUMBERS;
		Token.tokenType pattern = Token.tokenType.BACKTICK;
		Token.tokenType leftPar = Token.tokenType.LEFT_PARENTHESIS;
		Token.tokenType rightPar = Token.tokenType.RIGHT_PARENTHESIS;
		Token.tokenType logicNot = Token.tokenType.LOGICAL_NOT;
		Token.tokenType minus = Token.tokenType.MINUS;
		Token.tokenType plus = Token.tokenType.PLUS;
		Token.tokenType incr = Token.tokenType.INCREMENT;
		Token.tokenType decr = Token.tokenType.DECREMENT;
		Token.tokenType function = Token.tokenType.FUNCTION;
		
		
		Optional<Token> string = this.handler.MatchAndRemove(stringL);
		if(string.isPresent()) {
			return Optional.of( new Constant(string.get().getValue()));
		}
		Optional<Token> num = this.handler.MatchAndRemove(Number);
		if(num.isPresent()) {
			System.out.print("this is the number: " + num.get().getValue());
			return Optional.of(new Constant(num.get().getValue()));
		}
		Optional<Token> pat = this.handler.MatchAndRemove(pattern);
		if(pat.isPresent()) {
			return Optional.of( new PatternNode(pat.get().getValue()));
		}
		
		
		 
		if(handler.MatchAndRemove(leftPar).isPresent()) {
			Optional<Node> oper = parseOperation();
			if(this.handler.MatchAndRemove(rightPar).isPresent()) {
				System.out.print("\n\n in righgt paren goona order operations: "+ oper );
				return oper;				
			} 
			
			
		}
		if(this.handler.MatchAndRemove(logicNot).isPresent()) {
			return Optional.of( new OperationNode(parseOperation(), operation.NOT));
			
			
			
		}
		if(this.handler.MatchAndRemove(minus).isPresent()) {
			return Optional.of( new OperationNode(parseOperation(), operation.UNARYNEG));
		}
		if(this.handler.MatchAndRemove(plus).isPresent()) {
			return Optional.of( new OperationNode(parseOperation(), operation.UNARYPOS));
		}
		
		
		
		if(this.handler.MatchAndRemove(incr).isPresent()) {
			System.out.print("\n\n You are indeed incrementing");
			System.out.print("\n\nthis is incrment At BottomLevel: " );
			return Optional.of( new OperationNode(parseOperation(), operation.PREINC));
		}
		if(this.handler.MatchAndRemove(decr).isPresent()) {
			return Optional.of( new OperationNode(parseOperation(), operation.PREDEC));
		}
		if(this.handler.MatchAndRemove(function).isPresent()) {
			return parseFunctionCall();
		}
		
		System.out.print("\n\n calling parseFunctionCall() ");
		return parseLValue();
	}


	
	public Optional<Node> postOp(Optional<Node> bottomLvl) throws ParserException{
		Token.tokenType incr = Token.tokenType.INCREMENT;
		Token.tokenType decr = Token.tokenType.DECREMENT;
		System.out.print("\n\n Here at PostOP()  "+handler);
		if(handler.MatchAndRemove(incr).isPresent()) {
			System.out.print("\n At PostOP() after incr:  "+bottomLvl.get().getValue());
			return Optional.of( new OperationNode(bottomLvl, operation.POSTINC));
		}
		if(handler.MatchAndRemove(decr).isPresent()) {
			System.out.print("\n At PostOP() after decr:  "+bottomLvl.get().getValue());
			return Optional.of( new OperationNode(bottomLvl, operation.POSTDEC)); 
		}
		System.out.print("\n At the end Of PostOP(): "+bottomLvl);
		return Optional.of(bottomLvl.get());
	}
	
	
	
	// you can improve this u dont need the linkedList u improved on recurssion, rember how theres a right and left Node and Optional Right 
	// you need to just order it in reverse, Remember all the returns , that guy has to  report back to sombody and the same for sombody, while going though, recursion: switch reccursions 
	// Ex: "2^5^7";  2  calls on 5 and 5 calls on 7 and just order that right most  
	public Optional<Node> parseExponents(Optional<Node> bottomLvl,  Node left) throws ParserException{
		Token.tokenType exp = Token.tokenType.CARET;
		Optional<Node> newBottomLvl = bottomLvl;
		Node Soda;
		Node low;
		
		
		
		
		Node lefty = newBottomLvl.get();
		System.out.print("\n\n At parseExponets() this is the passed bottomlevel:  "+bottomLvl);
		if(left!=null) {
			lefty = left;
		} 
		
		System.out.print("\n\n At parseExponets() Current Node Operations  "+left);
		System.out.print("\n\n At parseExponets() Current token list  "+handler);
		
		// gets the Exponent then gets the next num, if their is a next Exponent return 
		
		//False if we remove the second Exponent
		if(handler.MatchAndRemove(exp).isPresent()) {
		// Lefty == 2	
		newBottomLvl = parseBottomLevel();// ==3
		/// if true we KNOW there is another Exponent 
		if(handler.MatchAndRemove(exp).isPresent()) {
		Optional<Node> getNext =parseBottomLevel();
		 // ==4
		low =  new OperationNode(newBottomLvl.get(),operation.EXPONENT,getNext);
		//Soda = new OperationNode(lefty,operation.EXPONENT,Optional.of(low));
		return parseExponents(newBottomLvl,new OperationNode(lefty,operation.EXPONENT,Optional.of(low)));
		}
		
		
		
			
			System.out.print("\n\n At parseExponets() If is Exponent return  ");
			//return parseExponents(newBottomLvl,new OperationNode(parseBottomLevel().get(),operation.EXPONENT, Optional.of(lefty)));
			return parseExponents(newBottomLvl,new OperationNode(lefty,operation.EXPONENT,newBottomLvl));
		}
		System.out.print("\n\n Bottom Return of parseExponets() Current Node Operations  "+left);
		return Optional.of(lefty); 
	}
	
	// 2^(3^4)
	
	//4^(3^2)
	
	// (4^3)^2 = 4096
	// (2^3)^4 = 4096 
	
	//  7^((h/2)+6)
	
	// (((h/2)+6)^7
	
	
	
	
	
	
	public Optional<Node> parseBasicMath(Optional<Node> bottomLvl, Node left) throws ParserException {
		
		Token.tokenType multi = Token.tokenType.ASTERISK;
		Token.tokenType div = Token.tokenType.SLASH;
		Token.tokenType mod = Token.tokenType.PERCENT;
		Token.tokenType add = Token.tokenType.PLUS;
		Token.tokenType sub = Token.tokenType.MINUS;
		Token.tokenType exponent = Token.tokenType.CARET;
		Token.tokenType string = Token.tokenType.WORDS;
		Token.tokenType exp = Token.tokenType.CARET;
		Optional<Node> currentOp;
		Optional<Node> newBottomLvl = bottomLvl;
		Node lefty = newBottomLvl.get();
		// sees if this is its frist time in recurring or not
		
		if(left!=null) {
			//newBottomLvl = parseBottomLevel();
			
			
			// need to find way to return Exponents without using get next type method :
			// 1: 
			
		
			lefty = left;
			
			
			
			// can  this be a differnt if what we need, to stop exponet from getting eaten 
			
		}
		
		
		if(get_NextType(0)==exp)
			lefty = parseExponents(newBottomLvl, lefty).get();	
		if(handler.MatchAndRemove(multi).isPresent()) {
			return parseBasicMath(newBottomLvl,new OperationNode(lefty,operation.MULTIPLY, parseBottomLevel()));
		}
		if(handler.MatchAndRemove(div).isPresent()) {
			return parseBasicMath(newBottomLvl,new OperationNode(lefty,operation.DIVIDE, parseBottomLevel()));
		}
		if(handler.MatchAndRemove(mod).isPresent()) {
			return parseBasicMath(newBottomLvl,new OperationNode(lefty,operation.MODULO, parseBottomLevel()));
		}
		if(handler.MatchAndRemove(add).isPresent()) {
			return parseBasicMath(newBottomLvl,new OperationNode(lefty,operation.ADD, parseBottomLevel()));
		}
		if(handler.MatchAndRemove(sub).isPresent()) {
			return parseBasicMath(newBottomLvl,new OperationNode(lefty,operation.SUBTRACT, parseBottomLevel()));
		}
		if(handler.MatchAndRemove(string).isPresent()) {
			return parseBasicMath(newBottomLvl,new OperationNode(lefty,operation.CONCATENATION, parseBottomLevel()));
		}
		
//		Optional <Node> conCat = parseConCat(newBottomLvl, lefty);
//		lefty = conCat.get();	
		//System.out.print("\n\n At parseBasicMath() This is the OperationNode   "+ left+"\n");
		//System.out.print("\n\n At parseBasicMath() Printing newParseBOttomlv: "+ newBottomLvl+"\n");
		return Optional.of(lefty);
	}

	
	
//	public Optional<Node> parseConCat(Optional<Node> bottomLvl,Node left) throws ParserException{
//		
//		Token.tokenType string = Token.tokenType.WORDS;
//		Optional<Node> newBottomLvl = bottomLvl;
//		Node lefty = newBottomLvl.get();
//		
//		if(left != null) {
//			lefty = left;
//		}
//		if(handler.MatchAndRemove(string).isPresent()) {
//			return parseConCat(newBottomLvl,new OperationNode(lefty,operation.CONCATENATION,parseBottomLevel()));
//		}
//		System.out.print("\nAt ParseExpression(): this is left "+left);
//		return Optional.of(lefty);
//	}
	
	
	 
	
	
	
	public Optional<Node> parseBoolCom(Optional<Node> bottomLvl) throws ParserException{
		System.out.print("\n\n\n\n\n\n\nHere at parseBoolCom(): heres bottmLvl   "+ bottomLvl+"\n\nAnd Printing tokens list "+ this.handler);
		Token.tokenType lessT = Token.tokenType.LESS_THAN;
		Token.tokenType lessThanE = Token.tokenType.LESS_THAN_OR_EQUAL;
		Token.tokenType notE = Token.tokenType.NOT_EQUAL;
		Token.tokenType equalT= Token.tokenType.EQUAL_EQUAL;
		Token.tokenType greaterT = Token.tokenType.GREATER_THAN;
		Token.tokenType greaterThenE = Token.tokenType.GREATER_THAN_OR_EQUAL;
		Token.tokenType ere = Token.tokenType.TILDE;
		Token.tokenType notEre = Token.tokenType.NOT_TILDE;
		System.out.print("\n\n\n\n"+handler.MoreTokens());
		if(this.handler.MoreTokens()) {
		if(this.handler.MatchAndRemove(lessT).isPresent()) {
			return Optional.of(new OperationNode(bottomLvl.get(),operation.LT,parseBottomLevel() ));
		}
		if(this.handler.MatchAndRemove(lessThanE).isPresent()) {
			System.out.print("\n\nHere at parseBoolCom(): Parsing is crazy   "+ bottomLvl); 
			return Optional.of(new OperationNode(bottomLvl.get(),operation.LE, parseBottomLevel()));
		}
		if(this.handler.MatchAndRemove(notE).isPresent()) {
			return Optional.of(new OperationNode(bottomLvl.get(),operation.NE, parseBottomLevel()));
		}
		if(this.handler.MatchAndRemove(equalT).isPresent()) {
			return Optional.of(new OperationNode(bottomLvl.get(),operation.EQ, parseBottomLevel()));
		}
		if(this.handler.MatchAndRemove(greaterT).isPresent()) {
			return Optional.of(new OperationNode(bottomLvl.get(),operation.GE, parseBottomLevel()));
		}
		if(this.handler.MatchAndRemove(greaterThenE).isPresent()) {
			return Optional.of(new OperationNode(bottomLvl.get(),operation.GT, parseBottomLevel()));
		}
		if(this.handler.MatchAndRemove(ere).isPresent()) {
			return Optional.of(new OperationNode(bottomLvl.get(),operation.MATCH, parseBottomLevel()));
		}
		if(this.handler.MatchAndRemove(notEre).isPresent()) {
			return Optional.of(new OperationNode(bottomLvl.get(),operation.NOTMATCH, parseBottomLevel()));
		}
		}
      return Optional.empty();
	}
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// so since we get the var name(EX: c), then its INDEX	 will be a operation node(Ex: return 7). so c[7], but once done will  assign a OperationNode(Ex: return 67).
	// now c[7]= 67, so now if we want to check weather or not Expression(Ex:OperationNode) is in Array(Ex: line ^) all we need to do is check assigmentNode

	
	
	
	//Just check for this Pattern: Exp,Exp,Exp,Exp and for mult: (1,1,1,1) in a  func(p,a,d)
	
	
	
	public Optional<Node> parseArrayMembers(Optional<Node> bottomLvl,Node left) throws ParserException{
		Token.tokenType in = Token.tokenType.IN;
		Token.tokenType coma = Token.tokenType.COMMA;
		Token.tokenType leftPar = Token.tokenType.LEFT_PARENTHESIS;
		Token.tokenType rightPar = Token.tokenType.LEFT_PARENTHESIS;
		Optional<Node> newBottomLvl = bottomLvl;
		Optional<Node> members;
		Node lefty = newBottomLvl.get();
		int counter =0;
		
		if(left != null) {
			
			lefty = left;
		}
		
	
		System.out.print("\n\nAt start of parseArrayMembers(): ");
		
		// check key word in
		if(this.handler.MatchAndRemove(in).isPresent()) {
			System.out.print("\n\nThis is parseArrayMembers(): This is Bottom In:   	"+ bottomLvl);
			return Optional.of(new OperationNode(lefty,operation.IN, parseBottomLevel()));
			
		}
		System.out.print("\n\nAt Coma of parseArrayMembers(): ");
		System.out.print("\n\nThis is parseArrayMembers(): This is Bottom Level LEft Node Expression :   	"+ left);
		while(acceptSeparators());
		// Here if In present and left and right Par
		// find way to stop going into here;
			
			// oh next is a coma then IN repats till rightPar.  problem it is a coma but i dont want it to do this because its a fucntionCall
		
			// check coma
		if(this.handler.MatchAndRemove(coma).isPresent())
			return parseArrayMembers(bottomLvl,new OperationNode(lefty, operation.IN, parseBottomLevel()));
		return Optional.of(lefty);
	}
	
	
	
	
	public Optional<Node> parseLogic(Optional<Node> bottomLvl,Node left) throws ParserException{
		
		Token.tokenType logicAnd = Token.tokenType.LOGICAL_AND;
		Token.tokenType logicOR = Token.tokenType.LOGICAL_OR;
		Optional<Node> newBottomLvl = bottomLvl;
		Node lefty = newBottomLvl.get();
		if(left != null) {
			lefty = left;
		}
		
		System.out.print("\n\n parseLogic() these are tokens : "+ handler);
		System.out.print("\n\n parseLogic() this is  : "+ lefty);
		if(this.handler.MatchAndRemove(logicAnd).isPresent()) {
			return parseLogic(newBottomLvl,new OperationNode(lefty, operation.ND, parseBottomLevel()));
		}
		if(this.handler.MatchAndRemove(logicOR).isPresent()) {
			return parseLogic(newBottomLvl,new OperationNode(lefty, operation.OR, parseBottomLevel()));
		}
		System.out.print("\n\n parseLogic() this is left at END  : "+ lefty);
		return Optional.of(lefty);
	}
	
	
	
	
	
	
	
public Optional<Node> parseTernary(Optional<Node> bottomLvl, Node left) throws ParserException{
		
		Token.tokenType qMark = Token.tokenType.QUESTION_MARK;
		Token.tokenType colon = Token.tokenType.COLON;
		Optional<Node> newBottomLvl = bottomLvl;
		Optional<Node> bot = Optional.empty();
		Optional<Node> tok = Optional.empty();
		
		Node lefty = newBottomLvl.get();
		if(left != null) {
			lefty = left;
		}
		
		System.out.print("\n\nthis is Lefty:  "+ lefty);
		if(this.handler.MatchAndRemove(qMark).isPresent()) {
			 bot = parseBottomLevel();
		if(handler.MatchAndRemove(colon).isEmpty())
			throw new unExpectedElement("\n Error Occurred During Parsing.    Expected:  {"+colon+"} But the Current Item is:  "+handler.peek(0));
		
		return  Optional.of(new TernaryNode(lefty, bot.get(), parseBottomLevel().get()));
		}
		
		return Optional.of(lefty);
}
	
	


	public Optional<Node> parseAssigenment(Optional<Node> bottomLvl,Node left) throws ParserException {
		Token.tokenType exponentE = Token.tokenType.CARET_EQUAL;
		Token.tokenType modE = Token.tokenType.PERCENT_EQUAL;
		Token.tokenType multiplyE = Token.tokenType.ASTERISK_EQUAL;
		Token.tokenType divisionE = Token.tokenType.SLASH_EQUAL;
		Token.tokenType addE= Token.tokenType.PLUS_EQUAL;
		Token.tokenType subE = Token.tokenType.MINUS_EQUAL;
		Token.tokenType equals = Token.tokenType.EQUALS;
		
		Optional<Node> newBottomLvl = bottomLvl;
		Node lefty = newBottomLvl.get();
		if(left != null) {
			lefty = left;
		}
		
		System.out.print("\n\n\n\nThis is parseAssigenment():  "+ lefty);
		
		if(handler.MatchAndRemove(exponentE).isPresent()) {
			return parseAssigenment(newBottomLvl, new AssigmentNode(newBottomLvl.get(),parseOperation().get()));
		}
		if(handler.MatchAndRemove(modE).isPresent()) {
			return parseAssigenment(newBottomLvl, new AssigmentNode(newBottomLvl.get(),parseBottomLevel().get()));
		}
		if(handler.MatchAndRemove(divisionE).isPresent()) {
			return parseAssigenment(newBottomLvl, new AssigmentNode(newBottomLvl.get(),parseBottomLevel().get()));
		}
		if(handler.MatchAndRemove(addE).isPresent()) {
			return parseAssigenment(newBottomLvl, new AssigmentNode(newBottomLvl.get(),parseBottomLevel().get()));
		}
		if(handler.MatchAndRemove(subE).isPresent()) {
			return parseAssigenment(newBottomLvl, new AssigmentNode(newBottomLvl.get(),parseBottomLevel().get()));
		}
		if(handler.MatchAndRemove(equals).isPresent()) {
			return parseAssigenment(newBottomLvl, new AssigmentNode(newBottomLvl.get(),parseBottomLevel().get()));
		}
		return Optional.of(lefty); 
	}










	// 
	public Optional<Node> parseOperation() throws ParserException {
		System.out.print("\n\n At Start of parseOperation() : The current Token List "+ handler);
		Optional<Node> BottomLvl = parseBottomLevel();
		Node Operation;
		if(BottomLvl.isPresent()) {
			Optional<Node> postOp = postOp(BottomLvl);
			Optional<Node> ParseExp =  parseExponents(BottomLvl,postOp.get());
			Optional<Node> basicMath = parseBasicMath(BottomLvl,ParseExp.get());
			Optional<Node> parseBoolCom = parseBoolCom(BottomLvl);
			Optional<Node> parseArrayMembers = parseArrayMembers(BottomLvl,basicMath.get());
			Optional<Node> parseLogic = parseLogic(BottomLvl,parseArrayMembers.get());
			Optional<Node> parseTernary = parseTernary(BottomLvl,parseLogic.get());
			Optional<Node> parseAssigenment = parseAssigenment(BottomLvl,parseTernary.get());
			//(6/2)*(1+2)
			
			//((2*x)^2)-20

//		if(postOp.isPresent()) {
//			System.out.print("\n\ncurrently at postOp() which returend a value:  "+postOp);
//			return postOp;}
		if(parseBoolCom.isPresent()) {
			System.out.print("\n\ncurrently at parseBoolCom which returend a value:  "+parseBoolCom);
			return parseBoolCom;}
		if(parseAssigenment.isPresent()) {
			System.out.print("\n\ncurrently at parseAssigenment which returend a value:  "+parseAssigenment);
			return parseAssigenment;}
		System.out.print("\n\n At the endp of ParseOperations this is current tokens : "+ handler); 
		}
		//return null;
		System.out.print("\n\n At the endp of ParseOperations this is current tokens : "+ handler); 
		return parseLValue();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
