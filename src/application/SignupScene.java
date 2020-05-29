package application;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class SignupScene extends Scene {
	private GridPane gp;
	private Controller controller;
	
	
	public SignupScene(Controller controller, GridPane gp) {
		super(gp);
		this.gp = gp;
		this.controller = controller;
		
		setupScene();
	}
	
	private void setupScene() {
		gp.setHgap(5);
		gp.setVgap(5);
		
		Label fl = new Label("First Name ");
		Label ll = new Label("Last Name ");
		Label un = new Label("Username ");
		Label pl = new Label("Password ");
		Label cpl = new Label("Confirm Password ");
		Label error = new Label();
		
		error.setVisible(false);
		
		TextField ft = new TextField();
		TextField lt = new TextField();
		TextField ut = new TextField();
		TextField pt = new TextField();
		TextField cpt = new TextField();
		
		Button submit = new Button("Submit");
		submit.setOnAction(new EventHandler<ActionEvent> () {
			@Override 
			public void handle(ActionEvent e) {
				if(ft.getLength() == 0 || lt.getLength() == 0 || ut.getLength() == 0 || pt.getLength() == 0 || cpt.getLength() == 0) {
					error.setVisible(true);
					error.setText("*All fields must be filled");
				} else if(!pt.getText().equals(cpt.getText())) {
					error.setVisible(true);
					error.setText("*Password fields do not match");
				} else if(!InputValidator.username(ut.getText())){
					error.setVisible(true);
					error.setText("*Password can only contain letters and numbers and be of length 6 to 20");
				} else if(!InputValidator.password(pt.getText())) {
					error.setVisible(true);
					error.setText("*Username can only contain letters and numbers and be of length 6 to 20");
				} else if(controller.userExists(ut.getText())){
					error.setText("*Username already exists");
				} else {
					controller.addUser(new User_Object(ut.getText(), pt.getText(), ft.getText(), lt.getText()));
				}
			}
		});
		
		
		Button signup = new Button("Back");
		signup.setOnAction(new EventHandler<ActionEvent> () {
			@Override 
			public void handle(ActionEvent e) {
				controller.loginScene();
			}
		});
		
		
		HBox hb = new HBox();
		hb.setPadding(new Insets(10,10,10,10));
		
		hb.getChildren().addAll(submit, signup);
		
		GridPane.setConstraints(fl, 0, 0);
		GridPane.setConstraints(ft, 1, 0);
		GridPane.setConstraints(ll, 0, 1);
		GridPane.setConstraints(lt, 1, 1);
		GridPane.setConstraints(un, 0, 2);
		GridPane.setConstraints(ut, 1, 2);
		GridPane.setConstraints(pl, 0, 3);
		GridPane.setConstraints(pt, 1, 3);
		GridPane.setConstraints(cpl, 0, 4);
		GridPane.setConstraints(cpt, 1, 4);
		GridPane.setConstraints(hb, 1, 5);
		GridPane.setConstraints(error, 1, 6);
		
		gp.getChildren().addAll(fl,ft,ll,lt,un,ut,pl,pt,cpl,cpt,hb,error);
		
		
		
	}
}
