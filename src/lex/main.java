package lex;
import java.util.HashMap;
import java.util.Optional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class main {

	 public static void main(String[] args) throws Exception {
	        // Define the path to the "count.awk" file in the "src" directory
	        String srcDirectoryPath = "src";
	        String awkFileName = "count.awk";
	        Path awkFilePath = Paths.get(srcDirectoryPath, awkFileName);
	        
	        
	        
	        String filePath = "src";
	        String fileName = "sum.awk";
	        Path filePathd = Paths.get(filePath, fileName);

	        try {
	            // Read the content of "count.awk"
	            String content = new String(Files.readAllBytes(awkFilePath));
	           // System.out.println("Content of " + awkFileName + ":\n" + content);
	            
	            
	            lexer lex = new lexer(content,1,0);
	           lex.Lex();
	           Parser parser = new Parser(lex.token);
	           System.out.print("\n\nthis is the curre tokens: "+lex.token);
	          
	           ProgramNode test = parser.parse();
	           //System.out.print("\n\n here is parseopper"+test);
	           Interpreter  terp = new Interpreter(test,filePathd);
	           HashMap<String, InterpreterDataType> data = new HashMap<String, InterpreterDataType>();
	           terp.interpreterFunctions();
	           
	           
	           terp.handler.SplitAndAssign();
	           
	           terp.interpretActionBlock();
				HashMap<String, AwkProgramOut> terper = terp.awkProgramOut;
	           System.out.println(" n");
	           
//	            
//	           data.put("print",new InterpreterArrayDataType());
//	           data.get("print").addVariable("These.balls", new InterpreterDataType("String"));
	           

	           

	           
//	           System.out.print("\n\n"+data.get("split").getDataType().toString());
	           //System.out.print("\n\n\n\n This is Global feilds"+ terp.Global.toString());
	           //System.out.print("\n this is the lamba func: "+terp.functionCall.get("getline").execute(data));
	         
	           try {
				//System.out.print("\n\n This is Main program node"+ parser.parse().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	          
	           System.out.print("\n"+lex.token +"\n");
	          
	          
	            
	            
	            
	            
	            
	            
	            
	            
	         
            //System.out.print("\n This is at tokkkkkk \n"+parser.MatchAndRemove(Token.tokenType.BEGIN));
            //System.out.print("\n This is at tokkkkkk \n"+ parser.peek(0));
	            
	          
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	     // You can continue with your lexer code here
	
	    }
	
	
	
	
	
	
	
	
}
