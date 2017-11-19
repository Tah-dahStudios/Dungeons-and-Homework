package models;

import java.io.Serializable;

/**
 * Created by Alex Chen on 2017-11-18.
 */

public class Item implements Serializable {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
