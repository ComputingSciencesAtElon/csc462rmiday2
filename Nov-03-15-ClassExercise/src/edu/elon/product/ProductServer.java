/**
 @version 1.20 2015-11-03
 @author Cay Horstmann
 */

package edu.elon.product;

import java.rmi.*;
import java.rmi.server.*;
import javax.naming.*;

/**
 * This server program instantiates two remote objects, registers them
 * with the naming service, and waits for clients to invoke methods on
 * the remote objects.
 */
public class ProductServer {
  public static void main(String args[]) {
    try {
      System.out.println("Constructing server implementations...");

      ProductImpl p1 = new ProductImpl("Blackwell Toaster");
      ProductImpl p2 = new ProductImpl("ZapXpress Microwave Oven");

      System.out.println("Binding server implementations to registry...");
      Context namingContext = new InitialContext();
      namingContext.rebind("rmi:toaster", p1);
      namingContext.rebind("rmi:microwave", p2);
      System.out.println("Waiting for invocations from clients...");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
