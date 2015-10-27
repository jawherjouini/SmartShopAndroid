package tn.esprit.autoidsys.smartshop.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Fares Ben Hamouda on 22/10/2015.
 */
public class Category implements Serializable {

    public List<Product> products;
    public String name ;
    public String urlPic;

    public Category() {
    }

    public Category(List<Product> products, String name) {
        this.products = products;
        this.name = name;
    }

    public String getUrlPic() {
        return urlPic;
    }

    public void setUrlPic(String urlPic) {
        this.urlPic = urlPic;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
