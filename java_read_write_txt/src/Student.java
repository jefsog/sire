/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeff_2
 */
public class Student {
     private String firstName, lastName, degree;
    
    public Student(String fn, String ln, String dg){
        firstName = fn;
        lastName = ln;
        degree =dg;
    }
    public String toString(){
        return firstName+" "+lastName+"\t"+degree+"\t\n";
    }
}
