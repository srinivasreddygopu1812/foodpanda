package com.foodpanda.daos;

import java.util.List;

import org.apache.log4j.Logger;
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

	final static Logger logger=Logger.getLogger(UserControllerDaoImpl.class);
	public UserControllerDaoImpl() {
		
	}

	static SessionFactory sf;
	static {
		Configuration cfg = new Configuration().configure();
		sf = cfg.buildSessionFactory();
		logger.warn("session factory builded successfully");
	}
	public void register(UserPojo up) {
		
		logger.info("entered into register()::UserControllerDaoImpl");
		Session sn = sf.openSession();
		logger.warn("session opened successfully");
		sn.save(up);
		logger.warn("data inserted in db successfully");
		sn.beginTransaction().commit();
		logger.warn("transaction committed successfully");
		sn.close();
		logger.warn("session closed successfully");
		logger.info("exited from register()::UserControllerDaoImpl");

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
	
	
	public  List<UserPojo> deleteUsers(String email) {
		logger.info("entered into deleteUsers()::UserControllerDaoImpl ");
		Session sn = sf.openSession();
		logger.warn("session opened");
		Query qr = sn.createQuery("delete from UserPojo  where emailId=?");
		qr.setParameter(0, email);
		qr.executeUpdate();
		logger.warn("data deleted from db");
		sn.beginTransaction().commit();
		logger.warn("transaction committed");

		Query qr1 = sn.createQuery("from UserPojo");
		List<UserPojo> list = qr1.list();
		logger.warn("data retrieved from db");
		sn.close();
		logger.warn("session closed");
		logger.info("exited from deleteUsers()::UserControllerDaoImpl ");
		return list;
	}
				
		
	
	
	public  List updateView(UserPojo up) {
		logger.info("entered into updateView()::UserControllerDaoImpl ");
		Session sn = sf.openSession();
		logger.warn("session opened");
		sn.update(up);
		logger.warn("data updated");
		Transaction transaction = sn.beginTransaction();
		transaction.commit();
		logger.warn("transaction committed successfully");
			Query qr = sn.createQuery("from UserPojo  ");
		List list = qr.list();
		logger.warn("data retrieved from db");
		sn.close();
		logger.warn("session was closed");
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
		logger.info("entered into getDetailsByUserName()::UserControllerDaoImpl ");
		Session sn = sf.openSession();
		logger.warn("session opened");
		Query qr = sn.createQuery("from UserPojo where emailId=:email");
		qr.setParameter("email", emailId);
		List<UserPojo> list = qr.list();
		logger.warn("data retrieved from db");
		logger.info("exited from getDetailsByUserName()::UserControllerDaoImpl ");
		return list;
	}
	
	
}

/*public List<UserPojo> updateProfile(UserPojo up )
	{
		logger.info("entered into  updateProfile()::UserControllerDaoImpl");
	 Session sn = sf.openSession();
	 logger.warn("session opened successfully");
	 UserPojo up1=(UserPojo) sn.get(UserPojo.class, up.getEmailId());
	  up1.setMobile(up.getMobile());
	  up1.setPassword(up.getPassword());
	 sn.update(up1);
	 logger.warn("data updated in db successfully");
	 sn.beginTransaction().commit();
	 logger.warn("transaction committed successfully");
	 Query qr = sn.createQuery("from UserPojo where emailId=:emailId ");
	 qr.setParameter("emailId",up.getEmailId() );
	 List list = qr.list();
		sn.close();
		logger.warn("session closed successfully");
		logger.info("exited from updateProfile()::UserControllerDaoImpl");
		return list;
		
	}*/

/*public void deleteProfile(String email) {
		Session sn = sf.openSession();
		Query qr = sn.createQuery("delete from UserPojo  where emailId=?");
		qr.setParameter(0, email);
		qr.executeUpdate();
		sn.beginTransaction().commit();
		sn.close();
		
	}*/