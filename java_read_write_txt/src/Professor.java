/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeff_2
 */
public class Professor {
    private String firstName, lastName, department;
    
    public Professor(String fn, String ln, String dpt){
        firstName = fn;
        lastName = ln;
        department =dpt;
    }
    
    public String toString(){
        return firstName+" "+lastName+"\t"+department+"\t\n";
    }
}
