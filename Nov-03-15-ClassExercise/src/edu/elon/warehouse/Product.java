/**
 @version 1.00 2015-11-03
 @author Cay Horstmann
 */

package edu.elon.warehouse;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface for remote product objects.
 */
public interface Product extends Remote {

  final int BOTH = 3;
  final int FEMALE = 2;
  final int MALE = 1;

  /**
   * Gets the description of this product.
   * 
   * @return the product description
   */
  String getDescription() throws RemoteException;

}
