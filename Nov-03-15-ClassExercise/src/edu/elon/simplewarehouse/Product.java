/**
 @version 1.00 2015-11-03
 @author Cay Horstmann
 */

package edu.elon.simplewarehouse;

public interface Product {

  String getDescription();

  final int MALE = 1;

  final int FEMALE = 2;

  final int BOTH = MALE + FEMALE;
}
