/**
   @version 1.30 2015-11-03
   @author Cay Horstmann
 */

package edu.elon.warehouse;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The client for the warehouse program.
 */
public class WarehouseClient {
  public static void main(String[] args) {
    try {
      System.setProperty("java.security.policy", "client.policy");
      System.setSecurityManager(new SecurityManager());
      String url = "rmi://localhost/central_warehouse";
      if (args.length == 1) {
        url = "rmi://" + args[0] + "/central_warehouse";
      }
      Context namingContext = new InitialContext();
      Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
      JFrame frame = new WarehouseClientFrame(centralWarehouse);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/**
 * A frame to select the customer's age, sex, and hobbies, and to show
 * the matching products resulting from a remote call to the
 * warehouse.
 */
class WarehouseClientFrame extends JFrame {
  private static final int DEFAULT_HEIGHT = 300;

  private static final int DEFAULT_WIDTH = 300;

  private JTextField age;
  private JRadioButton female;

  private ArrayList<JCheckBox> hobbies;
  private JRadioButton male;
  private JTextArea result;
  private Warehouse warehouse;

  public WarehouseClientFrame(Warehouse warehouse) {
    this.warehouse = warehouse;
    setTitle("WarehouseClient");
    setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(0, 2));

    panel.add(new JLabel("Age:"));
    age = new JTextField(4);
    age.setText("20");
    panel.add(age);

    female = new JRadioButton("Female", true);
    male = new JRadioButton("Male", true);
    ButtonGroup group = new ButtonGroup();
    panel.add(female);
    group.add(female);
    panel.add(male);
    group.add(male);

    panel.add(new JLabel("Hobbies: "));
    hobbies = new ArrayList<JCheckBox>();
    for (String h : new String[] { "Gardening", "Beauty", "Computers",
                                  "Household", "Sports" }) {
      JCheckBox checkBox = new JCheckBox(h);
      hobbies.add(checkBox);
      panel.add(checkBox);
    }

    result = new JTextArea(4, 40);
    result.setEditable(false);

    JPanel buttonPanel = new JPanel();
    JButton submitButton = new JButton("Submit");
    buttonPanel.add(submitButton);
    submitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        callWarehouse();
      }
    });

    add(panel, BorderLayout.NORTH);
    add(result, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * Call the remote warehouse to find matching products.
   */
  private void callWarehouse() {
    try {
      ArrayList<String> selected = new ArrayList<String>();
      for (JCheckBox checkBox : hobbies) {
        if (checkBox.isSelected()) {
          selected.add(checkBox.getText());
        }
      }
      Customer c =
        new Customer(Integer.parseInt(age.getText()),
          (male.isSelected() ? Product.MALE : 0)
                  + (female.isSelected() ? Product.FEMALE : 0),
          selected.toArray(new String[selected.size()]));
      ArrayList<Product> recommendations = warehouse.find(c);
      result.setText(c + "\n");
      for (Product p : recommendations) {
        String t = p.getDescription() + "\n";
        result.append(t);
      }
    } catch (Exception e) {
      e.printStackTrace();
      result.setText("Exception: " + e);
    }
  }
}
