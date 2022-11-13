package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SignInPage extends VBox {
	private boolean used = false;
	private OrderPage opage;

	// 0 for not logged in, 1 for logged in
	public int loggedIn = 0;

	public void homepage() throws FileNotFoundException {
		this.getChildren().clear();
		loggedIn = 0;
		GridPane pane = new GridPane(); 
		
		
		Image image = new Image(new FileInputStream("src\\application\\pizza.png"));
		ImageView view = new ImageView(image);
		view.setFitHeight(100); 
	    view.setFitWidth(100);
	    
	    //title of the pizzeria with a big text font and in maroon color
	    Label top = new Label("Sun Devils Pizzeria");
	    top.setStyle("-fx-font: 60 arial; -fx-text-fill: maroon;");
		
	    // hbox for sun devils pizza logo title
	    HBox hbox = new HBox();
	    hbox.getChildren().add(view);
	    hbox.getChildren().add(top);
	    
		//vbox for login page
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		
		//used in case user input is wrong
		Label error = new Label();
		error.setStyle("-fx-font: 20 arial");
		
		Label signLabel = new Label("Sign In");
		signLabel.setStyle("-fx-font: 40 arial; -fx-text-fill: rgba(0,178,255,1);");
		signLabel.setUnderline(true);

		Label userLabel = new Label("Username (ASU ID)");
		userLabel.setStyle("-fx-font: 20 arial");
		TextField userTF = new TextField();
		
		Label passwordLabel = new Label("Password");
		passwordLabel.setStyle("-fx-font: 20 arial");
		TextField passwordTF = new TextField();
		
		Label forgotLabel = new Label("Forgot Password");
		forgotLabel.setStyle("-fx-font: 20 arial; -fx-background-color: rgba(0,178,255,0.5);");
		
		Label loginLabel = new Label("Login");
		loginLabel.setStyle("-fx-font: 20 arial");
		
		Label signupLabel = new Label("New? Create User");
		signupLabel.setStyle("-fx-font: 20 arial");
		
	    //Creating Buttons 
	    Button forgotBtn = new Button();
	    forgotBtn.setText(forgotLabel.getText());
	    Button loginBtn = new Button();
	    loginBtn.setText(loginLabel.getText());
	    Button signupBtn = new Button();
	    signupBtn.setText(signupLabel.getText());
	    
	    //adding them all together
	    vbox.getChildren().add(error);
	    vbox.getChildren().add(signLabel);
	    vbox.getChildren().add(userLabel);
	    vbox.getChildren().add(userTF);
	    vbox.getChildren().add(passwordLabel);
	    vbox.getChildren().add(passwordTF);
	    vbox.getChildren().add(forgotBtn);
	    vbox.getChildren().add(loginBtn);
	    vbox.getChildren().add(signupBtn);
	    vbox.setAlignment(Pos.CENTER);
	    
	    pane.add(vbox, 0, 0);
	    pane.setAlignment(Pos.CENTER);
	    pane.setPadding(new Insets(10, 10, 10, 10));
	    pane.setHgap(10);
   	    this.setSpacing(30);      
	    this.getChildren().add(hbox);
	    this.getChildren().add(pane);
	    
	    //setting button actions
	    forgotBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					forgotPage();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	
	    });
	    
	    loginBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String line = "";
			    File file = new File("src/application/database.txt");
			    Scanner reader;
			    String text = "";
				try {
					reader = new Scanner(file);
					while (reader.hasNextLine()) {
				    	line = reader.nextLine();
				    	if (line.endsWith(userTF.getText())) {
				    		text = userTF.getText();
				    		break;
				    	}
				    }
					String fName = "", lName = "", email = "", asuID = "";
					if (text.equals(userTF.getText()) & text.isEmpty() == false) {
						error.setText("");
						int i=0, j=0;
						while (i<=3) {
					    	j=0;
					    	if (i != 3) {
						    	while (line.charAt(j) != ' ') {
						    		j++;
						    	}	    	
						    	text = line.substring(0, j);
					    	}
					    	else {
					    		text = line.substring(0);
					    	}
					    	
					    	if (i==0) {
					    		fName = text;
					    	}
					    	if (i==1) {
					    		lName = text;
				
					    	}
					    	if (i==2) {
					    		email = text;
				
					    	}
					    	else {
					    		asuID = text;
				
					    	}
					    	j++;
					    	line = line.substring(j);
					    	i++;
					    }
						
						user(fName, lName, email, asuID);
						
					}
					else {
						error.setText("User not Found");
					}
					
					opage.updateSignIn(loggedIn);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	
	    });
	    signupBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					pageforsignup();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	
	    });  
	}
	public SignInPage(OrderPage p) throws FileNotFoundException {
		this.opage = p;
		homepage();
	}
	
	
	private void forgotPage() throws FileNotFoundException {
		this.getChildren().clear();
		
		GridPane pane = new GridPane();
		Image image = new Image(new FileInputStream("src\\application\\pizza.png"));
		ImageView view = new ImageView(image);
		view.setFitHeight(100); 
	    view.setFitWidth(100);
	    
	    //title of the pizzeria with a big text font and in maroon color
	    Label top = new Label("Sun Devils Pizzeria");
	    top.setStyle("-fx-font: 60 arial; -fx-text-fill: maroon;");
		
	    // hbox for sun devils pizza logo title
	    HBox hbox = new HBox();
	    hbox.getChildren().add(view);
	    hbox.getChildren().add(top);
	    
	    // vbox for forgot login page
	    VBox vbox = new VBox();
	    vbox.setSpacing(5);
	    
	    Label error = new Label();
	    error.setStyle("-fx-font: 20 arial");
	    
	    Label email = new Label("Email");
	    email.setStyle("-fx-font: 20 arial");
	    TextField emailTF = new TextField();
	    
	    Label submit = new Label("Submit Password Request");
	    submit.setStyle("-fx-font: 20 arial");
	    
	    Label response = new Label();
	    response.setStyle("-fx-font: 20 arial");
	    
	    //create buttons
	    Button submitBtn = new Button(submit.getText());
	    Button homeBtn = new Button("Home");
		homeBtn.setStyle("-fx-font: 40 arial");

	    vbox.getChildren().add(error);
	    vbox.getChildren().add(homeBtn);
	    vbox.getChildren().add(email);
	    vbox.getChildren().add(emailTF);
	    vbox.getChildren().add(submitBtn);
	    vbox.getChildren().add(response);
	    vbox.setAlignment(Pos.CENTER);
	    
	    pane.add(vbox, 0, 0);
	    pane.setAlignment(Pos.CENTER);
	    pane.setPadding(new Insets(10, 10, 10, 10));
	    pane.setHgap(10);
   	    this.setSpacing(30);      
	    this.getChildren().add(hbox);
	    this.getChildren().add(pane);
	    
	    submitBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (emailTF.getText().isEmpty()) {
					error.setText("No Email Typed");
				}
				else {
					error.setText("");
					response.setText("Password Reset Instructions Sent to Email if it exists");
				}
			}
	    	
	    });
	    
	    homeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					homepage();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	
	    });
	}
	
	
	private void pageforsignup() throws FileNotFoundException {
		this.getChildren().clear();
		
		// used variable is to ensure that user does not accidentally write same account over and over again
		used = false;
		
		GridPane pane = new GridPane();
		Image image = new Image(new FileInputStream("src\\application\\pizza.png"));
		ImageView view = new ImageView(image);
		view.setFitHeight(100); 
	    view.setFitWidth(100);
	    
	    //title of the pizzeria with a big text font and in maroon color
	    Label top = new Label("Sun Devils Pizzeria");
	    top.setStyle("-fx-font: 60 arial; -fx-text-fill: maroon;");
		
	    // hbox for sun devils pizza logo title
	    HBox hbox = new HBox();
	    hbox.getChildren().add(view);
	    hbox.getChildren().add(top);
	    
	    // vbox for signup page
	    VBox vbox = new VBox();
	    vbox.setSpacing(5);
   
	    Label fname = new Label("First Name: ");
	    fname.setStyle("-fx-font: 20 arial");
	    TextField fnameTF = new TextField();
	    
	    Label lname = new Label("Last Name: ");
	    lname.setStyle("-fx-font: 20 arial");
	    TextField lnameTF = new TextField();
	    
	    Label email = new Label("Email: ");
	    email.setStyle("-fx-font: 20 arial");
	    TextField emailTF = new TextField();
	    
	    Label asuid = new Label("ASU ID: ");
	    asuid.setStyle("-fx-font: 20 arial");
	    TextField asuidTF = new TextField();

	    HBox fnameHbox = new HBox();
	    fnameHbox.getChildren().add(fname);
	    fnameHbox.getChildren().add(fnameTF);
	    
	    HBox lnameHbox = new HBox();
	    lnameHbox.getChildren().add(lname);
	    lnameHbox.getChildren().add(lnameTF);

	    HBox emailHbox = new HBox();
	    emailHbox.getChildren().add(email);
	    emailHbox.getChildren().add(emailTF);

	    HBox asuidHbox = new HBox();
	    asuidHbox.getChildren().add(asuid);
	    asuidHbox.getChildren().add(asuidTF);

	    Label response = new Label();
	    response.setStyle("-fx-font: 20 arial");
	    
	    //create buttons
	    Button createAcc = new Button("Create Account");
	    Button homeBtn = new Button("Home");
		homeBtn.setStyle("-fx-font: 40 arial");

	    vbox.getChildren().add(homeBtn);
	    vbox.getChildren().add(fnameHbox);
	    vbox.getChildren().add(lnameHbox);
	    vbox.getChildren().add(emailHbox);
	    vbox.getChildren().add(asuidHbox);
	    vbox.getChildren().add(createAcc);
	    vbox.getChildren().add(response);
	    vbox.setAlignment(Pos.CENTER);
	    
	    pane.add(vbox, 0, 0);
	    pane.setAlignment(Pos.CENTER);
	    pane.setPadding(new Insets(10, 10, 10, 10));
	    pane.setHgap(10);
   	    this.setSpacing(30);      
	    this.getChildren().add(hbox);
	    this.getChildren().add(pane);
	    
	    createAcc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (used == false) {
					used = true;
					FileWriter fwrite;
					try {
						fwrite = new FileWriter("src/application/database.txt", true);
						fwrite.write(fnameTF.getText());
						fwrite.write(" ");
						fwrite.write(lnameTF.getText());
						fwrite.write(" ");
						fwrite.write(emailTF.getText());
						fwrite.write(" ");
						fwrite.write(asuidTF.getText());
						fwrite.write("\n");
						fwrite.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
					response.setText("Account Successfully Created!");
				}
				else {
					response.setText("Must go back home to add another account");
				}
			}
	    	
	    });
	    
	    homeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					homepage();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	
	    });
	    
	}

	private void user(String firstName, String lastName, String email, String asuID) throws FileNotFoundException {
		this.getChildren().clear();
		loggedIn = 1;
		
		GridPane pane = new GridPane(); 
		
		Image image = new Image(new FileInputStream("src\\application\\pizza.png"));
		ImageView view = new ImageView(image);
		view.setFitHeight(100); 
	    view.setFitWidth(100);
	    
	    //title of the pizzeria with a big text font and in maroon color
	    Label top = new Label("Sun Devils Pizzeria");
	    top.setStyle("-fx-font: 60 arial; -fx-text-fill: maroon;");
		
	    // hbox for sun devils pizza logo title
	    HBox hbox = new HBox();
	    hbox.getChildren().add(view);
	    hbox.getChildren().add(top);
	    
		//vbox for Account page
		VBox accInfo = new VBox();
		accInfo.setSpacing(5);
		
		VBox orderHist = new VBox();
		orderHist.setSpacing(5);

		Label welcome = new Label("Welcome Back, " + firstName + "!");
		welcome.setStyle("-fx-font: 30 arial; -fx-text-fill: rgba(0,178,255,1);");
		welcome.setUnderline(true);	
		
		Label account = new Label("Account Info");
	    account.setStyle("-fx-font: 20 arial");
	    account.setUnderline(true);;
	    
	    Label fnameLabel = new Label("First Name:  ");
	    TextField fnameTF = new TextField();
	    fnameTF.setText(firstName);
	    
	    Label lnameLabel = new Label("Last Name:   ");
	    TextField lnameTF = new TextField();
	    lnameTF.setText(lastName);
	    
	    Label emailLabel = new Label("Email:           ");
	    TextField emailTF = new TextField();
	    emailTF.setText(email);
	    
	    Label asuidLabel = new Label("ASU ID:        ");
	    TextField asuidTF = new TextField();
	    asuidTF.setText(asuID);
	    
	    HBox fnameHbox = new HBox();
	    fnameHbox.getChildren().add(fnameLabel);
	    fnameHbox.getChildren().add(fnameTF);
	    fnameHbox.setAlignment(Pos.CENTER);
	    
	    HBox lnameHbox = new HBox();
	    lnameHbox.getChildren().add(lnameLabel);
	    lnameHbox.getChildren().add(lnameTF);
	    lnameHbox.setAlignment(Pos.CENTER);

	    HBox emailHbox = new HBox();
	    emailHbox.getChildren().add(emailLabel);
	    emailHbox.getChildren().add(emailTF);
	    emailHbox.setAlignment(Pos.CENTER);

	    HBox asuidHbox = new HBox();
	    asuidHbox.getChildren().add(asuidLabel);
	    asuidHbox.getChildren().add(asuidTF);  
	    asuidHbox.setAlignment(Pos.CENTER);
	    
	    Button home = new Button("Home");
		home.setStyle("-fx-font: 30 arial");
		
		//adding order history info on right side 
		Label titleLabel = new Label("Order History");
		titleLabel.setStyle("-fx-font: 30 arial");

		Label pzLabel = new Label ("Pizza Ordered: ");
		pzLabel.setStyle("-fx-font: 20 arial");

	    Label stLabel = new Label ("   Order Status: ");
	    stLabel.setStyle("-fx-font: 20 arial");

	    HBox titleHbox = new HBox();
	    titleHbox.setSpacing(20);
	    
	    HBox odHbox = new HBox();
	    odHbox.setSpacing(20);
	    
	    opage.stat.updatepage(opage.stat.order.getText());


	    Label pizzaLabel = new Label();
	    pizzaLabel.setStyle("-fx-font: 20 arial");

	    pizzaLabel.setText("No Order");
	    
	    if (opage.stat.order.getText().isEmpty() == false) {
		    pizzaLabel.setText(opage.stat.order.getText().substring(13));
	    }
	    
	    titleHbox.getChildren().add(pzLabel);
	    titleHbox.getChildren().add(pizzaLabel);
   
	    odHbox.getChildren().add(stLabel);
	    
	    Label statuslabel = new Label();
	    statuslabel.setStyle("-fx-font: 20 arial");

	    if (opage.stat.order.getText().isEmpty()) {
	    	statuslabel.setText("No Order");
	    }
	    else {
	    	statuslabel = opage.stat.status;
	    }
	    
    	odHbox.getChildren().add(statuslabel);

	    Button refresh = new Button("Refresh Page");
	    refresh.setStyle("-fx-font: 20 arial");
	    
	    home.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					homepage();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	
	    });

	    refresh.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					user(firstName, lastName, email, asuID);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	
	    });

	    accInfo.getChildren().add(home);
	    accInfo.getChildren().add(welcome);
	    accInfo.getChildren().add(account);
	    accInfo.getChildren().add(fnameHbox);
	    accInfo.getChildren().add(lnameHbox);
	    accInfo.getChildren().add(emailHbox);
	    accInfo.getChildren().add(asuidHbox);
	    accInfo.setAlignment(Pos.CENTER);
	    
	    orderHist.getChildren().add(refresh);
	    orderHist.getChildren().add(titleLabel);
	    orderHist.getChildren().add(titleHbox);
	    orderHist.getChildren().add(odHbox);
	    orderHist.setAlignment(Pos.CENTER);
	    
	    pane.add(accInfo, 0, 0);
	    pane.add(orderHist,  3,  0);
	    pane.setAlignment(Pos.CENTER);
	    pane.setPadding(new Insets(10, 10, 10, 10));
	    pane.setHgap(10);
   	    this.setSpacing(30);      
	    this.getChildren().add(hbox);
	    this.getChildren().add(pane);
	}
	
}
