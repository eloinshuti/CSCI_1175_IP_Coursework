package Programming_Exercise_33_9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise33_09Server extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  DataInputStream fromClient = null;
  DataOutputStream toClient = null;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    //taClient.setDisable(true);
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
    primaryStage.setTitle("Exercise31_09Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    
    taClient.setOnKeyPressed(e -> {
    	if (e.getCode() == KeyCode.ENTER) {
    		try {
    			toClient.writeUTF("S: " + taClient.getText().trim());   
    		
     			toClient.flush();
     			taServer.appendText("S:" + taClient.getText().trim() + "\n");
     			taClient.clear();
     			}
     		catch (IOException ex) {
     		System.err.println(ex);
     		} 		
     	}
     });
    try {
    	ServerSocket serversocket = new ServerSocket(8000);
    	Socket socket = serversocket.accept();
    	fromClient = new DataInputStream(socket.getInputStream());
    	toClient = new DataOutputStream(socket.getOutputStream());
    	new ReceiveInformation(socket);
    	}
    catch (IOException ex) {
    	ex.printStackTrace();
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
	    DataInputStream fromClient = new DataInputStream(socket.getInputStream());
	    while(true) {
	     String text = fromClient.readUTF();
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
