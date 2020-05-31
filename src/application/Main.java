package application;
	
import java.io.FileInputStream;
import java.util.Observable;
import java.util.Observer;

import Controller.Controller;
import Model.Model;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application implements Observer{
	private Stage primaryStage;
	private Controller controller;
	private Model model;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//initialize model
			this.model = new Model();
			//initialize controller with model
			this.controller = new Controller(model);
			//set private stage variable to primaryStage parameter passed through parameters
			this.primaryStage = primaryStage;
			
			//add this class as an observer of controller object.
			controller.addObserver(this);
			
			//start application with the the login scene
			Scene scene = SceneFactory.getScene(SceneEnum.LOGIN, controller);
			
			primaryStage.setScene(scene);
			//primaryStage.setMinWidth(600);
			//primaryStage.setMinHeight(800);
			FileInputStream fis = new FileInputStream("../PassApp/icon.jpg");
			primaryStage.getIcons().add(new Image(fis));
			primaryStage.setTitle("Password Storehouse");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeScene(Scene scene) {
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public void update(Observable obs, Object o) {	
		Data_Object dob = (Data_Object)o;
		Scene scene = SceneFactory.getScene(dob.getScene(), controller);
		
		Thread sceneChange = new Thread() {
			@Override
			public void run() {
				Platform.runLater(() -> changeScene(scene));
			}
		};
		sceneChange.run();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
