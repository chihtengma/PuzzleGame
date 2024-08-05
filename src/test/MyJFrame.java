package test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MyJFrame extends JFrame implements MouseListener, KeyListener {
   JButton button1 = new JButton("Click Me!!");
   JButton button2 = new JButton("Click Me~~");
   Random random = new Random();
   boolean scaleFlag = false;


   public MyJFrame() {
      this.setSize(750, 800);
      this.setTitle("Testing Window");
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setLayout(null);

      // Buttons
      button1.setBounds(0, 0, 125, 35);
      button1.addMouseListener(this);
      this.getContentPane().add(button1);

      // Keys
      this.addKeyListener(this);

      this.setVisible(true);

   }


   // Mouse Actions
   @Override
   public void mouseClicked(MouseEvent e) {
      System.out.println("Clicked!");
   }

   @Override
   public void mousePressed(MouseEvent e) {
      System.out.println("Pressed!");
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      System.out.println("Released!");
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      System.out.println("Entered!");
   }

   @Override
   public void mouseExited(MouseEvent e) {
      System.out.println("Exited!");
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