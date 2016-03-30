/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeff_2
 */
public class Customers {
    private String firstName, lastName;
    private double quantity = 0;
    
    public Customers(String fn, String ln){
        firstName = fn;
        lastName = ln;
    }
    
    public double getQuantity(){
        return quantity;
    }
    public void setQuantity(double c){
        quantity += c;
    }
    public String toString(){
        return firstName +" "+ lastName;
    }
}
