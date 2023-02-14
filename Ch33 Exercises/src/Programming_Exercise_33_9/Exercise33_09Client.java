package Programming_Exercise_33_9;

import javafx.application.Application;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Exercise33_09Client extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  DataOutputStream toServer = null;
  DataInputStream fromServer = null;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    //taServer.setDisable(true);
    taServer.setEditable(false);
    
    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // To complete later
    
    
    taClient.setOnKeyPressed(e -> {             
    	if (e.getCode() == KeyCode.ENTER) {
    		try {
    			// Send the values to the server    		
    			toServer.writeUTF("C: " + taClient.getText().trim());   
    			toServer.flush();
    			taServer.appendText("C:" + taClient.getText().trim() + "\n");
    			taClient.clear();
    			}
    		catch (IOException ex) {
    		System.err.println(ex);
    		} 		
    	}
    });
    try {
    	// Create a socket to connect to the server
    	Socket socket = new Socket("localhost", 8000);
    	
    	fromServer = new DataInputStream(socket.getInputStream());
    	toServer = new DataOutputStream(socket.getOutputStream());
    	new ReceiveInformation(socket);
    	}
    
    catch (IOException ex) {
    	taServer.appendText(ex.toString() + '\n');
    	}
    
  }
    
  class ReceiveInformation implements Runnable {
	  private Socket socket;
	  public ReceiveInformation(Socket socket) {
	   this.socket = socket;
	   Thread thread = new Thread(this);
	   thread.start();
	  }
	  public void run() {
	   try {
	    DataInputStream fromServer = new DataInputStream(socket.getInputStream());
	    while(true) {
	     String text = fromServer.readUTF();
	     Platform.runLater(() -> {
	    	 taServer.appendText(text + "\n");
	     });
	    }
	   } catch (IOException e) {
	    e.printStackTrace();
	   } 
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
