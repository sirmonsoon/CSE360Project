package application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;



public class OrderStatus extends VBox {
	
	private TextArea order;
	private Label st;
	private Label status;
	

	
	private int startTime = 60;
	private Timeline timeline;
	private int timeSeconds = startTime;
	
	public OrderStatus() throws FileNotFoundException {
		
		// gridpane that will contain the order summary and the current order status
		GridPane gp = new GridPane();
		
		Label l1 = new Label("Your Pizza");
		l1.setStyle("-fx-font: 20 arial");
		
		//vbox will contain the order summary
		VBox vbox = new VBox();
		vbox.setSpacing(5);

		order = new TextArea();
		order.setMaxSize(300,100);
		
		vbox.getChildren().add(l1);
		vbox.getChildren().add(order);
		vbox.setAlignment(Pos.CENTER);

		//hbox will contain the order status
		HBox hbox = new HBox();
		
		status = new Label();
		st = new Label("Order Status: ");
		status.setStyle("-fx-font: 25 arial");
		st.setStyle("-fx-font: 25 arial; -fx-text-fill: maroon;");
		
		
		hbox.getChildren().add(st);
		hbox.getChildren().add(status);
		
		
		gp.add(vbox, 0, 0);
		gp.add(hbox, 3, 0);
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(10, 10, 10, 10));
		gp.setHgap(10);
		
		//will show the sun devils pizza logo on the top left corner
		Image image = new Image(new FileInputStream("C:\\Users\\eduar\\Desktop\\pizza.png"));
		ImageView view = new ImageView(image);
		view.setFitHeight(100); 
	    view.setFitWidth(100);
	    
	  //title of the pizzeria with a big text font and in maroon color
	    Label title = new Label("Sun Devils Pizzeria");
	    title.setStyle("-fx-font: 60 arial; -fx-text-fill: maroon;");
	    
	    //hbox will contain the image and title 
	    HBox top = new HBox();
	    top.getChildren().add(view);
	    top.getChildren().add(title);
	    
	    this.setSpacing(30);
	    this.getChildren().add(top);
		this.getChildren().add(gp);

	}
	
	
	//updates the current status of the pizza. is called from another class
	public void updatepage(String pizza) {
		order.setText(pizza);
		displayStatus();
	}
	
	// implements a timer that will countdown timer from 60 seconds
	public void displayStatus() {
		if(timeline != null) {
			timeline.stop();
		}
		timeSeconds = startTime;
		status.setText(Integer.toString(timeSeconds));
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new TimeHandler()));
		
		timeline.playFromStart();
	}
	
	// will update the current status depending the the time left;
	private class TimeHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event) {
			timeSeconds--;
			
			if(timeSeconds <= 60) {
				status.setText("Ready To Cook\n");
			}
			
			if (timeSeconds <= 30) {
				status.setText("Cooking\n");
			}
			
			if(timeSeconds <= 0) {
				timeline.stop();
				status.setText("Ready\n");
			}
		
		}
	}
}
	
