/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeff_2
 */
public class Products {
    private String productName;
    int productSellingAmount = 0;
    
    public Products(String name){
        productName = name;
    }
    
    public int getProductSellingAmount(){
        return productSellingAmount;
    }
    
    public void setProductSellingAmount(int value){
        productSellingAmount += value;
    } 
    
    public String toString(){
        return productName;
    }
}
