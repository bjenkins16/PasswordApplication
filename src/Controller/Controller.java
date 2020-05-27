package Controller;

import java.util.ArrayList;
import java.util.Observable;

import Model.Model;
import application.SceneEnum;
import application.User_Object;
import application.Web_Object;

public class Controller extends Observable {
	private Model model;
	private User_Object uob;
	
	public Controller(Model model) {
		this.model = model;
		this.uob = null;
	}
	
	/*
	 * 
	 * Scene selection operations
	 * 
	 * */
	
	public void loginScene() {
		setChanged();
		notifyObservers(new User_Object(SceneEnum.LOGIN));
	}
	
	public void signupScene() {
		setChanged();
		notifyObservers(new User_Object(SceneEnum.SIGNUP));
	}
	
	public void addScene() {
		setChanged();
		notifyObservers(new User_Object(SceneEnum.ADD));
	}
	
	public void mainScene() {
		setChanged();
		notifyObservers(new User_Object(SceneEnum.MAIN));
	}
	
	public void userScene() {
		setChanged();
		notifyObservers(new User_Object(SceneEnum.USER));
	}
	
	public void editScene() {
		setChanged();
		notifyObservers(new User_Object(SceneEnum.EDIT));
	}
	
	public void updateScene() {
		setChanged();
		notifyObservers(new User_Object(SceneEnum.UPDATE));
	}
	
	/*
	 * 
	 * Data base interaction operations with no returns
	 * 
	 * */
	
	public void login(User_Object user) {
		if(model.open_session(user)) {
			uob = user;
			mainScene();
		} else {
			loginScene();
		}
	}
	
	public void logout() {
		model.close_session(uob);
		uob = null;
		loginScene();
	}
	
	public void addUser(User_Object user) {
		model.create_user(user);
		loginScene();
	}
	
	public void deleteUser() {
		model.remove_user(uob);
		uob = null;
		loginScene();
	}
	
	public void updateUser(User_Object old_uob, User_Object new_uob) {
		model.update_user(old_uob, new_uob);
	}
	
	public void deleteRecord(Web_Object wob) {
		wob.setUname(uob.getUname());
		model.remove_record(wob);
		mainScene();
	}
	
	public void insertRecord(Web_Object wob) {
		wob.setUname(uob.getUname());
		model.insert_record(wob);
		mainScene();
	}
	
	public void updateRecord(Web_Object wob_old, Web_Object wob_new) {
		wob_new.setUname(uob.getUname());
		wob_old.setUname(wob_old.getUname());
		model.update_record(wob_new, wob_old);
	}
	
	/*
	 * 
	 * Data base interaction operations with returns
	 * 
	 * */
	
	public ArrayList<Web_Object> getAllRecords() {
		Web_Object wob = model.get_records(uob);
		return wob.getWebRecords();
		
	}
	
	public void getSingleRecord() {
		
		
	}
	
	public User_Object getUser() {
		return uob;
	}
	
	
}
