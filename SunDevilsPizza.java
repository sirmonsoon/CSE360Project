package application;

	import javafx.application.Application;
	import javafx.stage.Stage;
	import javafx.scene.Scene;
	import javafx.scene.control.Tab;
	import javafx.scene.control.TabPane;
	import javafx.scene.layout.StackPane;



	public class SunDevilsPizza extends Application {
		private OrderPage OP;
		private OrderStatus OS;
		private SignInPage SI;
		@Override
		public void start(Stage primaryStage) {
			try {
				
				OS = new OrderStatus();
				OP = new OrderPage(OS);
				SI = new SignInPage(OP);
				
				StackPane root = new StackPane();
				
				TabPane tabPane = new TabPane();

			    Tab tab1 = new Tab();
			    tab1.setText("Order Page");
			    tab1.setContent(OP);

			    Tab tab2 = new Tab();
			    tab2.setText("Status Page");
			    tab2.setContent(OS);
			    
			    Tab tab3 = new Tab();
			    tab3.setText("View Account");
			    tab3.setContent(SI);
			    
			    tabPane.getSelectionModel().select(0);

			    tabPane.getTabs().addAll(tab1, tab2, tab3);
			    
			    root.getChildren().add(tabPane);


			    
				Scene scene = new Scene(root,700,500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());				
				primaryStage.setScene(scene);
				primaryStage.show();


			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			launch(args);
		}
	}

