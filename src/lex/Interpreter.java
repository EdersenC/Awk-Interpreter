package lex;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

import lex.OperationNode.operation;
import lex.ReturnType.RType;

public class Interpreter {

	public HashMap<String, InterpreterDataType> Global = new HashMap<String, InterpreterDataType>();
	public HashMap<String, FunctionDefinitionNode> functionCall =  new HashMap<String, FunctionDefinitionNode>();

	public HashMap<String, AwkProgramOut> awkProgramOut =  new HashMap<String,AwkProgramOut>();
	private InterpreterDataType Interpret;
	public LineManager handler;
	private ProgramNode awk;
	
	public Interpreter(ProgramNode awk, Path awkFile) throws IOException {
		this.awk = awk;
		this.handler = new LineManager(Files.readAllLines(awkFile));
		
		Global.put("FILENAME", new InterpreterDataType(awkFile.getFileName().toString()));
		Global.put("FS", new InterpreterDataType(","));
		Global.put("OFMT", new InterpreterDataType("%.6"));
		Global.put("OFS", new InterpreterDataType(" "));
		Global.put("ORS", new InterpreterDataType("\n"));
		
	
		
		
	}
	
	public Interpreter(ProgramNode awk) throws IOException {
		this.handler = new LineManager(null);
		
	}
	
	
	

	
	
	public String RunFunctionCall(FunctionDefinitionNode function,HashMap<String, InterpreterDataType> localVar) throws ParserException {
		function.execute(localVar);
		return "";
	}
	
	
	
	
	
	public InterpreterDataType ternaryNode(Node currentNode,HashMap<String, InterpreterDataType> localVar) throws ParserException {
		String boolCon = getIDT(currentNode.getNode().get(),localVar).getDataType();
		InterpreterDataType trueC = getIDT(currentNode.getLeft(),localVar);
		InterpreterDataType falseC = getIDT(currentNode.getLeft(),localVar);
		if(boolCon.equals("false"))
			return falseC;
		return trueC;
	}

	
	public InterpreterDataType VariableReferenceNode(Node currentNode,HashMap<String, InterpreterDataType> localVar) throws ParserException {
		HashMap<String, InterpreterDataType> varHolder = Global;
		
		String nodeVal = currentNode.getValue();
		if(localVar!=null);
		 varHolder = localVar;
		
		if(currentNode.getRight()!= null) {
			
		}
		if(currentNode.getRight().isEmpty()) {
			return varOperations(varHolder,nodeVal,false,new InterpreterDataType());
		}
		String index = getIDT(currentNode.getRight().get(),varHolder).getDataType();
		//InterpreterDataType results = getIDT(currentNode.getRight().get(),varHolder);
		if(varHolder.containsKey(nodeVal)) {
			InterpreterDataType var = varHolder.get(nodeVal);
			if(!(var instanceof InterpreterArrayDataType ))
				throw new  unExpectedElement("\n Not an InterpreterArrayDataType");
			if(var.hasVariable(index))
				return var.getVariable(index);
		}
		throw new  unExpectedElement("\n Both Global and Local Varibles failed to retrive A varible:   "+nodeVal);
	}
	

