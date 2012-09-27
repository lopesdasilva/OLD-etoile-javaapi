/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author rubenpaixao
 */
public class VoteException extends Exception{
    int a;
    
    public VoteException(){
        
    }
    
    public String toString(){
        return "Already voted.";
    }
}
    

