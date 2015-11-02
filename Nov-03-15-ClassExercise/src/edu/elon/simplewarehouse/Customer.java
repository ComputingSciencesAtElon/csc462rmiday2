/**
 @version 1.10 2015-11-03
 @author Cay Horstmann
 */

package edu.elon.simplewarehouse;

import java.io.*;

public class Customer implements Serializable {

  private int age;

  private String[] hobbies;

  private int sex;

  public Customer(int theAge, int theSex, String[] theHobbies) {
    age = theAge;
    sex = theSex;
    hobbies = theHobbies;
  }

  public int getAge() {
    return age;
  }

  public int getSex() {
    return sex;
  }

  public boolean hasHobby(String aHobby) {
    if (aHobby == "")
      return true;
    for (int i = 0; i < hobbies.length; i++)
      if (hobbies[i].equals(aHobby))
        return true;

    return false;
  }

  public void reset() {
    age = 0;
    sex = 0;
    hobbies = null;
  }

  public String toString() {
    String result = "Age: " + age + ", Sex: ";
    if (sex == Product.MALE)
      result += "Male";
    else if (sex == Product.FEMALE)
      result += "Female";
    else
      result += "Male or Female";
    result += ", Hobbies:";
    for (int i = 0; i < hobbies.length; i++)
      result += " " + hobbies[i];
    return result;
  }
}
