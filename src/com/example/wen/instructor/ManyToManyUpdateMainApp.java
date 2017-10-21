package com.example.wen.instructor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManyToManyUpdateMainApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = factory.getCurrentSession();	
		
		
	    try{
	    	session.beginTransaction();
	    	System.out.println("main app >> ");	    	
	    	/*int id = 19;
	    	Student student = session.get(Student.class, id);
	    	session.delete(student);*/
	    	int id = 30;
	    	Instructor instru = session.get(Instructor.class, id);
	    	session.delete(instru);
	    	session.getTransaction().commit();
	    	
	    	
	   }catch (Exception e){
		   e.printStackTrace();
	   }finally{
		   session.close();
		   factory.close();
	   }

	}

}
