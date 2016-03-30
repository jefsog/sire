/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeff_2
 */
import java.util.*;
import java.io.*;

public class DataProcessing {
    private HashMap<Integer, Customers> customers = new HashMap<>();
    private HashMap<Integer, Products> products = new HashMap<>();
    private String fileName;

    public DataProcessing(String fn){
        fileName = fn;
    }

    public void scanner(){
        try{
            FileReader fileIn = new FileReader(fileName);
            Scanner keyIn = new Scanner(fileIn);
            Integer customerNumber, productNumber;
            String firstName, lastName, productName;
            double productPrice;
            int purchasingAmount;
            while (keyIn.hasNext()){
                customerNumber = keyIn.nextInt();
                firstName = keyIn.next();                
                lastName = keyIn.next();
                productNumber = keyIn.nextInt();
                productName = keyIn.next();
                productPrice = keyIn.nextDouble();
                purchasingAmount = keyIn.nextInt();

                if(!customers.containsKey(customerNumber)){
                    customers.put(customerNumber, new Customers(firstName, lastName));                
                }
                customers.get(customerNumber).setQuantity(productPrice*purchasingAmount);
                if(!products.containsKey(productNumber))
                    products.put(productNumber, new Products(productName));
                products.get(productNumber).setProductSellingAmount(purchasingAmount);
            }
            keyIn.close();
        }catch(IOException iox){
            System.out.println(iox.getMessage());
        }
    }

    public void print(){
        System.out.println("CustomerID"+"\t"+"Customer Name"+"\t"+"Consumption Amount");
        for (Integer i : customers.keySet()){
            System.out.println(i+"\t\t"+customers.get(i).toString()+"\t"+customers.get(i).getQuantity());
        }
        System.out.println();
        System.out.println("ProductID"+"\t"+"Product Name"+"\t"+"Quantity of Products Sold");
        for (Integer j : products.keySet()){
            System.out.println(j+"\t\t"+products.get(j).toString()+"\t\t\t"+products.get(j).getProductSellingAmount());
        }
    }
        
}