	//todo: redundancy in this method
	public InterpreterDataType boolComapare(String c1, operation op, String c2) {
		Float compare1,compare2;
		StringBuilder builder = new StringBuilder();
		try {
			compare1 = Float.parseFloat(c1);
			compare2 = Float.parseFloat(c2);
        } catch (NumberFormatException e) {
        	if(op.equals(operation.EQ))
                return new InterpreterDataType(builder.append(c1.equals(c2)).toString());
        	if(op.equals(operation.NE))
        		return new InterpreterDataType(builder.append(!(c1.equals(c2))).toString());
        	if(op.equals(operation.LE))
        		return new InterpreterDataType(builder.append(c1.compareTo(c2)<= 0).toString());
        	if(op.equals(operation.LT))
        		return new InterpreterDataType(builder.append(c1.compareTo(c2)<0).toString());
        	if(op.equals(operation.GE))
        		return new InterpreterDataType(builder.append(c1.compareTo(c2)>= 0).toString());
        	if(op.equals(operation.GT))
        		return new InterpreterDataType(builder.append(c1.compareTo(c2)> 0).toString());
        	return new InterpreterDataType();
        }

		if(op.equals(operation.EQ))
            return new InterpreterDataType(builder.append(compare1.equals(compare2)).toString());
    	if(op.equals(operation.NE))
    		return new InterpreterDataType(builder.append(!(compare1.equals(compare2))).toString());
    	if(op.equals(operation.LE))
    		return new InterpreterDataType(builder.append(compare1.compareTo(compare2)<= 0).toString());
    	if(op.equals(operation.LT))
    		return new InterpreterDataType(builder.append(compare1.compareTo(compare2)< 0).toString());
    	if(op.equals(operation.GE))
    		return new InterpreterDataType(builder.append(compare1.compareTo(compare2)>= 0).toString());
    	if(op.equals(operation.GT))
    		return new InterpreterDataType(builder.append(compare1.compareTo(compare2)> 0).toString());
    	return new InterpreterDataType();
    	
	}
	
	
	
	
	
	
	public float getFloat(String string) throws ConversionError {
		try{
			return Float.parseFloat(string);
		}catch(Exception e) {
			 return 0;
			}
	}
	
	
	public boolean boolFloat(String c1) throws ConversionError {
		Float val;
		try {
			val = getFloat(c1);
			return val != 0;
        } catch (NumberFormatException e) {
        	return false;
        }
	}
	
	public String boolOps(String c1, operation op, String c2) throws ConversionError {
		Float compare1,compare2;
		StringBuilder builder = new StringBuilder();
		if(op.equals(operation.ND))
			return builder.append(boolFloat(c1)&&boolFloat(c2)).toString();
		if(op.equals(operation.OR))
			return builder.append(boolFloat(c1)||boolFloat(c2)).toString();
		if(op.equals(operation.NOT))
			return builder.append(!boolFloat(c1)).toString();
    	return "none";
	}
	
	public String matcher(String c1, operation op, Node pattern) throws unExpectedElement {
		
		Float compare1,compare2;
		StringBuilder builder = new StringBuilder();
		Pattern reg = Pattern.compile(pattern.getValue());
        // Create a Matcher object
        Matcher matcher = reg.matcher(c1);
		
		if(!(pattern instanceof PatternNode))
			throw new  unExpectedElement("\n Not an InterpreterArrayDataType");
		if(op.equals(operation.NOTMATCH))
			return builder.append(!(matcher.find())).toString();
		if(op.equals(operation.MATCH))
			return builder.append(matcher.find()).toString();
    	return "none";
	}
	
	

	
	public InterpreterDataType interpreteBasicMath(Node node,HashMap<String, InterpreterDataType> localVar) throws ConversionError {
		HashMap<String, InterpreterDataType> varHolder = Global;
		if(localVar!=null);
			varHolder = localVar;
		Node left =  node.getLeft();
		Node right = node.getRight().get();
		operation op = node.getType();
		try {
			InterpreterDataType leftIDT =  getIDT(left, varHolder);
			InterpreterDataType rightIDT =  getIDT(right, varHolder);
			float leftM = Float.parseFloat(leftIDT.getDataType());
			float rightM = Float.parseFloat(rightIDT.getDataType());
		
			if(op.equals(operation.EXPONENT))
				return new InterpreterDataType(String.valueOf(Math.pow(leftM, rightM)));
			if(op.equals(operation.MULTIPLY))
				return new InterpreterDataType(String.valueOf(leftM*rightM));	
			if(op.equals(operation.DIVIDE))
				return new InterpreterDataType(String.valueOf(leftM/rightM));
			if(op.equals(operation.ADD))
				return new InterpreterDataType(String.valueOf(leftM+rightM));
			if(op.equals(operation.SUBTRACT))
				return new InterpreterDataType(String.valueOf(leftM-rightM));
			if(op.equals(operation.CONCATENATION))
				new InterpreterDataType(String.valueOf(leftIDT.getDataType()+rightIDT.getDataType()));
			
		} catch(Exception e) {
			return new InterpreterDataType();
		}
		return new InterpreterDataType();
	}
	
	
	
	

	
	
	
	
	
	
	
	
