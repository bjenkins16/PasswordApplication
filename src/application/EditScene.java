package application;

import java.util.ArrayList;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EditScene extends Scene {
	private GridPane gp;
	private Controller controller;
	
	public EditScene(Controller controller, GridPane gp) {
		super(gp);
		
		this.gp = gp;
		this.controller = controller;
		
		setup();
	}
	
	//setup main scene
	private void setup() {
		
		gp.setPadding(new Insets(10,10,10,10));
		gp.setVgap(5);
		gp.setHgap(5);
		
		gp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		TableView table = getTable();
		
		Button delete = new Button("Delete");
		Button done = new Button("Done");
		
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Web_Object wob = (Web_Object)table.getSelectionModel().getSelectedItem();
				if(wob != null) {
					controller.deleteRecord(wob);
					controller.editScene();
				}
			}
		});
		
		done.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.mainScene();
			}
		});
		
		delete.setStyle("-fx-background-color: #00A8F3;");
		delete.setFont(Font.font("Verdana"));
		delete.setTextFill(Color.WHITE);
		
		done.setStyle("-fx-background-color: #00A8F3;");
		done.setFont(Font.font("Verdana"));
		done.setTextFill(Color.WHITE);
		
		DropShadow borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.web("0xFF95FC"));
		borderGlow.setWidth(0);
		borderGlow.setHeight(0);
		done.setEffect(borderGlow);
		delete.setEffect(borderGlow);
		
		HBox hb = new HBox();
		hb.setPadding(new Insets(10,10,10,10));
		hb.setSpacing(5);
		
		hb.getChildren().addAll(delete, done);
		
		gp.setPadding(new Insets(10,10,10,10));
		gp.setVgap(5);
		gp.setHgap(5);
		
		GridPane.setConstraints(table,0,0,2,1);
		GridPane.setConstraints(hb, 0, 1);
		
		gp.getChildren().addAll(hb, table);
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
