import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LibraryServer extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {

	  TableView<LibraryLog> tableView = new TableView<>();
	  ObservableList<LibraryLog> data = FXCollections.observableArrayList();
	  tableView.setItems(data);
	  
	  TableColumn firstNameColumn = new TableColumn("First Name");
	  firstNameColumn.setMinWidth(100);
	  firstNameColumn.setCellValueFactory(
	    new PropertyValueFactory<LibraryLog, String>("firstName"));
	    
	  TableColumn lastNameColumn = new TableColumn("Last Name");
	  lastNameColumn.setMinWidth(100);
	  lastNameColumn.setCellValueFactory(
	    new PropertyValueFactory<LibraryLog, String>("lastName"));

	  TableColumn bookNameColumn = new TableColumn("Book Name");
	  bookNameColumn.setMinWidth(125);
	  bookNameColumn.setCellValueFactory(
	    new PropertyValueFactory<LibraryLog, String>("bookName"));
	    
	  TableColumn isbnColumn = new TableColumn("ISBN");
	  isbnColumn.setMinWidth(150);
	  isbnColumn.setCellValueFactory(
	    new PropertyValueFactory<LibraryLog, String>("ISBN"));
	  
	  TableColumn emailColumn = new TableColumn("Email");
	  emailColumn.setMinWidth(175);
	  emailColumn.setCellValueFactory(
	    new PropertyValueFactory<LibraryLog, String>("email"));
	  
	  TableColumn phoneNumberColumn = new TableColumn("Phone Number");
	  phoneNumberColumn.setMinWidth(100);
	  phoneNumberColumn.setCellValueFactory(
	    new PropertyValueFactory<LibraryLog, String>("phoneNumber"));
	  
	  
	  TableColumn dateColumn = new TableColumn("Date Logged");
	  dateColumn.setMinWidth(200);
	  dateColumn.setCellValueFactory(
	    new PropertyValueFactory<LibraryLog, Date>("dateCreated"));
	  	    
	  tableView.getColumns().addAll(firstNameColumn, lastNameColumn, bookNameColumn, isbnColumn, emailColumn, phoneNumberColumn, dateColumn);
	  
	  MenuBar menuBar = new MenuBar();
	  Menu menuOperation = new Menu("Operation");
	  menuBar.getMenus().addAll(menuOperation);
	    
	  MenuItem menuItemClearRecord = new MenuItem("Clear Record");
	  MenuItem menuItemDeleteRow = new MenuItem("Delete Row");
	  MenuItem menuItemExit = new MenuItem("Exit");
	  menuOperation.getItems().addAll(menuItemDeleteRow, menuItemClearRecord, menuItemExit);
	  
	  
	  menuItemExit.setOnAction(e -> System.exit(0));
	  
	  //Button btClear = new Button("Clear Record");
	  HBox hbox = new HBox(5);
	  hbox.getChildren().add(menuBar);
	  hbox.setAlignment(Pos.TOP_LEFT);

	  menuItemDeleteRow.setOnAction(e -> {	    	
	    	ObservableList list = tableView.getSelectionModel().getSelectedIndices();
	    	Integer[] selectedIndices = new Integer[list.size()];
	    	selectedIndices = (Integer[]) list.toArray(selectedIndices);
	    	
	    	for(int i = selectedIndices.length - 1; i >= 0; i--) 
	    	{
	    		tableView.getSelectionModel().clearSelection(selectedIndices[i].intValue());
	    		tableView.getItems().remove(selectedIndices[i].intValue());
	    	}	    	
	    });
	  
	  menuItemClearRecord.setOnAction(e -> {
	  	tableView.getItems().clear();
	  	tableView.refresh(); 	
	  });
	   
	  BorderPane pane = new BorderPane();
	  pane.setTop(hbox);
	  pane.setCenter(tableView);
	  //pane.setBottom(flowPane);	    
	    
	  Scene scene = new Scene(pane, 950, 250);  
	  primaryStage.setTitle("Library Server"); // Set the window title
	  primaryStage.setScene(scene); // Place the scene in the window
	  primaryStage.show(); // Display the window
	  new Thread(() -> {
      
      
      
		  try {
			  // Create a server socket
			  ServerSocket serverSocket = new ServerSocket(8000);
			  // Listen for a connection request
			  Socket socket = serverSocket.accept();
			  // Create data input and output streams
			  DataInputStream inputFromClient = new DataInputStream(
					  socket.getInputStream());
			  DataOutputStream outputToClient = new DataOutputStream(
					  socket.getOutputStream());
		  }
		  catch(IOException ex) {
			  ex.printStackTrace();
		  }
      
      
	  }).start();
  	}
    
  public static class LibraryLog {
	    private final SimpleStringProperty firstName;
	    private final SimpleStringProperty lastName;
	    private final SimpleStringProperty bookName;
	    private final SimpleStringProperty isbn;
	    private final SimpleStringProperty email;
	    private final SimpleStringProperty phoneNumber;
	    private java.util.Date dateCreated;

	    private LibraryLog(String firstName, String lastName, String bookName, String isbn, String email,String phoneNumber) {
	      this.firstName = new SimpleStringProperty(firstName);
	      this.lastName = new SimpleStringProperty(lastName);
	      this.bookName = new SimpleStringProperty(bookName);
	      this.isbn = new SimpleStringProperty(isbn);
	      this.email = new SimpleStringProperty(email);
	      this.phoneNumber = new SimpleStringProperty(phoneNumber);
	      dateCreated = new java.util.Date();
	    }

	    public String getFirstName() {
	      return firstName.get();
	    }

	    public void setFirstName(String firstName) {
	      this.firstName.set(firstName);
	    }

	    public String getLastName() {
	      return lastName.get();
	    }

	    public void setLastName(String lastName) {
	      this.lastName.set(lastName);
	    }
	    
	    public String getBookName() {
	        return bookName.get();
	      }
	    
	    public void setBookName(String bookName) {
	        this.bookName.set(bookName);
	      }
	    
	    public String getISBN() {
	        return isbn.get();
	      }
	    
	    public void setISBN(String isbn) {
	        this.isbn.set(isbn);
	      }
	    
	    public String getEmail() {
	        return email.get();
	      }
	    
	    public void setEmail(String email) {
	        this.email.set(email);
	      }
	    
	    public String getPhoneNumber() {
	        return phoneNumber.get();
	      }
	    
	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber.set(phoneNumber);
	      }
	    
	    public java.util.Date getDateCreated(){
			return dateCreated;
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