	public InterpreterDataType InterpretInc(Node node, operation op, HashMap<String, InterpreterDataType> localVar,InterpreterDataType IDT) throws ParserException {
		String value = node.getValue();
		Float leftM = getFloat(IDT.getDataType());
System.out.print("\n\n  this is the node in inc  "+node+"   "+op+"   "+localVar+"   "+leftM+"   \n\n");
	if(!(node instanceof VariableReferenceNode))
			if (node.getNode().isPresent())
				value = node.getNode().get().getValue();

	if(op.equals(operation.PREINC)||op.equals(operation.POSTINC))
		return varOperations(localVar,value,true,new InterpreterDataType(String.valueOf(++leftM)));
	if(op.equals(operation.PREDEC)||op.equals(operation.POSTDEC))
		return new InterpreterDataType(String.valueOf(--leftM));
	if(op.equals(operation.UNARYPOS))
		return new InterpreterDataType(String.valueOf(++leftM));
	if(op.equals(operation.UNARYNEG))
		return new InterpreterDataType(String.valueOf(leftM--));
	
	return new InterpreterDataType();
	}




	public InterpreterDataType varOperations(HashMap<String, InterpreterDataType> map, String string ,boolean condition,InterpreterDataType newData) throws NotFound {
		HashMap<String, InterpreterDataType> varHolder = Global;
		if(!map.isEmpty())
			varHolder = map;
		// if in map swp will now be, the Var it founds vaule
		if(!varHolder.containsKey(string))
			throw new NotFound(string,"0");
		if(condition)
			varHolder.replace(string, newData);
		return varHolder.get(string);
	}
	
	public InterpreterDataType OperationNode(Node currentNode,HashMap<String, InterpreterDataType> localVar) throws ParserException {
		HashMap<String, InterpreterDataType> varHolder = Global;

		if(localVar!=null);
			varHolder = localVar;
		Node left =  currentNode.getLeft();
		if(!isNodePresent(currentNode,-1))
			if (currentNode.getNode().isPresent())
				left = currentNode.getNode().get();

		operation op = currentNode.getType();
		InterpreterDataType leftIDT =  getIDT(left, varHolder);
		if(!isNodePresent(currentNode,1)){// right>0
			return InterpretInc(currentNode,op,varHolder,leftIDT);
		}
		Node right = currentNode.getRight().get();

		InterpreterDataType rightIDT =  getIDT(right, varHolder);
//		float leftM = getFloat(leftIDT.getDataType());
//		float rightM = getFloat(rightIDT.getDataType());

		InterpreterDataType mathResults =  interpreteBasicMath(currentNode,varHolder);
		InterpreterDataType boolComResults =  boolComapare(leftIDT.getDataType(),op,rightIDT.getDataType());
		String calc = boolOps(leftIDT.getDataType(),op,rightIDT.getDataType());
		System.out.print(mathResults.getDataType());


		if(!(mathResults.isEmpty()))
			return mathResults;
		if(!(boolComResults.isEmpty()))
			return boolComResults;
		if(!calc.equals("none"))
			return new InterpreterDataType(calc);
		if(op.equals(operation.DOLLAR))
			return varHolder.get("$"+rightIDT);

	return new InterpreterDataType();
	}
	
	
	
	
	
	
	
	
	
	
	
