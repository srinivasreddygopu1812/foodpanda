package com.foodpanda.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.foodpanda.interfaces.Idaos;
import com.foodpanda.pojos.UserPojo;
/*@Repository*/
public class UserControllerJdbcDaoImpl implements Idaos {
	static Connection conn;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/versioning", "root", "root");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void register(UserPojo up) throws ClassNotFoundException, SQLException {
		System.out.println("entered into jdbc impl register");
		PreparedStatement ps = conn.prepareStatement("insert into foodpanda_user_details values(?,?,?,?,?)");
		up.setVersion(0);
		ps.setString(1, up.getEmailId());
		ps.setString(2, up.getMobile());
		ps.setString(3, up.getName());
		ps.setString(4, up.getPassword());
		ps.setLong(5, up.getVersion());

		ps.executeUpdate();
		ps.close();
		conn.close();
		System.out.println("exited from jdbc impl register");
	}

	public List<UserPojo> loginCheck(UserPojo up) throws SQLException {

		System.out.println("entered into jdbc impl login");
		System.out.println("---------jdbc code here-----");
		System.out.println("exited from jdbc impl login");
		
		
		
		PreparedStatement ps = conn
				.prepareStatement("select * from foodpanda_user_details  where emailId=? and password=?");
		ps.setString(1, up.getEmailId());
		ps.setString(2, up.getPassword());
		ResultSet rs = ps.executeQuery();

		List<UserPojo> list=new ArrayList();

		while(rs.next()) {
			UserPojo user = new UserPojo();      
		  user.setEmailId(rs.getString("emailId"));
		  user.setMobile(rs.getString("mobile"));
		  user.setName(rs.getString("name"));
		 user.setPassword(rs.getString("password"));
		 list.add(user);


		  
		} 
		return list;
			
			
			/*String emailId = rs.getString("emailId");
			String password = rs.getString("password");
			String mobile = rs.getString("mobile");
			String name = rs.getString("name");

			list.add(emailId);
			list.add(password);
			list.add(mobile);
			list.add(name);
*/
		}
		

		
		
	

	public List<UserPojo> updateProfile(UserPojo up ) {
		System.out.println("entered into updateProfile method !! UserControllerJdbcImpl");
		System.out.println("---------jdbc code here-----");
		System.out.println("exit from updateProfile method !! UserControllerJdbcImpl");
		List<UserPojo> list = null;
		return list;
	}

	public void deleteProfile(String emailId) {
		System.out.println("entered into deleteProfile method !! UserControllerJdbcImpl");
		System.out.println("---------jdbc code here-----");
		System.out.println("exit from deleteProfile method !! UserControllerJdbcImpl");
	
	}

	public List<UserPojo> deleteUsers(String emailId) {
		System.out.println("entered into deleteUsers method !! UserControllerJdbcImpl");
		System.out.println("---------jdbc code here-----");
		System.out.println("exit from deleteUsers method !! UserControllerJdbcImpl");
		return null;
	}

	public  List editView(String email) {
		System.out.println("entered into editView method !! UserControllerJdbcImpl");
		System.out.println("---------jdbc code here-----");
		System.out.println("exit from editView method !! UserControllerJdbcImpl");
		List list = null;
		
		return list;	
}

	public List updateView(UserPojo up) {
		System.out.println("entered into updateView method !! UserControllerJdbcImpl");
		System.out.println("---------jdbc code here-----");
		System.out.println("exit from updateView method !! UserControllerJdbcImpl");
		return null;
	}

	public List getDetailsByUserName(String emailId) {
		
		return null;

}
}
