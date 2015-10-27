package tn.esprit.autoidsys.smartshop.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Fares Ben Hamouda on 22/10/2015.
 */
public class Rayon implements Serializable {

    public List<Category> categories ;
    public int number ;

    public Rayon() {
    }

    public Rayon(List<Category> categories, int number) {
        this.categories = categories;
        this.number = number;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