	public InterpreterDataType getIDT(Node currentNode,HashMap<String, InterpreterDataType> localVar)throws ParserException {
		Node left = currentNode;
	System.out.println("\n This is the current node: "+currentNode);
		if(currentNode instanceof AssigmentNode) {
			return assingmentNode(currentNode, localVar);
		}
		if(currentNode instanceof Constant) {
			return new InterpreterDataType(left.getValue());
		}
		if(currentNode instanceof FunctionCallNode) {
			return new InterpreterDataType(left.getValue());
		}
		if(currentNode instanceof PatternNode) {
			throw new  unExpectedElement("\nInterpreter Came Across A Unexpected Pattern");
		}
		if(currentNode instanceof TernaryNode) 
			return ternaryNode(currentNode,localVar);
		if(currentNode instanceof VariableReferenceNode) 
			return VariableReferenceNode(currentNode,localVar);
		if(currentNode instanceof OperationNode)
			return OperationNode(currentNode,localVar);
		return new InterpreterDataType();
	}
	
	
	
//	public Optional<Node> matchAndRemove(int type) {
//		LinkedList<BlockNode> deBlocker = awk.actionBlocks;
//		LinkedList<Optional<StatementNode>> holder = deBlocker.get(0).getStatements();
//		Optional<Node> matches = Optional.empty();
//			 holder = deBlocker.get(0).getStatements();
//			 if(holder.isEmpty())
//				 return Optional.empty();
//			 if(type==0) {
//			 if(holder.getFirst() .get() instanceof AssigmentNode ) {
//				 System.out.print("\n\n\n"+holder.getFirst().get()+" MATCHES CLASS ASSIGMENTNODE     ");
//				 return holder.pollFirst();
//			 }
//
//			 }
//
//		return Optional.empty();
//	}
	

	

	
	
	
	
	
	public boolean isNodePresent(Node node, int pos) {
		if(node == null)
			return false;
		if(pos > 0) {
			if(node.getRight().isPresent())
				return true;
		}
		if(pos == 0) {
			if(node.getNode().isPresent())
				return true;
		}
		if(pos < 0) {
			if(node.getLeft()!=null&&node.getLeft().getNode().isPresent())
				return true;
		}
		return false;
	
	}
	
	
	
	

	
	// we will use local varibles so this blocks local varible hashmap: maybe just throw around a block hash map
	// checks each Node and depeding on what it is, we get the resukts with id IDT snd then we add that to the Hash map
	// Ex: AssingmentNode Checks ProgramNode if there return result, if not maybe we can just pass 
	public InterpreterDataType assingmentNode(Node currentNode , HashMap<String, InterpreterDataType> localVar ) throws ParserException {
		HashMap<String, InterpreterDataType> varHolder = Global;
		if(localVar!=null);
			varHolder = localVar;
		if(isNodePresent(currentNode,0))
			throw new  unExpectedElement("\nInterpreter The Node value is null");
		if(isNodePresent(currentNode,-1))
			return new InterpreterDataType();
	
		Node left =  currentNode.getLeft();
		String target = left.getValue();
		
		if(!((left instanceof VariableReferenceNode)||( left instanceof OperationNode && Objects.equals(left.getValue(), "$"))) )
			throw new unExpectedElement("\n Error Occurred During Parsing.    Expected:  { VariableRefrenceNode Or Operation Dolloar} But the Current Item is:  "+currentNode);
		if(left.getRight().isEmpty());
			//throw new unExpectedElement("\n Error Occurred During Parsing.    Expected:  {No Value for Target} But the Current Item is:  "+currentNode);
		InterpreterDataType value =  getIDT(currentNode.getRight().get(),varHolder);

		if(varHolder.containsKey(target))
			varHolder.replace(target,value);
		varHolder.put(target,value);

		return new InterpreterDataType(value.getDataType());
		}

	public ReturnType ProccessForNode(StatementNode statement,HashMap<String, InterpreterDataType> localVar ) throws ParserException {

		ForNode forNode = null;

		try{
			forNode = (ForNode) statement;
		}catch (Exception e) {
			return new ReturnType(RType.NONE);
		}
		int PARAMS = 2;
		Optional<StatementNode> var = forNode.getinit();
		Optional<Node> bool= forNode.getbool();
		Optional<StatementNode> inc = forNode.getinc();
		ReturnType init = processStatement(localVar,var.get());
		do {
			ReturnType statementList = interpretListOfStatements(statement.getBlock().getStatements(), localVar);
			if(statementList.getRType()== RType.RETURN)
				return statementList;
			if(statementList.getRType()== RType.BREAK)
				break;
			System.out.print("\n\n Holla This is local Var"+localVar.toString());
			 processStatement(localVar,inc.get());
		}while((getIDT(bool.get(), localVar).getDataType()).equals("true"));

	return new ReturnType(RType.NONE);
	}
	public ReturnType ProccessDowhileNode(StatementNode statement, HashMap<String, InterpreterDataType> localVar,boolean doWhile) throws ParserException {
		boolean skip = doWhile;
		do {
			if(skip) {
				ReturnType statementList = interpretListOfStatements(statement.getBlock().getStatements(), localVar);
				if (statementList.getRType() == RType.RETURN)
					return statementList;
				if (statementList.getRType() == RType.BREAK)
					return statementList;
				System.out.print("\n\n Holla This is local Var" + localVar.toString());
			}
			skip = true;
		}while((getIDT(statement.getNode().get(), localVar).getDataType()).equals("true"));
		
		return new ReturnType(RType.NONE);
	}


