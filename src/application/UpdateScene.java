package application;

import java.util.ArrayList;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UpdateScene extends Scene{
	private GridPane gp;
	private Controller controller;
	private Web_Object wob;
	
	public UpdateScene(Controller controller, GridPane gp) {
		super(gp);
		
		this.gp = gp;
		this.controller = controller;
		
		setup();
	}
	
	//setup main scene
	private void setup() {
		
		TableView table = getTable();
		
		gp.setPadding(new Insets(10,10,10,10));
		gp.setVgap(5);
		gp.setHgap(5);
		
		gp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Button update = new Button("Update");
		Button cancel = new Button("Done");
		Button submit = new Button("Submit");
		Button cancel2 = new Button("Cancel");
		
		Label website = new Label("Website");
		Label username = new Label("Username");
		Label password = new Label("Password");
		
		website.setVisible(false);
		username.setVisible(false);
		password.setVisible(false);
		submit.setVisible(false);
		cancel2.setVisible(false);
		
		TextField web, uname, pwd;
		web = new TextField();
		uname = new TextField();
		pwd = new TextField();
		
		web.setVisible(false);
		uname.setVisible(false);
		pwd.setVisible(false);
		
		
		update.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent e) {
				wob = (Web_Object)table.getSelectionModel().getSelectedItem();
				
				if(wob != null) {
					web.setVisible(true);
					uname.setVisible(true);
					pwd.setVisible(true);
					website.setVisible(true);
					username.setVisible(true);
					password.setVisible(true);
					submit.setVisible(true);
					cancel2.setVisible(true);
				
					update.setVisible(false);
					cancel.setVisible(false);

					web.setText(wob.getWebsite());
					uname.setText(wob.getW_uname());
					pwd.setText(wob.getPwd());
				}
				
			}
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent e) {
				controller.mainScene();
			}
		});
		
		submit.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent e) {
				Web_Object wob_new = new Web_Object(web.getText(), uname.getText(), pwd.getText(), null);
				
				if(web.getText().length() <= 0) {
					wob_new.setWebsite(wob.getWebsite());
				}
				if(uname.getText().length() <= 0) {
					wob_new.setW_uname(wob.getW_uname());
				}
				if(pwd.getText().length() <= 0) {	
					wob_new.setPwd(wob.getPwd());
				}
				
				controller.updateRecord(wob, wob_new);
				controller.updateScene();
			}
		});
		
		cancel2.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent e) {
				controller.updateScene();
			}
		});
		
		update.setStyle("-fx-background-color: #00A8F3;");
		update.setFont(Font.font("Verdana"));
		update.setTextFill(Color.WHITE);
		
		cancel.setStyle("-fx-background-color: #00A8F3;");
		cancel.setFont(Font.font("Verdana"));
		cancel.setTextFill(Color.WHITE);
		
		submit.setStyle("-fx-background-color: #00A8F3;");
		submit.setFont(Font.font("Verdana"));
		submit.setTextFill(Color.WHITE);
		
		cancel2.setStyle("-fx-background-color: #00A8F3;");
		cancel2.setFont(Font.font("Verdana"));
		cancel2.setTextFill(Color.WHITE);
		
		DropShadow borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.web("0xFF95FC"));
		borderGlow.setWidth(0);
		borderGlow.setHeight(0);
		submit.setEffect(borderGlow);
		update.setEffect(borderGlow);
		cancel.setEffect(borderGlow);
		cancel2.setEffect(borderGlow);
		
		HBox hb = new HBox();
		hb.setPadding(new Insets(10,10,10,10));
		hb.setSpacing(5);
		
		hb.getChildren().addAll(update, cancel);
		
		HBox hb2 = new HBox();
		hb.setPadding(new Insets(10,10,10,10));
		hb.setSpacing(5);
		
		hb.getChildren().addAll(submit, cancel2);
		
		
		gp.setPadding(new Insets(10,10,10,10));
		gp.setVgap(5);
		gp.setHgap(5);
		
		GridPane.setConstraints(table,0,0,2,1);
		GridPane.setConstraints(hb, 0, 1, 2, 1);
		GridPane.setConstraints(website, 0, 2);
		GridPane.setConstraints(web, 1, 2);
		GridPane.setConstraints(username, 0, 3);
		GridPane.setConstraints(uname, 1, 3);
		GridPane.setConstraints(password, 0, 4);
		GridPane.setConstraints(pwd, 1, 4);
		GridPane.setConstraints(hb2, 0, 5, 2, 1);	
		
		gp.getChildren().addAll(table, hb, website, web, username, uname, password, pwd, hb2);
	}
		
	//create table and fill with information
	private TableView getTable() {
		TableView table = new TableView();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		//Create table columns
		TableColumn<String, Web_Object> first_column = new TableColumn<>("Website");
		TableColumn<String, Web_Object> second_column = new TableColumn<>("User Name");
		TableColumn<String, Web_Object> third_column = new TableColumn<>("Password");
		
		//Setup column type constraints, specify which varialbe from Web_Object to insert into columns
		first_column.setCellValueFactory(new PropertyValueFactory<>("website"));
		second_column.setCellValueFactory(new PropertyValueFactory<>("w_uname"));
		third_column.setCellValueFactory(new PropertyValueFactory<>("pwd"));
		
		table.getColumns().addAll(first_column, second_column, third_column);
		
		//get list of user's websites, unames, and pwds
		ArrayList<Web_Object> wob = controller.getAllRecords();
		
		for(Web_Object obj : wob) {
			table.getItems().add(obj);
		}
		
		return table;
		
	}

}
