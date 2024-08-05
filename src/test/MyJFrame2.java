package test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrame2 extends JFrame implements KeyListener {

   public MyJFrame2() {
      this.setSize(600,900);
      this.setTitle("Test");
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setAlwaysOnTop(true);
      this.setLocationRelativeTo(null);


      this.addKeyListener(this);

      this.setVisible(true);
   }

   @Override
   public void keyTyped(KeyEvent e) {
      System.out.println("Typed: " + e.getKeyChar());
   }

   @Override
   public void keyPressed(KeyEvent e) {
      System.out.println("Pressed: " + e.getKeyChar());
   }

   @Override
   public void keyReleased(KeyEvent e) {
      System.out.println("Released: " + e.getKeyChar());
   }
}