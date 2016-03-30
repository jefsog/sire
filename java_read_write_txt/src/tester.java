/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeff_2
 */
import  java.io.*;
import java.util.Scanner;
public class tester {
    public static void main(String[] args){
        String inputFileName = "c:\\javaIO\\people.txt";
        Professor[] pf = new Professor[20];
        int pfCount = 0;
        Student[] st = new Student[20];
        int stCount = 0;
        try{
            FileReader in = new FileReader(inputFileName);
            Scanner scan = new Scanner(in);
            while(scan.hasNext()){
                String category = scan.next();
                String firstName = scan.next();
                String lastName = scan.next();
                String departmentOrDegree = scan.next();
                if(category.equals("Professor")){
                    pf[pfCount] = new Professor(firstName, lastName, departmentOrDegree);
                    pfCount++;
                }else{
                    st[stCount]= new Student(firstName, lastName, departmentOrDegree);
                    stCount++;
                }
            }
        }catch(IOException iox){

        }
        String outPutFileNameProfessor = "c:\\javaIO\\professor.txt";
        
        String outPutFileNameStudent = "c:\\javaIO\\Student.txt";
        
        try{
           FileWriter outProfessor = new FileWriter(outPutFileNameProfessor);
           for (int i = 0; i<pfCount; i++){
            outProfessor.write(pf[i].toString());
            
            }
           outProfessor.close();
        }catch(IOException iox){
            
        }
        
        try{
            BufferedWriter outStudent = new BufferedWriter(new FileWriter(outPutFileNameStudent));
            for(int i = 0; i < stCount; i++){
                outStudent.write(st[i].toString());
                outStudent.newLine();
            }
            outStudent.close();
        }catch(IOException iox){
            
        }
        
    }
}
