/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment6_jianfengsong;

/**
 *
 * @author Jeff_2
 */

import Lab6.AccountLockedException;
import java.util.ArrayList;
public class Assignment6_JianfengSong {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Database db; //= new Database();
        try{   
        
            db = new Database();
            db.createTable();
            ArrayList<String> strList = db.readFile("Transactions.txt");
            db.initializeBankAccount(strList);
           db.updateBankAccout(strList);
           for (String s : db.updatedBankAccount()){
               System.out.println(s);
           }
             db.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
           
        }
        
    }
    
}
