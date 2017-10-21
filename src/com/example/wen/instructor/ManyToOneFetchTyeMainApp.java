package com.example.wen.instructor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManyToOneFetchTyeMainApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = factory.getCurrentSession();	
		
		
	    try{
	    	session.beginTransaction();
	    	int id = 8;
	    	Instructor instru = session.get(Instructor.class, id);
	    	System.out.println("main app >> "+instru);	    
	    	System.out.println("main app >> "+instru.getReviews());	    	
	    	//System.out.println("main app >> "+instru.getCourses());
	    		    	
	    	session.getTransaction().commit();
	    	session.close();
	    	
	   }catch (Exception e){
		   e.printStackTrace();
	   }finally{
		   session.close();
		   factory.close();
	   }

	}

}
