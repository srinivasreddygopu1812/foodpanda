package com.foodpanda.interfaces;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.foodpanda.pojos.UserPojo;
public interface Idaos {
	public void register(UserPojo up) throws ClassNotFoundException, SQLException;
	public List<UserPojo> loginCheck(UserPojo up) throws SQLException;
	public List<UserPojo> updateProfile(UserPojo up,String email,String mobile,String password );
	public void deleteProfile(String email);
	public  List<UserPojo> deleteUsers(String email);
	public List  editView(String email);
	public List updateView(UserPojo up);
	public List<UserPojo> getDetailsByUserName(String emailId);
	
	
	
}
