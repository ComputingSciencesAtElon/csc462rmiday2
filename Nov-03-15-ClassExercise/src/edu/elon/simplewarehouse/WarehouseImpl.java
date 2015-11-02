/**
 @version 1.10 2015-11-03
 @author Cay Horstmann
 */

package edu.elon.simplewarehouse;

import java.io.*;
import java.util.*;

public class WarehouseImpl implements Warehouse {
  private ArrayList<ProductImpl> products;

  /**
   * Constructs a warehouse implementation.
   */
  public WarehouseImpl() {
    products = new ArrayList<ProductImpl>();
    add(new ProductImpl("Core Java Book", 0, 200, Product.BOTH, "Computers"));
    add(new ProductImpl("Blackwell Toaster", Product.BOTH, 18, 200, "Household"));
    add(new ProductImpl("ZapXpress Microwave Oven", Product.BOTH, 18, 200,
      "Household"));
    add(new ProductImpl("DirtDigger Steam Shovel", Product.MALE, 20, 60,
      "Gardening"));
    add(new ProductImpl("U238 Weed Killer", Product.BOTH, 20, 200, "Gardening"));
    add(new ProductImpl("Persistent Java Fragrance", Product.FEMALE, 15, 45,
      "Beauty"));
    add(new ProductImpl("Rabid Rodent Computer Mouse", Product.BOTH, 6, 40,
      "Computers"));
    add(new ProductImpl("My first Espresso Maker", Product.FEMALE, 6, 10,
      "Household"));
    add(new ProductImpl("JavaJungle Eau de Cologne", Product.MALE, 15, 45,
      "Beauty"));
    add(new ProductImpl("FireWire Espresso Maker", Product.BOTH, 20, 50,
      "Computers"));
    add(new ProductImpl("Learn Bad Java Habits in 21 Days Book", Product.BOTH,
      20, 200, "Computers"));
  }

  public void add(ProductImpl p) {

    products.add(p);

  }

  public ArrayList<Product> find(Customer c) {

    ArrayList<Product> result = new ArrayList<Product>();
    // add all matching products
    for (ProductImpl p : products) {
      if (p.match(c))
        result.add(p);
    }
    // add the product that is a good match for everyone, a
    // copy of Core Java
    if (!result.contains(products.get(0)))
      result.add(products.get(0));

    // we reset c just to show that c is a copy of the client
    // object

    return result;

  }
}
