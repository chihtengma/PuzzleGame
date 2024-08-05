package ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {
   // Login Frame
   // Everything related to "login" will be here

   public LoginJFrame() {
      this.setSize(488, 430);
      this.setTitle("Login");
      this.setAlwaysOnTop(true);
      // Center the window
      this.setLocationRelativeTo(null);

      // Exit the program when close the window
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);

      this.setVisible(true);
   }
}