	public ReturnType proccessIF(IfNode statement,HashMap<String, InterpreterDataType> localVar ) throws ParserException {
		IfNode walker = statement;
		boolean walk = false;
		InterpreterDataType condition = new InterpreterDataType();
		do {
			if (walk)
				walker = (IfNode) walker.getNext().get();
			walk = true;

			boolean bool = walker.getCondition().isPresent();
			if(bool)
				condition = getIDT(walker.getCondition().get(), localVar);
			if (condition.isEmpty() || condition.getDataType().equals("true")) {
				ReturnType statementList = interpretListOfStatements(statement.getBlock().getStatements(), localVar);
				if(!statementList.getRType().equals(RType.NONE))
					return statementList;
			}

		}while (walker.hasNext());



		return new ReturnType(RType.NONE);
	}



	
	public ReturnType ProccessFunctioCallNode(StatementNode statement,HashMap<String, InterpreterDataType> localVar ) throws ParserException {
		FunctionCallNode node = (FunctionCallNode) statement;
		String fName = node.getFunction();
		LinkedList<Optional<Node>> paramholder = node.getParams();
		LinkedList<String> params = new LinkedList<String>();
		HashMap<String, InterpreterDataType> scoped = new HashMap<String, InterpreterDataType>();

		for(int i = 0; i< paramholder.size(); i++) {
			String var ="";
			if(paramholder.get(i).isPresent()){
				var = paramholder.get(i).get().getValue();
			}
			if(localVar.containsKey(var))
				scoped.put(String.valueOf(i), localVar.get(var));
			scoped.put(String.valueOf(i), new InterpreterDataType(var));
		}
		if (functionCall.containsKey(fName))
			return new ReturnType(functionCall.get(fName).execute(scoped),RType.NONE);
		return new ReturnType(RType.NONE);
	}
	
	
	

	public ReturnType processStatement(HashMap<String, InterpreterDataType> localVar, StatementNode statement) throws ParserException {


		if(statement instanceof AssigmentNode) {
			return new ReturnType(getIDT(statement,localVar).getDataType(),RType.NONE);
		}
		if(statement instanceof BreakNode) {
			return new ReturnType(RType.BREAK);
		}
		if(statement instanceof ContinueNode) {
			return new ReturnType(RType.CONTINUE);
		}
		if(statement instanceof ReturnNode) {
			return new ReturnType(getIDT(statement.getNode().get(),localVar).getDataType(),RType.RETURN);
		}
		if(statement instanceof DeleteNode) {
			String key = statement.getNode().get().getValue();
			if(localVar.containsKey(key)) {
				localVar.remove(key);
				return new ReturnType(RType.DELETE);
			}
			if(Global.containsKey(key)) {
				Global.remove(key);
				return new ReturnType(RType.DELETE);
			}
			throw new NotFound(key,"0");
		}
		if(statement instanceof DoWhileNode ) {
			return ProccessDowhileNode(statement,localVar,true);
		}
		if(statement instanceof quickStatementNode ) {
			return new ReturnType(InterpretInc(statement.getNode().get(),statement.getType(),localVar,getIDT(statement.getNode().get(),localVar)).getDataType(),RType.NONE);
		}
		if(statement instanceof ForNode ) {
			return ProccessForNode(statement,localVar);
		}
		if(statement instanceof FunctionCallNode ) {
			return ProccessFunctioCallNode(statement,localVar);
		}
		if (statement instanceof IfNode) {
			return proccessIF((IfNode) statement,localVar);
		}
		if (statement instanceof WhileNode) {
			return ProccessDowhileNode(statement,localVar,false);
		}
		return new ReturnType(RType.NONE);
	}
	
	
	
	
	public ReturnType interpretListOfStatements(LinkedList<Optional<StatementNode>> statements, HashMap<String, InterpreterDataType> localVar) throws ParserException {
		LinkedList<BlockNode> deBlocker = awk.actionBlocks;
		ReturnType test = new ReturnType(RType.NONE);

		boolean termanate = false;
			for(int j = 0; j < statements.size(); j++) {
				test = processStatement(localVar,statements.get(j).get());
				if(test.getRType() != RType.NONE)
					return test;
		}
		return test;
	}
	
	
	

