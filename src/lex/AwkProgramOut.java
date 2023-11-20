package lex;

import java.util.HashMap;
import java.util.LinkedList;

public class AwkProgramOut {
    private LinkedList<String> awkPrints = new LinkedList<String>();
    public AwkProgramOut() {
    }

public void ExecutePrints(int i) {
        while (hasNextPrint() && i < getPrintsSize()) {
            System.out.println(awkPrints.getFirst());
            awkPrints.removeFirst();
            System.out.println(awkPrints.get(i));
        }
}

public void addPrint(String value) {
    awkPrints.add(value);
}

public int getPrintsSize() {
    return awkPrints.size();
}

public boolean hasNextPrint() {
        if(getPrintsSize() > 0)
            return true;
    return false;
}

public String getPrint(int i) {
    return awkPrints.get(i);
    }





}
