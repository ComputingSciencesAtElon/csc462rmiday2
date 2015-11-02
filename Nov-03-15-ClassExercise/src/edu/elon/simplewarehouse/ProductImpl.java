/**
 @version 1.00 2015-11-03
 @author Cay Horstmann
 */

package edu.elon.simplewarehouse;

import java.rmi.server.*;

/**
 * This is the implementation class for the remote product objects.
 */
public class ProductImpl implements Product {
  private int ageHigh;

  private int ageLow;

  private String hobby;

  private String name;

  private int sex;

  public ProductImpl(String n, int s, int age1, int age2, String h) {
    name = n;
    ageLow = age1;
    ageHigh = age2;
    sex = s;
    hobby = h;
  }

  public String getDescription() {
    return "I am a " + name + ". Buy me!";
  }

  public boolean match(Customer c) {
    if (c.getAge() < ageLow || c.getAge() > ageHigh)
      return false;
    if (!c.hasHobby(hobby))
      return false;
    if ((sex & c.getSex()) == 0)
      return false;
    return true;
  }
}
