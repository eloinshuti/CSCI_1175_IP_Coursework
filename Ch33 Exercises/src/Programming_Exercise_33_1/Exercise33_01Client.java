/* Eloi Nshuti
 * Programming Exercise 33-1
 * 02/13/2023
 */

package Programming_Exercise_33_1;
// Exercise31_01Client.java: The client sends the input to the server and receives
// result back from the server
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
public class Exercise33_01Client extends Application {
  // Text field for receiving radius
  private TextField tfAnnualInterestRate = new TextField();
  private TextField tfNumOfYears = new TextField();
  private TextField tfLoanAmount = new TextField();
  private Button btSubmit= new Button("Submit");
  DataOutputStream toServer = null;
  DataInputStream fromServer = null;
  // Text area to display contents
  private TextArea ta = new TextArea();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Annual Interest Rate"), 0, 0);
    gridPane.add(new Label("Number Of Years"), 0, 1);
    gridPane.add(new Label("Loan Amount"), 0, 2);
    gridPane.add(tfAnnualInterestRate, 1, 0);
    gridPane.add(tfNumOfYears, 1, 1);
    gridPane.add(tfLoanAmount, 1, 2);
    gridPane.add(btSubmit, 2, 1);
    
    tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
    tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
    tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);
    
    tfLoanAmount.setPrefColumnCount(5);
    tfNumOfYears.setPrefColumnCount(5);
    tfLoanAmount.setPrefColumnCount(5);
            
    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(ta));
    pane.setTop(gridPane);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    
    btSubmit.setOnAction(e -> { /////////////////////////////////////////// check if you need it to setonpressed
    	try {
    		// Get the values from the text field
    		double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText().trim());
    		int numberOfYears = Integer.parseInt(tfNumOfYears.getText().trim());
    		double loanAmount = Double.parseDouble(tfLoanAmount.getText().trim());
    		// Send the values to the server
    		toServer.writeDouble(annualInterestRate);
    		toServer.writeInt(numberOfYears);
    		toServer.writeDouble(loanAmount);
    		toServer.flush();
    		// Get area from the server
    		double monthlyPayment = fromServer.readDouble();
    		double totalPayment = fromServer.readDouble();
    		
    		// Display to the text area
    	       ta.appendText("Annual Interest Rate: " + annualInterestRate + '\n');
    	       ta.appendText("Number of Years: " + numberOfYears + '\n'); 
    	       ta.appendText("Loan Amount: " + loanAmount + '\n'); 
    	       ta.appendText("Monthly Payment: " + monthlyPayment + '\n'); 
    	       ta.appendText("Total Payment: " + totalPayment + '\n'); 
    		}
    	catch (IOException ex) {
    		System.err.println(ex);
    		}
    	});
    try {
    	

    	// Create a socket to connect to the server
    	Socket socket = new Socket("localhost", 8000);
    	
    	fromServer = new DataInputStream(socket.getInputStream());
    	toServer = new DataOutputStream(socket.getOutputStream());
    	}
    catch (IOException ex) {
    	ta.appendText(ex.toString() + '\n');
    	}
    
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}