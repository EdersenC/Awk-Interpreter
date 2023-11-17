package lex;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Optional;

public class TokenManaglinked{
	
	
	
	
private LinkedList<Token> Tokens = new LinkedList<Token>();
public int PrivateMember;



public TokenManaglinked(LinkedList<Token> Tokens,int privateMember) {
		
	
		this.Tokens = Tokens;
		//System.out.print("\n This is tokens \n "+ Tokens);
		this.PrivateMember = privateMember;
		
	
}


public Optional<Token> peek(int j){
	int overPeek = getSize();
	//System.out.print("\n do we have tokens in MatchandRemove: "+MoreTokens()+"\n");
	if (j < overPeek&&MoreTokens()) {
		//System.out.print("Here at peek Token");
      return Optional.of(this.Tokens.get(j));
    }
	else {return Optional.empty();}

}
	

public int getSize() {
return this.Tokens.size();
}

 
public boolean MoreTokens() {
	
	
	if (this.Tokens.isEmpty()==true) {
		
		return false;
	}else return true;
	
	
	
	
} 


public String toString() {
	return this.Tokens.toString();
}


public Optional<Token> MatchAndRemove(Token.tokenType t){
	
	Optional<Token> test = peek(0);
	Optional<Token> curr = Optional.empty();
	
	//System.out.print("\n this is test at matach and remove:"+test+"\n");
	if (test.isPresent()) {
		if(test.get().gettype() == t) {
			curr = Optional.of(this.Tokens.poll());
		}
		
		System.out.print("\n At MatchAndRemove:"+curr+"\n");
		return curr;
		
	}else {return curr;}
	
	
	
}





	
	
}
