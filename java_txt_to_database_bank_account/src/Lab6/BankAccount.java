/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6;

/**
 *
 * @author Jeff_2
 */
public class BankAccount {
    private String name;
    private int balance;
    private boolean locked;
    
    public BankAccount(String nm, int blnc){
        name = nm;
        balance =blnc;
        locked = false;
        printBalance();
    }
    
    public void deposit(int amount) throws AccountLockedException{
       if(locked)
           throw (new AccountLockedException("Account has been locked. No transaction can be done. Please unlock account"));
       balance += amount;
       printBalance();
    }
    public void withdrawl(int amount) throws AccountLockedException, InsufficientFundsException{
        if(locked)
           throw (new AccountLockedException("Account has been locked. No transaction can be done. Please unlock account"));
        if(balance < amount)
            throw(new InsufficientFundsException("Insufficient funds. Transaction can not be done."));
        balance -= amount;
        printBalance();
    }
    public void lockAccount(){
        locked = true;
        System.out.println(name + "'account is locked");
    }
    public void unlockAccount(){
        locked = false;
        System.out.println("Account: "+name + " is unlocked");
    }
    public void printBalance(){
        System.out.println(name +"\t"+balance);
    }
}
