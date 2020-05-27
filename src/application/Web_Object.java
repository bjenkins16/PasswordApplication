package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Web_Object implements Data_Object {
	private String                website;
	private String                w_uname;
	private String                pwd;
	private String                uname;
	private SceneEnum             scene;
	private ArrayList<Web_Object> web_records;
	
	public Web_Object (String website, String w_uname, String pwd, String uname) {
		this.website = website;
		this.w_uname = w_uname;
		this.pwd = pwd;
		this.uname = uname;
	}
	
	public Web_Object (SceneEnum scene) {
		this.scene = scene;
		this.website = null;
		this.w_uname = null;
		this.pwd = null;
		this.uname = null;
	}
	
	public Web_Object(ResultSet res) {
		web_records = new ArrayList<>();
		try {
			while(res.next()) {
				web_records.add(new Web_Object(res.getString("website"), res.getString("uname"), res.getString("u_pwd"), res.getString("user")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getWebsite() {
		return this.website;
	}
	
	public String getW_uname() {
		return this.w_uname;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public String getUname() {
		return this.uname;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public void setW_uname(String uname) {
		this.w_uname = uname;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public SceneEnum getScene() {
		return this.scene;
	}
	
	public void setScene(SceneEnum scene) {
		this.scene = scene;
	}
	
	public ArrayList<Web_Object> getWebRecords() {
		return this.web_records;
	}
}
