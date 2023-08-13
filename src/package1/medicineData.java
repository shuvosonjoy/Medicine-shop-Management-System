/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package package1;

import java.util.Date;

/**
 *
 * @author shuvo sonjoy
 */
public class medicineData {

    private String medicineId;
    private String brand;
    private String Product;
    private String type;
    private String status;
    private Double price;
    private String image;
    private Date date;

    public medicineData(String medicineId, String brand, String Product, String type, String status, Double price,String image, Date date) {
        this.medicineId = medicineId;
        this.brand = brand;
        this.Product =Product;
        this.type = type;
        this.status = status;
        this.price = price;
        this.image=image;
        this.date = date;
    }
    

    
    public String getMedicineId(){
        return medicineId;
    }
    
    public String getBrand(){
        return brand;
    }
    
    public String getProduct(){
        return Product;
    }
    
    public String getType(){
        return type;
    }
    public String getStatus(){
        return status;
    }
    
    public Double getPrice(){
        return price;
    }
    public String getImage(){
        return image;
    }

    public Date getDate(){
        return date;
    }
}
