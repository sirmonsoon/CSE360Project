package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.NumberFormat;

public class OrderPage extends VBox{

	private CheckBox addMushrooms;
	private CheckBox addOlives;
	private CheckBox addOnions;
	private TextArea text;
	private ChoiceBox cb; 
	private String order;
	private double totalPrice;
	private int checkSignIn;
	
	
	public OrderStatus stat;

	public OrderPage(OrderStatus p) throws FileNotFoundException {
		// calls the OrderStatus class in order for it to be updated
		stat = p;
		
		checkSignIn = 1;
		
		//choice box that creates a drop down menu
		cb = new ChoiceBox(FXCollections.observableArrayList(
			    "Select Pizza", "Pepperoni", "Cheese", "Veggi"));
		
		cb.getSelectionModel().selectFirst();
		
	
		//gridpane that will hold 2 vbox's
		GridPane gp1 = new GridPane();
		gp1.setPadding(new Insets(10, 10, 10, 10));
		gp1.setHgap(50);
		gp1.setAlignment(Pos.BASELINE_CENTER);
		
		
		//nested vbox that will contain the drop-down menu and its label
		VBox vbox1 = new VBox();
		Label pT = new Label("Pizza Type");
		pT.setStyle("-fx-font: 24 arial; -fx-text-fill: maroon;"); // changes font size and text color
		vbox1.getChildren().add(pT);
		vbox1.getChildren().add(cb);
		vbox1.setPadding(new Insets(20,20,20,20));
		vbox1.setSpacing(15);
		gp1.add(vbox1, 0, 0);
		
		//nested vbox that will contain the list of topping's and its label
		VBox vbox2 = new VBox();
		Label label2 = new Label("Pizza Toppings");
		label2.setStyle("-fx-font: 24 arial; -fx-text-fill: maroon;");
		addMushrooms = new CheckBox("Mushrooms");
		addOlives = new CheckBox("Olives");
		addOnions = new CheckBox("Onions");
		vbox2.getChildren().add(label2);
		vbox2.getChildren().add(addMushrooms);
		vbox2.getChildren().add(addOlives);
		vbox2.getChildren().add(addOnions);
		vbox2.setSpacing(15);
		gp1.add(vbox2, 3, 0);
		
		//second gridpane will have the button and the order summary
		GridPane gp2 = new GridPane();
		gp2.setAlignment(Pos.CENTER);
		
		Button calculate = new Button("Place Order");
		calculate.setAlignment(Pos.CENTER);
		text = new TextArea();
		text.setMaxSize(400, 100);

		
		gp2.add(calculate, 0, 0);
		gp2.setHalignment(calculate, HPos.CENTER);
		gp2.add(text, 0, 1);
		
		//will show the sun devils pizza logo on the top left corner
		Image image = new Image(new FileInputStream("src\\application\\pizza.png"));
		ImageView view = new ImageView(image);
		view.setFitHeight(100); 
	    view.setFitWidth(100);
	    
	    //title of the pizzeria with a big text font and in maroon color
	    Label title = new Label("Sun Devils Pizzeria");
	    title.setStyle("-fx-font: 60 arial; -fx-text-fill: maroon;");
	    
	    HBox hbox = new HBox();
	    hbox.getChildren().add(view);
	    hbox.getChildren().add(title);
		
	    this.setSpacing(15);
		this.getChildren().add(hbox);
		this.getChildren().add(gp1);
		this.getChildren().add(gp2);
		
		calculate.setOnAction(new ButtonHandler());
	}
	
	
	public void Order() { //will display the order the user has selected
		String a = "";
		String b = "";
		String c = "";
		String d = "";
		
		//gets the pizza type selected from the drop-down menu
		String value = (String) cb.getSelectionModel().getSelectedItem();
		
		//depending on the pizza type selected it will change the a variable
		if(value.equalsIgnoreCase("Pepperoni")) {
			a = "Peperroni";
		}
		if(value.equalsIgnoreCase("Cheese")) {
			a = "Cheese";
		}
		if(value.equalsIgnoreCase("Vegatable")) {
			a = "Veggi";
		}
		
		order = "You selected " + a + " with extra ";
		text.setText(order);
		
		//check if the extra topping are selected
		if(addMushrooms.isSelected()==true) {
			b = "Mushrooms";
			text.appendText(b + ", ");
		}
		if(addOlives.isSelected()==true) {
			c = "Olives";
			text.appendText(c + ", ");
		}
		if(addOnions.isSelected()==true) {
			d = "Onions";
			text.appendText(d);
		}
	}
	
	public void updatePrice() { // similar to the Order function but generates the price of the pizza
		double a = 0;
		double b = 0;
		double c = 0;
		double d = 0;
		
		String value = (String) cb.getSelectionModel().getSelectedItem();
		if(value.equalsIgnoreCase("Pepperoni")) {
			a = 8;
		}
		if(value.equalsIgnoreCase("Cheese")) {
			a = 9;
		}
		if(value.equalsIgnoreCase("Vegatable")) {
			a = 10;
		}
		if(addMushrooms.isSelected()==true) {
			b = 1;
		}
		if(addOlives.isSelected()==true) {
			c = 1;
		}
		if(addOnions.isSelected()==true) {
			d = 1;
		}
		
		totalPrice = a + b + c + d;
		
		NumberFormat money = NumberFormat.getCurrencyInstance();
		String s = String.valueOf(money.format(totalPrice));
		text.appendText("\nTotal Price: " + s);
	}
	
	public void signIn() {
		if(checkSignIn == 0) {
			text.setText("Please sign in to Make an Order");
		}
	}
	
	public void pageLinker() {
		
	}
	

	private class ButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event) {
			//checks if user is logged in
			if(checkSignIn == 0) {
				text.setText("Please sign in to Make an Order");
			}
			String value = (String) cb.getSelectionModel().getSelectedItem();
			if(value.equals("Select Pizza")) {
				text.setText("Please select a pizza type!");
			}
			else { // will call functions upon the press of the order button
				Order();
				updatePrice();
				// will updated the order status page
				stat.updatepage(order);
			}
			
		}
	}
		
}
