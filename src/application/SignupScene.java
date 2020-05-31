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
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
		
		gp.setPadding(new Insets(10,10,10,10));
		gp.setVgap(5);
		gp.setHgap(5);
		
		gp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
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
		PasswordField pt = new PasswordField();
		PasswordField cpt = new PasswordField();
		
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
					error.setText("*Username can only contain letters and numbers and be of length 6 to 20");
				} else if(!InputValidator.password(pt.getText())) {
					error.setVisible(true);
					
					error.setText("*Password can only contain letters and numbers\n*password must contain at least one upper and one lower case letter\n*password must contain at least one number\n*password must be of length 6 to 20");
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
		
		submit.setStyle("-fx-background-color: #00A8F3;");
		submit.setFont(Font.font("Verdana"));
		submit.setTextFill(Color.WHITE);
		
		signup.setStyle("-fx-background-color: #00A8F3;");
		signup.setFont(Font.font("Verdana"));
		signup.setTextFill(Color.WHITE);
		
		DropShadow borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.web("0xFF95FC"));
		borderGlow.setWidth(0);
		borderGlow.setHeight(0);
		submit.setEffect(borderGlow);
		signup.setEffect(borderGlow);
		
		
		HBox hb = new HBox();
		hb.setPadding(new Insets(10,10,10,10));
		hb.setSpacing(10);
		
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
