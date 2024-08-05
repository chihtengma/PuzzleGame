package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
   // Game Frame
   // Everything related to "game" will be here

   // Winning matrix
   private final int[][] win = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
   // Items in each option on the menu
   private final JMenuItem replayItem = new JMenuItem("Replay");
   private final JMenuItem reLoginItem = new JMenuItem("Re-Login");
   private final JMenuItem closeItem = new JMenuItem("Close");
   private final JMenuItem aboutItem = new JMenuItem("About Me");
   // Items in change image option
   private final JMenuItem animalOption = new JMenuItem("Animal");
   private final JMenuItem girlOption = new JMenuItem("Girl");
   private final JMenuItem sportOption = new JMenuItem("Sport");
   // A matrix for store the image data after randomizing the images
   // And initImages will initialize images corresponding to the index of the matrix
   private final int[][] data = new int[4][4];
   // Path to images folder
   private String selectedFolder;
   // Variables for storing the empty block in the matrix
   // x -> row index, y -> column index
   private int x, y;
   // Move counter
   private int stepCount;

   public GameJFrame() {
      // Initialize game window
      initJFrame();

      // Initialize menu on the game window
      initJMenu();

      // Randomly select an image folder
      startWithRandomImage();

      // Initialize data (randomize the images)
      initData();

      // Initialize images
      initImage();

      // Set window to visible, default is false
      this.setVisible(true);
   }

   private void initJMenu() {
      // Menu
      JMenuBar jMenuBar = new JMenuBar();

      // Options on the menu
      JMenu optionsJMenu = new JMenu("Options");
      JMenu aboutJMenu = new JMenu("About");

      JMenu changeImage = new JMenu("Change Image");


      // Add items to the corresponding option
      optionsJMenu.add(changeImage);
      optionsJMenu.add(replayItem);
      optionsJMenu.add(reLoginItem);
      optionsJMenu.add(closeItem);

      aboutJMenu.add(aboutItem);

      changeImage.add(animalOption);
      changeImage.add(girlOption);
      changeImage.add(sportOption);

      // Add options to the menu
      jMenuBar.add(optionsJMenu);
      jMenuBar.add(aboutJMenu);

      // Add Action commands for the menu items
      replayItem.setActionCommand("Replay");
      reLoginItem.setActionCommand("ReLogin");
      closeItem.setActionCommand("Close");
      aboutItem.setActionCommand("About");
      animalOption.setActionCommand("Animal");
      girlOption.setActionCommand("Girl");
      sportOption.setActionCommand("Sport");

      // Add listeners to the options
      replayItem.addActionListener(this);
      reLoginItem.addActionListener(this);
      closeItem.addActionListener(this);
      aboutItem.addActionListener(this);
      animalOption.addActionListener(this);
      girlOption.addActionListener(this);
      sportOption.addActionListener(this);

      // Add menu to game frame
      this.setJMenuBar(jMenuBar);
   }

   private void initJFrame() {
      this.setSize(603, 680);
      this.setTitle("Puzzle Game");
      this.setAlwaysOnTop(true);
      // Center the window
      this.setLocationRelativeTo(null);

      // Exit the program when close the window
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);

      // Set the default layout to null, so we can manually place the image to the place we want (based on (x,y))
      this.setLayout(null);

      // Set the minimum & maximum size to current window size
      this.setMinimumSize(this.getSize());
      this.setMaximumSize(this.getSize());

      // Add Key listener to the window, to manipulate images movement
      this.addKeyListener(this);
   }

   private void initImage() {
      // Remove all the image whenever this method gets call
      // Making sure images movement display properly
      this.getContentPane().removeAll();

      if (victory()) {
         JLabel winLabel = new JLabel(new ImageIcon("image/win.png"));
         winLabel.setBounds((this.getWidth() - 192) / 2, (this.getHeight() - 73) / 2, 192, 73);
         this.getContentPane().add(winLabel);
      }

      // Steps counter
      displayStepsCounter();

      // Images
      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 4; j++) {
            int num = data[i][j];
            ImageIcon imageIcon = new ImageIcon(selectedFolder + num + ".jpg");
            JLabel jLabel = new JLabel(imageIcon);
            jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
            jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
            this.getContentPane().add(jLabel);

         }
      }

      // Background
      displayBackground();

      // Refresh the images
      this.getContentPane().repaint();
   }

   // Initialize images data (randomize)
   private void initData() {
      // 1.Create an array with of images size (15)
      int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
      // 2.Randomize
      Random random = new Random();
      for (int i = 0; i < tempArr.length; i++) {
         // Get the random index
         int idx = random.nextInt(tempArr.length);
         // Swap the random index with the current index
         int temp = tempArr[i];
         tempArr[i] = tempArr[idx];
         tempArr[idx] = temp;
      }

      //  3. Push the tempArr data to the matrix
      for (int i = 0; i < tempArr.length; i++) {
         if (tempArr[i] == 0) {
            x = i / 4;
            y = i % 4;
         }
         data[i / 4][i % 4] = tempArr[i];

      }
   }

   @Override
   public void keyTyped(KeyEvent e) {
   }

   @Override
   public void keyPressed(KeyEvent e) {
      if (victory())
         return;

      int code = e.getKeyCode();

      if (code == 65) { // 65 -> 'a' key
         displayAnswer();
      }
   }

   // Images Movement
   @Override
   public void keyReleased(KeyEvent e) {
      // If user wins the game, return immediately
      if (victory())
         return;

      // Arrow keys code
      // left: 37 up: 38 right: 39 down: 40
      int code = e.getKeyCode();


      switch (code) {
         case KeyEvent.VK_LEFT -> moveLeft();
         case KeyEvent.VK_UP -> moveUp();
         case KeyEvent.VK_RIGHT -> moveRight();
         case KeyEvent.VK_DOWN -> moveDown();
         case KeyEvent.VK_A -> initImage(); // when 'a' released, puzzle game resume
         case KeyEvent.VK_W -> hackKey(); // WHen 'w' released, finished the game
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();

      switch (command) {
         case "Replay" -> {
            stepCount = 0;
            initData();
            initImage();
         }
         case "ReLogin" -> {
            this.setVisible(false);
            new LoginJFrame();
         }
         case "Close" -> System.exit(0); // Close the game

         case "About" -> {
            System.out.println("About");
            displayAbout();

         }
         // Change image actions
         case "Animal" -> {
            selectRandomFolder("image/animal/");
            stepCount = 0;
            initData();
            initImage();
         }
         case "Girl" -> {
            selectRandomFolder("image/girl/");
            stepCount = 0;
            initData();
            initImage();
         }
         case "Sport" -> {
            selectRandomFolder("image/sport/");
            stepCount = 0;
            initData();
            initImage();
         }
      }
   }

   // Image moving methods
   private void moveLeft() {
      // Check if the empty block cam move left
      if (y < 3) { // Ensure the y is not at the leftmost column (0)
         data[x][y] = data[x][y + 1];
         data[x][y + 1] = 0;
         y++;

         // Increase the steps counter
         stepCount++;

         initImage();
      }
   }

   private void moveRight() {
      // Check if the empty block can move right
      if (y > 0) { // Ensure y is not at the right most column
         data[x][y] = data[x][y - 1];
         data[x][y - 1] = 0;
         y--;

         stepCount++;

         initImage();
      }
   }

   private void moveUp() {
      // Check if the empty block can move up
      if (x < 3) { // Ensure x is not the top row (0)
         // Image movement logic: move the puzzle piece that is currently below the empty block up
         data[x][y] = data[x + 1][y];
         data[x + 1][y] = 0;
         x++; // Update the new position of the empty block

         stepCount++;

         // Refresh the images after updating the data
         initImage();
      }
   }

   private void moveDown() {
      // Check if the empty block cam move down
      if (x > 0) {
         data[x][y] = data[x - 1][y];
         data[x - 1][y] = 0;
         x--;

         stepCount++;

         initImage();
      }
   }

   // Display answer image when 'a' pressed
   private void displayAnswer() {
      this.getContentPane().removeAll();

      JLabel jLabel = new JLabel(new ImageIcon(selectedFolder + "all.jpg"));
      jLabel.setBounds(83, 134, 420, 420);
      jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
      this.getContentPane().add(jLabel);

      displayBackground();

      this.getContentPane().repaint();
   }

   // Display step counter
   private void displayStepsCounter() {
      JLabel stepCounter = new JLabel("Steps: " + stepCount);
      stepCounter.setBounds(50, 20, 120, 30);
      stepCounter.setFont(new Font("Serif", Font.BOLD, 24));
      this.getContentPane().add(stepCounter);
   }

   // Display background
   private void displayBackground() {
      JLabel background = new JLabel(new ImageIcon("image/background.png"));
      background.setBounds(40, 40, 508, 560);
      this.getContentPane().add((background));
   }

   // Display about content
   private void displayAbout() {
      JDialog dialog = new JDialog();
      dialog.setTitle("About Me");
      dialog.setSize(500, 250);
      dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
      dialog.setAlwaysOnTop(true);
      dialog.setLocationRelativeTo(null);
      dialog.setModal(true);

      // Intro Label
      JLabel introLabel = new JLabel("<html>Hi there, thanks for trying out the game!<br>" + "I'm ChihTeng Ma, a " +
            "passionate developer.<br>" + "Feel free to connect me on LinkedIn or check out my Github!</html>");
      introLabel.setFont(new Font("Serif", Font.PLAIN, 18));
      introLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      dialog.getContentPane().add(introLabel);

      // LinkedIn Label
      JLabel linkedInLabel = createLabel("LinkedIn", "https://www.linkedin.com/in/chihtengma/");
      dialog.getContentPane().add(linkedInLabel);
      JLabel githubLabel = createLabel("GitHub", "https://github.com/chihtengma");
      dialog.getContentPane().add(githubLabel);

      dialog.setVisible(true);
   }

   // Randomly choose image in the image folder when first opening the game
   private void startWithRandomImage() {
      Random random = new Random();
      String[] mainFolders = {"image/girl/", "image/animal/", "image/sport/"};

      // Randomly select a main folder
      String mainFolder = mainFolders[random.nextInt(mainFolders.length)];
      // List all sub-folders in the selected main folder
      selectRandomFolder(mainFolder);
   }

   // Randomly select the image when user selected one of the options in the change image option
   private void selectRandomFolder(String mainFolder) {
      Random random = new Random();
      File mainDir = new File(mainFolder);
      String[] subFolders = mainDir.list((current, name) -> new File(current, name).isDirectory());

      if (subFolders != null && subFolders.length > 0) {
         String subFolder = subFolders[random.nextInt(subFolders.length)];
         selectedFolder = mainFolder + subFolder + "/";
      } else {
         selectedFolder = mainFolder;
      }
   }

   private void hackKey() {
      for (int i = 0; i < data.length; i++) {
         System.arraycopy(win[i], 0, data[i], 0, data[i].length);
      }
      initImage();
   }

   // Check if user wins the game
   public boolean victory() {
      for (int i = 0; i < data.length; i++) {
         for (int j = 0; j < data[i].length; j++) {
            if (data[i][j] != win[i][j]) { // Return false immediately if one of the value is not the same
               return false;
            }
         }
      }
      return true;
   }

   // Helper method for creating label in about window
   private JLabel createLabel(String labelName, String urlString) {
      JLabel jLabel = new JLabel("<html><a href='" + urlString + "'>" + labelName + "</a></html>");
      jLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
      jLabel.setFont(new Font("Serif", Font.PLAIN, 18));
      jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      jLabel.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            openWebpage(urlString);
         }
      });

      return jLabel;
   }

   // Helper method to open a weg page
   private void openWebpage(String urlString) {
      try {
         Desktop.getDesktop().browse(URI.create(urlString));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}