/*
 * Eloi Nshuti
 * Final Project
 * 02/27/2023
 */

package Library_Project;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
public class LibraryClient extends Application {
  // Text field for receiving radius
  private TextField tfFirstName = new TextField();
  private TextField tfLastName = new TextField();
  private TextField tfBookName = new TextField();
  private TextField tfISBN = new TextField();
  private TextField tfEmail = new TextField();
  private TextField tfPhoneNumber = new TextField();
  private TextArea taDetails = new TextArea();
  private Button btSubmit= new Button("Submit");
  DataOutputStream toServer = null;
  DataInputStream fromServer = null;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
	TabPane tabPane = new TabPane();
	    Tab tab1 = new Tab("Log");
	    Tab tab2 = new Tab("Details");
	    tabPane.getTabs().addAll(tab1, tab2);
	    
	MenuBar menuBar = new MenuBar();
	HBox hbox = new HBox(5);
	hbox.getChildren().add(menuBar);
	hbox.setAlignment(Pos.TOP_LEFT);
		  
    GridPane gridPane = new GridPane();
    gridPane.add(hbox, 0, 0);
    gridPane.add(new Label("First Name: "), 0, 1);
    gridPane.add(new Label("Last Name: "), 0, 2);
    gridPane.add(new Label("Book Name: "), 0, 3);
    gridPane.add(new Label("ISBN: "), 0, 4);
    gridPane.add(new Label("Email: "), 0, 5);
    gridPane.add(new Label("Phone: "), 0, 6);
    gridPane.add(tfFirstName, 1, 1);
    gridPane.add(tfLastName, 1, 2);
    gridPane.add(tfBookName, 1, 3);
    gridPane.add(tfISBN, 1, 4);
    gridPane.add(tfEmail, 1, 5);
    gridPane.add(tfPhoneNumber, 1, 6);
    gridPane.add(btSubmit, 1, 7);
    
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    gridPane.setHgap(5.5);
    gridPane.setVgap(5.5);
    
    tfFirstName.setAlignment(Pos.BASELINE_LEFT);
    tfLastName.setAlignment(Pos.BASELINE_LEFT);
    tfBookName.setAlignment(Pos.BASELINE_LEFT);
    tfISBN.setAlignment(Pos.BASELINE_LEFT);
    tfEmail.setAlignment(Pos.BASELINE_LEFT);
    tfPhoneNumber.setAlignment(Pos.BASELINE_LEFT);
    
    taDetails.appendText("Southwest Technical College" + "\n");
    taDetails.appendText("Address: 757 W 800 S, Cedar City, UT 84720" + "\n");
    taDetails.appendText("Phone: (435)586-2899" + "\n");
    taDetails.appendText("Appointments: stech.edu" + "\n");
    tab1.setContent(gridPane);
    tab2.setContent(taDetails);
    

	  Menu menuOperation = new Menu("Operation");
	  menuBar.getMenus().addAll(menuOperation);
	    
	  MenuItem menuItemSubmit = new MenuItem("Submit");
	  MenuItem menuItemExit = new MenuItem("Exit");
	  menuOperation.getItems().addAll(menuItemSubmit, menuItemExit);
	  
	  
	  menuItemExit.setOnAction(e -> System.exit(0));

	  
	  menuItemSubmit.setOnAction(e -> {
	    	try {
	    		// Get the values from the text field   		
				String clientFirstName = tfFirstName.getText().trim();
				String clientLastName = tfLastName.getText().trim();
				String clientBookName = tfBookName.getText().trim();
				String clientISBN = tfISBN.getText().trim();
				String clientEmail = tfEmail.getText().trim();
				String clientPhoneNumber = tfPhoneNumber.getText().trim();
				
				tfFirstName.clear();
				tfLastName.clear();
				tfBookName.clear();
				tfISBN.clear();
				tfEmail.clear();
				tfPhoneNumber.clear();
				
	    		// Send the values to the server
	    		toServer.writeUTF(clientFirstName);
	    		toServer.writeUTF(clientLastName);
	    		toServer.writeUTF(clientBookName);
	    		toServer.writeUTF(clientISBN);
	    		toServer.writeUTF(clientEmail);
	    		toServer.writeUTF(clientPhoneNumber);
	    		toServer.flush();
	    		}
	    	catch (IOException ex) {
	    		System.err.println(ex);
	    		}
	    	});
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(tabPane, 400, 300);
    primaryStage.setTitle("Library Log"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    
    btSubmit.setOnAction(e -> {
    	try {
    		// Get the values from the text field   		
			String clientFirstName = tfFirstName.getText().trim();
			String clientLastName = tfLastName.getText().trim();
			String clientBookName = tfBookName.getText().trim();
			String clientISBN = tfISBN.getText().trim();
			String clientEmail = tfEmail.getText().trim();
			String clientPhoneNumber = tfPhoneNumber.getText().trim();
			
			tfFirstName.clear();
			tfLastName.clear();
			tfBookName.clear();
			tfISBN.clear();
			tfEmail.clear();
			tfPhoneNumber.clear();
			
    		// Send the values to the server
    		toServer.writeUTF(clientFirstName);
    		toServer.writeUTF(clientLastName);
    		toServer.writeUTF(clientBookName);
    		toServer.writeUTF(clientISBN);
    		toServer.writeUTF(clientEmail);
    		toServer.writeUTF(clientPhoneNumber);
    		toServer.flush();
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