	public HashMap<String, InterpreterDataType> interpretActionBlock() throws ParserException {
		LinkedList<BlockNode> deBlocker = awk.actionBlocks;

		HashMap<String, InterpreterDataType> localVar =  new HashMap<String, InterpreterDataType>();
		boolean termanate = false;
		for(int i = 0; i<deBlocker.size();i++) {
			LinkedList<Optional<StatementNode>> statements = deBlocker.get(i).getStatements();
				ReturnType test = interpretListOfStatements(statements,localVar);
				System.out.println("\n\n WE ARE DONE "+localVar.get("result").getDataType());
				System.out.println("\n\n"+test.toString());// Full Recursion oneLine.......!!
			// 1: Check AssigmentNode : make sure that the target is a variable (variableReferenceNode or OperationNode with type $DOLLAR). 
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// so we creating builtins for that Call node
	
	public void interpreterFunctions(){
		LinkedList<FunctionDefinitionNode> functions = awk.functionDefinitions;
		HashMap<String, InterpreterDataType> data = new HashMap<String, InterpreterDataType>();
		InterpreterDataType arrayData;
		InterpreterDataType dater;
		String[] builtin = {"print", "printf", "getline", "next","index", "length", "split", "substr", "tolower", "toupper","gsub", "match", "sub"};
		String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
		System.out.print("\n\n\nThese are user funcs  "+functions);
		// looping through hardCoded builtIns.
		for(int i = 0; i<builtin.length; i++) {
			if(builtin[i].equals("printf")) {
				arrayData = new InterpreterArrayDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],true,
					    (Function<HashMap<String, InterpreterDataType>, String>) print -> {
					        StringBuilder builder = new StringBuilder();
					        StringBuilder subBuilder = new StringBuilder();
					        InterpreterDataType paramsMap = print.get("0");
					        String printS = paramsMap.getVariable("0").getDataType();
					        paramsMap.removeVariable("0");
					        Object[] paramArray = paramsMap.toArray();
					        for(int j=0; j<paramArray.length;j++) {
					        	if (Global.containsKey(paramArray[j])){
					        		paramArray[j] = Global.get(paramArray[j]).getDataType();
					        	}
					        	 
					        }

					        String formattedString = String.format(printS, paramArray);
					        System.out.print(formattedString);
					        builder.append(formattedString);
							awkProgramOut.get("prints").addPrint(builder.toString().trim());
					        return builder.toString().trim(); // Returns the concatenated dataTypes
					    }
					));
			}
			
			if(builtin[i].equals("print")) {
				arrayData = new InterpreterArrayDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],true,
					    (Function<HashMap<String, InterpreterDataType>, String>) print -> {
					        StringBuilder builder = new StringBuilder();
					        StringBuilder subBuilder = new StringBuilder();
					        InterpreterDataType paramsMap = print.get("0");
					       // String printS = print.get("0").getVariable("0").getDataType();
					        Object[] paramArray = paramsMap.toArray();
					       
            
					       
					        for(int j=0; j<paramArray.length;j++) {
					        	subBuilder.append(" %s");
					        	if (Global.containsKey(paramArray[j])){
					        		paramArray[j] = Global.get(paramArray[j]).getDataType();
					        	}
					        	
					        }
					        String formattedString = String.format(subBuilder.toString(), paramArray);
					        System.out.print(formattedString);
					        builder.append(formattedString);

							AwkProgramOut printer =  new AwkProgramOut();
							printer.addPrint(builder.toString().trim());
						    awkProgramOut.get("prints").addPrint(builder.toString().trim());

					        return builder.toString().trim(); // Returns the concatenated dataTypes
					    }
					));
			}
			
			
			
			
			
			
			
			
			if(builtin[i].equals("next") || builtin[i].equals("getline")) {
				arrayData = new InterpreterArrayDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],false,
					    (Function<HashMap<String, InterpreterDataType>, String>) print -> {
							return String.valueOf(handler.SplitAndAssign()); // Returns the concatenated dataTypes
					    }
					));
			}
			
			
			
			if(builtin[i].equals("length")) {
				arrayData = new InterpreterArrayDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],false,
					    (Function<HashMap<String, InterpreterDataType>, String>) length -> {
					        StringBuilder builder = new StringBuilder();
					        String field =  Global.get(length.get("0").getDataType()).getDataType();
					        builder.append(field.length());
					        return builder.toString(); // Returns the concatenated dataTypes
					    }
					));
			}
			
			if(builtin[i].equals("split")) {
				arrayData = new InterpreterDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],false,
					    (Function<HashMap<String, InterpreterDataType>, String>) split -> {
					        StringBuilder builder = new StringBuilder();
					       
					         String[] splitArray = split.get("split").getDataType().split("\\|");
					         builder.append(Arrays.toString(splitArray));
					         System.out.print(builder.toString());
					         return builder.toString();
					    } 
					));
			}
			
			if(builtin[i].equals("substr")) {
				arrayData = new InterpreterDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],false,
					    (Function<HashMap<String, InterpreterDataType>, String>) split -> {
					        StringBuilder builder = new StringBuilder();

					        //0 woukd be feild Ex: $3 and 2 would be in start and 3 woud be int stop
					        String field =  Global.get(split.get("0").getDataType()).getDataType();
					        String start = split.get("1").getDataType();
					        String end 	 = split.get("2").getDataType();
					        builder.append(field.substring(3));
					 
					         return builder.toString();
					    }
					));
				

			}
			
			if(builtin[i].equals("tolower")) {
				arrayData = new InterpreterDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],false,
					    (Function<HashMap<String, InterpreterDataType>, String>) split -> {
					        StringBuilder builder = new StringBuilder();
					        //0 woukd be feild Ex: $3 and 2 would be in start and 3 woud be int stop
					        String field =  Global.get(split.get("0").getDataType()).getDataType();
					        builder.append(field.toLowerCase());
					 
					         return builder.toString();
					    }
					));
				
			}
			
			
			if(builtin[i].equals("toupper")) {
				arrayData = new InterpreterDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],false,
					    (Function<HashMap<String, InterpreterDataType>, String>) split -> {
					        StringBuilder builder = new StringBuilder();
					        //0 woukd be feild Ex: $3 and 2 would be in start and 3 woud be int stop
					        String field =  Global.get(split.get("0").getDataType()).getDataType();
					        builder.append(field.toUpperCase());
					         return builder.toString();
					    }
					));
				
			}
			
			
			if(builtin[i].equals("index")) {
				arrayData = new InterpreterDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],false,
					    (Function<HashMap<String, InterpreterDataType>, String>) split -> {
					        StringBuilder builder = new StringBuilder();
					        //0 woukd be feild Ex: $3 and 2 would be in start and 3 woud be int stop
					        String field =  Global.get(split.get("0").getDataType()).getDataType();
					        String index = split.get("1").getDataType();
					        builder.append(field.indexOf(index));
					         return builder.toString();
					    }
					));
				
			}

			if(builtin[i].equals("gsub")) {
				arrayData = new InterpreterDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],false,
					    (Function<HashMap<String, InterpreterDataType>, String>) split -> {
					        StringBuilder builder = new StringBuilder();
					        //0 woukd be feild Ex: $3 and 2 would be in start and 3 woud be int stop
					        String regex = split.get("0").getDataType();
					        String replace = split.get("1").getDataType();
					        String field =  handler.getLine();
					        if(split.size()==3) {
					        	field = Global.get(split.get("2").getDataType()).getDataType();
					        	 System.out.print("\n This is field"+field);
					        }
					        

					        if(regex.equals("/[0-9]+/")) {
					        	for(int n = 0; n< numbers.length; n++) {
					        		field = field.replaceAll(numbers[n], replace) ;
					        	}
					        	 System.out.print("\n This is Builder"+builder.toString());
					        	builder.append(field);
					        	return builder.toString();
					        }
					        field=  field.replaceAll(regex, replace);
					        builder.append(field);
					        System.out.printf("\n This is Builder"+builder.toString());
					         return builder.toString();
					    }
					));
				
			}
			
			if(builtin[i].equals("match")) {
				arrayData = new InterpreterDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],false,
					    (Function<HashMap<String, InterpreterDataType>, String>) split -> {
					        StringBuilder builder = new StringBuilder();
					  
					        //0 woukd be feild Ex: $3 and 2 would be in start and 3 woud be int stop
					        String field =  Global.get(split.get("0").getDataType()).getDataType();
					        String regex = split.get("1").getDataType();
					        boolean match = false;
	
					        
					        if(regex.equals("/[0-9]+/")) {
					        	for(int n = 0; n< numbers.length; n++) {
					        		
					        		match = field.contains(numbers[n]);
					        		if(match) {
							        	builder.append(match);
							        	return builder.toString();
					        		}
					      
					        	}

					        }
					        
					        match = field.contains(regex);
					        builder.append(match);
					        
					         return builder.toString();
					    }
					));
				
			}
			
			
			
			
			if(builtin[i].equals("sub")) {
				arrayData = new InterpreterDataType();
				data.put(builtin[i], arrayData);
				functionCall.put(builtin[i], new BuiltInFunctionDefinitionNode(builtin[i],false,
					    (Function<HashMap<String, InterpreterDataType>, String>) split -> {
					        StringBuilder builder = new StringBuilder();
					        //0 woukd be feild Ex: $3 and 2 would be in start and 3 woud be int stop
					        String regex = split.get("0").getDataType();
					        String replace = split.get("1").getDataType();
					        String field =  handler.getLine();
					        if(split.size()==3) {
					        	field = Global.get(split.get("2").getDataType()).getDataType();
					        	 System.out.print("\n This is field"+field);
					        }

					        if(regex.equals("/[0-9]+/")) {
					        	for(int n = 0; n< numbers.length; n++) {
					        		field = field.replaceFirst(numbers[n], replace) ;
					        	}
					        	 System.out.print("\n This is Builder"+builder.toString());
					        	builder.append(field);
					        	return builder.toString();
					        }
					        field=  field.replaceFirst(regex, replace);
					        builder.append(field);
					        System.out.printf("\n This is Builder"+builder.toString());
					         return builder.toString();
					    }
					));
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		// adding user made functions;
		for(int i = 0; i<functions.size(); i++) {
			functionCall.put(functions.get(i).getValue(),functions.get(i));
		}
		
	}
	
	
	
	
	
	
	
	
class LineManager {
	
	private List<String> lines;
	private int currentLine =0;
	
	public LineManager(List<String> lines) {
		this.lines = lines;
	
	}
	
		// Handling global vars
	 public boolean SplitAndAssign() {
		 StringBuilder builder = new StringBuilder();
	        // Fetch the next line and increment index
		 
		 if(currentLine>lines.size()-1) {
			 Global.put("NR", new InterpreterDataType());
		 		Global.put("FNR", new InterpreterDataType());
		 		return false;
		 }
		 
		 	String nextLine = lines.get(currentLine++);
	        // getting what we are Gonna be Spliting by EX:  " FS = " , " "

	        String[] fields = nextLine.split(Global.get("FS").getDataType());
	        // Assign to $0, $1, etc.
	        for(int i = 0; i < fields.length; i++) {
	        	Global.put("$"+String.valueOf(i), new InterpreterDataType(fields[i]));
	        }
	        builder.append(fields.length);
	      //set NF(Number of fields) 	 
	        Global.put("NF", new InterpreterDataType(builder.toString()));
	        
	        System.out.print("\n"+currentLine);
	return true;
}
	 
	
	public String getLine() {
		return lines.get(currentLine);
	}
	
	


}




	
}
