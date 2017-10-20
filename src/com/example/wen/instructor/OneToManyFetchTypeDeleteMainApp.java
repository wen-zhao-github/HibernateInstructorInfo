package com.example.wen.instructor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyFetchTypeDeleteMainApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = factory.getCurrentSession();	
		
		
	    try{
	    	session.beginTransaction();
	    	int id = 7;
	    	Instructor instru = session.get(Instructor.class, id);
	    	System.out.println("main app >> "+instru);
	    	
	    	
	    	
	    	session.getTransaction().commit();
	    	session.close();
	    	//one to one, by default eager fetch type no error
	    	System.out.println("main app >> "+instru.getInstructorDetail());
	    	//one to many, lazy, session closed, error
	    	System.out.println("main app >> "+instru.getCourses());
	   }catch (Exception e){
		   e.printStackTrace();
	   }finally{
		   session.close();
		   factory.close();
	   }

	}

}
