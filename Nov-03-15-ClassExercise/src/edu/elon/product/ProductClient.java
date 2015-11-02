/**
   @version 1.20 2015-11-03
   @author Cay Horstmann
 */

package edu.elon.product;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * This program demonstrates how to call a remote method on two
 * objects that are located through the naming service.
 */
public class ProductClient {

  public static void main(String[] args) {
    System.setProperty("java.security.policy", "client.policy");
    System.setSecurityManager(new SecurityManager());
    String url = "rmi://localhost/";
    if (args.length == 1) {
      url = "rmi://" + args[0] + "/central_warehouse";
    }
    // change to "rmi://yourserver.com/"
    // when server runs on remote machine yourserver.com
    try {
      Context namingContext = new InitialContext();
      Product c1 = (Product) namingContext.lookup(url + "toaster");
      Product c2 = (Product) namingContext.lookup(url + "microwave");

      System.out.println(c1.getDescription());
      System.out.println(c2.getDescription());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
