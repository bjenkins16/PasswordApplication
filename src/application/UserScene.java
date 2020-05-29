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

public class UserScene extends Scene {
	private GridPane gp;
	private Controller controller;
	
	public UserScene(Controller controller, GridPane gp) {
		super(gp);
		
		this.gp = gp;
		this.controller = controller;
		
		setup();
	}
	
	private void setup() {
		User_Object uob = controller.getUser();
		
		Label ul = new Label("Username: ");
		Label pl = new Label("Password: ");
		Label uname = new Label(uob.getUname());
		Label pwd = new Label(uob.getPwd());
		Label new_ul = new Label("new Username");
		Label new_pl = new Label("new Password");
		Label error = new Label();
		TextField ut = new TextField();
		TextField pt = new TextField();
		
		Button submit = new Button("Submit");
		Button edit = new Button("Edit");
		Button cancel = new Button("Cancel");
		Button back = new Button("Back");
		
		
		new_ul.setVisible(false);
		new_pl.setVisible(false);
		ut.setVisible(false);
		pt.setVisible(false);
		submit.setVisible(false);
		cancel.setVisible(false);
		error.setVisible(false);
		
		edit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new_ul.setVisible(true);
				new_pl.setVisible(true);
				ut.setVisible(true);
				pt.setVisible(true);
				submit.setVisible(true);
				cancel.setVisible(true);
				back.setVisible(false);
				edit.setVisible(false);
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.mainScene();
			}
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new_ul.setVisible(false);
				new_pl.setVisible(false);
				ut.setVisible(false);
				pt.setVisible(false);
				submit.setVisible(false);
				cancel.setVisible(false);
				back.setVisible(true);
				edit.setVisible(true);
				error.setVisible(false);
			}
		});
		
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				User_Object old_uob = controller.getUser();
				User_Object new_uob = new User_Object(old_uob.getUname(), old_uob.getPwd(), old_uob.getFname(), old_uob.getLname());
				
				String new_uname, new_pwd;
				new_uname = ut.getText();
				new_pwd = pt.getText();
				
				if(!validate(new_uname, new_pwd)) {
					error.setText("*Username and Password must only contain upper and lower case characters and numbers of length 6 to 20");
				} else {
				
					if(new_uname.length() > 0 && new_pwd.length() > 0 && !controller.userExists(new_uname)) {
						new_uob.setUname(new_uname);
						new_uob.setPwd(new_pwd);
						controller.updateUser(old_uob, new_uob);
						controller.userScene();
					} else if (new_uname.length() > 0 && !controller.userExists(new_uname)) {
						new_uob.setUname(new_uname);
						controller.updateUser(old_uob, new_uob);
						controller.userScene();
					} else if(new_uname.length() > 0 && controller.userExists(new_uname)) {
						error.setText("*Username already exists");
					} else if (new_pwd.length() > 0) {
						new_uob.setPwd(new_pwd);
						controller.updateUser(old_uob, new_uob);
						controller.userScene();
					} else {
						controller.userScene();
					}
				}
			}
		});
		
		
		HBox hb = new HBox();
		hb.setPadding(new Insets(10,10,10,10));
		hb.setSpacing(5);
		
		hb.getChildren().addAll(edit, back, submit, cancel);
		
		GridPane.setConstraints(ul, 0, 0);
		GridPane.setConstraints(uname, 1, 0);
		GridPane.setConstraints(new_ul, 2, 0);
		GridPane.setConstraints(ut, 3, 0);
		GridPane.setConstraints(pl, 0, 1);
		GridPane.setConstraints(pwd, 1, 1);
		GridPane.setConstraints(new_pl, 2, 1);
		GridPane.setConstraints(pt, 3, 1);
		GridPane.setConstraints(hb, 0, 2, 4, 1);
		GridPane.setConstraints(error, 2, 2, 2, 1);
		
		gp.getChildren().addAll(ul, pl, uname, pwd, new_ul, new_pl, ut, pt, hb, error);
	}
	
	private boolean validate(String uname, String pwd) {
		if(uname.length() > 0 && pwd.length() > 0) {
			return InputValidator.username(uname) && InputValidator.password(pwd);
		} else if (uname.length() > 0) {
			return InputValidator.username(uname);
		} else if (pwd.length() > 0) {
			return InputValidator.password(pwd);
		}
		
		return true;
	}
	
}
