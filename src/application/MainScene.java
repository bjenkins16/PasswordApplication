package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MainScene extends Scene{
	private GridPane gp;
	private Controller controller;
	
	public MainScene(Controller controller, GridPane gp) {
		super(gp);
		
		this.gp = gp;
		this.controller = controller;
		
		setup();
	}
	
	//setup main scene
	private void setup() {
		
		MenuBar mb = getMenuBar();
		
		TableView table = getTable();
		
		gp.setPadding(new Insets(10,10,10,10));
		gp.setVgap(5);
		gp.setHgap(5);
		
		gp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		GridPane.setConstraints(mb,0,0);
		GridPane.setConstraints(table, 0, 1);
		
		gp.getChildren().addAll(mb, table);
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
	
	//create menu bar
	private MenuBar getMenuBar() {
		MenuBar mb = new MenuBar();
		
		Menu menu1 = menu1();
		Menu menu2 = menu2();
		Menu menu3 = menu3();
		Menu menu4 = menu4();
		
		mb.getMenus().addAll(menu1, menu2, menu3);
		
		return mb;
	}
	
	//create menu1 
	private Menu menu1() {
		Menu menu1 = new Menu("File");
		
		gp.setPadding(new Insets(10,10,10,10));
		gp.setVgap(5);
		gp.setHgap(5);
		
		//create menu1 items
		MenuItem log_out = new MenuItem("Logout");
		log_out.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.logout();
			}
		});
		
		menu1.getItems().add(log_out);
		
		return menu1;
		
	}	
	
	//create menu2
	private Menu menu2() {
		Menu menu2 = new Menu("Edit");
		
		//create menu2 items
		MenuItem add = new MenuItem("Add New");
		add.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent e) {
				controller.addScene();
			}
			
		});
		
		MenuItem remove = new MenuItem("Remove");
		remove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.editScene();
			}
		});
		
		MenuItem update = new MenuItem("Update");
		update.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent e) {
				controller.updateScene();
			}
		});
		
		menu2.getItems().addAll(add, remove, update);
		
		return menu2;
	}
	
	//create menu3
	private Menu menu3() {
		Menu menu3 = new Menu("User");
		
		//create menu3 items
		MenuItem account = new MenuItem("Account");
		account.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.userScene();
			}
		});
				
		menu3.getItems().add(account);
		
		return menu3;
	}
	
	//create menu 4
	private Menu menu4() {
		//create menu4 items
		// TODODODODODODODODODODODOODODODODODO
		
		return null;
	}
}
