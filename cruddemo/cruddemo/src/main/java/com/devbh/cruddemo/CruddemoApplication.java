package com.devbh.cruddemo;

import com.devbh.cruddemo.dao.AppDao;
import com.devbh.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> {
			//createCourseAndStudents(appDao);

			//findCourseAndStudents(appDao);

			//findStudentAndCourses(appDao);

			//addMoreCoursesForStudent(appDao);

			//deleteCourse(appDao);

			deleteStudent(appDao);
		};
	}

	private void deleteStudent(AppDao appDao) {
		int theId = 3;
		System.out.println("Deleting student id: " + theId);
		appDao.deleteStudentById(theId);
		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDao appDao) {
		int theId = 3;
		Student tempStudent = appDao.findStudentAndCoursesByStudentId(theId);

		// create more courses
		Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
		Course tempCourse2 = new Course("Atari 2600 - Game Development");

		tempStudent.addCourses(tempCourse1);
		tempStudent.addCourses(tempCourse2);

		System.out.println("Updating student: " + tempStudent);
		System.out.println("associated courses: " + tempStudent.getCourses());

		appDao.update(tempStudent);

		System.out.println("Done!");
	}

	private void findStudentAndCourses(AppDao appDao) {
		int theId = 4;
		Student tempStudent = appDao.findStudentAndCoursesByStudentId(theId);

		System.out.println("Loaded student: " + tempStudent);
		System.out.println("Courses: " + tempStudent.getCourses());

		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDao appDao) {
		int theId = 11;
		Course tempCourse = appDao.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loaded course: " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());

		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDao appDao) {
		// create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");
		// create the students
		Student tempStudent1 = new Student("John", "Doe", "john@devbh.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@devbh.com");
		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);
		// save the course and associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("Associated students: " + tempCourse.getStudents());

		appDao.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDao appDao) {
		int theId = 10;
		System.out.println("Deleting course id: " + theId);
		appDao.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDao appDao) {
		// get the course and reviews
		int theId = 10;
		Course tempCourse = appDao.findCourseAndReviewsByCourseId(theId);
		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());
		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDao appDao) {
		// create a course
		Course tempCourse = new Course("Pacman - How to Score One Million Points");
		// add some reviews
		tempCourse.addReview(new Review("Great course loved it!"));
		tempCourse.addReview(new Review("Cool course."));
		tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

		// save the course leverage cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDao.save(tempCourse);
		System.out.println("Done!");


	}

	private void deleteCourse(AppDao appDao) {
		int theId = 11;

		System.out.println("Deleting course id: " + theId);

		appDao.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(AppDao appDao) {
		int theId = 10;

		// find the course
		System.out.println("Finding course id: " + theId);
		Course tempCourse = appDao.findCourseById(theId);

		// update the course
		System.out.println("Updating course id: " + theId);
		tempCourse.setTitle("Enjoy the Simple Things");

		appDao.update(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDao appDao) {
		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);

		// update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");

		appDao.update(tempInstructor);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDao.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDao appDao) {
		int theId = 1;
		// find instructor
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDao.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDao.findCourseByInstructorId(theId);

		// associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDao appDao) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDao.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDao appDao) {
		// create the instructor
		Instructor tempInstructor =
				new Instructor("Susan", "Public", "susan.public@bhdev.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.youtube.com", "Video Games");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		// add course to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		//NOTE: this will ALSO save the course because of CascadeType.PERSIST
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDao.save(tempInstructor);

		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDao appDao) {
		int theId = 3 ;
		System.out.println("Deleting instructor detail id: " + theId);
		appDao.deleteInstructorDetailById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDao appDao) {

		// get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDao.findInstructorDetailById(theId);
		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);
		// print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
		System.out.println("Done!");
	}

	private void deleteInstructor(AppDao appDao) {
		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);
		appDao.deleteInstructorById(theId);
		System.out.println("Done!");

	}

	private void findInstructor(AppDao appDao) {
		int theId = 2;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDao.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDao appDao) {
		/*// create the instructor
		Instructor tempInstructor =
				new Instructor("Burcu", "Haddad", "burcu@bhdev.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.burcudev.com/youtube", "Coding");*/

		Instructor tempInstructor =
				new Instructor("Madhu", "Patel", "madhu@bhdev.com");
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.burcudev.com/youtube", "Guitar");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		// NOTE: this will also save the details object
		// because of CascadeType.ALL

		System.out.println("Saving instructor: " + tempInstructor);
		appDao.save(tempInstructor);

		System.out.println("Done !");
	}

}

