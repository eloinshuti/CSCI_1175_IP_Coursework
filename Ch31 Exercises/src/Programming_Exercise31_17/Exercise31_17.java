/*
 * Eloi Nshuti
 * 02/24/2023
 * Programming Exercise 31-17
 */
package Programming_Exercise31_17;

import java.text.DecimalFormat;
import javafx.geometry.HPos;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise31_17 extends Application {
  private TextField tfInvestmentAmount = new TextField();
  private TextField tfNumberOfYears = new TextField();
  private TextField tfAnnualInterestRate = new TextField();
  private TextField tfFutureValue = new TextField();
  
    
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    MenuBar menuBar = new MenuBar();    
    
    Menu menuOperation = new Menu("Operation");
    menuBar.getMenus().addAll(menuOperation);
    
    MenuItem menuItemCalculate = new MenuItem("Calculate");
    MenuItem menuItemExit = new MenuItem("Exit");
    menuOperation.getItems().addAll(menuItemCalculate, menuItemExit);
    
    tfInvestmentAmount.setAlignment(Pos.BOTTOM_RIGHT);
    tfNumberOfYears.setAlignment(Pos.BOTTOM_RIGHT);
    tfAnnualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
    tfFutureValue.setAlignment(Pos.BOTTOM_RIGHT);
    
    
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    pane.add(new Label("Investment Amount:"), 0, 0);
    pane.add(tfInvestmentAmount, 1, 0);
    pane.add(new Label("Number of Years:"), 0, 1);
    pane.add(tfNumberOfYears, 1, 1);
    pane.add(new Label("Annual Interest Rate:"), 0, 2);
    pane.add(tfAnnualInterestRate, 1, 2);
    pane.add(new Label("Future value:"), 0, 3);
    pane.add(tfFutureValue, 1, 3);
    Button btCalculate = new Button("Calculate");
    pane.add(btCalculate, 1, 4);
    GridPane.setHalignment(btCalculate, HPos.RIGHT);


    VBox vBox = new VBox(5);
    
    vBox.getChildren().addAll(menuBar,pane);
    vBox.setAlignment(Pos.CENTER);
    
    Scene scene = new Scene(vBox, 300, 200);  
    primaryStage.setTitle("Exercise31_17"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
    
    // Handle menu actions
    menuItemCalculate.setOnAction(e -> perform());
    menuItemExit.setOnAction(e -> System.exit(0));
    
    // Handle button actions
    btCalculate.setOnAction(e -> perform());

  }

  private void perform() {
    double investmentAmount = Double.parseDouble(tfInvestmentAmount.getText());
    int numberOfYears = Integer.parseInt(tfNumberOfYears.getText());
    double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText());
    double futureValue = investmentAmount*Math.pow((1 + (annualInterestRate/12)/100),(numberOfYears*12));
    DecimalFormat twoDecimals = new DecimalFormat("##.00");
    tfFutureValue.setText(twoDecimals.format(futureValue) + "");
  };

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}