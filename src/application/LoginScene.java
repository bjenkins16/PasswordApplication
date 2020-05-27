package application;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LoginScene extends Scene {
	private GridPane gp;
	private Controller controller;
	
	
	public LoginScene(Controller controller, GridPane gp) {
		super(gp);
		
		this.gp = gp;
		this.controller = controller;
		
		setupScene();
	}
	
	private void setupScene() {
		gp.setHgap(5);
		gp.setVgap(5);
		
		
		Label un = new Label("Username ");
		Label pl = new Label("Password ");
		
		Label error = new Label();
		error.setVisible(false);
		
		TextField ut = new TextField();
		PasswordField pt = new PasswordField();
		
		
		Button submit = new Button("Submit");
		submit.setOnAction(new EventHandler<ActionEvent> () {
			@Override 
			public void handle(ActionEvent e) {
				if(ut.getLength() == 0 || pt.getLength() == 0) {
					error.setVisible(true);
					error.setText("*All fields must be filled");
				} else {
					controller.login(new User_Object(ut.getText(), pt.getText(), null, null));
				}
			}
		});
		
		
		Button signup = new Button("Signup");
		signup.setOnAction(new EventHandler<ActionEvent> () {
			@Override 
			public void handle(ActionEvent e) {
				controller.signupScene();
			}
		});
		
		
		HBox hb = new HBox();
		hb.setPadding(new Insets(10,10,10,10));
		
		hb.getChildren().addAll(submit, signup);
		
		GridPane.setConstraints(un, 0, 0);
		GridPane.setConstraints(ut, 1, 0);
		GridPane.setConstraints(pl, 0, 1);
		GridPane.setConstraints(pt, 1, 1);
		GridPane.setConstraints(hb, 1, 2);
		GridPane.setConstraints(error, 1, 3);
		
		gp.getChildren().addAll(un,ut,pl,pt,hb);
	}
}
