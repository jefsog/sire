
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeff_2
 */
public class User {
    public static void main(String[] args){
        Database db; //= new Database();
        try{
            db = new Database();
            db.createTable();
            //db.insertData();
            /*
            for(String s: db.customerBills()){
                System.out.println(s);
            }
            save(db.customerBills());
                    */
            db.close();
            
        }catch(Exception ex){
            ex.getMessage();
        }finally{
            //db.close();
        }
        
        
        
        
    }
    
    
    public static void save(ArrayList<String> ls){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("customerBills.txt"));
            for(String s : ls){
                bw.write(s);
                bw.newLine();
            }
            bw.close();
        }catch(Exception ex){
            ex.getMessage();
        }
            
        
                    
    }
}
