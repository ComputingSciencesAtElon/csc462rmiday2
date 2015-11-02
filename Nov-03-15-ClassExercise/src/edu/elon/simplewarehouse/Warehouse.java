/**
 @version 1.02 2015-11-03
 @author Cay Horstmann
 */

package edu.elon.simplewarehouse;

import java.util.ArrayList;

/**
 * The remote interface for a warehouse with products.
 */
public interface Warehouse {

  ArrayList<Product> find(Customer c);
}
