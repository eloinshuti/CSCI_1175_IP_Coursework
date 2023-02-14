/* Eloi Nshuti
 * Programming Exercise 33-1
 * 02/13/2023
 */

package Programming_Exercise_33_1;
// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.*;
import java.net.*;
import java.util.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
new Thread(() -> {
  try {
    // Create a server socket
    ServerSocket serverSocket = new ServerSocket(8000);
    Platform.runLater(() ->
      ta.appendText("Exercise31_01Server started at " + new Date() + '\n'));
    // Listen for a connection request
    Socket socket = serverSocket.accept();
    // Create data input and output streams
    DataInputStream inputFromClient = new DataInputStream(
      socket.getInputStream());
    DataOutputStream outputToClient = new DataOutputStream(
      socket.getOutputStream());

    while (true) {
      double annualInterestRate = inputFromClient.readDouble();
      int numberOfYears = inputFromClient.readInt();
      double loanAmount = inputFromClient.readDouble();
      Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);
      
      
      // Compute area
      //double area = radius * radius * Math.PI;
      // Send area back to the client
      outputToClient.writeDouble(loan.getMonthlyPayment());
      outputToClient.writeDouble(loan.getTotalPayment());
      
      Platform.runLater(() -> {
        ta.appendText("Annual Interest Rate: " + annualInterestRate + '\n');
        ta.appendText("Number of Years: " + numberOfYears + '\n'); 
        ta.appendText("Loan Amount: " + loanAmount + '\n'); 
        ta.appendText("Monthly Payment: " + loan.getMonthlyPayment() + '\n'); 
        ta.appendText("Total Payment: " + loan.getTotalPayment() + '\n'); 
        
      });
    }
  }
  catch(IOException ex) {
    ex.printStackTrace();
  }
}).start();
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}