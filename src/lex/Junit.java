package lex;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;


public class Junit {
	
	
	public String filePath = "src";
	public String fileName = "sum.awk";
	public Path filePathd = Paths.get(filePath, fileName);
	
	 
	    public void testFullLine1() {
	        lexer lexer = new lexer("$0 = tolower($0 6)gfgdgfgdg", 0, 0);
	        lexer.Lex();
	        
	        // Check the tokens produced by the lexer
	        
	        
	        assertEquals(Token.tokenType.SINGLE_CHAR_SYMBOL, lexer.token.get(0).gettype());
	        assertEquals(Token.tokenType.NUMBERS, lexer.token.get(1).gettype());
	        assertEquals(Token.tokenType.SINGLE_CHAR_SYMBOL, lexer.token.get(2).gettype());

	        // Add more assertions for other tokens
	     
	        assertEquals(Token.tokenType.SINGLE_CHAR_SYMBOL, lexer.token.get(4).gettype());
	        assertEquals(Token.tokenType.SINGLE_CHAR_SYMBOL, lexer.token.get(5).gettype());
	        assertEquals(Token.tokenType.NUMBERS, lexer.token.get(6).gettype());
	        assertEquals(Token.tokenType.NUMBERS, lexer.token.get(7).gettype());
	        assertEquals(Token.tokenType.SINGLE_CHAR_SYMBOL, lexer.token.get(8).gettype());
	        assertEquals(Token.tokenType.WORDS, lexer.token.get(9).gettype());
	        
	        
	 }
	
	 
	    public void testParser1() throws ParserException {
	        lexer lexer = new lexer("BEGIN {\r\n"
	        		+ "    FS = \",\"\r\n"
	        		+ "    OFS = \",\"\r\n"
	        		+ "    print \"Employee ID,Name,Salary,Department\"\r\n"
	        		+ "}\r\n"
	        		+ "\r\n"
	        		+ "\r\n"
	        		+ "function add_employee(emp_name, emp_salary, emp_department) {\r\n"
	        		+ "    department_employees[emp_department] = department_employees[emp_department] emp_name \",\"\r\n"
	        		+ "    department_total_salary[emp_department] += emp_salary\r\n"
	        		+ "    department_employee_count[emp_department]++\r\n"
	        		+ "}", 0, 0);
	        lexer.Lex();
	        Parser parser = new Parser(lexer.token);
	       ProgramNode test = new ProgramNode();
	        
	        // Check the tokens produced by the lexer
	        assertEquals(parser.acceptSeparators(),false);
	        assertEquals(parser.parseAction(test), true);
	        assertEquals(parser.parseAction(test), false);
	        assertEquals(parser.acceptSeparators(),true);
	        assertEquals(parser.acceptSeparators(),true);
	        assertEquals(parser.acceptSeparators(),true);
	        assertEquals(parser.parseFunction(test),true);
	        
	        //assertEquals(parser.parseFunction(test), true);
	 }

	        
	        

public void testParser2() throws ParserException {
    lexer lexer = new lexer("{\r\n"
    		+ "    emp_id = $1\r\n"
    		+ "    emp_name = $2\r\n"
    		+ "    emp_salary = $3\r\n"
    		+ "    emp_department = $4\r\n"
    		+ "\r\n"
    		+ "    add_employee(emp_name, emp_salary, emp_department)\r\n"
    		+ "}\r\n"
    		+ "\r\n"
    		+ "# End of file, print department summaries\r\n"
    		+ "END {\r\n"
    		+ "    for (department in department_employees) {\r\n"
    		+ "        print_department_summary(department)\r\n"
    		+ "    }\r\n"
    		+ "   { }\r\n"
    		+ "   \r\n"
    		+ "   \r\n"
    		+ "   \r\n"
    		+ "   \r\n"
    		+ "   \r\n"
    		+ "   \r\n"
    		+ "    water\r\n"
    		+ "}\r\n", 0, 0);
    lexer.Lex();
    Parser parser = new Parser(lexer.token);
   ProgramNode test = new ProgramNode();
    
    // Check the tokens produced by the lexer
    assertEquals(parser.acceptSeparators(),false);
    assertEquals(parser.acceptSeparators(),false);
    assertEquals(parser.parseAction(test), true);
    assertEquals(parser.acceptSeparators(),true);
    
    //assertEquals(parser.parseFunction(test), true);

	        
	        
	        
	        
	 }
	
	

// (9^5)^(5^9)^(99-35)

//45-99^




public void testOp1() throws Exception {
    lexer lexer1 = new lexer(
    		"{  {  \r\n"
    		+ "\r\n"
    		+ "if (i<=30)\r\n"
    		+ "\r\n"
    		+ "x = abs;\r\n"
    		+ "\r\n"
    		+ "}", 0, 0);
    lexer1.Lex();
    Parser parser1 = new Parser(lexer1.token);
    System.out.print("\n\nthis is the current tokens: " + lexer1.token);
    System.out.print("\n\n here is parseOp: " + parser1.parse().toString());
    ProgramNode test1 = new ProgramNode();
    // You can add assertions here for expected values
  //assertEquals(parser1.parseOperation().get().getValue(),true);
}
 

public void testOp2() throws ParserException {
    lexer lexer2 = new lexer("4/(h^g)+ 8 * 2^7", 0, 0);
    lexer2.Lex();
    Parser parser2 = new Parser(lexer2.token);
    System.out.print("\n\nthis is the current tokens: " + lexer2.token);
    System.out.print("\n\n here is parseOp: " + parser2.parseOperation().get().getValue());
    ProgramNode test2 = new ProgramNode();
    // You can add assertions here for expected values
}


public void testOp3() throws ParserException {
    lexer lexer3 = new lexer("eddy = (2^55)", 0, 0);
    lexer3.Lex();
    Parser parser3 = new Parser(lexer3.token);
    System.out.print("\n\nthis is the current tokens: " + lexer3.token);
    System.out.print("\n\n here is parseOp: " + parser3.parseOperation().get().toString());
    ProgramNode test3 = new ProgramNode();
    // You can add assertions here for expected values
}

 
public void testPrints() throws ParserException, IOException {
    lexer lexer3 = new lexer("2^3^4", 0, 0);
    lexer3.Lex();
    Parser parser3 = new Parser(lexer3.token);
    System.out.print("\n\nthis is the current tokens: " + lexer3.token);
    System.out.print("\n\n here is parseOp: " + parser3.parseOperation().get().getValue());
    ProgramNode test3 = new ProgramNode();
    Interpreter  terp = new Interpreter(test3,filePathd);
    HashMap<String, InterpreterDataType> data = new HashMap<String, InterpreterDataType>();
    terp.interpreterFunctions();
    data.put("0",new InterpreterArrayDataType());
    data.get("0").addVariable("0", new InterpreterDataType("\nHello world: %s %s and Feild: %s"));
    data.get("0").addVariable("1", new InterpreterDataType("The univerese of soda"));
    data.get("0").addVariable("2", new InterpreterDataType("Foo+Bar"));
    data.get("0").addVariable("3", new InterpreterDataType("d"));
    data.get("0").addVariable("4", new InterpreterDataType("$2"));
    terp.handler.SplitAndAssign();
    System.out.print("\n Print Function ");
    assertEquals("Hello world: %s %s and Feild: %s The univerese of soda Foo+Bar d 12345",terp.functionCall.get("print").execute(data) );
    data.get("0").removeVariable("2");
    assertEquals("Hello world: %s %s and Feild: %s The univerese of soda d 12345",terp.functionCall.get("print").execute(data) );
    System.out.print("\n PrintF Function ");
    assertEquals("Hello world: The univerese of soda d and Feild: 12345",terp.functionCall.get("printf").execute(data) );
    
    
    
    
}

 
 
public void testGetNext() throws ParserException, IOException {
    lexer lexer3 = new lexer("2^3^4", 0, 0);
    lexer3.Lex();
    Parser parser3 = new Parser(lexer3.token);
    System.out.print("\n\nthis is the current tokens: " + lexer3.token);
    System.out.print("\n\n here is parseOp: " + parser3.parseOperation().get().getValue());
    ProgramNode test3 = new ProgramNode();
    Interpreter  terp = new Interpreter(test3,filePathd);
    HashMap<String, InterpreterDataType> data = new HashMap<String, InterpreterDataType>();
    terp.interpreterFunctions();
    data.put("0",new InterpreterArrayDataType());
    data.get("0").addVariable("0", new InterpreterDataType("\nHello world: %s %s and Feild: %s"));
    data.get("0").addVariable("1", new InterpreterDataType("The univerese of soda"));
    data.get("0").addVariable("2", new InterpreterDataType("Foo+Bar"));
    data.get("0").addVariable("3", new InterpreterDataType("d"));
    data.get("0").addVariable("4", new InterpreterDataType("$2"));
    terp.handler.SplitAndAssign();
    System.out.print("\n Getline&Next Function ");
    assertEquals("true",terp.functionCall.get("getline").execute(data));
    assertEquals("true",terp.functionCall.get("getline").execute(data));
    assertEquals("true",terp.functionCall.get("next").execute(data));
    assertEquals("true",terp.functionCall.get("getline").execute(data));
    assertEquals("false",terp.functionCall.get("getline").execute(data));
    assertEquals("false",terp.functionCall.get("next").execute(data));
    
    
    
    
}
 
 
 
 
public void testRegex() throws ParserException, IOException {
    lexer lexer3 = new lexer("2^3^4", 0, 0);
    lexer3.Lex();
    Parser parser3 = new Parser(lexer3.token);
    System.out.print("\n\nthis is the current tokens: " + lexer3.token);
    System.out.print("\n\n here is parseOp: " + parser3.parseOperation().get().getValue());
    ProgramNode test3 = new ProgramNode();
    Interpreter  terp = new Interpreter(test3,filePathd);
    HashMap<String, InterpreterDataType> data = new HashMap<String, InterpreterDataType>();
    terp.interpreterFunctions();
    terp.handler.SplitAndAssign();
    
    data.put("0",new InterpreterDataType("/[0-9]+/"));
    data.put("1",new InterpreterDataType("#"));
    data.put("2",new InterpreterDataType("$4"));
    
    System.out.print("\n Getline&Next Function ");
    assertEquals(" abc###xyz",terp.functionCall.get("gsub").execute(data));
    data.replace("0", new InterpreterDataType("xyz"));
    assertEquals(" abc123#",terp.functionCall.get("gsub").execute(data));
    data.remove("2");
    assertEquals("goodbye, GOODBYE, 98765, 43210, xydoez789abc, barbaz, jane.doe@example.net",terp.functionCall.get("gsub").execute(data));
    data.replace("0", new InterpreterDataType("$6"));
    data.replace("1", new InterpreterDataType("com"));
    assertEquals("true",terp.functionCall.get("match").execute(data));
    data.remove("2");
    assertEquals("true",terp.functionCall.get("match").execute(data));
    
    data.put("1",new InterpreterDataType("SAD"));
    data.replace("0", new InterpreterDataType("doe"));
    assertEquals("goodbye, GOODBYE, 98765, 43210, xySADz789abc, barbaz, jane.doe@example.net",terp.functionCall.get("sub").execute(data));
}
 
 
 
 
 
public void testString() throws ParserException, IOException {
    lexer lexer3 = new lexer("2^3^4", 0, 0);
    lexer3.Lex();
    Parser parser3 = new Parser(lexer3.token);
    System.out.print("\n\nthis is the current tokens: " + lexer3.token);
    System.out.print("\n\n here is parseOp: " + parser3.parseOperation().get().getValue());
    ProgramNode test3 = new ProgramNode();
    Interpreter  terp = new Interpreter(test3,filePathd);
    HashMap<String, InterpreterDataType> data = new HashMap<String, InterpreterDataType>();
    terp.interpreterFunctions();
    terp.handler.SplitAndAssign();
    
    data.put("0",new InterpreterDataType("/[0-9]+/"));
    data.put("1",new InterpreterDataType("#"));
    data.put("2",new InterpreterDataType("$4"));
    
    System.out.print("\n Getline&Next Function ");
    assertEquals(" abc###xyz",terp.functionCall.get("gsub").execute(data));
    data.replace("0", new InterpreterDataType("xyz"));
    assertEquals(" abc123#",terp.functionCall.get("gsub").execute(data));
    data.remove("2");
    assertEquals("goodbye, GOODBYE, 98765, 43210, xydoez789abc, barbaz, jane.doe@example.net",terp.functionCall.get("gsub").execute(data));
    data.replace("0", new InterpreterDataType("$6"));
    data.replace("1", new InterpreterDataType("com"));
    assertEquals("true",terp.functionCall.get("match").execute(data));
    data.remove("2");
    assertEquals("true",terp.functionCall.get("match").execute(data));
    
    data.put("1",new InterpreterDataType("SAD"));
    data.replace("0", new InterpreterDataType("doe"));
    assertEquals("goodbye, GOODBYE, 98765, 43210, xySADz789abc, barbaz, jane.doe@example.net",terp.functionCall.get("sub").execute(data));
}
 
 
@Test
public void testAssigns() throws ParserException, IOException {
	lexer lexer3 = new lexer(" eddy = ((7<44)||(2>7))", 0, 0);
   lexer3.Lex();
   Parser parser3 = new Parser(lexer3.token);
   System.out.print("\n\nthis is the current tokens: " + lexer3.token);
   System.out.print("\n\n here is parseOp: " + parser3.parseOperation().get().getValue());
   ProgramNode test3 = new ProgramNode();
   Interpreter  terp = new Interpreter(test3,filePathd);
   HashMap<String, InterpreterDataType> data = new HashMap<String, InterpreterDataType>();
   terp.interpreterFunctions();
   terp.handler.SplitAndAssign();
   terp.interpretActionBlock();
   System.out.print("\n Getline&Next Function ");
   assertEquals(Token.tokenType.SINGLE_CHAR_SYMBOL, lexer3.token.get(0).gettype());
}
 
 
 
 
 


}