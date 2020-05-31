package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
		ImageView img = null;
		try {
			FileInputStream fis = new FileInputStream("../PassApp/logo.png");
			Image i = new Image(fis, 88, 43, false, false);
			img = new ImageView(i);
			
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		gp.setPadding(new Insets(10,10,10,10));
		gp.setVgap(5);
		gp.setHgap(5);
		
		gp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		
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
					if(controller.login(new User_Object(ut.getText(), pt.getText(), null, null))) {
						controller.mainScene();
					}
					
					error.setVisible(true);
					error.setText("*The Username or Password does not exist");
					
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
		
		GridPane.setConstraints(img, 2, 0);
		GridPane.setConstraints(un, 1, 1);
		GridPane.setConstraints(ut, 2, 1);
		GridPane.setConstraints(pl, 1, 2);
		GridPane.setConstraints(pt, 2, 2);
		GridPane.setConstraints(hb, 2, 3, 2, 1);
		GridPane.setConstraints(error, 1, 4, 2, 1);
		
		gp.getChildren().addAll(img, un,ut,pl,pt,hb);
	}
}
