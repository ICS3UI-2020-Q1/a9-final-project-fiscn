import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements Runnable, ActionListener{

  // Class Variables 

  //this is the main panel for all the interface variables to be displayed on  
  JPanel mainPanel; 
  //this panel holds all the "cards" or buttons for the Memory game 
  JPanel cardPanel; 
  //this panels holds the info display and reset button above the cards panel 
  JPanel topPanel; 

  //creates an array for the buttons to be made easily  
  JButton[] cardButtons; 
  //creates a button for restesting the game to play it again
  JButton resetButton; 

  //this creates a text box to put informational text that the program outputs 
  JTextField infoDisplay; 
  
  //this creates an array for the symbols that are used for the cards 
  String[] cardSymbols; 

  //lastCard1 and lastCard2 keep track of how many times the user clicks on and off the wrong cards 
  int lastCard1 = -1; 
  int lastCard2 = -1;  

  //this integer keeps track of how many times the user clicks on the screen 
  int clicks = 0; 
  //this integer keeps track of the matching pairs of cards or how many buttons are disabled 
  int closedButtons = 0;

  // Method to assemble our GUI
  public void run(){
    // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
    JFrame frame = new JFrame("Memory Game!");
    // Makes the X button close the program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // makes the windows 800 pixel wide by 600 pixels tall
    frame.setSize(800,600);
    // shows the window
    frame.setVisible(true); 

    //initalize the main panel and layout 
    mainPanel = new JPanel(); 
    mainPanel.setLayout(new BorderLayout()); 

    //initalize the panel for the cards with a grid layout 
    cardPanel = new JPanel(); 
    cardPanel.setLayout(new GridLayout(6,2)); 

    //initalizes the top panel for the info display and reset button 
    topPanel = new JPanel(); 

    //add display for words at the top  
    infoDisplay = new JTextField(); 
    //set display size 
    infoDisplay.setPreferredSize(new Dimension(400,100)); 
    //add to the top 
    topPanel.add(infoDisplay); 
    //Disable the pannel so that the user cannot type in it 
    infoDisplay.setEnabled(false); 

    //create cards for the game 
    cardButtons = new JButton[12]; 
    for(int i = 0; i < cardButtons.length; i++){
      cardButtons[i] = new JButton(); 
      //add action listener and command 
      cardButtons[i].setActionCommand("" + i);  
      cardButtons[i].addActionListener(this); 
    }  

    //create an array to create symbols for the buttons
    cardSymbols = new String[12]; 
    cardSymbols[0] = new String("A"); 
    cardSymbols[1] = new String("A"); 
    cardSymbols[2] = new String("B");
    cardSymbols[3] = new String("B");
    cardSymbols[4] = new String("C");
    cardSymbols[5] = new String("C");
    cardSymbols[6] = new String("D");
    cardSymbols[7] = new String("D");
    cardSymbols[8] = new String("E");
    cardSymbols[9] = new String("E");
    cardSymbols[10] = new String("F");
    cardSymbols[11] = new String("F");


     
  

    // this is a code that mixes up the arrays 
    for(int i = 0; i < cardSymbols.length; i++){

      // Pick a remaining element…
      int spot = (int)Math.floor(Math.random() * cardSymbols.length);

      // And swap it with the current element.
      String temp =cardSymbols[i];
    cardSymbols[i] =cardSymbols[spot];
    cardSymbols[spot] = temp;
    }

    
  


    //create reset button  
    resetButton = new JButton("Reset"); 
    resetButton.addActionListener(this); 
    //add to top display 
    topPanel.add(resetButton);

    //add cards to the screen 
    cardPanel.add(cardButtons[0]);
    cardPanel.add(cardButtons[1]);
    cardPanel.add(cardButtons[2]);
    cardPanel.add(cardButtons[3]);
    cardPanel.add(cardButtons[4]);
    cardPanel.add(cardButtons[5]);
    cardPanel.add(cardButtons[6]);
    cardPanel.add(cardButtons[7]); 
    cardPanel.add(cardButtons[8]);
    cardPanel.add(cardButtons[9]);
    cardPanel.add(cardButtons[10]);
    cardPanel.add(cardButtons[11]);

    //assemble the panels 
    mainPanel.add(topPanel, BorderLayout.PAGE_START); 
    mainPanel.add(cardPanel, BorderLayout.CENTER); 

    //add panel to frame 
    frame.add(mainPanel); 
    

  }

  // method called when a button is pressed
  public void actionPerformed(ActionEvent e){
    // get the command from the action
    String command = e.getActionCommand(); 

    //crate a variable that takes the input by the user and 
    int card = Integer.parseInt(command); 
    
    //this registers the clicks the user makes 
    clicks++; 

    //
    cardButtons[card].setText(cardSymbols[card]); 
    
    //this if statement is when the user clicks on the wrong pair of cards and the statement hides the card sysmbols again 
    if(clicks == 3){
      cardButtons[lastCard1].setText(""); 
      cardButtons[lastCard2].setText("");  

      //resets the users amount of clicks the user made back to 1 
      clicks = 1;
    }

    //this is what the program will run when the user selects an incorrect pair 
    if(lastCard1 != -1 && !cardSymbols[card].equals(cardSymbols[lastCard1])){ 
      
      //prints out the following in the display at top 
      infoDisplay.setText("Sorry thats not a match");  

    } 

    //this is what the program will run if there's a matching pair 
    if(lastCard1 != -1 && cardSymbols[card].equals(cardSymbols[lastCard1])){ 

      //displays the following to the display at the top 
      infoDisplay.setText("That's a match!"); 

      //this sets the "cards" or buttons disabled so that the user can't click on the same pair again  
      cardButtons[card].setEnabled(false); 
      cardButtons[lastCard1].setEnabled(false); 

      //sets the clicks the user has made back to zero 
      clicks = 0; 
      
      //this adds the value of 2 to the closedButtons variable
      closedButtons = closedButtons + 2;  

      
    }
      //this "if statement" outputs the following when the closedButtons variable has a value equal to or greater than 12 
      if(closedButtons >= 12){
        infoDisplay.setText("YOU WON! Press the reset button to play again!");
      }
      
    
      

      //these variables keep track of the cards the user slected in order from first to last 
      lastCard2 = lastCard1;
      lastCard1 = card;  
  }

  // Main method to start our program
  public static void main(String[] args){
    // Creates an instance of our program
    Main gui = new Main();
    // Lets the computer know to start it in the event thread
    SwingUtilities.invokeLater(gui);
  }
}
