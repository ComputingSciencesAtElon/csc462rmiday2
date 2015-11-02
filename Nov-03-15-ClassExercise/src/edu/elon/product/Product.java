/**
   @version 1.00 2015-11-03
   @author Cay Horstmann
 */

package edu.elon.product;

import java.rmi.*;

/**
 * The interface for remote product objects.
 */
public interface Product extends Remote {
  /**
   * Gets the description of this product.
   * 
   * @return the product description
   */
  String getDescription() throws RemoteException;
}
