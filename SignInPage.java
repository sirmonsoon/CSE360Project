package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SignInPage extends GridPane {
	private String firstName;
	private String lastName;
	private int userID;
	private String password;
	private String payment;
	//private UserAccount user;
	boolean changed = false;

	
	public SignInPage() {
		
		Text title = new Text();
		Text t1 = new Text("Please sign in to view account details");
		Text t2 = new Text("Please enter the email associated with your account");
		title.setText(t1.getText());
		Text email = new Text("Email: ");       
	      
	    //creating label password 
		Text text2 = new Text();
	    Text pw = new Text("Password: "); 
  
	    Text login = new Text("Log In");
	    Text signup = new Text("Sign Up");
	    Text submit = new Text("Submit Password Request");
	    Text forgot = new Text("Forgot Password");
	    Text clear = new Text("Clear");
	    Text _return = new Text("Back to Login");
	    Text comments = new Text("Comments: ");
	    
	    text2.setText(pw.getText());
        //Creating Text Filed for email        
	    TextField textField1 = new TextField();       
	    
	    //Creating Text Filed for password        
	    TextField textField2 = new TextField();  
	     
	    //Creating Buttons 
	    Button btn1 = new Button();
	    btn1.setText(login.getText());
	    Button btn2 = new Button();
	    btn2.setText(signup.getText());
	    Button btn3 = new Button();
	    btn3.setText(forgot.getText());
	    
	    //Setting size for the pane  
	    this.setMinSize(400, 200); 
	     
	    //Setting the padding  
	    this.setPadding(new Insets(10, 10, 10, 10)); 
	    
	    //Setting the vertical and horizontal gaps between the columns 
	    this.setVgap(5); 
	    this.setHgap(5);       
	    
	    //Setting the Grid alignment 
	    this.setAlignment(Pos.TOP_CENTER); 
 
	    //Arranging all the nodes in the grid 
 
	    //login button and submit password request button
	    btn1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (btn1.getText() == login.getText()) {
					user();
				}
				else {
					// send password link to email
				}
			}
	    	
	    });
	    
	    //sign up button and clear button
	    btn2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (btn2.getText() == signup.getText()) {
					
				}
				textField1.clear();
				textField2.clear();
			}
	    	
	    });
	    
	    //forgot button
	    btn3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (changed == false) {
					changed = true;
					title.setText(t2.getText());
					text2.setText(comments.getText());
					btn1.setText(submit.getText());
					btn2.setText(clear.getText());
					btn3.setText(_return.getText());
				} 
				else {
					changed = false;
					title.setText(t1.getText());
					text2.setText(pw.getText());
					btn1.setText(login.getText());
					btn2.setText(signup.getText());
					btn3.setText(forgot.getText());
				}
				textField1.clear();
				textField2.clear();
			}
	    	
	    });	    


	    this.add(title,  1,  0);
	    this.add(email, 0, 1); 
	    this.add(textField1, 1, 1); 
		this.add(text2, 0, 2);       
		this.add(textField2, 1, 2); 
	    this.add(btn1, 1, 3); 
	    this.add(btn2, 3, 1);
	    this.add(btn3,  3,  2);
	   
	}
	
	
	private void user() {
	    //Setting size for the pane  
	    this.setMinSize(400, 200); 
	     
	    //Setting the padding  
	    this.setPadding(new Insets(10, 10, 10, 10)); 
	    
	    //Setting the vertical and horizontal gaps between the columns 
	    this.setVgap(5); 
	    this.setHgap(5);       
	    
	    //Setting the Grid alignment 
	    this.setAlignment(Pos.TOP_CENTER); 
	    
	    
	}
	
	private void setFirstName(String name) {
		firstName = name;
	}
	
	private String getFirstName() {
		return firstName;
	}
	
	private void setLastName(String name) {
		lastName = name;
	}
	
	private String getLastName() {
		return lastName;
	}
	
	private void setUserID(int id) {
		userID = id;
	}
	
	private int getUserID() {
		return userID;
	}
	
	private void setPassword(String pw) {
		password = pw;
	}
	
	private String getPassword() {
		return password;
	}
	
	private void setPayment(String pymnt) {
		payment = pymnt;
	}
	
	private String getPayment() {
		return payment;
	}

	protected void resetPassword() {
		
	}
	
	public void pageLinker() {
		
	}
	
	
}
