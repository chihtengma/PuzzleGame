package ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
   // Register Frame
   // Everything related to "register" will be here

   public RegisterJFrame() {
      this.setSize(488, 500);
      this.setTitle("Register");
      this.setAlwaysOnTop(true);
      // Center the window
      this.setLocationRelativeTo(null);

      // Exit the program when close the window
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);

      this.setVisible(true);
   }
}