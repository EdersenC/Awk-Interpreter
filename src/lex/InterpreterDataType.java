package lex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lex.Token.tokenType;

//IDT
public class InterpreterDataType {

	private String dataType = null;
	
	 
	public InterpreterDataType() {
	}
	
	public InterpreterDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataType() {
		return this.dataType;
	}
	public void  updateVar() {
		
	}
	
	public boolean isEmpty() {
		if(dataType == null)
			return true;
		return false;
	}
		
	public void addVariable(String name, InterpreterDataType value) {
        
    }

    // Method to retrieve a variable from the storage
    public InterpreterDataType getVariable(String name) {
        return null;
    }

    // Method to check if a variable exists in the storage
    public boolean hasVariable(String name) {
    	return false;
    }

    // Method to remove a variable from the storage
    public void removeVariable(String name) {
    	
    }
	
    public Object[] toArray(){
    	return null;
    }
	
	
	
    public int getSize() {
    	return 0;
    }
	
	
	
	
	
}


//IADT
class InterpreterArrayDataType extends InterpreterDataType{
	
	private HashMap<String, InterpreterDataType> variableStorage;

    public InterpreterArrayDataType() {
        // Initialize the HashMap in the constructor
        this.variableStorage = new HashMap<>();
    }
    

    
    
  
    public Object[] toArray() {
        String[] build = new String[variableStorage.size()];
        int i = 0; // Use an index to place items into the array
        for (Map.Entry<String, InterpreterDataType> entry : variableStorage.entrySet()) {

        		build[i++] = entry.getValue().getDataType();
        }
        
        return build;
    }
    
    
    public int getSize() {
    	return variableStorage.size();
    }
    public void addVariable(String name, InterpreterDataType value) {
        variableStorage.put(name, value);
    }

    // Method to retrieve a variable from the storage
    public InterpreterDataType getVariable(String name) {
        return variableStorage.get(name);
    }

    // Method to check if a variable exists in the storage
    public boolean hasVariable(String name) {
        return variableStorage.containsKey(name);
    }

    // Method to remove a variable from the storage
    public void removeVariable(String name) {
        variableStorage.remove(name);
    }
	
	
	
	
	
	
	
	
}