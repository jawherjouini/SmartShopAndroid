package tn.esprit.autoidsys.smartshop.models;

import java.io.Serializable;

/**
 * Created by Fares Ben Hamouda on 20/10/2015.
 */
public class Product  implements Serializable {


    String urlPic ;
    String name ;
    String description ;
    Double price ;

    @Override
    public String toString() {
        return "Product{" +
                "urlPic='" + urlPic + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public String getUrlPic() {
        return urlPic;
    }

    public void setUrlPic(String urlPic) {
        this.urlPic = urlPic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product() {
    }

    public Product(String urlPic, Double price, String description, String name) {
        this.urlPic = urlPic;
        this.price = price;
        this.description = description;
        this.name = name;
    }

}
