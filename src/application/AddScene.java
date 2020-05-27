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

public class AddScene extends Scene {
	private GridPane gp;
	private Controller controller;
	
	public AddScene(Controller controller, GridPane gp) {
		super(gp);
		
		this.gp = gp;
		this.controller = controller;
		
		setup();
	}
	
	private void setup() {
		
		Label wl = new Label("Website");
		Label ul = new Label("Username");
		Label pl = new Label("Password");
		
		TextField wt = new TextField();
		TextField ut = new TextField();
		TextField pt = new TextField();
		
		Label error = new Label("***All fields must be filled!");
		
		error.setVisible(false);
		
		Button submit = new Button("Submit");
		Button back = new Button("Back");
		
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String web, uname, pwd;
				web = wt.getText();
				uname = ut.getText();
				pwd = pt.getText();
				
				if(web.length() <= 0 || uname.length() <= 0 || pwd.length() <= 0) {
					error.setVisible(true);
				} else {
					Web_Object wob = new Web_Object(web, uname, pwd, "");
					controller.insertRecord(wob);
					controller.mainScene();
				}
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent e) {
				controller.mainScene();
			}
		});
		
		HBox hb = new HBox();
		hb.setPadding(new Insets(10,10,10,10));
		hb.setSpacing(5);
		
		hb.getChildren().addAll(submit, back);
		
		GridPane.setConstraints(wl,0,0);
		GridPane.setConstraints(wt,1,0);
		GridPane.setConstraints(ul,0,1);
		GridPane.setConstraints(ut,1,1);
		GridPane.setConstraints(pl,0,2);
		GridPane.setConstraints(pt,1,2);
		GridPane.setConstraints(hb,1,3);
		GridPane.setConstraints(error,1,4);
		
		gp.getChildren().addAll(wl, ul, pl, wt, ut, pt, error, hb);

	}
}
