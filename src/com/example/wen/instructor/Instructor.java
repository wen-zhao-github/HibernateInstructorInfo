package com.example.wen.instructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "instructor")
public class Instructor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column (name = "email")
	private String email;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "instructor_detail_id")
	private InstructorDetail instructorDetail;
	
	//mappedby refer to instructor property in the course class
	//Duplicate entry '12-7' for key 'PRIMARY'
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "instructor",cascade = CascadeType.ALL)
	private List<Course> courses;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="instructor",cascade=CascadeType.ALL)
	private List<Review> reviews;
	
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable( name = "instructor_student",
			joinColumns = @JoinColumn(name = "instructor_id"),
	        inverseJoinColumns = @JoinColumn(name = "student_id")
			)
	private List<Student> students;
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public Instructor(){}

	public Instructor(int id, String firstName, String lastName, String email, InstructorDetail instructorDetail) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.instructorDetail = instructorDetail;
	}

	public Instructor(String firstName, String lastName, String email, InstructorDetail instructorDetail) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.instructorDetail = instructorDetail;
	}



	public Instructor(String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + "]";
	}
	public void addCourse(Course cous){
		if (courses == null){
			courses = new ArrayList<>();
		}
		courses.add(cous);
	}
	public void addReview(Review review){
		if (reviews == null){
			reviews = new ArrayList<>();
		}
		reviews.add(review);
	}
	public void deleteReview(Review review){
		reviews.remove(review);
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public void addStudent(Student s){
		if (students == null){
			students = new ArrayList<>();
		}
		students.add(s);
	}
	public void deleteStudent(Student s){
		try{
			students.remove(s);
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("this is no such student in db!");
		}
	}

}
