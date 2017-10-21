package com.example.wen.instructor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManyToOneDeleteMainApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		Session session = factory.getCurrentSession();	
		
		
	    try{
	    	session.beginTransaction();
	    	/*delete review, does not delete instructor
	    	 * int id = 1;
	    	Review review = session.get(Review.class, id);
	    	review.getInstructor().deleteReview(review);
	    	session.delete(review);*/
	    	//delete instructor, delete review, delete instructor detail and set related courses' instructor to null
	    	int id = 8;
	    	Instructor instru = session.get(Instructor.class, id);
	    	List<Course> courses = instru.getCourses();
	    	for(Course temp:courses){
	    		temp.setInstructor(null);
	    	}
	    	session.delete(instru);
	    	
	    	
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
