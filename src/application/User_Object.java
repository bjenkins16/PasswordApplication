package application;

public class User_Object implements Data_Object {
	
	private String       uname;
	private String       pwd;
	private String       fname;
	private String       lname;
	private SceneEnum    scene;
	
	public User_Object(String uname, String pwd, String fname, String lname) {
		this.uname = uname;
		this.pwd    = pwd;
		this.fname  = fname;
		this.lname  = lname;
	}
	
	public User_Object(SceneEnum scene) {
		this.scene = scene;
		this.uname = null;
		this.pwd    = null;
		this.fname  = null;
		this.lname  = null;
		
	}
	
	public String getUname() {
		return this.uname;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public String getFname() {
		return this.fname;
	}
	
	public String getLname() {
		return this.lname;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public SceneEnum getScene() {
		return this.scene;
	}
	
	public void setScene(SceneEnum scene) {
		this.scene = scene;
	}
}
