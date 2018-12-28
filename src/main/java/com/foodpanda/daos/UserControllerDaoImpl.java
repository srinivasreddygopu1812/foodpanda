package com.foodpanda.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodpanda.interfaces.Idaos;
import com.foodpanda.pojos.UserPojo;
@Repository
public class UserControllerDaoImpl implements Idaos  {
	public UserControllerDaoImpl() {
		
		System.out.println(" hibernate object created");
	}

	static SessionFactory sf;
	static {
		Configuration cfg = new Configuration().configure();
		sf = cfg.buildSessionFactory();
	}
	public void register(UserPojo up) {
		
		
		Session sn = sf.openSession();
		sn.save(up);
		sn.beginTransaction().commit();
		sn.close();
		System.out.println("Data inserted into db successfully");

	}
	
	public List<UserPojo> loginCheck(UserPojo up) {
		
		Session sn = sf.openSession();
		Query qr = sn.createQuery(" from UserPojo where emailId=? AND password=? ");
		//SQLQuery qr = sn.createSQLQuery("select * from foodpanda_user_details  where emailId=? and password=?");
		qr.setParameter(0, up.getEmailId());
		qr.setParameter(1, up.getPassword());
		List<UserPojo> list = qr.list();
		
		sn.close();
		return list;
		
		
	}
	
	public List<UserPojo> updateProfile(UserPojo up,String email,String mobile,String password )
	{
		
	 Session sn = sf.openSession();
	 UserPojo up1=(UserPojo) sn.get(UserPojo.class, email);
	  up1.setMobile(up.getMobile());
	  up1.setPassword(up.getPassword());
	 sn.update(up1);
	 sn.beginTransaction().commit();
		
	 Query qr = sn.createQuery("from UserPojo where emailId=:emailId ");
	 qr.setParameter("emailId", email);
	 List list = qr.list();
		
		return list;
		
	}
	
	public void deleteProfile(String email) {
		Session sn = sf.openSession();
		Query qr = sn.createQuery("delete from UserPojo  where emailId=?");
		qr.setParameter(0, email);
		qr.executeUpdate();
		sn.beginTransaction().commit();
		sn.close();
		
	}
	
	public  List<UserPojo> deleteUsers(String email) {
		Session sn = sf.openSession();
		Query qr = sn.createQuery("delete from UserPojo  where emailId=?");
		qr.setParameter(0, email);
		qr.executeUpdate();
		sn.beginTransaction().commit();
		

		Query qr1 = sn.createQuery("from UserPojo");
		
		List<UserPojo> list = qr1.list();
		sn.close();
		return list;
	}
				
		
	
	
	public  List updateView(UserPojo up) {
		Session sn = sf.openSession();
		
		sn.update(up);

		Transaction transaction = sn.beginTransaction();
		transaction.commit();
		
	
		Query qr = sn.createQuery("from UserPojo  ");
		List list = qr.list();

		sn.close();
		return list;
	}

	@Override
	public List editView(String email) {
		Session sn = sf.openSession();
		Query qr = sn.createQuery("from UserPojo where emailId=:email  ");
		qr.setString("email", email);
		List list = qr.list();
		return list;
		
	}

	public List<UserPojo> getDetailsByUserName(String emailId) {
		Session sn = sf.openSession();
		Query qr = sn.createQuery("from UserPojo where emailId=:email");
		qr.setParameter("email", emailId);
		List<UserPojo> list = qr.list();
		return list;
	}
	
	
}
