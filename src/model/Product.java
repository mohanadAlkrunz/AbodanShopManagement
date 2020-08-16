/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Product {


    int id;
    String name;
    double originalPrice;
    double salePrice;
    int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


public static String invoice ="";
public void printInvoice() {
    String saleprice="";
    String quantity="";
//    System.out.println(String.format("%2s %2s %20s %2s %2s ,%2s,%2s,%2s%2s", this.getId(), "|", this.getName(), "|", this.getSalePrice(),"|",this.getQuantity(),"|",this.getQuantity()*this.getSalePrice()));
//    return String.format("%30s %25s %10.2f %25s %10s", this.getId(), "|", this.getSalePrice(), "|", this.getQuantity());
            if(this.salePrice <10){
                saleprice +="  "+this.salePrice;
            }else if(this.salePrice >=10 && this.salePrice<=99){
                saleprice +=" "+this.salePrice;
            }else{
                saleprice+=this.salePrice+"";
            }

             if(this.quantity <10){
                quantity +="  "+this.quantity;
            }else if(this.quantity >=10 && this.quantity<=99){
                quantity +=" "+this.quantity;
            }else{
                quantity+=this.quantity+"";
            }
         invoice+=(String.format("%12s%6s%4s%6s%4s%6s%4s", this.getName(), "|", saleprice,"|",quantity,"|",this.getQuantity()*this.getSalePrice()))+"\n";
}

public static List<Product> buildInvoice(List<Product> list) {
    List<Product> itemList = new ArrayList<>();
    itemList.clear();
    for(int i=0;i<list.size();i++){
        itemList.add(list.get(i));
    }
    return itemList;
}

}
