/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeff_2
 */

public class assignment4_jianfengsong {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String fileName = "src/Sales.txt";
        DataProcessing dp = new DataProcessing(fileName);
        dp.scanner();
        dp.print();
        
    }   
    
}
