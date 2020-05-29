package Model;

import java.sql.*;
import java.util.Observable;

import application.SceneEnum;
import application.User_Object;
import application.Web_Object;

public class Model {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/finance_db";
	private String uname = "root";
	private String upwd = "!Toblat11!";
	
	//Add user to user table if username not exists
	public void create_user(User_Object user) {
		Connection conn = connect();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO u_info (user, pwd, f_name, l_name) VALUES (?, ?, ?, ?)");
			
			ps.setString(1, user.getUname());
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getFname());
			ps.setString(4, user.getLname());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//update user record in table
	public void update_user(User_Object old_user, User_Object new_user) {
		Connection conn = connect();
		
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE u_info SET user=?, pwd=? WHERE user=?");
			
			ps.setString(1, new_user.getUname());
			ps.setString(2, new_user.getPwd());
			ps.setString(3, old_user.getUname());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//get user info from user table
	public User_Object get_user(User_Object user) {
		Connection conn = connect();
		
		User_Object user_info = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM u_info WHERE user = ?");
			
			ps.setString(1, user.getUname());
			
			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				user_info = new User_Object(res.getString("user"), res.getString("pwd"), res.getString("f_name"), res.getString("l_name"));
			}

			return user_info;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//delete user record
	public void remove_user(User_Object user) {
		Connection conn = connect();
		
		try {
			
			PreparedStatement ps;
			
			ps = conn.prepareStatement("DELETE FROM web_info WHERE user=?");
			ps.setString(1, user.getUname());
			ps.executeUpdate();
			
			ps = conn.prepareStatement("DELETE FROM u_info WHERE user=?");
			ps.setString(1, user.getUname());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//open a session for user
	/**
	 * @param user
	 * @return
	 */
	public boolean open_session(User_Object user) {
		Connection conn = connect();
		
		User_Object user_info = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM u_info WHERE user=? AND pwd=?");
			ps.setString(1, user.getUname());
			ps.setString(2, user.getPwd());
			
			ResultSet res = ps.executeQuery();
			
			if(res.next()) {
				user.setFname(res.getString("f_name"));
				user.setLname(res.getString("l_name"));
			} else {
				return false;
			}
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	//end session for user
	public void close_session(User_Object user) {
		user = null;
		return;
	}
	
	public boolean userExists(String uname) {
		Connection conn = connect();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM u_info WHERE user=?");
			ps.setString(1, uname);
			
			ResultSet res = ps.executeQuery();
			
			if(!res.next()) {
				return false;
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	//insert new record for website/password
	public void insert_record(Web_Object wob) {
		Connection conn = connect();
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO web_info (website, uname, u_pwd, user) VALUES (?,?,?,?)");
			ps.setString(1, wob.getWebsite());
			ps.setString(2, wob.getW_uname());
			ps.setString(3, wob.getPwd());
			ps.setString(4, wob.getUname());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//delete record for website/password
	public void remove_record(Web_Object wob) {
		Connection conn = connect();
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM web_info WHERE website=? AND uname=? AND user=?");
			ps.setString(1, wob.getWebsite());
			ps.setString(2, wob.getW_uname());
			ps.setString(3,  wob.getUname());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//update record for website/password
	public void update_record(Web_Object wob_new, Web_Object wob_old) {
		Connection conn = connect();
		
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE web_info SET website=? AND uname=? AND u_pwd=? AND user=? WHERE website=? AND uname=? AND user=?");
			ps.setString(1, wob_new.getWebsite());
			ps.setString(2, wob_new.getW_uname());
			ps.setString(3, wob_new.getPwd());
			ps.setString(4, wob_new.getUname());
			ps.setString(5, wob_old.getWebsite());
			ps.setString(6, wob_old.getW_uname());
			ps.setString(7, wob_old.getUname());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//get all records
	public Web_Object get_records(User_Object user) {
		Connection conn = connect();
			
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM web_info WHERE user=?");
			ps.setString(1, user.getUname());
				
			ResultSet res = ps.executeQuery();
			
			Web_Object web_records = new Web_Object(res);
				
			return web_records;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//get specific record
	public Web_Object get_record(Web_Object wob) {
		Connection conn = connect();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM web_info WHERE website=? AND uname=? AND user=?");
			ps.setString(1, wob.getWebsite());
			ps.setString(2, wob.getW_uname());
			ps.setString(3, wob.getUname());
			
			ResultSet res = ps.executeQuery();
			
			Web_Object found = null;
			
			while(res.next()) {
				found = new Web_Object(res.getString("website"), res.getString("uname"), res.getString("u_pwd"),res.getString("user"));
			}
			
			return found;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	//
	
	
	private Connection connect(){
		
		
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uname, upwd);
			
		} catch (Exception e) {
			e.printStackTrace();;
			
		}
		
		return conn;
	}
}
