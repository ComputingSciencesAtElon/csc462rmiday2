/**
 @version 1.00 2015-11-03
 @author Cay Horstmann
 */

package edu.elon.warehouse;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This is the implementation class for the remote product objects.
 */
public class ProductImpl extends UnicastRemoteObject implements Product {
  private int ageHigh;
  private int ageLow;
  private String hobby;
  private String name;
  private int sex;

  /**
   * Constructs a product implementation
   * 
   * @param n the product name
   * @param s the suggested sex (MALE, FEMALE, or BOTH)
   * @param age1 the lower bound for the suggested age
   * @param age2 the upper bound for the suggested age
   * @param h the hobby matching this product
   */
  public ProductImpl(String n, int s, int age1, int age2, String h)
                                                                   throws RemoteException {
    name = n;
    ageLow = age1;
    ageHigh = age2;
    sex = s;
    hobby = h;
  }

  public String getDescription() throws RemoteException {
    return "I am a " + name + ". Buy me!";
  }

  /**
   * Checks whether this product is a good match for a customer. Note
   * that this method is a local method since it is not part of the
   * Product interface.
   * 
   * @param c the customer to match against this product
   * @return true if this product is appropriate for the customer
   */
  public boolean match(Customer c) {
    if ((c.getAge() < ageLow) || (c.getAge() > ageHigh)) {
      return false;
    }
    if (!c.hasHobby(hobby)) {
      return false;
    }
    if ((sex & c.getSex()) == 0) {
      return false;
    }
    return true;
  }
}
