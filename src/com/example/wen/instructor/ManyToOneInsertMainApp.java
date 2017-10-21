package com.example.wen.instructor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManyToOneInsertMainApp {

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
	    	Review review1 = new Review("not bad!");
	    	System.out.println("main app >> "+review1);
	    	Review review2 = new Review("good!");
	    	System.out.println("main app >> "+review2);
	    	/*instru.addReview(review1);
	    	instru.addReview(review2);*/
	    	/*review1.setInstructor(instru);
	    	review2.setInstructor(instru);*/
	    	session.save(review1);
	    	session.save(review2);
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
